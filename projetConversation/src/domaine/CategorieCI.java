package domaine;

import java.util.ArrayList;

public class CategorieCI {
	private String nom;
	private int idCat;
	private ArrayList<SouscategorieCI> listeSousCategorie;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}

	public ArrayList<SouscategorieCI> getListeSousCategorie() {
		return listeSousCategorie;
	}

	public void setListeSousCategorie(
			ArrayList<SouscategorieCI> listeSousCategorie) {
		this.listeSousCategorie = listeSousCategorie;
	}

}
