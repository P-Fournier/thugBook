package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;
import IHM.Fenetre;

import persistence.CategorieCIMapper;
import persistence.SousCategorieCIMapper;
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

	public static void deleteCI(HashMap<CategorieCI, ArrayList<SousCategorieCI>> ci,
			HashMap<CategorieCI, ArrayList<SousCategorieCI>> suppr) {
	
		for (CategorieCI cate : suppr.keySet()){
			ArrayList<SousCategorieCI> result = ci.get(cate);
			result.removeAll(suppr.get(cate));
			if (result.isEmpty()){
				ci.remove(cate);
			}else{
				ci.put(cate, result);
			}
		}
	}

	public static ArrayList<String> recupererLesCategories() throws ClassNotFoundException, SQLException {
		return CategorieCIMapper.getInstance().allLibelle();
	}

	public static ArrayList<String> recupererLesSousCategories(String categorie) throws ClassNotFoundException, SQLException {
		return SousCategorieCIMapper.getInstance().allLibelleCategorie(categorie);
	}

	public static CategorieCI getCategorieByNomLazy(String nom) throws ClassNotFoundException, SQLException {
		return CategorieCIMapper.getInstance().findByNomLazy(nom);
	}

	public static SousCategorieCI getSousCategorieByNom(String nom) throws ClassNotFoundException, SQLException {
		return SousCategorieCIMapper.getInstance().findByNom(nom);
	}

	public static void updateProfil(Utilisateur u) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().updateProfil(u);
	}
	
	
}
