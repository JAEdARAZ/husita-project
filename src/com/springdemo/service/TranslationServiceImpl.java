package com.springdemo.service;

import java.util.List;

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
	public List<Translation> getTranslations() {
		return translationDAO.getTranslations();
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
