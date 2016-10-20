package domaine.critere;

import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class CritereSousCategorie extends CritereCentreInteret {
	private SousCategorieCI critere ;

	public SousCategorieCI getCritere() {
		return critere;
	}

	public void setCritere(SousCategorieCI critere) {
		this.critere = critere;
	}

	public boolean evaluer(Utilisateur user) {
		return user.getListeInteret().contains(critere) ; 
	} 
	
}
