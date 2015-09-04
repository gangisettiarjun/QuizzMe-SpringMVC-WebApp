package com.sjsu.quizzme.model;

public class CommentsDTO {
	
	private int id;
	private int userId;
	private int quizId;
	private String comment;
	
	public CommentsDTO(){}
	
	public CommentsDTO(int id){
		this.id = id;
	}
	
	public CommentsDTO(int id, int userId,int quizId,String comment){
		this.id= id;
		this.userId=userId;
		this.quizId= quizId;
		this.comment= comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
