package domaine.critere;

import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class CritereSousCategorie extends CritereSimple{
	private SousCategorieCI sousCategorie;
	@Override
	public boolean evaluer(Utilisateur u) {
		return u.getListeInteret().containsValue(sousCategorie);
	}
	
	
}
