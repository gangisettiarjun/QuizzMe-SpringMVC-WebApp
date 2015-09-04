package com.sjsu.quizzme.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.CommentsImpl;
import com.sjsu.quizzme.dao.QuizImpl;
import com.sjsu.quizzme.dao.UserImpl;
import com.sjsu.quizzme.model.Comments;
import com.sjsu.quizzme.model.CommentsDTO;
import com.sjsu.quizzme.model.Quiz;
import com.sjsu.quizzme.model.User;

@Service
public class CommentsServices {

	@Autowired
	private CommentsImpl commentsDAO;
	
	@Autowired
	private QuizImpl quizImpl;
	
	@Autowired
	private UserImpl userImpl;
	
	@Transactional
	public Comments createComments(int userId, int quizId, String comment){
		
		//get hibernate objects for user and quiz
		User commenter = userImpl.getEntity(userId);
		Quiz commentedQuiz = quizImpl.getEntity(quizId);
		
		Comments userComments = new Comments();
		userComments.setUser(commenter);
		userComments.setQuizz(commentedQuiz);
		userComments.setComment(comment);
		Comments comments = commentsDAO.saveComments(userComments);
		return comments;
		
	}

	//Get comments made by specific user
	@Transactional
	public List<CommentsDTO> getListForUser(int user) {
		try {
			List<CommentsDTO> commentDtos = new ArrayList<CommentsDTO>();
	        List<Comments> comments =new ArrayList<Comments>();
	        Set<Comments> commentsByUser=commentsDAO.getCommentsListForUser(user);
	    	for (Iterator<Comments> it = commentsByUser.iterator(); it
					.hasNext();) {
	    		Comments f = it.next();
	    		comments.add(f);
	    	}
	        if(comments==null || comments.size()==0) {
	            return null;
	        }
	        
	        convertCommentsToDTOs(commentDtos, comments);
	        return commentDtos;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void convertCommentsToDTOs(List<CommentsDTO> commentDtos,
			List<Comments> comments) {
		for(Comments comment : comments){
			CommentsDTO commentsDTO = new CommentsDTO();
			commentsDTO.setId(comment.getId());
			commentsDTO.setComment(comment.getComment());
			commentsDTO.setQuizId(comment.getQuizz().getId());
			commentsDTO.setUserId(comment.getUser().getId());
			commentDtos.add(commentsDTO);
      
		}
	}
	
	//Get comments made on specific quiz
	@Transactional
	public List<CommentsDTO> getListForQuiz(int quiz) {
		try {
			List<CommentsDTO> commentDtos = new ArrayList<CommentsDTO>();
	        List<Comments> comments =new ArrayList<Comments>();
	        Set<Comments> commentsByUser=commentsDAO.getCommentsListForQuiz(quiz);
	    	for (Iterator<Comments> it = commentsByUser.iterator(); it
					.hasNext();) {
	    		Comments f = it.next();
	    		comments.add(f);
	    	}
	        if(comments==null || comments.size()==0) {
	            return null;
	        }
	        convertCommentsToDTOs(commentDtos, comments);
	        return commentDtos;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Delete comment
		@Transactional
	    public void deleteComment(int id) {
	        commentsDAO.delete(id);
	    }
}
