package domaine.notification;
import java.sql.SQLException;


import domaine.Utilisateur;

public abstract class Notification implements VisitableNotification{
	
	private String message;				// message de la notification
	private boolean vue;				// indique si l'utilisateur à déjà consulté la notification
	private int id;						// id de la notification en base
	private Utilisateur destinataire;	// utilisateur visé par la notification
	private String dateEnvoie;			// date de création de la notification
	
	// CONSTRUCTEUR(S)
	
	public Notification (String message , boolean vue, Utilisateur destinataire){
		this.message = message;
		this.vue = vue;
		this.destinataire = destinataire;
	}
	
	public Notification (String message , boolean vue , int id ,String dateEnvoie,Utilisateur destinataire){
		this.message = message;
		this.vue = vue;
		this.id = id;
		this.dateEnvoie = dateEnvoie;
		this.destinataire = destinataire;
	}

	// ACCESSEUR(S)
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVue() {
		return vue;
	}

	public void setVue(boolean vue) {
		this.vue = vue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(String dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}

	public Utilisateur getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Utilisateur destinataire) {
		this.destinataire = destinataire;
	}
	
	// FONCTION(S)

	public abstract void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException;

}
