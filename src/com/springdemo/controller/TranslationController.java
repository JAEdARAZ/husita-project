package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Translation;
import com.springdemo.service.TranslationService;


@Controller
@RequestMapping("/translation")
public class TranslationController {
	
	@Autowired
	private TranslationService translationService;
	
	@GetMapping("/list")
	public String listTranslations(Model theModel) {
		List<Translation> translations = translationService.getTranslations();
		theModel.addAttribute("translations", translations);
		
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
		translationService.updateTranslation(theTranslation);
		
		return "redirect:/translation/list";
	}
	
	@GetMapping("/deleteTranslation")
	public String deleteTranslation(@RequestParam("translationId") int theId) {
		translationService.deleteTranslation(theId);
		
		return "redirect:/translation/list";
	}
	
}
