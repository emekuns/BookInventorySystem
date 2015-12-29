package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Message;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessMessages {
	
	private DataAccessStub dataAccess;
	private List<Message> messages;
	
	public AccessMessages() {
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		messages = new ArrayList<Message>();
	}

    public String getMessages(List<Message> messages){
    	messages.clear();
        return dataAccess.getMessages(messages);
    }
    
    public List<Message> getMessageBySubject(String key){
    	List<Message> quary = new ArrayList<Message>();
    	List<Message> list = new ArrayList<Message>();
    	Boolean exactMatch = false;
    	if(key != null && !key.isEmpty()){
	    	getMessages(list);
	    	
	    	if(list != null && list.size() > 0){
	    		for(Message msg: list){
	    			String subject = msg.getSubject();
	    			if(!exactMatch && subject != null && !subject.isEmpty()){
	    				subject = subject.toUpperCase();
	    				key = key.toUpperCase();
	    				if(subject.equals(key)){
	    					quary = new ArrayList<Message>();
	    					quary.add(msg);
	    					exactMatch = true;
	    					if(msg.getHasReply()){
	    						quary.addAll(getMessageBySubject("Re: " + key));
	    					}
	    				}else if(subject.contains(key)){
	    					quary.add(msg);
	    				}
	    			}
	    		}
	    	}
    	}
    	return quary;
    }
    
    public List<Message> getMessageByTo(String key){
    	List<Message> quary = new ArrayList<Message>();
    	List<Message> list = new ArrayList<Message>();
    	if(key != null && !key.isEmpty()){
			key = key.toUpperCase();
	    	getMessages(list);
	    	
	    	if(list != null && list.size() > 0){
	    		for(Message msg: list){
	    			String to = msg.getTo();
	    			if(to != null && !to.isEmpty()){
	    				to = to.toUpperCase();
	    				if(to.equals(key)){
	    					quary.add(msg);
	    				}
	    			}
	    		}
	    	}
    	}
    	return quary;
    }
    
    public Message getReply(Message originalMessage){
    	Message reply = null;
    	getMessages(messages);
    	for(Message message: messages){
    		if(message.getMessageID() == originalMessage.getMessageID() && message.getIsReply()){
    			reply = message;
    		}
    	}    	
    	return reply;
    }
    
	public String addMessage(Message currentMessage){
		return dataAccess.addMessage(currentMessage);
	}

	public String updateMessage(Message currentMessage){
		return dataAccess.updateMessage(currentMessage);
	}

	public String removeMessage(Message currentMessage){
		return dataAccess.removeMessage(currentMessage);
	}
 
}