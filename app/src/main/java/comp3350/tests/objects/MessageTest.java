package comp3350.tests.objects;

import comp3350.srsys.objects.Message;
import junit.framework.TestCase;

public class MessageTest extends TestCase {
	
	Message message;
	Message messageNull;

	public MessageTest(String arg0)	{
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		message = new Message("Student","Employee","book lookup","How do I lookup a book?");
		messageNull = new Message(null,null,null,null);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testNullMessage(){
		assertNotNull(messageNull);
		assertTrue(null == messageNull.getFrom());
		assertTrue(null == messageNull.getTo());
		assertTrue(null == messageNull.getSubject());
		assertTrue(null == messageNull.getBody());
		assertFalse(messageNull.getIsReply());
		assertFalse(messageNull.getHasReply());
	
		assertTrue(messageNull.equals(messageNull));
	}

	public void testStandardMessage(){
		assertNotNull(message);
		assertTrue("Student".equals(message.getFrom()));
		assertTrue("Employee".equals(message.getTo()));
		assertTrue("book lookup".equals(message.getSubject()));
		assertTrue("How do I lookup a book?".equals(message.getBody()));
		assertFalse(message.getIsReply());
		assertFalse(message.getHasReply());
		
		assertTrue(message.equals(message));
		assertFalse(message.equals(messageNull));
	}
	
	public void testCreatReply(){
		Message reply = new Message("Go to the page and search.", message);
		message.replyMade();
		
		assertTrue(message.getHasReply());

		assertNotNull(reply);
		assertTrue("Employee".equals(reply.getFrom()));
		assertTrue("Student".equals(reply.getTo()));
		assertTrue("Re: book lookup".equals(reply.getSubject()));
		assertTrue("Go to the page and search.".equals(reply.getBody()));
		assertTrue(reply.getIsReply());
		assertFalse(reply.getHasReply());
		assertTrue(message.getMessageID() == reply.getMessageID());
		
		assertTrue(reply.equals(reply));
		assertFalse(reply.equals(message));
	}
}
