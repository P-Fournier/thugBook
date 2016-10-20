package domaine;

import java.util.ArrayList;


public class Utilisateur {
	private int idU;
	private String nom;
	private String prenom;
	private String ndc;
	private String password;
	private ArrayList<SousCategorieCI> listeInteret;
	private ArrayList<Utilisateur> amis;
	private ArrayList<Utilisateur> demandeAmis;
	private ArrayList<GroupeDiscussion> groupeDiscussion;
	private ArrayList<String> notifications;


	public Utilisateur(int id, String nom, String prenom, String ndc,
			String password) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.password = password;
		this.listeInteret = new ArrayList<SousCategorieCI> ();
		this.amis = new ArrayList<Utilisateur>();
		this.demandeAmis = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<String>();
		 
	}

	public Utilisateur(int id, String nom, String prenom, String ndc) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.listeInteret = new ArrayList<SousCategorieCI> ();
		this.amis = new ArrayList<Utilisateur>();
		this.demandeAmis = new ArrayList<Utilisateur>();
		this.groupeDiscussion = new ArrayList<GroupeDiscussion>();
		this.notifications = new ArrayList<String>();
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

	public ArrayList<Utilisateur> getDemandeAmis() {
		return demandeAmis;
	}

	public void setDemandeAmis(ArrayList<Utilisateur> demandeAmis) {
		this.demandeAmis = demandeAmis;
	}
	
	
	
	public ArrayList<Utilisateur> getAmis() {
		return amis;
	}

	public void setAmis(ArrayList<Utilisateur> amis) {
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
	
	public ArrayList<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<String> notifications) {
		this.notifications = notifications;
	}

	public String toString (){
		String s = "Nom de compte : "+ndc;
		s += "\nNom : "+nom;
		s += "\nPrenom : "+prenom;
		s += "\nCentre d'interets : ";
		if (this.listeInteret.size() == 0){
			s += "\n=> Aucun";
		}else{
			for (SousCategorieCI scci : listeInteret){
				s += "\n=> "+scci.getNom();
			}
		}
		return s;
	}
}
