package com.sjsu.quizzme.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.CategoryImpl;
import com.sjsu.quizzme.dao.QuestionImpl;
import com.sjsu.quizzme.dao.QuizImpl;
import com.sjsu.quizzme.model.Category;
import com.sjsu.quizzme.model.Questions;
import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Result;

@Service
public class QuizServices {
	private QuizImpl quizDAO;

	public void setQuizDAO(QuizImpl quizdao) {
		this.quizDAO = quizdao;
	}

	@Autowired
	private CategoryImpl catImpl;
	
	@Autowired
	private QuestionImpl questionImpl;

	/**
	 * Get the top 5 popular quizzes registered till now
	 * 
	 * @return list of 5 quizzes
	 */
	@Transactional
	public List<Quiz> getMostPopularQuizes() {
		try {
			List<Quiz> popularQuizzes = quizDAO.getPopularQuizList();
			for (int i = 0; i < popularQuizzes.size(); i++) {
				popularQuizzes.get(i).getCategory().getCategoryName();

			}
			return popularQuizzes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get all the quizzes registered in the system.
	 * 
	 * @return list of the quizzes
	 */
	@Transactional
	public List<Quiz> getList() {
		try {

			List<Quiz> quizList = quizDAO.getList();
			return quizList;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get category wise quiz list
	 * 
	 * @return list of the quizzes
	 */
	@Transactional
	public HashMap<String, Integer> getCategoryWiseList() {
		try {

			List<Quiz> quizList = quizDAO.getCategoryWiseQuizList();
			HashMap<String, Integer> finalList = new HashMap<String, Integer>();
			for (int i = 0; i < quizList.size(); i++) {
				String currentCategory = quizList.get(i).getCategory()
						.getCategoryName();
				if (finalList.containsKey(currentCategory)) {
					finalList.put(currentCategory,
							finalList.get(currentCategory) + 1);
				} else {
					finalList.put(currentCategory, 1);
				}

			}
			return finalList;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Get a certain quiz details
	 * 
	 * @param id
	 *            primary key of the quiz
	 * @returns the quiz entity
	 */
	@Transactional
	public Quiz getQuiz(int id) {
		try {

			Quiz requiredQuiz = quizDAO.getEntity(id);
			return requiredQuiz;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getLevelList() {
		List<String> levelList = new ArrayList<String>();
		levelList.add("Level 1");
		levelList.add("Level 2");
		levelList.add("Level 3");
		levelList.add("Level 4");
		levelList.add("Level 5");
		return levelList;
	}

	public List<Questions> getEmptyQuestionList() {
		List<Questions> questionList = new ArrayList<Questions>();
		for (int count = 0; count < 10; count++) {
			Questions newQstn = new Questions();
			newQstn.setQuestion("");
			newQstn.setAnswer(0);
			questionList.add(newQstn);
		}
		return questionList;
	}

	@Transactional
	public Result<Quiz> add(Quiz entity, int categoryId) {
		Result<Quiz> res = new Result<Quiz>();
		try {
			Category selectedCat = catImpl.getEntity(categoryId);
			entity.setCategory(selectedCat);
			res = quizDAO.add(entity);
			return res;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Result<Quiz> addQuestions(int quizId, List<Questions> questions) {
		Result<Quiz> res = new Result<Quiz>();
		try {
			Set<Questions> newQuestions = new HashSet<Questions>();
			Quiz updatedQuiz = quizDAO.getEntity(quizId);
			for (int count = 0; count < questions.size(); count++) {
				newQuestions.add(questions.get(count));
				Questions newQuestion=questions.get(count);
				newQuestion.setQuiz(updatedQuiz);
				questionImpl.add(newQuestion);
			}		
			res.setEntity(null);
			res.setResultCode(0);
			return res;		
			
		} catch (HibernateException e) {
			e.printStackTrace();
			res.setEntity(null);
			res.setResultCode(-1);
			return res;
		}

	}

}
