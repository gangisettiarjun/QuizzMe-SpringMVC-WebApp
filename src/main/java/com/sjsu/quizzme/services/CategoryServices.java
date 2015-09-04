package com.sjsu.quizzme.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sjsu.quizzme.dao.CategoryImpl;
import com.sjsu.quizzme.model.Category;

public class CategoryServices {
	private CategoryImpl categoryDAO;

	public void setCategoryDAO(CategoryImpl categorydao) {
		this.categoryDAO = categorydao;
	}
	
	@Transactional
	public List<Category> getAllCategories() {
		try {
			List<Category> categoryList = categoryDAO.getList();
			return categoryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
