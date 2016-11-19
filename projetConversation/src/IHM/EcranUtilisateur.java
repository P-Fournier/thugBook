package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class EcranUtilisateur extends Ecran implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8523863168389588727L;
	
	protected Utilisateur u;
	
	private JButton amiBoutton;
	
	private JButton messageBoutton;
	
	private JButton profilBoutton;
	
	private JButton groupeBoutton;
	
	private JButton notificationBoutton;
	
	private JButton deconnexionBoutton;
	
	protected Fenetre fen;

	public EcranUtilisateur (Utilisateur u,Fenetre fen){
		
		// paramètre généraux
		this.u = u ;
		this.fen = fen;
		
		fen.changerTitre("Réseau social - Accueil");
		
		this.add(new Scrollbar());
		this.setLayout(null);
		
		//carré identité
		
		JLabel identi = new JLabel("Identité");
		
		identi.setBounds(220, 35,150, 10);
		identi.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(identi);
		
		JLabel nom = new JLabel ("Nom : "+u.getNom());
		JLabel prenom = new JLabel("Prénom : "+u.getPrenom());
		JLabel ndc = new JLabel ("Nom de compte : "+u.getNdc());
		ndc.setForeground(Color.white);
		ndc.setBounds(90, 60, 300, 30);
		nom.setForeground(Color.white);
		nom.setBounds(90, 110, 300, 30);
		prenom.setForeground(Color.white);
		prenom.setBounds(90,160,300,30);
		
		this.add(nom);
		this.add(prenom);
		this.add(ndc);
		
		// carré centre d'intéret
		
		JLabel centreInte = new JLabel ("Centres d'intérêts");
		
		centreInte.setBounds(740, 35,150, 10);
		centreInte.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(centreInte);
		
		Vector<SousCategorieCI> mesCI = new Vector<SousCategorieCI> ();
		for (SousCategorieCI sousCate : u.getListeInteret()){
			mesCI.add(sousCate);
		}
		
		JList<SousCategorieCI> listeCI = new JList<SousCategorieCI> (mesCI);
		
		listeCI.setBackground(Color.white);
		listeCI.setForeground(Fenetre.BLEU_CIEL);
		listeCI.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		ScrollPane panelCI = new ScrollPane();
		
		panelCI.add(listeCI);
		
		panelCI.setBounds(500,80,420,220);
		
		this.add(panelCI);
		
		
		
		
		// carré mon univers
		
		JLabel univers = new JLabel("Mon univers");
		
		univers.setBounds(220, 235,150, 10);
		univers.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(univers);
		
		messageBoutton = new JButton ("Mes discussions ("+u.getDiscussions().size()+")");
		messageBoutton.setBounds(60, 270, 170, 30);
		messageBoutton.setBackground(Fenetre.BLEU_CIEL);
		messageBoutton.setForeground(Color.white);
		messageBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		messageBoutton.addActionListener(this);
		this.add(messageBoutton);
		
		groupeBoutton = new JButton ("Mes groupes ("+u.getGroupeDiscussion().size()+")");
		groupeBoutton.setBounds(250, 270, 170, 30);
		groupeBoutton.setBackground(Fenetre.BLEU_CIEL);
		groupeBoutton.setForeground(Color.white);
		groupeBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		groupeBoutton.addActionListener(this);
		this.add(groupeBoutton);
		
		amiBoutton = new JButton ("Mes amis ("+u.getAmis().size()+")");
		amiBoutton.setBounds(60,310,170,30);
		amiBoutton.setBackground(Fenetre.BLEU_CIEL);
		amiBoutton.setForeground(Color.white);
		amiBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		amiBoutton.addActionListener(this);
		this.add(amiBoutton);
		
		notificationBoutton = new JButton ("Notifications ("+u.nbNotifNonVues()+")");
		notificationBoutton.setBounds(250, 310, 170, 30);
		notificationBoutton.setBackground(Fenetre.BLEU_CIEL);
		notificationBoutton.setForeground(Color.white);
		notificationBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		notificationBoutton.addActionListener(this);
		this.add(notificationBoutton);
		
		profilBoutton = new JButton ("Gérer mon profil");
		profilBoutton.setBounds(60, 350, 170, 30);
		profilBoutton.setBackground(Fenetre.BLEU_CIEL);
		profilBoutton.setForeground(Color.white);
		profilBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		profilBoutton.addActionListener(this);
		this.add(profilBoutton);
		
		deconnexionBoutton = new JButton ("Déconnexion");
		deconnexionBoutton.setBounds(250, 350, 170, 30);
		deconnexionBoutton.setBackground(Fenetre.BLEU_CIEL);
		deconnexionBoutton.setForeground(Color.white);
		deconnexionBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		deconnexionBoutton.addActionListener(this);
		this.add(deconnexionBoutton);
	}
	
	public void paintComponent (Graphics g){
		
		// remise à 0 écran
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// carré identité
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 40, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,20,200,40,50,50);
		
		// carré centre d'interet
		
		g.fillRoundRect(460,40,500,300,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(720,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,20,200,40,50,50);
		
		g.setColor(Color.white);
		
		// carré mon univers
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 240, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,220,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,220,200,40,50,50);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==deconnexionBoutton){
			fen.changerEcran(new EcranConnexion(fen));
		}
		if(e.getSource()==profilBoutton){
			ArrayList<SousCategorieCI> ci = new ArrayList<SousCategorieCI>();
			for (SousCategorieCI ssc : u.getListeInteret()){
				ci.add(ssc);
			}
			Utilisateur temp = new Utilisateur (u.getIdU(),u.getNom(),u.getPrenom(),u.getNdc());
			temp.setListeInteret(ci);
			fen.changerEcran(new EcranGestionProfil(fen,this,temp));
		}
		if(e.getSource()==amiBoutton){
			fen.changerEcran(new EcranAmi(fen,this,new JComboBox<Utilisateur>()));
		}
		if(e.getSource()==notificationBoutton){
			fen.changerEcran(new EcranNotification(fen,this));
		}
		if(e.getSource()==groupeBoutton){
			fen.changerEcran(new EcranGroupe(fen,this,null));
		}
		if(e.getSource()==messageBoutton){
			fen.changerEcran(new EcranListeDiscussions(fen,this));
		}
		
	}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}
	
	public void refresh (){
		fen.changerEcran(new EcranUtilisateur(u,fen));
	}
	

}
