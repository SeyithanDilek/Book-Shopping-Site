package com.bookstore.dao;

import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.bookstore.entity.Users;

public class UserDAOTest  {
	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		userDAO=new UserDAO();
	}
	
	@Test
	public void testCreateUsers() {
		Users userTest=new Users();
		userTest.setEmail("mehmet@gmail.com");
		userTest.setFullName("Pekder");
		userTest.setPassword("hjfslo");
		userTest=userDAO.create(userTest);
		assertTrue(userTest.getUserId()>0);	
	}
	
	
	@Test(expected=PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users userTest = new Users();
		userTest=userDAO.create(userTest);
		
	}
	
	@Test
	public void testUpdateUsers() {
		Users userTest=new Users();
		userTest.setUserId(19);
		userTest.setEmail("ssseyit.65");
		userTest.setFullName("Seyithan");
		userTest.setPassword("hellomap");
		
		userTest=userDAO.update(userTest);
		String expected="hellomap";
		String actual=userTest.getPassword();
		assertEquals(expected,actual);
	}	
	
	@Test
	public void testGetUserFound() {
		Integer userId=19;
		Users user=userDAO.get(userId);
		if(user != null) {
		System.out.println("User Name:"+user.getFullName());
		System.out.println("User Email"+user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserNotFound() {
		Integer userId=99;
		Users user=userDAO.get(userId);
		assertNotNull(user);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteNotExistUsers() {
		Integer userId=55;
		userDAO.delete(userId);
		
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId=21;
		userDAO.delete(userId);
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	
	@Test 
	public void testListAll() {
		List<Users> listUsers=userDAO.listAll();
		for(Users user: listUsers) {
			System.out.println(user.getFullName());
		}
		assertTrue(listUsers.size() >0);
	}
	
	@Test
	public void testCount() {
		long userCount= userDAO.count();
		System.out.println("Count User:" + userCount);
		List<Users> listUsers=userDAO.listAll();
		System.out.println(listUsers.size());
		
		assertTrue(userCount>0);
	}
	
	@Test
	public void testFindByEmail() {
		String email="ssseyit.65";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSucess() {
		String email="root@admin.com";
		String password="admin";
		boolean loginResult=userDAO.checkLogin(email, password);
		assertTrue(loginResult);
		
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email="root@admin.com";
		String password="admin";
		boolean loginResult=userDAO.checkLogin(email, password);
		assertFalse(loginResult);
		
	}
	
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}
}