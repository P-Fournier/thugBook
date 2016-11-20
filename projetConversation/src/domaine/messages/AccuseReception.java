package domaine.messages;

import java.util.HashMap;

import javax.swing.JLabel;

import domaine.Utilisateur;
import domaine.VisiteurOption;

public class AccuseReception extends Option {
	
	private HashMap<Utilisateur,Boolean> destinataires;
	
	public AccuseReception (HashMap<Utilisateur,Boolean> destinataires){
		this.destinataires = destinataires;
	}
	
	@Override
	public JLabel decorate(JLabel message) {
		String result = message.getText()+"     Vu par : ";
		for (Utilisateur u : destinataires.keySet()){
			if (destinataires.get(u)){
				System.out.println(u.getNdc());
				result += u.getNdc()+" ";
			}
		}
		message.setText(result);
		return message;
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
