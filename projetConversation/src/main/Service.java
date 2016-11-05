package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;
import IHM.Fenetre;

import persistence.UtilisateurMapper;
import domaine.CategorieCI;
import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class Service {

	public static JPanel connexion(String text, String password,Fenetre fen) throws ClassNotFoundException, SQLException , ConnexionException {
		if (text.equals("") || password.equals("")){
			throw new ConnexionException("Il faut renseigner tout les champs");
		}
		
		Utilisateur u = UtilisateurMapper.getInstance().connection(text, password);
		
		if (u == null){
			throw new ConnexionException("Mot de passe ou nom de compte incorrect");
		}
		if (UtilisateurMapper.getInstance().isAdministrateur(u)){
			return new EcranAdministrateur(u,fen);
		}else{
			return new EcranUtilisateur(u,fen);
		}
		
	}

	public static void deleteCI(Utilisateur u,
			HashMap<CategorieCI, ArrayList<SousCategorieCI>> suppr) {
	
		for (CategorieCI cate : suppr.keySet()){
			ArrayList<SousCategorieCI> result = u.getListeInteret().get(cate);
			result.removeAll(suppr.get(cate));
			if (result.isEmpty()){
				u.getListeInteret().remove(cate);
			}else{
				u.getListeInteret().put(cate, result);
			}
		}
	}
	
	
}
