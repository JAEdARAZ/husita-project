package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Translation;

@Repository
public class TranslationDAOImpl implements TranslationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Translation> getTranslations() {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
		
		//order by date
		Query <Translation> theQuery = currentSession.createQuery("from Translation order by date desc, id desc", 
																	Translation.class);
 		
		return theQuery.getResultList();
	}

	@Override
	public Translation getTranslation(int theId) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
		
		return currentSession.get(Translation.class, theId);
	}

	@Override
	public void updateTranslation(Translation theTranslation) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.update(theTranslation);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteTranslation(int theId) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete from Translation where id=:translationId");
		theQuery.setParameter("translationId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public void insertTranslations(Translation translation) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(translation);
	}

	@Override
	public List<Translation> getEngSearch(String word) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
				
		//order by date and id
		Query <Translation> theQuery = currentSession.createQuery("from Translation "
																+ "where sentEnglish like :word "
																+ "order by date desc, id desc", Translation.class);
		theQuery.setParameter("word", "%" + word + "%");
		return theQuery.getResultList();
	}

	@Override
	public List<Translation> getEspSearch(String word) {
		//get current hibernate session and create query
		Session currentSession = sessionFactory.getCurrentSession();
				
		//order by date and id
		Query <Translation> theQuery = currentSession.createQuery("from Translation "
																+ "where sentSpanish like :word "
																+ "order by date desc, id desc", Translation.class);
		theQuery.setParameter("word", "%" + word + "%");
		return theQuery.getResultList();
	}
	
}
