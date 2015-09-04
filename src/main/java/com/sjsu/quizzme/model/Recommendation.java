package com.sjsu.quizzme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "recommenderId")
	private User recommender;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "recommendeeId")
	private User recommendee;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "qid")
	private Quiz quiz;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getRecommender() {
		return recommender;
	}

	public void setRecommender(User recommender) {
		this.recommender = recommender;
	}

	public User getRecommendee() {
		return recommendee;
	}

	public void setRecommendee(User recommendee) {
		this.recommendee = recommendee;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	
}
