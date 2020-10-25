package com.springdemo.service;

import java.util.List;

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
	public List<WordRank> getRanking() {
		return wordRankDAO.getRanking();
	}
	
}
