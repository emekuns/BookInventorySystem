package comp3350.tests.business;

import java.util.ArrayList;

import comp3350.srsys.business.Inventory;
import comp3350.srsys.objects.Book;
import comp3350.srsys.persistence.DataAccessStub;
import comp3350.srsys.application.Services;
import junit.framework.TestCase;

public class InventoryTest extends TestCase{
	private Inventory inven;
	private Book bk;
	private ArrayList<Book> list;
	
	public void setUp(){
		Services.createDataAccess("testOne");
		inven = new Inventory();
	}
	
	public void tearDown(){
		Services.closeDataAccess();
	}
	
	public void testBook(){
		bk = new Book("Super Book", "Crazy Author", "Amazing Subject", "Dunno What ISBN", 50.0, 5);
		
		assertEquals("Super Book", bk.getTitle());
		assertEquals("Crazy Author", bk.getAuthor());
		assertEquals("Amazing Subject", bk.getSubject());
		assertEquals("Dunno What ISBN", bk.getISBN());
		assertEquals(50.0, bk.getCost());
		assertEquals(5, bk.getAmount());
		
		Book other = new Book("Super Book", null, null, null, 0.0, 0);
		assertTrue(other.equals(bk));
		
		other = new Book("SuPeR BOOK", null, null, null, 0.0, 0);
		assertTrue(other.equals(bk));
		
		other = new Book("Not Super Book", null, null, null, 0.0, 0);
		assertFalse(other.equals(bk));
		
		other = new Book(null, null, null, null, 0.0, 0);
		assertFalse(other.equals(bk));
		
		other = null;
		assertFalse(bk.equals(other));
	}
	
	
	public void testOneBookSearch(){
		DataAccessStub data = (DataAccessStub) Services.getDataAccess("SC");
		Book orig = new Book("Super Book", "Crazy Author", "Amazing Subject", "Dunno What ISBN", 50.0, 5);
		data.addBook(orig);
		
		list = inven.findBooks("TITLE", "Super Book");
		assertEquals(1, list.size());
		assertTrue(orig.equals(list.get(0)));
		
		list = inven.findBooks("AUTHOR", "Crazy Author");
		assertTrue(orig.equals(list.get(0)));
		
		list = inven.findBooks("SUBJECT", "Amazing Subject");
		assertTrue(orig.equals(list.get(0)));
		
		list = inven.findBooks("ISBN", "Dunno What ISBN");
		assertTrue(orig.equals(list.get(0)));	
		
		list = inven.findBooks("asdfasd", "Dunno What ISBN");
		assertEquals(0, list.size());	
		
		list = inven.findBooks("TITLE", "Book");
		assertTrue(orig.equals(list.get(0)));	
		
		list = inven.findBooks("TITLE", "sUPER book");
		assertTrue(orig.equals(list.get(0)));	
		
		list = inven.findBooks("TITLE", "super Bo");
		assertTrue(orig.equals(list.get(0)));	
		
		list = inven.findBooks("TITLE", "");
		assertEquals(0, list.size());	
		
		list = inven.findBooks("TITLE", " ");
		assertEquals(0, list.size());
		
		list = inven.findBooks("TITLE", ".*");
		assertEquals(0, list.size());
		
		list = inven.findBooks(null, ".*");
		assertEquals(0, list.size());
		
		list = inven.findBooks("TITLE", null);
		assertEquals(0, list.size());
	}
	
	
	public void testBookRemoval(){
		DataAccessStub data = (DataAccessStub) Services.getDataAccess("SC");
		
		Book bk;
		Book orig = new Book("Super Book", "Crazy Author", "Amazing Subject", "Dunno What ISBN", 50.0, 5);
		data.addBook(orig);
		
		list = inven.findBooks("TITLE", "Super Book");
		assertEquals(1, list.size());
		assertTrue(orig.equals(list.get(0)));
		
		inven.removeBook("Super Book");
		
		list = inven.findBooks("TITLE", "Super Book");
		assertEquals(0, list.size());
		
		list = inven.findBooks("TITLE", "Super Book");
		assertEquals(0, list.size());
		
		data.addBook(orig);
		bk = new Book("Super Book that teach about subsets", "Crazy Author", "Amazing Subject", "Dunno What ISBN", 50.0, 5);
		data.addBook(bk);
		
		inven.removeBook("Super Book");
		list = inven.findBooks("TITLE", "Super");
		assertEquals(1, list.size());		
		
		data.addBook(orig);
		inven.removeBook("Super");
		list = inven.findBooks("TITLE", "Super");
		assertEquals(2, list.size());
	}
	
	/*
	public void testTwoBooks(){
		DataAccessStub data = (DataAccessStub) Services.createDataAccess("testTwo");
		
		bk = new Book("Software Engineering for Dummies", "Joe Blow", "CS", null, 150.5, 5);
		data.addBook(bk);
		bk = new Book("Waterfall", "John Glow", "CS", null, 200.5, 10);
		data.addBook(bk);
		
		list = inven.findBooks("TITLE", "Software");
		
	}
	*/
}
