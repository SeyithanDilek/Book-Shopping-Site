package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public interface GenericDAO<E> {
	public E create(E t);
	public E update(E t);
	public E get(Object id);
	public void delete(Object id);
	public long count();
	public List<E> listAll();
	Users findByEmail(String email);
	Category findByName(String name);
}
