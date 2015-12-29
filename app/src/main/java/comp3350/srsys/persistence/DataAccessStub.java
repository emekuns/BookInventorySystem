package comp3350.srsys.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.objects.Message;
import comp3350.srsys.objects.Book;

public class DataAccessStub
{
	private String dbName;
	private String dbType = "stub";

	private ArrayList<Book> books;
	private ArrayList<Message> messages;

	public DataAccessStub(String dbName)
	{
		this.dbName = dbName;
	}

	public DataAccessStub()
	{
		this(Main.dbName);
	}

	public void open(String dbName)
	{
		Book book;
		books = new ArrayList<Book>();
		book = new Book("Software Engineering for Dummies", "Joe Blow", "CS", null, 150.5, 5);
		books.add(book);
		book = new Book("Waterfall", "John Glow", "CS", null, 200.5, 10);
		books.add(book);

		Message message;
		messages = new ArrayList<Message>();
		message = new Message("Student","Employee","hi","Hello you");
		messages.add(message);
		message = new Message("Student","Employee","book lookup","How do I lookup a book?");
		messages.add(message);
		message = new Message("Go to the page and search.", message);
		messages.add(message);
		message = new Message("Student","Employee","book lookup help","How do I lookup a book in command line?");
		messages.add(message);
		
		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		System.out.println("Closed " +dbType +" database " +dbName);
	}
	
	public String addBook(Book book)
	{
		books.add(book);
		return null;
	}
	
	public String updateBook(Book book){
		int index = books.indexOf(book);
		if(index >= 0){
			books.set(index, book);
		}
		return null;
	}
	
	public String removeBook(Book book){
		int index = books.indexOf(book);
		if(index >= 0){
			books.remove(index);
		}
		return null;
	}
	
	public String getBooks(List<Book> bookList){
		bookList.addAll(books);
		return null;
	}
	
	public String getMessages(List<Message> messageResult)
	{
		messageResult.addAll(messages);
		return null;
	}
	
	public String addMessage(Message message){
		messages.add(message);
		return null;
	}
	
	public String updateMessage(Message message){
		int index = messages.indexOf(message);
		if(index >= 0){
			messages.set(index, message);
		}
		return null;
	}
	
	public String removeMessage(Message message){
		int index = messages.indexOf(message);
		if(index >= 0){
			messages.remove(index);
		}
		return null;
	}
}
