package domaine.messages;


public class DelaiExpiration extends Option{
	
	private String dateExpiration;	// Date à laquelle le message relié à cette option ne sera plus visible

	// CONSTRUCTEUR(S)
	
	public DelaiExpiration (String dateExpiration){
		this.dateExpiration = dateExpiration;
	}

	// ACCESSEUR(S)
	

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

	
}
