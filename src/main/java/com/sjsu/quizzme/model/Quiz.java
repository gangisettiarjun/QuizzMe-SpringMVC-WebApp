package com.sjsu.quizzme.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quizzName", nullable = false)
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@Column(name = "level", nullable = false)
	private int level;
	
	@Column(name = "popularity", nullable = false)
	private int popularity;
	
	@OneToMany(mappedBy="quiz",fetch=FetchType.EAGER)
	private Set<Questions> questions= new HashSet<Questions>();
	
	@OneToMany(mappedBy="quiz",fetch=FetchType.EAGER)
	private Set<Comments> comments= new HashSet<Comments>();
	
	@OneToMany(mappedBy="quiz",fetch=FetchType.EAGER)
	private Set<ScoreCard> scores= new HashSet<ScoreCard>();
	
	public Set<ScoreCard> getScores() {
		return scores;
	}

	public void setScores(Set<ScoreCard> scores) {
		this.scores = scores;
	}

	public Set<Recommendation> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy="quiz")
	private Set<Recommendation> recommendations = new HashSet<Recommendation>();
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public Set<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}

	

}
