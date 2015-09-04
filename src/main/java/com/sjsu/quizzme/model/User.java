package com.sjsu.quizzme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Transient
	private String confirmPassword;	
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	private Set<ScoreCard> cards= new HashSet<ScoreCard>();	

	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	private Set<Comments> comments= new HashSet<Comments>();	

	@OneToMany(mappedBy="recommender",fetch=FetchType.EAGER)
	private Set<Recommendation> recommendationsByUser= new HashSet<Recommendation>();

	@OneToMany(mappedBy="recommendee",fetch=FetchType.EAGER)
	private Set<Recommendation> recommendationsForUser= new HashSet<Recommendation>();

	public Set<Recommendation> getRecommendationsByUser() {
		return recommendationsByUser;
	}

	public void setRecommendationsByUser(Set<Recommendation> recommendationsByUser) {
		this.recommendationsByUser = recommendationsByUser;
	}

	public Set<Recommendation> getRecommendationsForUser() {
		return recommendationsForUser;
	}

	public void setRecommendationsForUser(
			Set<Recommendation> recommendationsForUser) {
		this.recommendationsForUser = recommendationsForUser;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

	public Set<ScoreCard> getCards() {
		return cards;
	}

	public void setCards(Set<ScoreCard> cards) {
		this.cards = cards;
	}
	
	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	
}
