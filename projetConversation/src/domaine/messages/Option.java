package domaine.messages;

import javax.swing.JLabel;

import domaine.VisiteurOption;

public abstract class Option {
	public abstract JLabel decorate(JLabel message);
	public abstract void accepter (VisiteurOption v);
}
