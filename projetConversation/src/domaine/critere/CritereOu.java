package domaine.critere;

import domaine.Utilisateur;

public class CritereOu extends CritereCompose{
	
	@Override
	public boolean evaluer(Utilisateur u) {
		for (Critere c : criteres){
			if (c.evaluer(u)){
				return true;
			}
		}
		return false;
	}

}
