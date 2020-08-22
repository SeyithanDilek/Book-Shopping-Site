package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO=new BookDAO();
	
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book bookTest=new Book();
		
		//kitap bir kategoriye bagli oldugu icin kategori olusturulmali
		Category category=new Category("Life");
		category.setCategoryId(21);
		bookTest.setCategory(category);
		bookTest.setTitle("Java 8 in Action");
		bookTest.setAuthor("Mario Fusco");
		bookTest.setDescription("Every new version of Java is important");
		bookTest.setPrice(36.72f);
		bookTest.setIsbn("1617291994");
		
		//Tarih setlenmeden once tipi belirtilmelidir
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("28/08/2014");
		bookTest.setPublishDate(publishDate);
		
		//get(imagepath) methodu byte dizisi dondurur bunu bir degiskene atamaliyiz
		String imagePath="E:\\BookstoreProject\\BookStoreWebsite\\WebContent\\images\\core_java.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		bookTest.setImage(imageBytes);
		
		Book createBook=bookDAO.create(bookTest);
		assertTrue(createBook.getBookId()>0);
		
	}
	
	//Update yontemi create yonteminin aynisidir.
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book bookTest=new Book();
		bookTest.setBookId(21);
		
		Category category = new Category("Life");
		category.setCategoryId(21);
		bookTest.setCategory(category);
		bookTest.setTitle("Java Core");
		bookTest.setAuthor("Maryo Fusco");
		bookTest.setDescription("To get an answer you need to provide some more information");
		bookTest.setPrice(30.02f);
		bookTest.setIsbn("1617291994");
		
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("28/08/2014");
		bookTest.setPublishDate(publishDate);
		
		String imagePath="E:\\BookstoreProject\\BookStoreWebsite\\WebContent\\images\\core_java.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		bookTest.setImage(imageBytes);
		
		Book updateBook=bookDAO.update(bookTest);
		assertEquals(updateBook.getTitle(),"Java Core");
	}
	
	
	@Test
	public void testDeleteBook() {
		Integer bookId=40;
		bookDAO.delete(bookId);
		Book book=bookDAO.get(bookId);
		assertNull(book);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteNotExistBook() {
		Integer bookId=1;
		bookDAO.delete(bookId);
	}
	
	@Test
	public void testgetBook() {
		Integer bookId=39;
		Book book=bookDAO.get(bookId);
		
		if(book !=null) {
			System.out.println("Book Title: "+book.getTitle());
			System.out.println("Book Author "+book.getAuthor());
		}
		assertNotNull(book);
	}
	
	
	@Test
	public void testListAll() {
		List<Book> listBooks=bookDAO.listAll();
		for(Book book :listBooks) {
			System.out.println(book.getTitle());
			System.out.println(book.getAuthor());
		}
		assertFalse(listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitle() {
		String title="Effect Java (2nd Edition)";
		Book book=bookDAO.findByTitle(title);
		assertNotNull(book);
	}
	
	@Test
	public void testCount() {
		long bookCount=bookDAO.count();
		assertEquals(2,bookCount);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}

}
