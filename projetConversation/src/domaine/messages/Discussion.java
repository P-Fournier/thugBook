package domaine.messages;

import java.util.ArrayList;

import domaine.Utilisateur;


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

	public void addMessage(Message msg) {
		messages.add(msg);
	}
	
	public void vuPar (Utilisateur u){
		for (Message msg : messages){
			for (Option o : msg.getOptions()){
				if (o instanceof AccuseReception && msg.getExpediteur() != u){
					AccuseReception acr = (AccuseReception) o;
					acr.getDestinataires().put(u,true);
				}
			}
		}
	}
	
}
