package domaine.notification;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import domaine.Utilisateur;

public abstract class Notification implements VisitableNotification{
	private String message;
	private boolean vue;
	private int id;
	private Utilisateur destinataire;
	private String dateEnvoie;
	
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
	
	public abstract void accepter(VisiteurNotification v) throws ClassNotFoundException, SQLException;

}
