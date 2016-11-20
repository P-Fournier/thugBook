package IHM;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import domaine.Utilisateur;
import domaine.VisiteurOption;
import domaine.messages.AccuseReception;
import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;

public class RenduListeMessage extends VisiteurOption implements ListCellRenderer {

	private JLabel current;
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
			Message msg = (Message) value;
			String s = msg.getContenu();
			current = new JLabel(s); // servira a l'affichage
			
			for (Option o : msg.getOptions()){
				accepter(o);
			}
			
			current.setText(msg.getExpediteur()+" ("+msg.getDateEnvoie()+") : "+current.getText()); 
			
			current.setOpaque(true);   

			return current;
	}

	@Override
	public void visiter(AccuseReception a) {
		String result = current.getText()+"     Vu par : ";
		for (Utilisateur u : a.getDestinataires().keySet()){
			if (a.getDestinataires().get(u)){
				System.out.println(u.getNdc());
				result += u.getNdc()+" ";
			}
		}
		current.setText(result);
	}

	@Override
	public void visiter(Chiffrement ch) {
		String aChanger = current.getText();
		String result = "";
		for (int i = 0; i < aChanger.length(); i++) {
            char c = aChanger.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            result += c;
        }
		current.setText(result);
	}


	@Override
	public void visiter(Prioritaire p) {
		current.setForeground(Color.RED);
	}

	@Override
	public void visiter(DelaiExpiration d) {
		String dateCourante=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date());
		if (dateCourante.compareTo(d.getDateExpiration())>0){
			current.setText("Délai d'expiration dépassé");
		}
	}

}
