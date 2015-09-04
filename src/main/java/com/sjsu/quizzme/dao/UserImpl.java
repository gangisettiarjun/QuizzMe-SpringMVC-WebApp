package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sjsu.quizzme.model.Result;
import com.sjsu.quizzme.model.User;

public class UserImpl implements IQuizzMeDao<User> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	/**
	 * Register a new user User name should be unique
	 */
	@Override
	public Result<User> add(User entity) {
		Result<User> res = new Result<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			User ifExists = (User) session
					.createQuery(
							"select pl from User pl where pl.email = :email")
					.setString("email", entity.getEmail()).uniqueResult();
			// check for duplicate email entry: user name should be unique
			if (ifExists != null) {
				// duplicate email entry
				res.setEntity(entity);
				res.setResultCode(-1);
				return res;
			}
			// successful entry
			session.persist(entity);
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

	/**
	 * Update the existing the user entry
	 */
	@Override
	public User update(User entity) {
		try {
			// get the session object
			Session session = this.sessionFactory.getCurrentSession();
			// update the entity
			session.update(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

		return entity;
	}

	/**
	 * Delete existing user
	 * 
	 * @param id
	 *            of the user to be deleted
	 */
	@Override
	public Result<User> delete(int id) {
		Result<User> res = new Result<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			User existingUser = (User) session.get(User.class, new Integer(id));

			if (null != existingUser) {
				session.delete(existingUser);
				res.setResultCode(0);
				res.getEntity();
				return res;
			}
			// entity does not exist
			res.setEntity(null);
			res.setResultCode(-3);
			return res;
		} catch (HibernateException e) {
			e.printStackTrace();
			// on exception
			res.setEntity(null);
			res.setResultCode(-2);
			return res;
		}
	}

	/**
	 * Retrieve the user using the primary key
	 * 
	 * @param userId
	 */
	@Override
	public User getEntity(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			User desiredUser = (User) session.get(User.class, new Integer(id));
			return desiredUser;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Validate the login credentials and return the existing user object
	 * 
	 * @param username
	 *            : email address of the user
	 * @param password
	 *            : password as string
	 * @return null if invalid credentials else returns the existing user object
	 */
	public User Login(String username, String password) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			User ifExists = (User) session
					.createQuery(
							"select user from User user where user.email = :email"
									+ " and user.password= :pwd")
					.setString("email", username).setString("pwd", password)
					.uniqueResult();
			return ifExists;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get the user details from the email address
	 * @param emailAddress
	 * @return
	 */
	public User getEntityFromEmail(String emailAddress) {

		try {
			Session session = this.sessionFactory.getCurrentSession();
			User ifExists = (User) session
					.createQuery(
							"select pl from User pl where pl.email = :email")
					.setString("email", emailAddress).uniqueResult();
			return ifExists;

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
