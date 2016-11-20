package domaine.messages;

import java.awt.Color;

import javax.swing.JLabel;

import domaine.VisiteurOption;

public class Prioritaire extends Option{

	@Override
	public JLabel decorate(JLabel message) {
		message.setForeground(Color.RED);
		return message;
	}

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

}
