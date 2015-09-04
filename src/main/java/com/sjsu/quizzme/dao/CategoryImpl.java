package com.sjsu.quizzme.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sjsu.quizzme.model.Category;
import com.sjsu.quizzme.model.Result;
import com.sjsu.quizzme.model.User;

public class CategoryImpl implements IQuizzMeDao<Category>{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


	@Override
	public Result<Category> add(Category entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Category> delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getEntity(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Category desiredCategory = (Category) session.get(Category.class, new Integer(id));
			return desiredCategory;
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getList() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Category> categoryList = session.createQuery("from Category")
					.list();
			return categoryList;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}		
		
	}
	

}
