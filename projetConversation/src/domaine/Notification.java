package domaine;

import java.sql.Time;

public class Notification {
	private String message;
	private boolean vue;
	private int id;
	private Time dateEnvoie;
	
	public Notification (String message , boolean vue , int id ,Time dateEnvoie){
		this.message = message;
		this.vue = vue;
		this.id = id;
		this.dateEnvoie = dateEnvoie;
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

	public Time getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Time dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}
	
	
	

}
