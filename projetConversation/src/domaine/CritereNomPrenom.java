package domaine;

public class CritereNomPrenom implements CritereRecherche {
	private String nom ; 
	private String prenom ; 
	
	public CritereNomPrenom(String nom , String prenom){
		this.nom = nom ; 
		this.prenom = prenom ; 
	}
	public boolean evaluer(Utilisateur user) {
		return user.getNom().equals(this.nom) && user.getPrenom().equals(this.prenom) ; 
	}
	
}
