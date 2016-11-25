package domaine.messages;

import java.util.ArrayList;

import domaine.Utilisateur;
public class GroupeDiscussion extends Discussion{
	
	protected String nom;						// nom du groupe de discussion
	protected Utilisateur moderateur;			// utilisateur modérateur du groupe
	protected ArrayList<Utilisateur> listeUser;	// liste des membres du groupe (hors modérateur)
	
	// CONSTRUCTEUR(S)
	
	public GroupeDiscussion (int id , String nom , Utilisateur moderateur){
		super(id);
		this.nom = nom;
		this.moderateur = moderateur;
		this.listeUser = new ArrayList<Utilisateur>();
	}
	
	// ACCESSEUR(S)
	
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

	// FONCTION(S)
	
	/**
	 * ajoute un membre au groupe de discussion
	 * @param u
	 */
	public void addUser (Utilisateur u){
		if (!listeUser.contains(u)&&u!=moderateur){
			listeUser.add(u);
			u.getGroupeDiscussion().add(this);
		}
	}
	
	public String toString (){
		return this.nom;
	}


}
