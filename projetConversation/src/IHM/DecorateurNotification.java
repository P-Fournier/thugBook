package IHM;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationDiscussion;
import domaine.notification.NotificationSimple;
import domaine.notification.VisiteurNotification;

public class DecorateurNotification extends VisiteurNotification{

	private HashMap<JButton,NotificationDemandeAmi> accepterDemande;
	private HashMap<JButton,NotificationDemandeAmi> refuserDemande;
	private HashMap<JButton,NotificationDiscussion> redirectionDiscussion;
	private int hauteur ;
	private EcranNotification ecran;
	
	
	public DecorateurNotification ( HashMap<JButton,NotificationDemandeAmi> accepterDemande, HashMap<JButton,NotificationDemandeAmi> refuserDemande,
			 HashMap<JButton,NotificationDiscussion> redirectionDiscussion, int hauteur,EcranNotification ecran){
		this.accepterDemande = accepterDemande;
		this.refuserDemande = refuserDemande;
		this.redirectionDiscussion = redirectionDiscussion;
		this.hauteur = hauteur;
		this.ecran = ecran;
	}
	
	@Override
	public void visiter(NotificationSimple n) {
		hauteur += 60;
	}

	@Override
	public void visiter(NotificationDiscussion n)
			throws ClassNotFoundException, SQLException {
		JButton redirection = new JButton ("Voir discussion");
		redirection.setBounds(725, hauteur, 150, 30);
		redirection.addActionListener(ecran);
		redirection.setForeground(Color.WHITE);
		redirection.setBackground(Fenetre.BLEU_CIEL);
		redirection.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		hauteur += 60;
		ecran.add(redirection);
		redirectionDiscussion.put(redirection, n);
	}

	@Override
	public void visiter(NotificationDemandeAmi n)
			throws ClassNotFoundException, SQLException {
		if (n.isVue()){
			JLabel vue = new JLabel ("Demande traitée");
			vue.setBounds(700, hauteur, 200, 30);
			vue.setForeground(Color.WHITE);
			ecran.add(vue);
		}else{
			JButton accepter = new JButton ("Accepter");
			accepter.setBounds(650, hauteur, 100, 30);
			accepter.addActionListener(ecran);
			accepter.setForeground(Color.WHITE);
			accepter.setBackground(Fenetre.BLEU_CIEL);
			accepter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			ecran.add(accepter);
			accepterDemande.put(accepter, n);
			
			JButton refuser = new JButton ("Refuser");
			refuser.setBounds(775, hauteur, 100, 30);
			refuser.addActionListener(ecran);
			refuser.setForeground(Color.WHITE);
			refuser.setBackground(Fenetre.BLEU_CIEL);
			refuser.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			ecran.add(refuser);
			refuserDemande.put(refuser, n);
			
		}
		hauteur += 60;
	}

}
