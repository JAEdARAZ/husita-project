package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.WordRank;

@Repository
public class WordRankDAOImpl implements WordRankDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<WordRank> getRanking() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		//order alphabetically
		Query <WordRank> theQuery = currentSession.createQuery("from WordRank order by counter desc", WordRank.class);
		theQuery.setMaxResults(20);
		
		return theQuery.getResultList();
	}

	@Override
	public List<WordRank> getAllWords() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <WordRank> theQuery = currentSession.createQuery("from WordRank", WordRank.class);
		
		return theQuery.getResultList();
	}

	@Override
	public void saveUpdateWord(WordRank w) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(w);
	}

	@Override
	public void decreaseCounter(String removedWord, int diffCounter) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		//get word to decrease counter
		Query <WordRank >theQuery = currentSession.createQuery("select w from WordRank w where w.wordEsp = :removedWord", WordRank.class);
		theQuery.setParameter("removedWord", removedWord);
		WordRank w = theQuery.uniqueResult();
		
		w.setCounter(w.getCounter()+diffCounter); //diffCounter has negative value
	}
	
}
