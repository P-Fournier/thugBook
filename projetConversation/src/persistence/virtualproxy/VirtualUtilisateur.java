package persistence.virtualproxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import persistence.DiscussionPriveMapper;
import persistence.DemandeAmiMapper;
import persistence.GroupeDiscussionMapper;
import persistence.NotificationMapper;
import persistence.SousCategorieCIMapper;

import domaine.SousCategorieCI;
import domaine.Utilisateur;
import domaine.messages.DiscussionPrive;
import domaine.messages.GroupeDiscussion;
import domaine.notification.Notification;

public class VirtualUtilisateur extends Utilisateur{
	
	public VirtualUtilisateur(int id, String nom, String prenom, String ndc,
			String password) {
		super(id,nom,prenom,ndc,password);
		this.listeInteret = null;
		this.amis = null;
		this.demandeAmisRecues = null;
		this.demandesAmisSoumises = null;
		this.groupeDiscussion = null;
		this.notifications = null;
	}
	
	public VirtualUtilisateur(String nom, String prenom, String ndc,
			String password) {
		super(nom,prenom,ndc,password);
		this.listeInteret = null;
		this.amis = null;
		this.demandeAmisRecues = null;
		this.demandesAmisSoumises = null;
		this.groupeDiscussion = null;
		this.notifications = null;
	}
	
	@Override
	public ArrayList<SousCategorieCI> getListeInteret(){
		if (listeInteret == null){
			try {
				listeInteret = SousCategorieCIMapper.getInstance().findByUser(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeInteret;
	}
	
	@Override
	public HashMap<Utilisateur,DiscussionPrive> getAmis(){
		if (amis == null){
			try {
				amis = DiscussionPriveMapper.getInstance().restituerAmis(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return amis;
	}
	
	@Override 
	public ArrayList<Utilisateur> getDemandeAmisRecues() {
		if (demandeAmisRecues == null){
			try {
				demandeAmisRecues =  DemandeAmiMapper.getInstance().restituerDemandesRecues(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return demandeAmisRecues;
	}
	
	@Override 
	public ArrayList<Utilisateur> getDemandesAmisSoumises() {
		if (demandesAmisSoumises == null){
			try {
				demandesAmisSoumises =  DemandeAmiMapper.getInstance().restituerDemandesSoumises(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return demandesAmisSoumises;
	}
	
	@Override
	public ArrayList<GroupeDiscussion> getGroupeDiscussion(){
		if (groupeDiscussion==null){
			try {
				groupeDiscussion = GroupeDiscussionMapper.getInstance().restituerGroupe(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return groupeDiscussion;
	}
	
	@Override
	public ArrayList<Notification> getNotifications (){
		if (notifications == null){
			try {
				notifications = NotificationMapper.getInstance().restituerNotification(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return notifications;
	}
	
}
