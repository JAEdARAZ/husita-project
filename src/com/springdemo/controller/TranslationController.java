package com.springdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Translation;
import com.springdemo.entity.WordRank;
import com.springdemo.service.TranslationService;
import com.springdemo.service.WordRankService;


@Controller
@RequestMapping("/translation")
public class TranslationController {
	
	@Autowired
	private TranslationService translationService;
	
	@Autowired
	private WordRankService wordRankService;
	
	@GetMapping("/list")
	public String listTranslations(Model theModel) {
		Map<String, ArrayList<Translation>> translations = translationService.getTranslations();
		theModel.addAttribute("mapTranslations", translations);

		return "list-translations";
	}
	
	@GetMapping("/showFormUpdate")
	public String showFormUpdate(@RequestParam("translationId") int theId, Model theModel) {
		Translation translation = translationService.getTranslation(theId);
		theModel.addAttribute("translation", translation);
		
		return "translation-form";
	}
	
	@PostMapping("/updateTranslation")
	public String updateTranslation(@ModelAttribute("translation") Translation theTranslation) {
		//process changed words in ranking
		Translation oldTranslation = translationService.getTranslation(theTranslation.getId());
		wordRankService.processUpdatedSentence(oldTranslation.getSentSpanish(), theTranslation.getSentSpanish());
		
		translationService.updateTranslation(theTranslation);
		
		return "redirect:/translation/list";
	}
	
	@GetMapping("/showInsertForm")
	public String showInsertForm(Model theModel) {
		return "insertion-table-form";
	}
	
	@PostMapping("/insertTranslations")
	public String insertTranslations(@RequestParam("areaTranslations") String areaTranslations, Model theModel) {
		translationService.insertTranslations(areaTranslations);
		wordRankService.processAddedWords(areaTranslations);
		
		return "redirect:/translation/list";
	}
	
	@GetMapping("/deleteTranslation")
	public String deleteTranslation(@RequestParam("translationId") int theId, Model theModel) {
		Translation deletedTranslation = translationService.getTranslation(theId);
		wordRankService.countersDeletedSentence(deletedTranslation.getSentSpanish());
		
		translationService.deleteTranslation(theId);
		
		return "redirect:/translation/list";
	}
	
	@GetMapping("/searchTranslations")
	public String searchTranslations(@RequestParam("word") String word, 
										@RequestParam("langButtonSelected") String language, Model theModel) {
		
		List<Translation> searchTranslations = translationService.getSearchTranslations(word, language);
		String titleSearch = "Results search: '" + word + "'";
		if(language.equals("esp")) {
			titleSearch = "Resultados búsqueda: '" + word + "'";
		}
		
		theModel.addAttribute("titleSearch", titleSearch);
		theModel.addAttribute("searchTranslations", searchTranslations);
		
		return "search-list";
	}
	
	@GetMapping("/rankingWords")
	public String rankingWords(Model theModel) {
		List<WordRank> wordsRanked = wordRankService.getRanking();
		theModel.addAttribute("listWords", wordsRanked);
		
		return "ranking-words";
	}
	
}
