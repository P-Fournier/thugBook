package domaine;

public class CategorieCI {
	
	private String nom;				// nom de la catégorie
	private int idCat;				// id de la catégorie en base

	//	CONSTRUCTEUR(S)
	
	public CategorieCI (int idCat , String nom){
		this.idCat = idCat;
		this.nom = nom;
	}
	
	public CategorieCI (String nom){
		this.nom = nom;
	}
	
	//	ACCESSEUR(S)
	
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
	
	//	FONCTION(S)
	
	public String toString (){
		return this.nom;
	}

}
