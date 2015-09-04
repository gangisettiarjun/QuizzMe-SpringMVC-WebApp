package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sjsu.quizzme.model.Questions;
import com.sjsu.quizzme.model.Result;

public class QuestionImpl implements IQuizzMeDao<Questions> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	@Override
	public Result<Questions> add(Questions entity) {
		Result<Questions> newQuestionRes = new Result<Questions>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(entity);
			newQuestionRes.setEntity(entity);
			newQuestionRes.setResultCode(0);
			return newQuestionRes;
		} 	catch (HibernateException e) {
			e.printStackTrace();
			newQuestionRes.setEntity(null);
			newQuestionRes.setResultCode(-2);
			return newQuestionRes;
		}
	}

	@Override
	public Questions update(Questions entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Questions> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Questions getEntity(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questions> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
