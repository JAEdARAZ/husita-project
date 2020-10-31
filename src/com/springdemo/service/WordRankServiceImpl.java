package com.springdemo.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	
	@Value("#{'${avoid.words}'.split(',')}")
	private List<String> wordsToAvoid;

	
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
		
		//clean words that are found in the avoidable list (properties file)
		wordsAdded.keySet().removeAll(wordsToAvoid);
		
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
		Map<String, Integer> diffCounter = new HashMap<String, Integer>(); 
		
		//OLD SENTENCE: decrease the counter for the words present
		oldSentSpanish = oldSentSpanish.trim();
		String[] ArrOldSent = oldSentSpanish.split(" ");
		countWordsSentence(diffCounter, ArrOldSent, -1);
		
		//NEW SENTENCE: increase the counters for the words present
		newSentSpanish = newSentSpanish.trim();
		String[] ArrNewSent = newSentSpanish.split(" ");
		countWordsSentence(diffCounter, ArrNewSent, 1);
		
		//clean words that are found in the avoidable list (properties file)
		diffCounter.keySet().removeAll(wordsToAvoid);
		
		//get all words from DB
		List<WordRank> allWords = getAllWords();
		Map<String, WordRank> wordsCountDB = new HashMap<String, WordRank>();
		for(WordRank w : allWords) {
			wordsCountDB.put(w.getWordEsp(), w);
		}
		
		//change counters: <0 just decrease, >0 increase (the word could be a new one), 0 no action
		for(Map.Entry<String, Integer> entry : diffCounter.entrySet()) {
			int wordDiffCounter = entry.getValue();
			
			if(wordDiffCounter<0) {
				wordRankDAO.decreaseCounter(entry.getKey(), wordDiffCounter);
			}
			
			if(wordDiffCounter>0) {
				updateAddedWord(entry, wordsCountDB);
			}
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
	
	
	private void updateAddedWord(Map.Entry<String, Integer> wordCount, Map<String, WordRank> wordsCountDB) {
		String wAdded = wordCount.getKey();
		WordRank w = wordsCountDB.get(wAdded);
		
		if (w == null) { //the word is not in the DB
			wordRankDAO.saveUpdateWord( new WordRank(wAdded, wordCount.getValue()) );
		}
		else { //counter in DB + counter new insertions 
			w.setCounter(w.getCounter() + wordCount.getValue());
			wordRankDAO.saveUpdateWord(w);
		}
	}

	
	//symbol: -1 for old sentence, +1 for new sentence
	private void countWordsSentence(Map<String,Integer> diffCounter, String[] words, int symbol){
		for(String w : words) {
			Integer count = diffCounter.get(w);
			if(count == null) {
				diffCounter.put(w, symbol);
			}
			else {
				diffCounter.put(w, count + symbol);
			}
		}
	}
	
}
