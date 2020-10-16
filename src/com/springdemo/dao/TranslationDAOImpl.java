package com.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Translation;

@Repository
public class TranslationDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Translation> getTranslations() {
		
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
		
		//order by last name
		Query <Translation> theQuery = currentSession.createQuery("from Translation", Translation.class);
		List<Translation> translations = theQuery.getResultList();
 		
		return translations;
	}
	
}
