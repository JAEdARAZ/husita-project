package com.springdemo.service;

import java.util.ArrayList;
import java.util.Map;

import com.springdemo.entity.Translation;

public interface TranslationService {

	public Map<String, ArrayList<Translation>> getTranslations();
	
	public Translation getTranslation(int theId);

	public void updateTranslation(Translation theTranslation);

	public void deleteTranslation(int theId);

	public void insertTranslations(String areaTranslations);
	
}
