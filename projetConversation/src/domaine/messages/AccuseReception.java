package domaine.messages;

import java.util.HashMap;

import domaine.Utilisateur;
import domaine.VisiteurOption;

public class AccuseReception extends Option {
	
	private HashMap<Utilisateur,Boolean> destinataires;
	
	public AccuseReception (HashMap<Utilisateur,Boolean> destinataires){
		this.destinataires = destinataires;
	}

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

	public HashMap<Utilisateur, Boolean> getDestinataires() {
		return destinataires;
	}

	public void setDestinataires(HashMap<Utilisateur, Boolean> destinataires) {
		this.destinataires = destinataires;
	}

	
}
