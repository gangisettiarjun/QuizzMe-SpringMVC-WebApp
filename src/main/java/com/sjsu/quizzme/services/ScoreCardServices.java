package com.sjsu.quizzme.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.QuizImpl;
import com.sjsu.quizzme.dao.ScoreCardImpl;
import com.sjsu.quizzme.model.Questions;
import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.Result;
import com.sjsu.quizzme.model.ScoreCard;
import com.sjsu.quizzme.model.User;

public class ScoreCardServices {

	private ScoreCardImpl scoreCardDAO;

	public void setScoreCardDAO(ScoreCardImpl scoreCarddao) {
		this.scoreCardDAO = scoreCarddao;
	}

	@Autowired
	private QuizImpl quizImpl;

	@Transactional
	public Result<ScoreCard> attemptQuiz(Quiz attemptedQuiz,
			User attemptingUser, List<Questions> answeredQuestions) {
		Result<ScoreCard> res = new Result<ScoreCard>();
		try {
			int score = 0;

			if (attemptedQuiz != null) {
				if (attemptedQuiz.getQuestions().size() > 0) {
					Set<Questions> questions = attemptedQuiz.getQuestions();
					for (Iterator<Questions> it = questions.iterator(); it
							.hasNext();) {
						Questions currentQuestion = it.next();
						int answerByUser = getAnswerEnteredForQuestion(
								currentQuestion.getQuestion(),
								answeredQuestions);
						if (currentQuestion.getAnswer() == answerByUser) {
							score = score + 1;
						}
					}

					ScoreCard newScoreCard = new ScoreCard();
					newScoreCard.setQuiz(attemptedQuiz);
					newScoreCard.setScore(score);
					newScoreCard.setUser(attemptingUser);
					if (!attemptingUser.getCards().contains(newScoreCard)) {
						scoreCardDAO.add(newScoreCard);
					}
					res.setEntity(newScoreCard);
					res.setResultCode(0);
					return res;

				}
				// required things missing
				res.setEntity(null);
				res.setResultCode(-3);
				return res;
			}
			// on null object
			res.setEntity(null);
			res.setResultCode(-3);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			// on exception
			res.setEntity(null);
			res.setResultCode(-2);
			return res;
		}

	}

	/**
	 * get top scorers in the system along with the quiz details
	 * 
	 * @return
	 */
	@Transactional
	public List<ScoreCard> getTopScorers() {
		try {
			List<ScoreCard> topScorers = new ArrayList<ScoreCard>();
			topScorers = scoreCardDAO.getTopScorerList();
			for (int i = 0; i < topScorers.size(); i++) {
				topScorers.get(i).getQuiz().getName();
				topScorers.get(i).getUser().getFirstName();
			}
			return topScorers;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get quizzes attempted by the user category wise and get their highest
	 * scores
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public List<ScoreCard> getAttemptedQuizes(Set<ScoreCard> attemptedQuizzes) {
		try {
			List<ScoreCard> finalList = new ArrayList<ScoreCard>();
			for (Iterator<ScoreCard> it = attemptedQuizzes.iterator(); it
					.hasNext();) {
				ScoreCard f = it.next();
				Set<ScoreCard> scoresForQuiz = f.getQuiz().getScores();
				f.setHighestScore(findHighestScore(scoresForQuiz));
				finalList.add(f);

			}
			if (finalList.size() < 5) {
				return finalList;
			}
			return finalList.subList(0, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Category wise distribution of attempted quizzes
	 * 
	 * @param attemptedQuizzes
	 * @return
	 */
	@Transactional
	public HashMap<String, Integer> getCategoryCountForAttemptedQuizzes(
			Set<ScoreCard> attemptedQuizzes) {
		try {
			HashMap<String, Integer> finalList = new HashMap<String, Integer>();
			for (Iterator<ScoreCard> it = attemptedQuizzes.iterator(); it
					.hasNext();) {
				ScoreCard f = it.next();
				String currentCategory = f.getQuiz().getCategory()
						.getCategoryName();
				if (finalList.containsKey(currentCategory)) {
					finalList.put(currentCategory,
							finalList.get(currentCategory) + 1);
				} else {
					finalList.put(currentCategory, 1);
				}

			}
			return finalList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public int findHighestScore(Set<ScoreCard> list) {
		int highestScore = 0;
		for (Iterator<ScoreCard> it = list.iterator(); it.hasNext();) {
			ScoreCard f = it.next();
			if (f.getScore() > highestScore)
				highestScore = f.getScore();

		}
		return highestScore;
	}

	/**
	 * Get the answer submitted by the user for the quiz
	 * 
	 * @param question
	 * @param answeredQuestions
	 * @return
	 */
	public int getAnswerEnteredForQuestion(String question,
			List<Questions> answeredQuestions) {
		for (int i = 0; i < answeredQuestions.size(); i++) {
			if (answeredQuestions.get(i).getQuestion()
					.equalsIgnoreCase(question.trim())) {
				return answeredQuestions.get(i).getAnswer();
			}
		}
		return -1;
	}

	/**
	 * Find if user has already attempted the quiz
	 * 
	 * @param currentUser
	 * @param quizId
	 * @return
	 */
	public boolean checkIfUserHasAttemptedQuiz(User currentUser, int quizId) {
		Set<ScoreCard> scoreCards = currentUser.getCards();
		for (Iterator<ScoreCard> it = scoreCards.iterator(); it.hasNext();) {
			ScoreCard f = it.next();
			if (f.getQuiz().getId() == quizId
					&& f.getUser().getId() == currentUser.getId())
				return true;

		}
		return false;

	}

}
