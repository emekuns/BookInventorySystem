package comp3350.tests.business;

import java.util.ArrayList;

import comp3350.srsys.application.Services;
import comp3350.srsys.business.Browse;
import comp3350.srsys.objects.Book;
import comp3350.srsys.persistence.DataAccessStub;
import junit.framework.TestCase;

public class BrowseTest extends TestCase {
	
	private DataAccessStub data;	

	public BrowseTest(String arg0)	{
		super(arg0);
	}
	
	public void setUp(){
		Services.createDataAccess("testBrowse");
	}

	public void testBrowse()
	{
		
		data = (DataAccessStub) Services.getDataAccess("SC");
		
		ArrayList<Book> list;
		
		Browse browse = new Browse();
		
		Book sampleBook = new Book("Introduction to Algorithms", "Mike", "Computer Sience", "3422424", 60.0, 5);
		
		assertEquals("Introduction to Algorithms", sampleBook.getTitle());
		
		data.addBook(sampleBook);
		
		list = browse.search("TITLE", "Introduction to Algorithms");
		assertEquals(1, list.size());
		
		assertTrue(sampleBook.equals(list.get(0)));		
		
		list = browse.search("AUTHOR", "Mike");
		assertTrue(sampleBook.equals(list.get(0)));

	}	

}
