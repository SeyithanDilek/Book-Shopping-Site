package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
	}

	@Override
	public Users create(Users user) {
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public Users findByEmail(String email) {
		 List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail","email",email);
		 if(listUsers != null && listUsers.size()>0) {
			 return listUsers.get(0);
		 }
		 return null;
	}
	
	public boolean checkLogin(String email,String password) {
		
		Map<String,Object> parameters=new HashMap();
		parameters.put("email",email );
		parameters.put("password",password);
		
		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin",parameters);
		//login isleminde eposta ve sifre dogru olmasi durumunda yalnızca bir kullanici dondururulur.
		if(listUsers.size()==1) {
			return true;
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
}
