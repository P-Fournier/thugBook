package domaine.messages;

import domaine.VisiteurOption;

public class DelaiExpiration extends Option{
	
	private String dateExpiration;

	public DelaiExpiration (String dateExpiration){
		this.dateExpiration = dateExpiration;
	}

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	
}
