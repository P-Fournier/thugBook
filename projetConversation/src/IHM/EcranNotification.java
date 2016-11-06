package IHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domaine.Notification;

public class EcranNotification extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2945823019409342990L;
	
	private Fenetre fen;
	private EcranUtilisateur accueil;
	
	public EcranNotification (Fenetre fen , EcranUtilisateur accueil){
		this.fen = fen;
		this.accueil = accueil;
		int y = 50;
		for (Notification notif : accueil.getU().getNotifications()){
			JLabel labelNotif = new JLabel (notif.getMessage());
			labelNotif.setForeground(Fenetre.BLEU_CIEL);
			labelNotif.setBounds(y, 50 , 500, 30);
			this.add(labelNotif);
			y += 50;
		}
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
