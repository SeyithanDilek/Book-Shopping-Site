package com.bookstore.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {

	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	private void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listpage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listpage);
		requestDispatcher.forward(request, response);

	}

	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category existCat=categoryDAO.findByName(name);
		
		if (existCat != null) {
			String message = "Could not create category A category " + "with name" + " already exists";
			request.setAttribute("message", message);
			listCategory(message);
	}else {
		Category newCategory=new Category(name);
		categoryDAO.create(newCategory);
		String message="New Category Created Sucessfully";
		listCategory(message);
	}
}

	public void editCategory() throws ServletException, IOException {
		int catId=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDAO.get(catId);
		String editPage="category_form.jsp";
		request.setAttribute("category", category);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	

	public void updateCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		String name=request.getParameter("name");
		Category existCategory=categoryDAO.findByName(name);
		Category newCat=categoryDAO.get(categoryId);
		
		if(existCategory != null || newCat == null) {
			String message = "Could not create category A category " + "with name" + " already exists";
			listCategory(message);
		} else {
			Category category=new Category(categoryId,name);
			categoryDAO.update(category);
			String message = "User has been updated successfully";
			listCategory(message);
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category newCat=categoryDAO.get(categoryId);
		if(newCat == null) {
			String message="Category has been already deleted";
			listCategory(message);
		}else {
			categoryDAO.delete(categoryId);
			String message="Category has been deleted sucesfully";
			listCategory(message);
		}
		
	}
}