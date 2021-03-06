package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.WordRank;

public interface WordRankService {

	public List<WordRank> getRanking();
	
	public List<WordRank> getAllWords ();

	public void processAddedWords(String areaTranslations);

	public void processUpdatedSentence(String oldSentSpanish, String newSentSpanish);

	public void countersDeletedSentence(String sentSpanish);

}
