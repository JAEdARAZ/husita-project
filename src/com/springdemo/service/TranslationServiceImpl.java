package com.springdemo.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.TranslationDAO;
import com.springdemo.entity.Translation;

@Service
public class TranslationServiceImpl implements TranslationService {

	@Autowired
	private TranslationDAO translationDAO;
	
	@Override
	@Transactional
	public Map<String, ArrayList<Translation>> getTranslations() {
		List<Translation> translations = translationDAO.getTranslations();
		Map<String, ArrayList<Translation>> mapDateTranslation = new LinkedHashMap<String, ArrayList<Translation>>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		//key = [date of translation], value = [list of translations from that date] 
		for(Translation t : translations){
			String dateTranslation = df.format(t.getDate());
			ArrayList<Translation> transListAux = mapDateTranslation.get(dateTranslation);
			
			if (transListAux == null) {
				transListAux = new ArrayList<Translation>();
				transListAux.add(t);
				mapDateTranslation.put(dateTranslation, transListAux);
			}
			else {
				mapDateTranslation.get(dateTranslation).add(t);
			}
		}
		
		return mapDateTranslation;
	}

	@Override
	@Transactional
	public Translation getTranslation(int theId) {
		return translationDAO.getTranslation(theId);
	}

	@Override
	@Transactional
	public void updateTranslation(Translation theTranslation) {
		translationDAO.updateTranslation(theTranslation);
	}

	@Override
	@Transactional
	public void deleteTranslation(int theId) {
		translationDAO.deleteTranslation(theId);
		
	}

	@Override
	@Transactional
	public void insertTranslations(String areaTranslations) {
		//split by new line
		String[] translations = areaTranslations.split("\\r?\\n");
		
		List<Translation> translationsToInsert = new ArrayList<>();
		long now = System.currentTimeMillis();
		
		for(String t : translations) {
			t = t.replace("#", "");
			t = t.trim();
			String[] sentenceEngEsp = t.split("-");
			
			Translation auxTrans = new Translation();
			auxTrans.setSentEnglish(sentenceEngEsp[0]);
			auxTrans.setSentSpanish(sentenceEngEsp[1]);
			auxTrans.setDate(new Timestamp(now));
			
			translationsToInsert.add(auxTrans);
		}
		
		for(Translation t : translationsToInsert) {
			translationDAO.insertTranslations(t);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Translation> getSearchTranslations(String word, String language) {
		List<Translation> searchTranslations;
		if(language.equals("eng")) {
			searchTranslations = translationDAO.getEngSearch(word);
		}
		else {
			searchTranslations = translationDAO.getEspSearch(word);
		}
		
		boldSearchedWord(searchTranslations, word, language);
		return searchTranslations;
	}
	
	private void boldSearchedWord (List<Translation> translations, String word, String language){
		if(language.equals("eng")) {
			for(Translation t : translations) {
				String sentenceEng = t.getSentEnglish();
				sentenceEng = sentenceEng.replace(word, "<b>" + word + "</b>");
				t.setSentEnglish(sentenceEng);
			}
		}
		else {
			for(Translation t : translations) {
				String sentenceEsp = t.getSentSpanish();
				sentenceEsp = sentenceEsp.replace(word, "<b>" + word + "</b>");
				t.setSentSpanish(sentenceEsp);
			}
		}
	}

}
