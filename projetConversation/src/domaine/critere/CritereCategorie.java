package domaine.critere;

import domaine.CategorieCI;
import domaine.Utilisateur;

public class CritereCategorie extends CritereSimple{
	private CategorieCI categorie;

	@Override
	public boolean evaluer(Utilisateur u) {
		return u.getListeInteret().containsKey(categorie);
	}
	
	
}
