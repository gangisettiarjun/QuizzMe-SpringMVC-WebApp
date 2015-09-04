package com.sjsu.quizzme.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.QuestionImpl;
import com.sjsu.quizzme.model.Questions;
import com.sjsu.quizzme.model.Result;

@Service
public class QuestionServices {
	
	private QuestionImpl questionDAO;

	public void setQuestionDAO(QuestionImpl questiondao) {
		this.questionDAO = questiondao;
	}
	
	@Transactional
	public Result<Questions> add(Questions entity) {
		try {
			if(entity!=null)
			{
				Result<Questions> res= questionDAO.add(entity);
				return res;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
