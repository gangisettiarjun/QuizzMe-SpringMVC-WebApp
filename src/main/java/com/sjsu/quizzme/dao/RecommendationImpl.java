package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.quizzme.model.Recommendation;
import com.sjsu.quizzme.model.Result;

@Repository
public class RecommendationImpl implements IQuizzMeDao<Recommendation>{
	
		@Autowired
	    private SessionFactory sessionFactory;

	  
		public Recommendation saveRecommendation(Recommendation recommendation) {
	        Session session = getSession();
	        session.save(recommendation);
	        return recommendation;
	    }
	    public Session getSession() throws HibernateException {
	        return sessionFactory.getCurrentSession();
	    }
		@Override
		public Result<Recommendation> add(Recommendation entity) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Recommendation update(Recommendation entity) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Result<Recommendation> delete(int id) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Recommendation getEntity(int id) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<Recommendation> getList() {
			// TODO Auto-generated method stub
			return null;
		}
		
}

