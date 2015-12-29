package comp3350.srsys.presentation;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.business.AccessMessages;
import comp3350.srsys.business.Inventory;
import comp3350.srsys.objects.Book;
import comp3350.srsys.objects.Message;

public class CLI  // command-line interface
{
	public static BufferedReader console;
	public static String inputLine;
	public static String[] inputTokens;
	
	public static Inventory inventory = new Inventory();
	public static AccessMessages accessMessages = new AccessMessages();

	public static Message message;
	
	public static void run(){
		try{
			console = new BufferedReader(new InputStreamReader(System.in));
			process();
			console.close();
		}
		catch (IOException ioe){
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void process(){
		System.out.println("(enter \"help\" for command list)");
		readLine();
		while ((inputLine != null) && (!inputLine.equalsIgnoreCase("exit"))
				&& (!inputLine.equalsIgnoreCase("quit"))
				&& (!inputLine.equalsIgnoreCase("q"))
				&& (!inputLine.equalsIgnoreCase("bye")))
		{	// use cntl-c or exit to exit
			inputTokens = inputLine.split("\\s+");
			parse();
			readLine();
		}
	}

	public static void readLine(){
		try{
			System.out.print(">");
			inputLine = console.readLine();
		}
		catch (IOException ioe){
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	public static void parse(){
		if (inputTokens[0].equalsIgnoreCase("get")){
			processGet();
		}
		else if(inputTokens[0].equalsIgnoreCase("help")){
			listCommands();
		}
		else if(inputTokens[0].equalsIgnoreCase("Employee")){
			employeeCommands();
		}
		else if(inputTokens[0].equalsIgnoreCase("Professor")){
			professorCommands();
		}
		else if(inputTokens[0].equalsIgnoreCase("Srudent")){
			studentCommands();
		}
		else
		{
			System.out.println("Invalid command.(enter \"help\" for command list)");
		}
	}

	private static void listCommands() {
			System.out.println("Command List:");
			System.out.println("\"get books\": lists all book Titles");
			System.out.println("\"get messages\": lists all message Subjects.");
			System.out.println("\"get book <Title>\": gets book with specified <Title> or if title is not found "
								+ "all books that contain the <Title> in there title will be shown.");
			System.out.println("\"get message <Subject>\": gets message with specified <Subject> or if subject is not found "
								+ "all message that contain the <Subject> in there subject will be shown.\n"
								+ "Note: If exact Subject is entered the message will display the reply aswell (if there is one).");
			System.out.println("\"student question\": send a question to the employees.");
			System.out.println("\"employee reply <Subject>\": Employee can reply to a message.");
			System.out.println("\"professor reply <Subject>\": Professor can reply to a message.");
			System.out.println("\"professor request\": Professor can request a book.");
			System.out.println("\"exit\" or \"quit\" or \"q\" or \"bye\": exits the program.");
	}

	public static void processGet(){
		if (inputTokens[1].equalsIgnoreCase("Book")){
			processGetBook();
		}
		else if (inputTokens[1].equalsIgnoreCase("Message")){
			processGetMessage();
		}
		else if (inputTokens[1].equalsIgnoreCase("Books")){
			processGetBooks();
		}
		else if (inputTokens[1].equalsIgnoreCase("Messages")){
			processGetMessages();
		}
		else if (inputTokens[1].equalsIgnoreCase("MessagesTo")){
			processGetMessagesTo();
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}

	private static void processGetBooks() {
		List<Book> books = inventory.findAllBooks();
		
		System.out.println("List of Book Titles:");
		for(Book book: books){
			System.out.println(book.getTitle());
		}
		
	}

	private static void processGetBook() {
		System.out.println("Get Book Process Here\n");
		
		List<Book> books;
		if (inputTokens.length > 2){
			String title = inputTokens[2];
			for(int i = 3; i < inputTokens.length; i++){
				title += " " + inputTokens[i];
			}
			
			System.out.println("Search for: " + title + "\n");
			books = inventory.findBooks("TITLE", title);
			
			for(Book book: books){
				System.out.println(book.toString() + "\n");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}
	
	private static void processGetMessages() {
		List<Message> messages = new ArrayList<Message>();
		
		System.out.println("List of Message Subjects:");
		accessMessages.getMessages(messages);
		
		for(Message message: messages){
			System.out.println(message.getSubject());
		}
	}

	private static void processGetMessage() {
		System.out.println("Get Message Process Here\n");

		List<Message> messages;
		if (inputTokens.length > 2){
			String subject = inputTokens[2];
			for(int i = 3; i < inputTokens.length; i++){
				subject += " " + inputTokens[i];
			}
			
			System.out.println("Search for: " + subject + "\n");
			messages = accessMessages.getMessageBySubject(subject);
			
			for(Message message: messages){
				System.out.println(message.toString() + "\n");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}		
	}
	
	private static void processGetMessagesTo() {
		System.out.println("Get Message To Process Here\n");

		List<Message> messages;
		if (inputTokens.length > 2){
			String to = inputTokens[2];
			
			System.out.println("Search for Messages to: " + to + "\n");
			messages = accessMessages.getMessageByTo(to);
			
			for(Message message: messages){
				System.out.println(message.toString() + "\n");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}
	
	private static void employeeCommands() {
		if (inputTokens.length > 1){
			if (inputTokens[1].equalsIgnoreCase("reply")){
				employeeProcessReply();
			}
			else{
				System.out.println("Invalid data type (enter \"help\" for command list)");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}

	private static void employeeProcessReply() {
		if (inputTokens.length > 2){
			List<Message> messages;
			String reply = "";
			double cost = 0;
			String line = "";
			String costLine = "";
			String subject = inputTokens[2];
			boolean gotCost = false;
			
			for(int i = 3; i < inputTokens.length; i++){
				subject += " " + inputTokens[i];
			}
			
			System.out.println("Search for: " + subject + "\n");
			messages = accessMessages.getMessageBySubject(subject);
			
			if(messages.size() == 1 && messages.get(0).getTo().equalsIgnoreCase("employee")){
				System.out.println(messages.get(0).toString());
				if(messages.get(0).getFrom().equalsIgnoreCase("professor")){
					System.out.println("Accept Book Request?(y/n)");
					do{
						System.out.println("\n(y/n)");
						try {
							line = console.readLine();
							
							if(line.equalsIgnoreCase("y")){
								while(!gotCost){
									System.out.print("Enter a cost per book(use numbers/decimal only):");
									costLine = console.readLine();
									try{
										cost =  Double.parseDouble(costLine);	
										gotCost = true;
									}
									catch(Exception e){
										
									}
								}
								String tokins[] = messages.get(0).getBody().split("\\r?\\n");
								try{									
									inventory.addBook(tokins[0].split(":")[1].trim(), tokins[1].split(":")[1].trim(), 
										tokins[2].split(":")[1].trim(), null, cost, Integer.parseInt(tokins[3].split(":")[1].trim()));
								}
								catch(Exception e){
									System.out.println("Amount was not a number have book request resubmited in proper format.");
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n"));
				}
				
				System.out.println("\n\nPlease enter a reply(submit no text to cancel the reply message | enter to submit): ");
				try {
					reply = console.readLine();
				} catch (IOException ioe){
					System.out.println(ioe.getMessage());
					ioe.printStackTrace();
				}
				if(reply != null && !reply.isEmpty()){
					accessMessages.addMessage(new Message(reply, messages.get(0)));
				}
				else{
					System.out.println("Reply canceled");
				}
				
			}
			else if(messages.size() == 2 && (messages.get(0).getSubject().equals("Re: " + messages.get(1).getSubject()) || 
					messages.get(1).getSubject().equals("Re: " + messages.get(0).getSubject()))){
				System.out.println("This message already has a reply.\n");
				for(Message message: messages){
					System.out.println(message.toString() + "\n");
				}
			}
			else{
				System.out.println("The message subject \"" + subject + "\" does not exist or is spelled wrong.");
				System.out.println("To get a list of messages to employees use \"get messagesTo employee\"");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}

	private static void professorCommands() {
		if (inputTokens.length > 1){
			if (inputTokens[1].equalsIgnoreCase("reply")){
				professorProcessReply();
			}
			else if (inputTokens[1].equalsIgnoreCase("request")){
				professorProcessRequest();
			}

			else{
				System.out.println("Invalid data type (enter \"help\" for command list)");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}

	private static void professorProcessReply() {
		if (inputTokens.length > 2){
			List<Message> messages;
			String reply = "";
			String subject = inputTokens[2];
			for(int i = 3; i < inputTokens.length; i++){
				subject += " " + inputTokens[i];
			}
			
			System.out.println("Search for: " + subject + "\n");
			messages = accessMessages.getMessageBySubject(subject);
			
			if(messages.size() == 1 && messages.get(0).getTo().equalsIgnoreCase("professor")){
				System.out.println(messages.get(0).toString() + "\n\nPlease enter a reply(submit no text to cancel | enter to submit): ");
				try {
					reply = console.readLine();
				} catch (IOException ioe){
					System.out.println(ioe.getMessage());
					ioe.printStackTrace();
				}
				if(reply != null && !reply.isEmpty()){
					accessMessages.addMessage(new Message(reply, messages.get(0)));
				}
				else{
					System.out.println("Reply canceled");
				}
			}
			else if(messages.size() == 2 && (messages.get(0).getSubject().equals("Re: " + messages.get(1).getSubject()) || 
					messages.get(1).getSubject().equals("Re: " + messages.get(0).getSubject()))){
				System.out.println("This message already has a reply.\n");
				for(Message message: messages){
					System.out.println(message.toString() + "\n");
				}
			}
			else{
				System.out.println("The message subject \"" + subject + "\" does not exist or is spelled wrong.");
				System.out.println("To get a list of messages to professors use \"get messagesTo professor\"");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}
	
	private static void professorProcessRequest() {
		String title = "";
		String author = "";
		String subject = "";
		String amount = "";
		String body = "";
		String line = "";
		try {
			System.out.println("Enter a Title:");
			title = console.readLine();
			System.out.println("Enter an Author:");
			author = console.readLine();
			System.out.println("Enter a Subject:");
			subject = console.readLine();
			System.out.println("Enter an amount:");
			amount = console.readLine();
			
			if(title != null && !title.isEmpty() && author != null && !author.isEmpty() &&
					subject != null && !subject.isEmpty() && amount != null && !amount.isEmpty()){
				body = "Title: " + title + "\nAuthor: " + author + "\nSubject: " + subject + "\nAmount requesting: " + amount;
				System.out.println("Do you want to send request:\n" + body);
				do{
					System.out.println("\n(y/n)");
					line = console.readLine();
					if(line.equalsIgnoreCase("y"))
					{
						accessMessages.addMessage(new Message("Professor", "Employee", "Request for book:" + title, body));
					}
					
				}while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n"));
			}
			else{
				System.out.println("A feald was not filled, canceling message.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void studentCommands() {
		if (inputTokens.length > 1){
			if (inputTokens[1].equalsIgnoreCase("question")){
				studentProcessQuestion();
			}
			else{
				System.out.println("Invalid data type (enter \"help\" for command list)");
			}
		}
		else{
			System.out.println("Invalid data type (enter \"help\" for command list)");
		}
	}

	private static void studentProcessQuestion() {
		String subject = "";
		String body = "";
		String line = "";
		try {
			System.out.println("Enter a Subject:");
			subject = console.readLine();
			System.out.println("Enter the body:");
			body = console.readLine();
			
			if(subject != null && !subject.isEmpty() && body != null && !body.isEmpty()){
				System.out.println("Do you want to send request:\n" + subject + "\n" + body);
				do{
					System.out.println("\n(y/n)");
					line = console.readLine();
					if(line.equalsIgnoreCase("y"))
					{
						accessMessages.addMessage(new Message("Student", "Employee", subject, body));
					}				
				}while(!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n"));
			}
			else{
				System.out.println("A feald was not filled, canceling message.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}