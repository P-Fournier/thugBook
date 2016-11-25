package main;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;
import IHM.Fenetre;

import persistence.DiscussionPriveMapper;
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
import domaine.SousCategorieCI;
import domaine.Utilisateur;
import domaine.messages.Discussion;
import domaine.messages.GroupeDiscussion;
import domaine.messages.Message;
import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationDiscussion;
import domaine.notification.NotificationSimple;

public class Service {
	
	/**
	 * retourne l'écran correspondant aux identifiants passé en paramètre
	 * @param ndc	 nom de compte de l'utilisateur
	 * @param password	mot de passe de l'utilisateur
	 * @param fen		frame du programme
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConnexionException
	 */
	public static JPanel connexion(String ndc, String password, Fenetre fen)
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
	
	/**
	 * permet de récuperer l'ensemble des catégories présentes en base
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<CategorieCI> recupererLesCategories() throws ClassNotFoundException, SQLException {
		return CategorieCIMapper.getInstance().all();
	}
	
	/**
	 * permet de récupérer l'ensemble des sous catégories présente en base
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<SousCategorieCI> recupererLesSousCategories() throws ClassNotFoundException, SQLException {
		return SousCategorieCIMapper.getInstance().all();
	}

	/**
	 * permet de mettre à jour l'utilisateur passé en paramètre en base
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateProfil(Utilisateur u) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().updateProfil(u);
	}

	/**
	 * permet de mettre à jour les notification de l'utilisateur passé en paramètre
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateNotification(Utilisateur u) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().update(u);

	}

	/**
	 * permet de trouver un utilisateur via son nom et prénom
	 * @param nom
	 * @param prenom
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Utilisateur> rechercherUtilisateurParNom(String nom, String prenom)
			throws ClassNotFoundException, SQLException {
		return UtilisateurMapper.getInstance().findByNom(nom, prenom);

	}

	/**
	 * envoie d'une demande d'ami de l'utilisateur exp à l'utilisateur dest (se sera dest qui décidera d'accepter ou de
	 * refuser)
	 * @param exp
	 * @param dest
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void demandeAmi(Utilisateur exp, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationDemandeAmi(exp.getNdc()+" vous a envoyé une demande d'ami",false,dest,exp));
		DemandeAmiMapper.getInstance().demandeAmi(exp, dest);
	}

	/**
	 * refuser une demande d'ami
	 * @param u
	 * @param dest
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void refuserInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" a refusé votre demande d'ami",false,dest));
		DemandeAmiMapper.getInstance().refuserDemande(u, dest);
	}

	/**
	 * accepter une demande d'ami
	 * @param u
	 * @param dest
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void accepterInvitation(Utilisateur u, Utilisateur dest) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" a accepté votre demande d'ami",false,u));
		DemandeAmiMapper.getInstance().accepterDemande(u, dest);
	}

	/**
	 * supprime une amitié
	 * @param u
	 * @param suppr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerAmitie(Utilisateur u, Utilisateur suppr) throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (u.getNdc()+" vous a supprimer de sa liste d'ami",false,suppr));
		DiscussionPriveMapper.getInstance().suppressionAmi(u, suppr);
	}

	/**
	 * supprime l'utilisateur du groupe
	 * @param u
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerDuGroupe(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple(grp.getModerateur()+" vous a supprimé du groupe" +
				" : "+grp.getNom(),false,u));
		
		GroupeDiscussionMapper.getInstance().supprimerUtilisateur(u, grp);
	}

	/**
	 * ajoute au groupe grp l'utilisateur u
	 * @param u
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ajouterAuGroupe(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple (grp.getModerateur()+" vous a ajouté au groupe : " +
				grp.getNom(),false,u));
		GroupeDiscussionMapper.getInstance().ajouterAuGroupe(u, grp);
	}

	/**
	 * donne à l'utilisateur u le rôle de modérateur pour le groupe grp (l'ancien modérateur devient
	 * un membre simple du groupe)
	 * @param u
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void changerModerateur(Utilisateur u, GroupeDiscussion grp)
			throws ClassNotFoundException, SQLException {
		NotificationMapper.getInstance().insert(new NotificationSimple(grp.getModerateur()+" vous a nommé modérateur " +
				"du groupe : "+grp.getNom(),false,u));
		GroupeDiscussionMapper.getInstance().changerModerateur(u, grp);
	}

	/**
	 * permet de supprimer un groupe de discussion
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerGroupe(GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		DiscussionMapper.getInstance().supprimer(grp);
	}

	/**
	 * permet de créer un groupe de discussion
	 * @param nomDuGroupe
	 * @param moderateur
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static GroupeDiscussion creerGroupe(String nomDuGroupe, Utilisateur moderateur)
			throws ClassNotFoundException, SQLException {
		return GroupeDiscussionMapper.getInstance().creerGroupe(nomDuGroupe, moderateur);
	}
	
	/**
	 * test si le nom est déjà attribué à un groupe de discussion
	 * @param nom
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean existenceNomDeGroupe(String nom) throws ClassNotFoundException, SQLException {
		return GroupeDiscussionMapper.getInstance().existenceNomDeGroupe(nom);
	}

	/**
	 * permet d'obtenir les sous catégories reliées à la catégorie passé en paramètre
	 * @param selected
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<SousCategorieCI> obtenirLesSousCategories(CategorieCI selected)
			throws ClassNotFoundException, SQLException {
		return SousCategorieCIMapper.getInstance().findByCategorie(selected);
	}

	/**
	 * retourne tout les utilisateur 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Utilisateur> obtenirTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
		return UtilisateurMapper.getInstance().getAllUtilisateur();
	}

	/**
	 * permet d'envoyer le message msg dans la discussion selected
	 * @param selected
	 * @param msg
	 * @param dest
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void envoieMessage(Discussion selected, Message msg,ArrayList<Utilisateur> dest) throws ClassNotFoundException, SQLException {
		for (Utilisateur u : dest){
			NotificationMapper.getInstance().insert(new NotificationDiscussion("Vous avez reçu un message envoyé par "+msg.getExpediteur(),false,u,selected));
		}
		selected.addMessage(msg);
		MessageMapper.getInstance().insert(selected, msg);
	}

	/**
	 * note tout les messages de la discussion à vu pour l'utilisateur u
	 * @param selected
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void vuPar(Discussion selected, Utilisateur u) throws ClassNotFoundException, SQLException {
		selected.vuPar(u);
		DiscussionMapper.getInstance().vuPar(selected, u);
	}

	/**
	 * permet de supprimer l'utilisateur u
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerUtilisateur(Utilisateur u) throws ClassNotFoundException, SQLException {
		UtilisateurMapper.getInstance().delete(u);
	}

	/**
	 * permet d'ajouter une catégorie de CI
	 * @param nouvelleCategorie
	 * @throws MySQLIntegrityConstraintViolationException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ajouterCI(CategorieCI nouvelleCategorie) throws MySQLIntegrityConstraintViolationException,ClassNotFoundException, SQLException {
		CategorieCIMapper.getInstance().insert(nouvelleCategorie);
	}

	/**
	 * permet d'ajouter une sous catégorie de CI
	 * @param nouvelleSousCategorie
	 * @throws MySQLIntegrityConstraintViolationException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void ajouterCI(SousCategorieCI nouvelleSousCategorie) throws MySQLIntegrityConstraintViolationException,ClassNotFoundException, SQLException {
		SousCategorieCIMapper.getInstance().insert(nouvelleSousCategorie);
		
	}

	/**
	 * permet de supprimer une catégorie de CI
	 * @param cate
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerCI(CategorieCI cate) throws ClassNotFoundException, SQLException {
		CategorieCIMapper.getInstance().delete(cate);
		
	}

	/**
	 * permet de supprimer une sous catégorie de CI
	 * @param sousCate
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void supprimerCI(SousCategorieCI sousCate) throws ClassNotFoundException, SQLException {
		SousCategorieCIMapper.getInstance().delete(sousCate);
	}

	/**
	 * permet de mettre à jour une catégorie de CI
	 * @param cate
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateCategorieCI(CategorieCI cate) throws ClassNotFoundException, SQLException {
		CategorieCIMapper.getInstance().update(cate);
	}

	/**
	 * permet de mettre à jour une sous catégorie de CI
	 * @param sscate
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updateCategorieCI(SousCategorieCI sscate) throws ClassNotFoundException, SQLException {
		SousCategorieCIMapper.getInstance().update(sscate);
	}
	
	/**
	 * permet de créer un utilisateur
	 * @param ndc
	 * @param password
	 * @param confirmationPassword
	 * @param nom
	 * @param prenom
	 * @return
	 * @throws ConnexionException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Utilisateur creerUtilisateur(String ndc, char[] password, char[] confirmationPassword, String nom,
			String prenom) throws ConnexionException, ClassNotFoundException, SQLException {
		//	Si les champs sont vides 
		if (ndc.equals("") || password.equals("") || confirmationPassword.equals("") || nom.equals("")
				|| prenom.equals("")) {
			throw new ConnexionException("Il faut renseigner tout les champs");
		}
		String passwordClean = new String(password);
		String passwordClean2 = new String(confirmationPassword);
		if (passwordClean.equals(passwordClean2)) {
			Utilisateur u = new Utilisateur(nom, prenom, ndc, passwordClean);
			UtilisateurMapper.getInstance().insert(u);
			return u;
		}
		//	Si le mot de pas n'est pas le même que la confirmation 
		else {
			throw new ConnexionException("Le mot de passe n'est pas le même que celui dans la confirmation");
		}
	}
}