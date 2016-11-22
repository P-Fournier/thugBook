package domaine;

public class SousCategorieCI {
	
	private int idSousCategorie;
	private String nom;
	private CategorieCI categorie;

	
	public CategorieCI getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieCI categorie) {
		this.categorie = categorie;
	}

	public SousCategorieCI(Integer idCI, String nom, CategorieCI categorie) {
		this.idSousCategorie = idCI;
		this.nom = nom;
		this.categorie = categorie;
	}
	
	public SousCategorieCI (String nom, CategorieCI categorie){
		this.nom = nom;
		this.categorie = categorie;
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
	
	public String toString (){
		return this.categorie.getNom()+" - "+this.nom;
	}
	

	
}
