package com.springdemo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springdemo.dao.WordRankDAO;
import com.springdemo.entity.WordRank;

@Service
public class WordRankServiceImpl implements WordRankService {

	@Autowired
	private WordRankDAO wordRankDAO;
	
	@Value("${words.example}")
	private String[] wordsExamplePrint;

	
	@Override
	@Transactional
	public List<WordRank> getRanking() {
		return wordRankDAO.getRanking();
	}
	
	@Override
	@Transactional
	public List<WordRank> getAllWords () {
		return wordRankDAO.getAllWords();
	}

	@Override
	@Transactional
	public void processAddedWords(String areaTranslations) {
		//clean areaTranslations so I have a Map with <word, count>
		Map<String, Integer> wordsAdded = processAreaTranslations(areaTranslations);
		
		//recover all words from the table and set them in a Map
		List<WordRank> allWords = getAllWords();
		
		Map<String, WordRank> wordsCountDB = new HashMap<String, WordRank>();
		for(WordRank w : allWords) {
			wordsCountDB.put(w.getWordEsp(), w);
		}
		
		//loop over words added in new sentences. update/insert in word_rank depending if the word is in the DB already
		if(wordsAdded!=null) {
			for(Map.Entry<String, Integer> entry : wordsAdded.entrySet()) {
				String wAdded = entry.getKey();
				WordRank w = wordsCountDB.get(wAdded); //search in the words from DB Map
				
				if (w == null) { //the word is not in the DB
					wordRankDAO.saveUpdateWord( new WordRank(wAdded, entry.getValue()) );
				}
				else { //update the counter (counter from DB + counter from wordsAdded) 
					w.setCounter(w.getCounter() + entry.getValue());
					wordRankDAO.saveUpdateWord(w);
				}
			}
		}
	}

	@Override
	@Transactional
	public void processUpdatedSentence(String oldSentSpanish, String newSentSpanish) {
		//OLD SENTENCE: decrease the counter for the words
		oldSentSpanish = oldSentSpanish.trim();
		String[] ArrOldSent = oldSentSpanish.split(" ");
		Map<String, Integer> wordsOldSent = countWordsSentence(ArrOldSent);
		
		for(Map.Entry<String, Integer> entry : wordsOldSent.entrySet()) {
			wordRankDAO.decreaseCounter(entry.getKey(), entry.getValue());
		}
		
		//NEW SENTENCE: add count for every word (there may be new ones to word_rank)
		newSentSpanish = newSentSpanish.trim();
		String[] ArrNewSent = newSentSpanish.split(" ");
		Map<String, Integer> wordsNewSent = countWordsSentence(ArrNewSent);
		
		if(wordsNewSent!=null) {
			updateAddedWord(wordsNewSent);
		}
	}
	
	
	private Map<String, Integer> processAreaTranslations(String areaTranslations) {
		//remove # and split to have translations in an array
		areaTranslations = areaTranslations.replace("#", "");
		String[] translations = areaTranslations.split("\\r?\\n");
		
		Map<String, Integer> wordsCount = new HashMap<String, Integer>();
		
		for(String t : translations) {
			//get the spanish sentence and remove double or more spaces together
			String sentenceEsp = t.substring(t.indexOf("-") + 1);
			sentenceEsp = sentenceEsp.replaceAll("\\s{2,}", " ").trim();
			
			//loop over spanish words to insert in a Map the words and number of appearences in the sentences inserted  
			String[] words = sentenceEsp.split(" ");
			for(String w : words) {
				Integer count = wordsCount.get(w);
				if(count == null) {
					wordsCount.put(w, 1);
				}
				else {
					wordsCount.put(w, count+1);
				}
			}
		}
		
		return wordsCount;
	}
	
	
	private void updateAddedWord(Map<String, Integer> wordsNewSent) {
		//get all words from DB
		List<WordRank> allWords = getAllWords();
		Map<String, WordRank> wordsCountDB = new HashMap<String, WordRank>();
		for(WordRank w : allWords) {
			wordsCountDB.put(w.getWordEsp(), w);
		}
		
		//loop over added words, check if they are in the DB
		for(Map.Entry<String, Integer> entry : wordsNewSent.entrySet()) {
			//search in the words from DB Map
			String wAdded = entry.getKey();
			WordRank w = wordsCountDB.get(wAdded);
			
			if (w == null) { //the word is not in the DB
				wordRankDAO.saveUpdateWord( new WordRank(wAdded, entry.getValue()) );
			}
			else { //counter in DB + counter new insertions 
				w.setCounter(w.getCounter() + entry.getValue());
				wordRankDAO.saveUpdateWord(w);
			}
		}
	}

	
	private Map<String, Integer> countWordsSentence(String[] words){
		Map<String, Integer> wordsCounted = new HashMap<String, Integer>();
		
		for(String w : words) {
			Integer count = wordsCounted.get(w);
			if(count == null) {
				wordsCounted.put(w, 1);
			}
			else {
				wordsCounted.put(w, count+1);
			}
		}
		
		return wordsCounted;
	}
}
