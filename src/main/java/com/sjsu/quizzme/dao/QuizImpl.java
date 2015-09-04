package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Result;

public class QuizImpl implements IQuizzMeDao<Quiz> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Result<Quiz> add(Quiz entity) {
		Result<Quiz> newQuizRes = new Result<Quiz>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(entity);
			newQuizRes.setEntity(entity);
			newQuizRes.setResultCode(0);
			return newQuizRes;
		} 	catch (HibernateException e) {
			e.printStackTrace();
			newQuizRes.setEntity(entity);
			newQuizRes.setResultCode(-2);
			return newQuizRes;
		}
	}

	@Override
	public Quiz update(Quiz entity) {
		try {
			// get the session object
			Session session = this.sessionFactory.getCurrentSession();
			//update the entity
			session.update(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

		return entity;
	}

	@Override
	public Result<Quiz> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getEntity(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Quiz desiredQuiz = (Quiz) session.get(Quiz.class, new Integer(id));
			return desiredQuiz;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Quiz> getList() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Quiz> quizList = session.createQuery("from Quiz")
					.list();
			return quizList;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Get the top 5 popular quizzes registered so far
	 * @return
	 */
	public List<Quiz> getPopularQuizList() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Quiz> popularQuizzes = session.createQuery(
					"select q from Quiz q order by q.popularity").setMaxResults(5).list();
			
			return popularQuizzes;
		} catch (HibernateException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get the category wise quiz list
	 * @return
	 */
	public List<Quiz> getCategoryWiseQuizList() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Quiz> quizList = session.createQuery("select q from Quiz q group by q.category")
					.list();
			return quizList;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
