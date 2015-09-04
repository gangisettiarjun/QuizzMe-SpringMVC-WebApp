package com.sjsu.quizzme.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.*;
import com.sjsu.quizzme.model.*;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationImpl recommendationDAO;

	@Autowired
	private UserImpl userImpl;

	@Autowired
	private QuizImpl quizImpl;

	@Transactional
	public Recommendation createRecommendation(int recommenderId,
			String recommendeeEmailId, int quizId) {

		// Getting Recommender and Recommendee User objects.

		User recommender = userImpl.getEntity(recommenderId);
		String recommenderEmail = recommender.getEmail();
		String recommenderName = recommender.getFirstName();

		User recommendee = userImpl.getEntityFromEmail(recommendeeEmailId);
		String recommendeeEmail;
		String recommendeeName;
		if (recommendee == null) {
			// if the user is not in the system send an email anyhow
			recommendeeEmail = recommendeeEmailId;
			recommendeeName = "";
		} else {
			recommendeeEmail = recommendee.getEmail();
			recommendeeName = recommendee.getFirstName();
		}

		Quiz quiz = quizImpl.getEntity(quizId);
		int quizID = quiz.getId();

		// Simple email notification via SMTP
		final String username = "cmpe275.group18.quizzme@gmail.com";
		final String password = "Group18@quizzme";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(recommenderEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recommendeeEmail));
			message.setSubject("Quiz Recommendation");
			message.setText("Hello "
					+ recommendeeName
					+ ", \n"
					+ recommenderName
					+ " thinks you will be interested in checking out the following quiz: \n"
					+ "http://localhost:8080/quizzme/quizzes/attempt/" + quizID + "\n"
					+ "Regards,\n" + "Team 18");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		// Persisting data in recommendation database
		Recommendation userRecommendation = new Recommendation();
		userRecommendation.setRecommender(recommender);
		userRecommendation.setRecommendee(recommendee);
		userRecommendation.setQuiz(quiz);
		Recommendation recommendation = recommendationDAO
				.saveRecommendation(userRecommendation);
		return recommendation;
	}

}
