package com.springdemo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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

}
