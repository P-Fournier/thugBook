package domaine;

import java.util.ArrayList;

public class GroupeDiscussion {
	private Utilisateur moderateur;
	private ArrayList<Utilisateur> listeUser;

	public Utilisateur getModerateur() {
		return moderateur;
	}

	public void setModerateur(Utilisateur moderateur) {
		this.moderateur = moderateur;
	}

	public ArrayList<Utilisateur> getListeUser() {
		return listeUser;
	}

	public void setListeUser(ArrayList<Utilisateur> listeUser) {
		this.listeUser = listeUser;
	}

}
