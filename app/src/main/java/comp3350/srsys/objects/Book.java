package comp3350.srsys.objects;

import java.util.Locale;

public class Book implements Comparable<Book>{
	String title, author, subject, isbn;
	Double cost;
	int amount;
	
	public Book(String title, String author, String subject, String isbn, Double cost, int amount) {
		this.title = title;
		this.author = author;
		this.subject = subject;
		this.isbn = isbn;
		this.cost = cost;
		this.amount = amount;
	}
	
	//mutators
	public void setTitle(String newTitle){
		title = newTitle;
	}
	
	public void setAuthor(String newAuthor){
		author = newAuthor;
	}
	
	public void setSubject(String newSubject){
		subject = newSubject;
	}
	
	public void setISBN(String newISBN){
		isbn = newISBN;
	}
	
	public void setCost(Double newCost) {
		cost = newCost;
	}
	
	public void setAmount(int newAmount){
		amount = newAmount;
	}
	
	//accessors
	public String getTitle(){
		return title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public String getISBN(){
		return isbn;
	}
	
	public Double getCost(){
		return cost;
	}
	
	public int getAmount(){
		return amount;
	}
	
	//assume that Books have unique titles
	public int compareTo(Book other){
		int comp = Integer.MAX_VALUE;
		if(other != null && this.title != null){
			comp = this.title.toUpperCase(Locale.CANADA).compareTo(other.title.toUpperCase(Locale.CANADA));
		}
		return comp;
	}
	
	public boolean equals(Book other){
		return compareTo(other) == 0;
	}
	
	//stub, reminder to add into a formatted form
	public String toString(){
		return "Title: " + title + "\nAuthor: " + author + "\nSubject: " + subject + "\nisbn: " + 
					isbn + String.format( "\nPrice: $%.2f", cost );
	}


	
}