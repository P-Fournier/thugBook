package domaine.messages;

import java.util.HashMap;

import javax.swing.JLabel;

import domaine.Utilisateur;

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
				result += u.getNdc()+" ";
			}
		}
		message.setText(result);
		return message;
	}

}
