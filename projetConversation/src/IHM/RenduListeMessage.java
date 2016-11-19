package IHM;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import domaine.messages.Message;
import domaine.messages.Option;

public class RenduListeMessage implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
			Message msg = (Message) value;
			String s = msg.getContenu();
			JLabel label = new JLabel(s); // servira a l'affichage
			
			for (Option o : msg.getOptions()){
				label = o.decorate(label);
			}
			
			label.setText(msg.getExpediteur()+" ("+msg.getDateEnvoie()+") : "+label.getText()); 
			
			label.setOpaque(true);   

			return label;
	}

}
