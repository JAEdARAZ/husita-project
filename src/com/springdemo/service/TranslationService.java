package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.Translation;

public interface TranslationService {

	public List<Translation> getTranslations();
	
	public Translation getTranslation(int theId);

	public void updateTranslation(Translation theTranslation);

	public void deleteTranslation(int theId);
	
}
