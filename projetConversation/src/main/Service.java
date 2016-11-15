package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;
import IHM.Fenetre;

import persistence.AmiMapper;
import persistence.CategorieCIMapper;
import persistence.DemandeAmiMapper;
import persistence.DiscussionMapper;
import persistence.GroupeDiscussionMapper;
import persistence.NotificationMapper;
import persistence.UtilisateurMapper;
import domaine.CategorieCI;
import domaine.GroupeDiscussion;
import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class Service {

	public static JPanel connexion(String ndc, String password,Fenetre fen) throws ClassNotFoundException, SQLException , ConnexionException {
		if (ndc.equals("") || password.equals("")){
			throw new ConnexionException("Il faut renseigner tout les champs");
		}
		
		Utilisateur u = UtilisateurMapper.getInstance().connection(ndc, password);
		
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

	public static ArrayList<CategorieCI> recupererLesCategories() throws ClassNotFoundException, SQLException {
		return CategorieCIMapper.getInstance().all();
	}
	
	public static void updateProfil(Utilisateur u) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().updateProfil(u);
	}

	public static void updateNotification(Utilisateur u) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().update(u);
		
	}

	public static ArrayList<Utilisateur> rechercherUtilisateurParNom(
			String nom, String prenom) throws ClassNotFoundException, SQLException {
		return UtilisateurMapper.getInstance().findByNom(nom,prenom);
		
	}

	public static void demandeAmi(Utilisateur exp, Utilisateur dest) throws ClassNotFoundException, SQLException {
		DemandeAmiMapper.getInstance().demandeAmi(exp, dest);
		
	}

	public static void refuserInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		DemandeAmiMapper.getInstance().refuserDemande(u, dest);
	}

	public static void accepterInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		DemandeAmiMapper.getInstance().accepterDemande(u, dest);
	}

	public static void supprimerAmitie(Utilisateur u, Utilisateur suppr) throws ClassNotFoundException, SQLException {
		AmiMapper.getInstance().suppressionAmi(u, suppr);
	}

	public static void supprimerDuGroupe(Utilisateur u, GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		GroupeDiscussionMapper.getInstance().supprimerUtilisateur(u,grp);
	}

	public static void ajouterAuGroupe(Utilisateur u, GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		GroupeDiscussionMapper.getInstance().ajouterAuGroupe(u,grp);
	}

	public static void changerModerateur(Utilisateur u, GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		GroupeDiscussionMapper.getInstance().changerModerateur(u,grp);
	}

	public static void supprimerGroupe(GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		DiscussionMapper.supprimer(grp.getId());
	}

	public static void creerGroupe(String nomDuGroupe, Utilisateur moderateur) throws ClassNotFoundException, SQLException {
		GroupeDiscussionMapper.getInstance().creerGroupe(nomDuGroupe,moderateur);
	}

	public static boolean existenceNomDeGroupe(String nom) throws ClassNotFoundException, SQLException {
		return GroupeDiscussionMapper.getInstance().existenceNomDeGroupe(nom);
	}
	
	
}
