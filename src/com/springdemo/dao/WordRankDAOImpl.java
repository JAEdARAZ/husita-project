package com.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public List<WordRank> getRanking() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		//order alphabetically
		Query <WordRank> theQuery = currentSession.createQuery("from WordRank order by counter desc", WordRank.class);
		theQuery.setMaxResults(20);
		
		return theQuery.getResultList();
	}
	
}
