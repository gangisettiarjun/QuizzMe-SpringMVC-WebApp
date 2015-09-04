package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Result;
import com.sjsu.quizzme.model.ScoreCard;

public class ScoreCardImpl implements IQuizzMeDao<ScoreCard> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Result<ScoreCard> add(ScoreCard entity) {
		Result<ScoreCard> res = new Result<ScoreCard>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			/*ScoreCard ifExists = (ScoreCard) session
					.createQuery(
							"select sc from ScoreCard sc where sc.userId = :userID and sc.quizId= :quizID")
					.setInteger("userID", entity.getUser().getId())
					.setInteger("quizID", entity.getQuiz().getId())
					.uniqueResult();
			// check for duplicate email entry: user name should be unique
			if (ifExists != null) {
				// quiz already attempted
				res.setEntity(entity);
				res.setResultCode(-1);
				return res;
			}*/
			// successful entry
			session.persist(entity);
			// now update the popularity count of the quiz
			int increasedPopularity = entity.getQuiz().getPopularity() + 1;
			entity.getQuiz().setPopularity(increasedPopularity);
			session.update(entity.getQuiz());
			res.setEntity(entity);
			res.setResultCode(0);
			return res;
		} catch (HibernateException e) {
			e.printStackTrace();
			// on exception
			res.setEntity(entity);
			res.setResultCode(-2);
			return res;
		}
	}

	@Override
	public ScoreCard update(ScoreCard entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<ScoreCard> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreCard getEntity(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreCard> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ScoreCard> getTopScorerList() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<ScoreCard> quizList = session
					.createQuery("select s from  ScoreCard s order by s.score")
					.setMaxResults(5).list();
			return quizList;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;

	}


	

}
