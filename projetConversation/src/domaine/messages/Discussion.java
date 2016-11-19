package domaine.messages;

import java.util.ArrayList;


public class Discussion {
	private int id;
	private ArrayList<Message> messages ;
	
	public Discussion (int id){
		this.id = id;
		this.messages = new ArrayList<Message>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
}
