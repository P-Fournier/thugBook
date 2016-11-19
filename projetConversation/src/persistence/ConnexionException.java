package persistence;

public class ConnexionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2279241322846368928L;
	private String messageErreur ;
	
	public ConnexionException (String m){
		super();
		this.messageErreur = m;
	}

	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	
	
	
}
