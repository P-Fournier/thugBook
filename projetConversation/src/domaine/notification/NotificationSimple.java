package domaine.notification;

import domaine.Utilisateur;



public class NotificationSimple extends Notification{
	
	// CONSTRUCTEUR(S°
	
	public NotificationSimple(String message, boolean vue, Utilisateur destinataire) {
		super(message, vue, destinataire);
	}
	
	public NotificationSimple(String message, boolean vue, int id,
			String dateEnvoie, Utilisateur destinataire) {
		super(message, vue, id, dateEnvoie, destinataire);
	}
	
	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurNotification v) {
		v.visiter(this);
	}

}
