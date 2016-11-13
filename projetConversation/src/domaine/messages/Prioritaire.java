package domaine.messages;

import java.awt.Color;

import javax.swing.JLabel;

public class Prioritaire extends Option{

	@Override
	public JLabel decorate(JLabel message) {
		message.setBackground(Color.RED);
		return message;
	}

}
