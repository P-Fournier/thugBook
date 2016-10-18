package domaine.critere;

import domaine.SouscategorieCI;
import domaine.Utilisateur;

public class CritereSousCategorie extends CritereCentreInteret {
	private SouscategorieCI critere ;

	public SouscategorieCI getCritere() {
		return critere;
	}

	public void setCritere(SouscategorieCI critere) {
		this.critere = critere;
	}

	public boolean evaluer(Utilisateur user) {
		return user.getListeInteret().contains(critere) ; 
	} 
	
}
