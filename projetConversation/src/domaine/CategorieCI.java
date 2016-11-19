package domaine;

public class CategorieCI {
	private String nom;
	private int idCat;

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
	
	public String toString (){
		return this.nom;
	}

}
