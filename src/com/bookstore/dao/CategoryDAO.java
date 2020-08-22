package com.bookstore.dao;

import java.util.List;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
	}

	@Override
	public Category create(Category category) {
		// super kullanilarak bir üst menüye tasinirlar.  
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return super.update(category);
	}

	@Override
	public Category get(Object categoryId) {
		// TODO Auto-generated method stub
		return super.find(Category.class, categoryId);
	}

	@Override
	public void delete(Object categoryId) {
		// TODO Auto-generated method stub
		super.delete(Category.class, categoryId);
		
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}
	
	
	public Category findByName(String categoryName) {
		List<Category> listCats=super.findWithNamedQuery("Category.findByName","name",categoryName);
		if(listCats != null && listCats.size()>0) {
			return listCats.get(0);
		}
		return null;
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
