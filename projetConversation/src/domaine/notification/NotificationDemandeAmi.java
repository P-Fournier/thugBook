package domaine.notification;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import domaine.Utilisateur;

public class NotificationDemandeAmi extends Notification{
	
	private Utilisateur demandeur;
	
	public NotificationDemandeAmi(String message, boolean vue,Utilisateur destinataire,Utilisateur demandeur) {
		super(message, vue,destinataire);
		this.demandeur = demandeur;
	}
	
	public NotificationDemandeAmi(String message, boolean vue, int id,
			String dateEnvoie,Utilisateur destinataire,Utilisateur demandeur) {
		super(message, vue, id, dateEnvoie,destinataire);
		this.demandeur = demandeur;
	}
	
	public Utilisateur getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Utilisateur demandeur) {
		this.demandeur = demandeur;
	}
	
	public void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException {
		v.visiter(this);
	}
}
