package domaine.messages;

import java.util.ArrayList;

import domaine.Utilisateur;

public class Message {
	
	private Utilisateur expediteur;		// Utilisateur qui a envoyé le message
	private int id;						// id du message en base
	private String contenu;				// texte (contenu) du message
	private String dateEnvoie;			// date à laquelle a été envoyé le message
	private ArrayList<Option> options;	// liste des options du messages (prioritaire,chiffré,...)
	
	//	CONSTRUCTEUR(S)
	
	public Message (Utilisateur expediteur, String contenu, String dateEnvoie ,ArrayList<Option> options){
		this.expediteur=expediteur;
		this.contenu=contenu;
		this.dateEnvoie= dateEnvoie;
		this.options = options;
	}
	
	public Message (int id, Utilisateur expediteur, String contenu , String dateEnvoie,ArrayList<Option> options){
		this.id = id;
		this.expediteur=expediteur;
		this.contenu=contenu;
		this.options = options;
		this.dateEnvoie = dateEnvoie;
	}
	
	//	ACCESSEUR(S)
	
	public Utilisateur getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(String dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	//	FONCTION(S)
	
	/**
	 * test si le message à l'option prioritaire
	 * @return
	 */
	public boolean isPrioritaire() {
		for (Option o : options){
			if (o instanceof Prioritaire){
				return true;
			}
		}
		return false;
	}
	
}
