package domaine.critere;

import domaine.Utilisateur;

public abstract class CritereCentreInteret implements CritereRecherche{
	public abstract boolean evaluer(Utilisateur user) ; 
}
