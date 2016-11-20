package main;

import java.sql.SQLException;
import java.util.ArrayList;
import IHM.Ecran;
import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;
import IHM.Fenetre;

import persistence.AmiMapper;
import persistence.CategorieCIMapper;
import persistence.ConnexionException;
import persistence.DemandeAmiMapper;
import persistence.DiscussionMapper;
import persistence.GroupeDiscussionMapper;
import persistence.MessageMapper;
import persistence.NotificationMapper;
import persistence.SousCategorieCIMapper;
import persistence.UtilisateurMapper;
import domaine.CategorieCI;
import domaine.GroupeDiscussion;
import domaine.SousCategorieCI;
import domaine.Utilisateur;
import domaine.messages.Discussion;
import domaine.messages.Message;
import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationDiscussion;
import domaine.notification.NotificationSimple;

public class Service {

	public static Ecran connexion(String ndc, String password, Fenetre fen)
			throws ClassNotFoundException, SQLException, ConnexionException {
		if (ndc.equals("") || password.equals("")) {
			throw new ConnexionException("Il faut renseigner tout les champs");
		}

		Utilisateur u = UtilisateurMapper.getInstance().connection(ndc, password);

		if (u == null) {
			throw new ConnexionException("Mot de passe ou nom de compte incorrect");
		}
		if (UtilisateurMapper.getInstance().isAdministrateur(u)) {
			return new EcranAdministrateur(u, fen);
		} else {
			return new EcranUtilisateur(u, fen);
		}

	}

	/*
	 * public static void deleteCI(ArrayList<SousCategorieCI> arrayList,
	 * HashMap<CategorieCI, ArrayList<SousCategorieCI>> suppr) {
	 * 
	 * for (CategorieCI cate : suppr.keySet()){ ArrayList<SousCategorieCI>
	 * result = arrayList.get(cate); result.removeAll(suppr.get(cate)); if
	 * (result.isEmpty()){ arrayList.remove(cate); }else{ arrayList.put(cate,
	 * result); } } }
	 */

	public static ArrayList<CategorieCI> recupererLesCategories() throws ClassNotFoundException, SQLException {
		return CategorieCIMapper.getInstance().all();
	}

	public static void updateProfil(Utilisateur u) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().updateProfil(u);
	}

	public static void updateNotification(Utilisateur u) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().update(u);

	}

	public static ArrayList<Utilisateur> rechercherUtilisateurParNom(String nom, String prenom)
			throws ClassNotFoundException, SQLException {
		return UtilisateurMapper.getInstance().findByNom(nom, prenom);

	}

	public static void demandeAmi(Utilisateur exp, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationDemandeAmi(exp.getNdc()+" vous a envoyé une demande d'ami",false,dest,exp));
		DemandeAmiMapper.getInstance().demandeAmi(exp, dest);
	}

	public static void refuserInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" a refusé votre demande d'ami",false,dest));
		DemandeAmiMapper.getInstance().refuserDemande(u, dest);
	}

	public static void accepterInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" a accepté votre demande d'ami",false,u));
		DemandeAmiMapper.getInstance().accepterDemande(u, dest);
	}

	public static void supprimerAmitie(Utilisateur u, Utilisateur suppr) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" vous a supprimer de sa liste d'ami",false,suppr));
		AmiMapper.getInstance().suppressionAmi(u, suppr);
	}

	public static void supprimerDuGroupe(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple(grp.getModerateur()+" vous a supprimé du groupe" +
				" : "+grp.getNom(),false,u));
		
		GroupeDiscussionMapper.getInstance().supprimerUtilisateur(u, grp);
	}

	public static void ajouterAuGroupe(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (grp.getModerateur()+" vous a ajouté au groupe : " +
				grp.getNom(),false,u));
		GroupeDiscussionMapper.getInstance().ajouterAuGroupe(u, grp);
	}

	public static void changerModerateur(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple(grp.getModerateur()+" vous a nommé modérateur " +
				"du groupe : "+grp.getNom(),false,u));
		GroupeDiscussionMapper.getInstance().changerModerateur(u, grp);
	}

	public static void supprimerGroupe(GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		DiscussionMapper.getInstance().supprimer(grp.getId());
	}

	public static void creerGroupe(String nomDuGroupe, Utilisateur moderateur)
			throws ClassNotFoundException, SQLException {
		GroupeDiscussionMapper.getInstance().creerGroupe(nomDuGroupe, moderateur);
	}

	public static boolean existenceNomDeGroupe(String nom) throws ClassNotFoundException, SQLException {
		return GroupeDiscussionMapper.getInstance().existenceNomDeGroupe(nom);
	}

	public static ArrayList<SousCategorieCI> obtenirLesSousCategories(CategorieCI selected)
			throws ClassNotFoundException, SQLException {
		return SousCategorieCIMapper.getInstance().findByCategorie(selected);
	}

	public static ArrayList<Utilisateur> obtenirTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
		return UtilisateurMapper.getInstance().getAllUtilisateur();
	}

	public static void envoieMessage(Discussion selected, Message msg,ArrayList<Utilisateur> dest) throws ClassNotFoundException, SQLException {
		for (Utilisateur u : dest){
			NotificationMapper.getInstance().insert(new NotificationDiscussion("Vous avez reçu un message envoyé par "+msg.getExpediteur(),false,u,selected));
		}
		selected.addMessage(msg);
		MessageMapper.getInstance().insert(selected, msg);
	}

	public static void vuPar(Discussion selected, Utilisateur u) throws ClassNotFoundException, SQLException {
		selected.vuPar(u);
		DiscussionMapper.getInstance().vuPar(selected, u);
	}

	public static void supprimerUtilisateur(String ndc) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().delete(ndc);
	}
}