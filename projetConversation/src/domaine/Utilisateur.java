package domaine;

import java.util.ArrayList;
import java.util.HashMap;

import domaine.messages.DiscussionPrive;
import domaine.messages.GroupeDiscussion;
import domaine.notification.Notification;


public class Utilisateur {
	
	private int idU;											// id en base
	private String nom;											// nom de l'utilisateur
	private String prenom;										// prenom de l'utilisateur
	private String ndc;											// nom de compte de l'utilisateur
	private String password;									// mot de passe de l'utilisateur
	protected ArrayList<SousCategorieCI> listeInteret;			// centres d'intérêt de l'utilisateur
	protected HashMap<Utilisateur,DiscussionPrive> amis;				// amis de l'utilisateur rattaché avec la discussion privé correspondante
	protected ArrayList<Utilisateur> demandeAmisRecues;			// demandes d'ami recues par l'utilisateur
	protected ArrayList<Utilisateur> demandesAmisSoumises;		// demandes d'ami soumises par l'utilisateur
	protected ArrayList<GroupeDiscussion> groupeDiscussion;		// groupes de discussion de l'utilisateur
	protected ArrayList<Notification> notifications;			// notifications de l'utilisateur


	// CONSTRUCTEUR(S)
	
	public Utilisateur(int id, String nom, String prenom, String ndc,
			String password) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.password = password;
		this.listeInteret = new ArrayList<SousCategorieCI>();
		this.amis = new HashMap<Utilisateur,DiscussionPrive>();
		this.demandeAmisRecues = new ArrayList<Utilisateur>();
		this.demandesAmisSoumises = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<Notification>();
	}
	
	public Utilisateur(String nom, String prenom, String ndc,
			String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.password = password;
		this.listeInteret = new ArrayList<SousCategorieCI>();
		this.amis = new HashMap<Utilisateur,DiscussionPrive>();
		this.demandeAmisRecues = new ArrayList<Utilisateur>();
		this.demandesAmisSoumises = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<Notification>();
	}

	// ACCESSEUR(S)
	
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

	public HashMap<Utilisateur,DiscussionPrive> getAmis() {
		return amis;
	}

	public void setAmis(HashMap<Utilisateur,DiscussionPrive> amis) {
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
	
	// FONCTION(S)
	
	public String toString (){
		return this.ndc;
	}
	
}
