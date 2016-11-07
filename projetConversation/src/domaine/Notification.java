package domaine;

public class Notification {
	private String message;
	private boolean vue;
	private int id;
	
	public Notification (String message , boolean vue){
		this.message = message ;
		this.vue = vue;
	}
	
	public Notification (String message , boolean vue , int id){
		this.message = message;
		this.vue = vue;
		this.id = id;
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
	
	

}
