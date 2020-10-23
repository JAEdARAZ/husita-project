package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Translation;

public interface TranslationDAO {

	public List<Translation> getTranslations();
	
	public Translation getTranslation(int theId);

	public void updateTranslation(Translation theTranslation);

	public void deleteTranslation(int theId);

	public void insertTranslations(Translation translations);
	
}
