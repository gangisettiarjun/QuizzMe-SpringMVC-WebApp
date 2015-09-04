package com.sjsu.quizzme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "questions")
public class Questions {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "question", nullable = false)
	private String question;
	@Column(name = "answer", nullable = false)
	private int answer;
	
	@Transient
	private int selectedAnswer=0;	
	
	@ManyToOne
	@JoinColumn(name="quizId")
	private Quiz quiz;
	
	public int getId() {
		return id;
	}

	public int getSelectedAnswer() {
		return selectedAnswer;
	}



	public void setSelectedAnswer(int selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String newQuestion) {
		question = newQuestion;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int requiredAnswer) {
		answer = requiredAnswer;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	

}
