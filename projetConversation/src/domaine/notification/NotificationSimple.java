package domaine.notification;

import java.sql.Date;
import java.sql.Time;

import domaine.Utilisateur;



public class NotificationSimple extends Notification{
	
	public NotificationSimple(String message, boolean vue, Utilisateur destinataire) {
		super(message, vue, destinataire);
	}
	
	public NotificationSimple(String message, boolean vue, int id,
			String dateEnvoie, Utilisateur destinataire) {
		super(message, vue, id, dateEnvoie, destinataire);
	}
	
	public void accepter(VisiteurNotification v) {
		v.visiter(this);
	}

}
