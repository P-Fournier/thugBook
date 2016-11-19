package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Service;

import domaine.notification.Notification;
import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationDiscussion;

public class EcranNotification extends Ecran implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2945823019409342990L;
	
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private HashMap<JButton,Notification> suppr ;
	private JButton retour;
	private int maxHeight;
	private int maxWidth;
	private HashMap<JButton,NotificationDemandeAmi> accepterDemande;
	private HashMap<JButton,NotificationDemandeAmi> refuserDemande;
	private HashMap<JButton,NotificationDiscussion> redirectionDiscussion;
	
	public EcranNotification (Fenetre fen , EcranUtilisateur accueil){
		
		this.fen = fen;
		this.accueil = accueil;
		this.add(new Scrollbar());
		this.setLayout(null);
		this.suppr= new HashMap<JButton,Notification>();
		this.maxHeight = this.getHeight();
		this.maxWidth = this.getWidth();
		this.accepterDemande = new HashMap<JButton,NotificationDemandeAmi>();
		this.refuserDemande = new HashMap<JButton,NotificationDemandeAmi>();
		this.redirectionDiscussion = new HashMap<JButton,NotificationDiscussion>();
		
		fen.changerTitre("Réseau social - Mes notifications");
		
		int y = 100;
		
		DecorateurNotification decorator = new DecorateurNotification(accepterDemande , refuserDemande, redirectionDiscussion,y,this);
		
		for (final Notification notif : accueil.getU().getNotifications()){
			try {
				decorator.visiter(notif);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			}
			JLabel labelNotif = new JLabel (notif.getMessage());
			if (notif.isVue()){
				labelNotif.setForeground(Fenetre.BLEU_CIEL);
			}else{
				labelNotif.setForeground(Color.WHITE);
			}
			
			labelNotif.setBounds(100, y , 800, 30);
			labelNotif.addMouseListener(new NotificationListener(fen,accueil,notif));
			
			JButton suppression = new JButton (new ImageIcon("src/img/croix.png"));
			
			suppression.setBorder(null);
			suppression.addActionListener(this);
			suppr.put(suppression, notif);
			suppression.setBounds(900, y+10, 10,10);
			
			this.add(suppression);
			this.add(labelNotif);
			y += 60;
		}
		
		//boutton retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Fenetre.BLEU_CIEL);
		
		int y = 90;
		
		for (Notification n : accueil.getU().getNotifications()){
			if (n.isVue()){
				g.drawRoundRect(50,y , 900 , 50, 50, 50);
			}else{
				g.fillRoundRect(50,y, 900, 50, 50, 50);
			}
			
			y+= 60;
		}
		
		if (y>maxHeight+10){
			maxHeight = y+10;
		}
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
		
	}

	public void actionPerformed(ActionEvent e) {
		if (suppr.containsKey(e.getSource())){
			Notification action = suppr.get(e.getSource());
			accueil.getU().getNotifications().remove(action);
			refresh();
		}
		if (e.getSource()==retour){
			try {
				Service.updateNotification(accueil.getU());
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
			accueil.refresh();
		}
		if (accepterDemande.containsKey(e.getSource())){
			NotificationDemandeAmi n = accepterDemande.get(e.getSource());
			try {
				Service.accepterInvitation(accueil.getU(), n.getDemandeur());
				n.setVue(true);
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if(refuserDemande.containsKey(e.getSource())){
			NotificationDemandeAmi n = accepterDemande.get(e.getSource());
			try {
				Service.refuserInvitation(accueil.getU(), n.getDemandeur());
				n.setVue(true);
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	public void refresh (){
		fen.changerEcran(new EcranNotification(fen, accueil));
	}
}
