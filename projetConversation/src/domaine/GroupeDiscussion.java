package domaine;

import java.util.ArrayList;

import domaine.messages.Discussion;
public class GroupeDiscussion {
	private String nom;
	private Utilisateur moderateur;
	private ArrayList<Utilisateur> listeUser;
	private int id;
	private Discussion discussion ;
	
	public GroupeDiscussion (int id , String nom , Utilisateur moderateur , Discussion discussion){
		this.id = id;
		this.nom = nom;
		this.moderateur = moderateur;
		moderateur.getGroupeDiscussion().add(this);
		this.discussion = discussion;
		this.listeUser = new ArrayList<Utilisateur>();
	}
	
	public void addUser (Utilisateur u){
		if (!listeUser.contains(u)&&u!=moderateur){
			listeUser.add(u);
			u.getGroupeDiscussion().add(this);
		}
	}

	public Utilisateur getModerateur() {
		return moderateur;
	}

	public void setModerateur(Utilisateur moderateur) {
		this.moderateur = moderateur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Utilisateur> getListeUser() {
		return listeUser;
	}

	public void setListeUser(ArrayList<Utilisateur> listeUser) {
		this.listeUser = listeUser;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
	
	

}
