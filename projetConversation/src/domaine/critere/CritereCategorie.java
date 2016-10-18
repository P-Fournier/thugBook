package domaine.critere;

import domaine.CategorieCI;
import domaine.SouscategorieCI;
import domaine.Utilisateur;

public class CritereCategorie extends CritereCentreInteret{
	private CategorieCI critere ;

	/**
	 * @return the critere
	 */
	public CategorieCI getCritere() {
		return critere;
	}

	/**
	 * @param critere the critere to set
	 */
	public void setCritere(CategorieCI critere) {
		this.critere = critere;
	}

	public boolean evaluer(Utilisateur user) {
		for (SouscategorieCI ssc : user.getListeInteret() ) {
			if(critere.getListeSousCategorie().contains(ssc)){
				return true;
			}
		}
		return false;
	} 
}
