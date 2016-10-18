package domaine.messages;

import domaine.Utilisateur;

public class MessageSimple extends Message {
	private Utilisateur expediteur;
	private Utilisateur destinataire;
	private String texte;

	@Override
	public void delivrer() {
		// TODO Auto-generated method stub

	}

	public Utilisateur getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}

	public Utilisateur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

}
