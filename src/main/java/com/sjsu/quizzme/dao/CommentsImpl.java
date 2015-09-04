package com.sjsu.quizzme.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.quizzme.model.Comments;
import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Result;

@Repository
public class CommentsImpl implements IQuizzMeDao<Comments> {

	@Autowired
	public SessionFactory sessionFactory;

	public Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Result<Comments> add(Comments entity) {
		Session session = getSession();
		session.save(entity);
		return null;
	}

	@Override
	public Comments update(Comments entity) {
		// TODO Auto-generated method stub
		return null;
	}

	// Delete comments for user
	@Override
	public Result<Comments> delete(int id) {
		Session session = getSession();
		Criteria criteria = getSession().createCriteria(Comments.class);
		criteria.add(Restrictions.eq("id", id));
		Comments comments = (Comments) criteria.uniqueResult();
		session.delete(comments);
		return null;
	}

	@Override
	public Comments getEntity(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comments> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Comments saveComments(Comments comments) {
		Session session = getSession();
		session.save(comments);
		return comments;
	}

	// comments for a quiz

	public Set<Comments> getCommentsListForQuiz(int quiz) {
		Session session = this.sessionFactory.getCurrentSession();
		Quiz desiredQuiz = (Quiz) session.get(Quiz.class, new Integer(quiz));
		Set<Comments> commentsForQuiz = desiredQuiz.getComments();
		return commentsForQuiz;
	}

	// Get comments for a user
	public Set<Comments> getCommentsListForUser(int user) {
		Session session = this.sessionFactory.getCurrentSession();
		Quiz desiredQuiz = (Quiz) session.get(Quiz.class, new Integer(user));
		Set<Comments> commentsForUser = desiredQuiz.getComments();
		return commentsForUser;
	}

}
