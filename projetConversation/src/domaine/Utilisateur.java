package domaine;

import java.util.ArrayList;

public class Utilisateur {
	private int idU;
	private String nom;
	private String prenom;
	private String userName;
	private String passWord;
	private ArrayList<SouscategorieCI> listeInteret;

	public ArrayList<SouscategorieCI> getListeInteret() {
		return listeInteret;
	}

	public void setListeInteret(ArrayList<SouscategorieCI> listeInteret) {
		this.listeInteret = listeInteret;
	}

	public Utilisateur(int idU, String nom, String prenom, String userName,
			String passWord, ArrayList<CategorieCI> liste) {
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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
