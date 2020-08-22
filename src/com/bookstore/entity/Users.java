package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name="Users.findAll" , query="SELECT u FROM Users u ORDER BY u.fullName"),
	@NamedQuery(name="Users.findByEmail", query="SELECT u FROM Users u WHERE u.email = :email " ),
	@NamedQuery(name="Users.countAll" , query="SELECT Count(*) FROM Users u"),
	@NamedQuery(name="Users.checkLogin",query="SELECT u FROM Users u WHERE u.email = :email AND u.password = :password")
})
public class Users {
	private Integer userId;
	private String email;
	private String fullName;
	private String password;

	public Users() {
		
	}
	
	
	public Users(String email, String fullName, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public Users(int userId, String email, String fullName, String password) {
		this(email,fullName,password);
		this.userId=userId;
	}

	@Column(name="user_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
