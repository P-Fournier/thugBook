package IHM;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import domaine.Notification;

public class NotificationListener implements MouseListener {
	
	Fenetre fen;
	Notification notif;
	EcranUtilisateur accueil;
	
	public NotificationListener (Fenetre fen,EcranUtilisateur accueil,Notification notif){
		this.fen = fen;
		this.accueil = accueil;
		this.notif= notif;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		notif.setVue(true);
		fen.changerEcran(new EcranNotification(fen,accueil));
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
