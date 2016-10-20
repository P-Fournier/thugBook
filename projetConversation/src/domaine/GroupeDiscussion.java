package domaine;

import java.util.ArrayList;

public class GroupeDiscussion {
	private String nom;
	private Utilisateur moderateur;
	private ArrayList<Utilisateur> listeUser;
	private int id;
	
	public GroupeDiscussion (int id , String nom , Utilisateur moderateur){
		this.id = id;
		this.nom = nom;
		this.moderateur = moderateur;
		moderateur.getGroupeDiscussion().add(this);
		this.listeUser = new ArrayList<Utilisateur>();
		listeUser.add(moderateur);
	}
	
	public void addUser (Utilisateur u){
		if (!listeUser.contains(u)){
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
	

}
