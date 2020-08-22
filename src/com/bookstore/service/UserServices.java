package com.bookstore.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices( HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("listUsers", listUsers);

		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users exitsUser = userDAO.findByEmail(email);

		if (exitsUser != null) {
			String message = "Could not create user A user " + "with email" + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, fullName, password);
			userDAO.create(newUser);
			String message = "New User Created Sucessfully";
			listUser(message);
		}
	}

	public void editUser() throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		Users existUser = userDAO.findByEmail(email);
		Users user0 = userDAO.get(userId);

		// emaili aynı olan veya var olmayan kullanıcının güncellenmesini engellemis
		// oluruz
		if (existUser != null || user0 == null) {
			String message = "Could not create user A user " + "with email" + " already exists";
			listUser(message);

		} else {
			Users user = new Users(userId, email, fullName, password);
			userDAO.update(user);
			String message = "User has been updated successfully";
			listUser(message);
		}
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		if(user == null) {
			String message ="user has already been deleted";
			listUser(message);
		}else {
			String message = "User has been deleted successfully";
			userDAO.delete(userId);
			listUser(message);	
		}
		
	}
	
	public void login() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean result=userDAO.checkLogin(email, password);
		if(result) {
			System.out.println("User is authenticated");	
			request.getSession().setAttribute("userEmail", email);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
			
			
			
		}else {
			String message="Login Failed";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}