package domaine;

import java.util.ArrayList;
import java.util.HashMap;

import domaine.messages.Discussion;
import domaine.notification.Notification;


public class Utilisateur {
	private int idU;
	private String nom;
	private String prenom;
	private String ndc;
	private String password;
	private ArrayList<SousCategorieCI> listeInteret;
	private HashMap<Utilisateur,Discussion> amis;
	private ArrayList<Utilisateur> demandeAmisRecues;
	private ArrayList<Utilisateur> demandesAmisSoumises;
	private ArrayList<GroupeDiscussion> groupeDiscussion;
	private ArrayList<Notification> notifications;


	public Utilisateur(int id, String nom, String prenom, String ndc,
			String password) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.password = password;
		this.listeInteret = new ArrayList<SousCategorieCI>();
		this.amis = new HashMap<Utilisateur,Discussion>();
		this.demandeAmisRecues = new ArrayList<Utilisateur>();
		this.demandesAmisSoumises = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<Notification>();
	}

	public Utilisateur(int id, String nom, String prenom, String ndc) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.listeInteret = new ArrayList<SousCategorieCI> ();
		this.amis = new HashMap<Utilisateur,Discussion>();
		this.demandeAmisRecues = new ArrayList<Utilisateur>();
		this.demandesAmisSoumises = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<Notification>();
	}

	public ArrayList<SousCategorieCI> getListeInteret() {
		return listeInteret;
	}

	public void setListeInteret(ArrayList<SousCategorieCI> listeInteret) {
		this.listeInteret = listeInteret;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	public ArrayList<Utilisateur> getDemandeAmisRecues() {
		return demandeAmisRecues;
	}

	public void setDemandeAmisRecues(ArrayList<Utilisateur> demandeAmisRecues) {
		this.demandeAmisRecues = demandeAmisRecues;
	}
	
	public ArrayList<Utilisateur> getDemandesAmisSoumises() {
		return demandesAmisSoumises;
	}

	public void setDemandesAmisSoumises(ArrayList<Utilisateur> demandesAmisSoumises) {
		this.demandesAmisSoumises = demandesAmisSoumises;
	}

	public HashMap<Utilisateur,Discussion> getAmis() {
		return amis;
	}

	public void setAmis(HashMap<Utilisateur,Discussion> amis) {
		this.amis = amis;
	}

	public ArrayList<GroupeDiscussion> getGroupeDiscussion() {
		return groupeDiscussion;
	}

	public void setGroupeDiscussion(ArrayList<GroupeDiscussion> groupeDiscussion) {
		this.groupeDiscussion = groupeDiscussion;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	public int nbNotifNonVues (){
		int result = 0;
		for (Notification n : notifications){
			if (!n.isVue()){
				result ++;
			}
		}
		return result;
	}
	
	public String toString (){
		return this.ndc;
	}
	
}
