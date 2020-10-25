package com.springdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly=true)
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
		for(Map.Entry<String, Integer> entry : wordsAdded.entrySet()) {
			String wAdded = entry.getKey();
			WordRank w = wordsCountDB.get(wAdded); //search in the words from DB Map
			
			if (w == null) { //the word is not in the DB
				saveUpdateWord( new WordRank(wAdded, entry.getValue()) );
			}
			else { //update the counter (counter from DB + counter from wordsAdded) 
				w.setCounter(w.getCounter() + entry.getValue());
				saveUpdateWord(w);
			}
		}
		
	}

	@Transactional
	private void saveUpdateWord(WordRank w) {
		wordRankDAO.saveUpdateWord(w);
	}

	private Map<String, Integer> processAreaTranslations(String areaTranslations) {
		return null;
	}

}
