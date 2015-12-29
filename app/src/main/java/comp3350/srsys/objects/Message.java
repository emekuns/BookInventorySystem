package comp3350.srsys.objects;

public class Message {
	static int maxID = 0;

	private String from, to, subject, body;
	private int messageID;
	private boolean isReply, hasReply;
	
	public Message(String from, String to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.messageID = maxID;
		maxID++;
		this.isReply = false;
		this.hasReply = false;
	}

	public Message(String body, Message message) {
		this.from = message.to;
		this.to = message.from;
		this.subject = "Re: " + message.subject;
		this.body = body;
		this.messageID = message.getMessageID();
		this.isReply = true;
		this.hasReply = false;
		
		message.replyMade();
	}
	
	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public int getMessageID() {
		return messageID;
	}

	public boolean getIsReply() {
		return isReply;
	}
	
	public boolean getHasReply() {
		return hasReply;
	}
	
	public void replyMade(){
		hasReply = true;
	}
	
	public String toString(){
		return "Title: " + subject + "\nTo: " + to + " From: " + from + " \n" + body + "\nReplied to = " + hasReply;
	}

	public boolean equals(Message message){
		boolean isEqual = true;
		
		if(message != null){
			if(message.getMessageID() != this.messageID){
				isEqual = false;
			}
			
			if(message.getIsReply() != this.isReply){
				isEqual = false;
			}
		}
		else{
			isEqual = false;
		}
			
		return isEqual;		
	}
}