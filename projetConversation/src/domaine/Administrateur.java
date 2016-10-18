package domaine;

import java.util.ArrayList;

public class Administrateur extends Utilisateur {
	
	public Administrateur(int idU, String nom, String prenom, String userName,
			String passWord, ArrayList<CategorieCI> liste) {
		super(idU, nom, prenom, userName, passWord, liste);
	}

}
