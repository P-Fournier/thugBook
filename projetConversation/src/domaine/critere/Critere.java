package domaine.critere;

import domaine.Utilisateur;

public abstract class Critere {
	public abstract boolean evaluer (Utilisateur u);
	public abstract int nbCritereSimple ();
}
