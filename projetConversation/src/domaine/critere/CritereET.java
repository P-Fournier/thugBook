package domaine.critere;

import java.util.ArrayList;

import domaine.Utilisateur;

public class CritereET extends CritereCentreInteret {
	private ArrayList<CritereCentreInteret> critere ;

	/**
	 * @return the critere
	 */
	public ArrayList<CritereCentreInteret> getCritere() {
		return critere;
	}

	/**
	 * @param critere the critere to set
	 */
	
	public void setCritere(ArrayList<CritereCentreInteret> critere) {
		this.critere = critere;
	}

	
	/**
	 * @param user the user you wanna test "and" criterion on 
	 */
	public boolean evaluer(Utilisateur user) {
		for (CritereCentreInteret cr : critere) {
			if (!cr.evaluer(user)){
				return false ;
			}
		}
		return true ; 
	} 
}
