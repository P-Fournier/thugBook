package domaine.notification;

import java.sql.SQLException;

import domaine.Utilisateur;
import domaine.messages.Discussion;

public class NotificationDiscussion extends Notification{

	private Discussion discussion;
	
	public NotificationDiscussion(String message, boolean vue, int id,
			String dateEnvoie, Utilisateur destinataire, Discussion discussion) {
		super(message, vue, id, dateEnvoie, destinataire);
		this.discussion = discussion;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}
	
	public void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException {
		v.visiter(this);
	}

}
