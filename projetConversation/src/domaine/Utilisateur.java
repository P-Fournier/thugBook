package domaine;

import java.util.ArrayList;


public class Utilisateur {
	private int idU;
	private String nom;
	private String prenom;
	private String ndc;
	private String password;
	private ArrayList<SousCategorieCI> listeInteret;

	public ArrayList<SousCategorieCI> getListeInteret() {
		return listeInteret;
	}

	public void setListeInteret(ArrayList<SousCategorieCI> listeInteret) {
		this.listeInteret = listeInteret;
	}

	public Utilisateur(int id, String nom, String prenom, String ndc,
			String password, ArrayList<SousCategorieCI> ci) {
		this.idU = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ndc = ndc;
		this.password = password;
		this.listeInteret = ci;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUserName() {
		return ndc;
	}

	public void setUserName(String ndc) {
		this.ndc = ndc;
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
}
