package domaine;

public class SousCategorieCI {
	
	private int idSousCategorie;
	private String nom;

	public SousCategorieCI(Integer idCI, String nom) {
		this.idSousCategorie = idCI;
		this.nom = nom;
	}

	public int getSsCat() {
		return idSousCategorie;
	}

	public void setSsCat(int ssCat) {
		this.idSousCategorie = ssCat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdSousCategorie() {
		return idSousCategorie;
	}

	public void setIdSousCategorie(int idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}
	
	

}
