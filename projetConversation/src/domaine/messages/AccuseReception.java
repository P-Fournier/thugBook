package domaine.messages;

import java.util.HashMap;

import domaine.Utilisateur;

public class AccuseReception extends Option {
	
	private HashMap<Utilisateur,Boolean> destinataires;	//cette HashMap sert a savoir lequels des destinataires 
	//a vu le message relié à cette option
	
	// CONSTRUCTEUR(S)
	
	public AccuseReception (HashMap<Utilisateur,Boolean> destinataires){
		this.destinataires = destinataires;
	}
	
	// ACCESSEUR(S)
	
	public HashMap<Utilisateur, Boolean> getDestinataires() {
		return destinataires;
	}

	public void setDestinataires(HashMap<Utilisateur, Boolean> destinataires) {
		this.destinataires = destinataires;
	}
	
	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

	
}
