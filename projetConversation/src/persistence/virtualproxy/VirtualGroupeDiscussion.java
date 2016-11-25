package persistence.virtualproxy;

import java.sql.SQLException;
import java.util.ArrayList;

import persistence.GroupeDiscussionMapper;
import persistence.MessageMapper;
import domaine.Utilisateur;
import domaine.messages.GroupeDiscussion;
import domaine.messages.Message;

public class VirtualGroupeDiscussion extends GroupeDiscussion {

	public VirtualGroupeDiscussion(int id, String nom,Utilisateur moderateur) {
		super(id, nom,moderateur);
		this.messages = null;
	}
	
	@Override
	public ArrayList<Message> getMessages (){
		if (messages == null){
			try {
				messages = MessageMapper.getInstance().findByIdDiscussion(this.id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return messages;
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
