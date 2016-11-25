package domaine;

public class SousCategorieCI {
	
	private int idSousCategorie;		// id en base
	private String nom;					// nom de la sous catégorie en base
	private CategorieCI categorie;		// catégorie rattaché à cette sous catégorie

	// CONSTRUCTEUR(S)
	
	public SousCategorieCI(Integer idCI, String nom, CategorieCI categorie) {
		this.idSousCategorie = idCI;
		this.nom = nom;
		this.categorie = categorie;
	}
	
	public SousCategorieCI (String nom, CategorieCI categorie){
		this.nom = nom;
		this.categorie = categorie;
	}
	
	// ACCESSEUR(S)
	
	public CategorieCI getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieCI categorie) {
		this.categorie = categorie;
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
	
	// FONCTION(S)
	
	public String toString (){
		return this.categorie.getNom()+" - "+this.nom;
	}
	

	
}
