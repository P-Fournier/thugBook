package domaine.messages;

import java.util.ArrayList;

import domaine.Utilisateur;


public abstract class Discussion {
	
	protected int id;			// id de la discussion en base
	protected ArrayList<Message> messages ;	// ensemble des messages de la discussion
	
	// CONSTRUCTEUR(S)
	
	public Discussion (int id){
		this.id = id;
		this.messages = new ArrayList<Message>();
	}
	
	// ACCESSEUR(S)
	
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
	
	// FONCTION(S)
	
	/**
	 * ajoute le message passé en paramètre à la discussion (dans la liste de message)
	 * @param msg
	 */
	public void addMessage(Message msg) {
		messages.add(msg);
	}
	
	/**
	 * passe tout les messages de la discussion ayant l'option accusé de reception à vu pour l'utilisateur 
	 * passé en paramètre
	 * @param u
	 */
	public void vuPar (Utilisateur u){
		for (Message msg : this.getMessages()){
			for (Option o : msg.getOptions()){
				if (o instanceof AccuseReception && msg.getExpediteur() != u){
					AccuseReception acr = (AccuseReception) o;
					acr.getDestinataires().put(u,true);
				}
			}
		}
	}
}
