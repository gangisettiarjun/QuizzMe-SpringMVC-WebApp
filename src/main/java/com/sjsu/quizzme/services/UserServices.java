package com.sjsu.quizzme.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.UserImpl;
import com.sjsu.quizzme.model.*;

@Service
public class UserServices {
	private UserImpl userDAO;

	public void setUserDAO(UserImpl userdao) {
		this.userDAO = userdao;
	}
	
	@Transactional
	public Result<User> add(User entity) {
		try {
			if(entity!=null)
			{
				Result<User> existingUser= userDAO.add(entity);
				return existingUser;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public User Login(Login inputCredentials)
	{
		try {
			if(inputCredentials!=null)
			{
				User existingUser= userDAO.Login(inputCredentials.getUsername(), inputCredentials.getPassword());
				existingUser.getCards();
				existingUser.getComments();
				existingUser.getRecommendationsByUser();
				existingUser.getRecommendationsForUser();
				return existingUser;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
