package persistence.virtualproxy;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.GroupeDiscussionMapper;
import persistence.MessageMapper;
import domaine.GroupeDiscussion;
import domaine.Utilisateur;
import domaine.messages.Discussion;

public class VirtualGroupeDiscussion extends GroupeDiscussion {

	public VirtualGroupeDiscussion(int id, String nom,Utilisateur moderateur) {
		super(id, nom,moderateur);
	}
	
	@Override
	public Discussion getDiscussion (){
		if (discussion == null){
			try {
				discussion = MessageMapper.getInstance().findByIdDiscussion(this.id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return discussion;
	}
	
	@Override
	public ArrayList<Utilisateur> getListeUser(){
		if (listeUser == null){
			try {
				listeUser = GroupeDiscussionMapper.getInstance().trouverMembres(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listeUser;
	}

}
