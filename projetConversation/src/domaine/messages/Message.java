package domaine.messages;

import java.util.ArrayList;

import domaine.Utilisateur;

public class Message {
	
	private Utilisateur expediteur;
	private int id;
	private String contenu;
	private String dateEnvoie;
	private boolean vu;
	private ArrayList<Option> options;
	
	public Message (Utilisateur expediteur, String contenu, String dateEnvoie, ArrayList<Option> options){
		this.expediteur=expediteur;
		this.contenu=contenu;
		this.vu = false;
		this.options = options;
		this.dateEnvoie = dateEnvoie;
	}
	
	public Message (int id, Utilisateur expediteur, String contenu , String dateEnvoie, boolean vu, ArrayList<Option> options){
		this.id = id;
		this.expediteur=expediteur;
		this.contenu=contenu;
		this.vu = vu;
		this.options = options;
		this.dateEnvoie = dateEnvoie;
	}

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

	public boolean isVu() {
		return vu;
	}

	public void setVu(boolean vu) {
		this.vu = vu;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public boolean isPrioritaire() {
		for (Option o : options){
			if (o instanceof Prioritaire){
				return true;
			}
		}
		return false;
	}
	
	
}
