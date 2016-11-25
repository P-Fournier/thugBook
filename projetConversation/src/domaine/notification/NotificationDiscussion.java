package domaine.notification;

import java.sql.SQLException;

import domaine.Utilisateur;
import domaine.messages.Discussion;

public class NotificationDiscussion extends Notification{

	private Discussion discussion;			// Discussion concerné par la notification
	
	// CONSTRUCTEUR(S)
	
	public NotificationDiscussion(String message, boolean vue, int id,
			String dateEnvoie, Utilisateur destinataire, Discussion discussion) {
		super(message, vue, id, dateEnvoie, destinataire);
		this.discussion = discussion;
	}
	
	public NotificationDiscussion(String message, boolean vue,Utilisateur destinataire, Discussion discussion) {
		super(message, vue, destinataire);
		this.discussion = discussion;
	}

	// ACCESSEUR(S)
	
	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
	
	//	FONCTION(S)
	
	@Override
	public void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException {
		v.visiter(this);
	}

}
