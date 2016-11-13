package domaine.messages;

import java.util.HashMap;

import javax.swing.JLabel;

import domaine.Utilisateur;

public class AccuseReception extends Option {
	private HashMap<Utilisateur,Boolean> destinataire;
	@Override
	public JLabel decorate(JLabel message) {
		String result = message.getText()+"     Vu par : ";
		for (Utilisateur u : destinataire.keySet()){
			if (destinataire.get(u)){
				result += u.getNdc()+" ";
			}
		}
		message.setText(result);
		return message;
	}
}
