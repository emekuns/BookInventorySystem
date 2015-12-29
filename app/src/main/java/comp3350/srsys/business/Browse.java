package comp3350.srsys.business;

import java.util.*;

import comp3350.srsys.objects.Book;


public class Browse {
	
	private Inventory data;
	
	public Browse(){
		data = new Inventory();
	}
	
	public ArrayList<Book> browseByCat(String category)
	{
		ArrayList<Book> retList = new ArrayList<Book>();
		
		retList = data.findBooks("SUBJECT", category);
		
		return retList;		
	}
	
	public ArrayList<Book> search(String category, String searchKey)
	{
		ArrayList<Book> retList = new ArrayList<Book>();

		retList = data.findBooks(category, searchKey);
			
		return retList;
			
	}
	
}