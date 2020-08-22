package com.bookstore.dao;

import java.util.Date;
import java.util.List;
import com.bookstore.entity.Book;
import com.bookstore.entity.Users;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}
	
	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}
	
	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}
	

	@Override
	public Book get(Object bookId) {
		// TODO Auto-generated method stub
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Book findByTitle(String title) {
		List<Book> listBooks=super.findWithNamedQuery("Book.findByTitle","title",title);
		if(!listBooks.isEmpty()) {
			return listBooks.get(0);
		}
		return null;
		
	}
}
