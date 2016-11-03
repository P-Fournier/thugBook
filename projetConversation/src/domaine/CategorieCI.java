package domaine;

import java.util.ArrayList;



public class CategorieCI {
	private String nom;
	private int idCat;
	private ArrayList<SousCategorieCI> listeSousCategorie;

	public CategorieCI (int idCat , String nom){
		this.idCat = idCat;
		this.nom = nom;
	}
	
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

	public ArrayList<SousCategorieCI> getListeSousCategorie() {
		return listeSousCategorie;
	}

	public void setListeSousCategorie(
			ArrayList<SousCategorieCI> listeSousCategorie) {
		this.listeSousCategorie = listeSousCategorie;
	}

}
