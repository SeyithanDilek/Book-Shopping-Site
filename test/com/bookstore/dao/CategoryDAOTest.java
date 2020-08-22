package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Health");
		Category category=categoryDAO.create(newCat);
		assertTrue( category != null && category.getCategoryId()>0);
	}

	@Test
	public void testUpdateCategory() {
		Category newCat = new Category("Health");
		newCat.setCategoryId(11);
		Category category = categoryDAO.update(newCat);
		assertEquals(newCat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		Integer catId=11;
		Category newCat=categoryDAO.get(catId);
		System.out.println(newCat.getName());
		assertNotNull(newCat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId=11;
		categoryDAO.delete(catId);
		Category cat= categoryDAO.get(catId);
		assertNull(cat);
	}

	@Test
	public void testCount() {
		long totalCategories=categoryDAO.count();
		assertEquals(2,totalCategories);
			
	}

	@Test
	public void testListAll() {
		List<Category> listCategory=categoryDAO.listAll();
		listCategory.forEach(c -> System.out.println(c.getName()+","));
		assertTrue(listCategory.size()>0);
	}
	
	@Test
	public void testFindByName() {
		String name="Core Java";
		Category cat=categoryDAO.findByName(name);
		assertNotNull(cat);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name=" Java";
		Category cat=categoryDAO.findByName(name);
		assertNull(cat);
	}
}