package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Iterator;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Book;
import comp3350.srsys.persistence.DataAccessStub;

public class Inventory {
	
	private DataAccessStub dataAccess;
	private List<Book> books;
	public Inventory(){
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		books = null;
	}
	
	public String addBook(String title, String author, String subject, String isbn, Double cost, int amount){
		String status = null;
		if(title != null && !title.isEmpty()){
			status = dataAccess.addBook(new Book(title,author,subject,isbn,cost,amount));
		}
		return status;
	}
	
	public String removeBook(String title){
		String status = null;
		ArrayList<Book> list = findBooks("TITLE", title);
		if(list.size() != 1){
			status = "Please input a more specific title.";
		}else{
			status = dataAccess.removeBook(list.get(0));
		}
		return status;
	}

	
	public String updateBook(String title, String author, String subject, String isbn, Double cost){
		String status = null;
		ArrayList<Book> list = findBooks("TITLE", title);
		if(list.size() != 1){
			status = "Please input a more specific title.";
		}else{
			String newAuthor = author;
			String newSubject = subject;
			String newISBN = isbn;
			Double newCost = cost;
			
			//empty fields should leave Book fields intact
			if("".equals(author) || author == null){
				newAuthor = list.get(0).getAuthor();
			}
			if("".equals(subject) || subject == null){
				newSubject = list.get(0).getSubject();
			}
			if("".equals(isbn) || isbn == null){
				newISBN = list.get(0).getISBN();
			}
			//will change cost to a different type later
			if(cost == 0.0){
				newCost = list.get(0).getCost();
			}
			Book changedBook = new Book(title, newAuthor, newSubject, newISBN, newCost, list.get(0).getAmount());
			
			status = dataAccess.updateBook(changedBook);
		}
		return status;
	}
	
	public String updateStock(String title, int amount){
		String status = null;
		ArrayList<Book> list = findBooks("TITLE", title);
		if(list.size() != 1){
			status = "Please input a more specific title.";
		}else{
			Book changedBook = list.get(0);
			changedBook.setAmount(amount);
			status = dataAccess.updateBook(changedBook);
		}		
		return status;
	}
	
	public ArrayList<Book> findBooks(String criteria, String key){
		ArrayList<Book> matches = new ArrayList<Book>();;
		if(criteria != null && key != null && !key.trim().isEmpty()){
			if(criteria.equals("TITLE")){
				matches = findBooksByTitle(key);
			}else{
				books = new ArrayList<Book>();
				dataAccess.getBooks(books);
				if(books != null){
					for(Book bk: books){
						String info = "";
						if(criteria.equals("AUTHOR")){
							info = bk.getAuthor();
						}else if(criteria.equals("SUBJECT")){
							info = bk.getSubject();
						}else if(criteria.equals("ISBN")){
							info = bk.getISBN();
						}
						
						if(info != null && !info.isEmpty()){
							String upInfo = info.toUpperCase(Locale.getDefault());
							String upKey = key.toUpperCase(Locale.getDefault());
							
							if(upInfo.contains(upKey)){
								matches.add(bk);
							}
						}
					}
				}
			}
		}
		return matches;
	}
	
	private ArrayList<Book> findBooksByTitle(String key){
		ArrayList<Book> matches = new ArrayList<Book>();
		books = new ArrayList<Book>();
		dataAccess.getBooks(books);
		boolean exactMatch = false;
		Book bk = null;
		if(books != null){
			for(Iterator<Book> ii = books.iterator(); ii.hasNext() && !exactMatch;){
				bk = ii.next();
				String title = bk.getTitle();
				if(title != null && !title.isEmpty()){
					String upTitle = title.toUpperCase(Locale.CANADA);
					String upKey = key.toUpperCase(Locale.CANADA);
					if(upTitle.equals(upKey)){
						exactMatch = true;
						matches = new ArrayList<Book>();
						matches.add(bk);
					}else if(upTitle.contains(upKey)){
						matches.add(bk);
					}
				}
			}
		}
		
		return matches;
	}
	
	public List<Book> findAllBooks(){
		books = new ArrayList<Book>();
		dataAccess.getBooks(books);
		return books;
	}
		
}
