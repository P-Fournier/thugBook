package domaine.messages;

import javax.swing.JLabel;

public class Chiffrement extends Option{

	@Override
	public JLabel decorate(JLabel message) {
		String aChanger = message.getText();
		String result = "";
		for (int i = 0; i < aChanger.length(); i++) {
            char c = aChanger.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            result += c;
        }
		message.setText(result);
		return message;
	}


}
