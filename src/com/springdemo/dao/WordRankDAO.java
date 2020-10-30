package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.WordRank;

public interface WordRankDAO {

	public List<WordRank> getRanking();

	public List<WordRank> getAllWords();

	public void saveUpdateWord(WordRank w);

	public void decreaseCounter(String removedWord, int diffCounter);

}
