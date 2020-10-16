package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springdemo.dao.TranslationDAOImpl;
import com.springdemo.entity.Translation;


@Controller
@RequestMapping("/translation")
public class TranslationController {
	
	@Autowired
	private TranslationDAOImpl translationDAO;
	
	@GetMapping("/list")
	public String listTranslations(Model theModel) {
		List<Translation> translations = translationDAO.getTranslations();
		theModel.addAttribute("translations", translations);
		
		return "list-translations";
	}
	
}
