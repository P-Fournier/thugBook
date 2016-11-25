package domaine.notification;

import java.sql.SQLException;

import domaine.Utilisateur;

public class NotificationDemandeAmi extends Notification{
	
	private Utilisateur demandeur;				// personne ayant fait la demande d'ami
	
	// CONSTRUCTEUR(S)
	
	public NotificationDemandeAmi(String message, boolean vue,Utilisateur destinataire,Utilisateur demandeur) {
		super(message, vue,destinataire);
		this.demandeur = demandeur;
	}
	
	public NotificationDemandeAmi(String message, boolean vue, int id,
			String dateEnvoie,Utilisateur destinataire,Utilisateur demandeur) {
		super(message, vue, id, dateEnvoie,destinataire);
		this.demandeur = demandeur;
	}
	
	// ACCESSEUR(S)
	
	public Utilisateur getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Utilisateur demandeur) {
		this.demandeur = demandeur;
	}
	
	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException {
		v.visiter(this);
	}
}
