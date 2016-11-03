package domaine;

public class Notification {
	private String message;
	private boolean vue;
	
	public Notification (String message , boolean vue){
		this.message = message ;
		this.vue = vue;
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
	
	

}
