package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Service;

import domaine.Utilisateur;
import domaine.critere.Critere;
import domaine.critere.CritereSimple;

public class EcranAmi extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4630560523226763002L;
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private int maxHeight;
	private int maxWidth;
	private JButton retour;
	private JTextField nom;
	private JTextField prenom;
	private JButton rechercherParNom;
	private HashMap<JButton,Utilisateur> resultRechercheParNom;
	private HashMap<JButton,Utilisateur> refusInvitation;
	private HashMap<JButton,Utilisateur> accepteInvitation;
	private HashMap<JButton,Utilisateur> suppressionAmi;
	private HashMap<JComboBox,JComboBox> choixTypeCritere;
	private HashMap<JComboBox,CritereSimple> choixCritereSimple;
	//private HashMap<JButton,CritereOu> 
	private Critere critereDeRecherche ;
	
	
	public EcranAmi (Fenetre fen,EcranUtilisateur ecran,HashMap<JButton,Utilisateur> resultRechercheParNom) {
		
		this.fen = fen;
		fen.changerTitre("Réseau social - Mes amis");
		this.accueil = ecran;
		this.maxWidth = this.getWidth();
		this.maxHeight = this.getHeight();
		this.resultRechercheParNom = resultRechercheParNom;
		this.accepteInvitation = new HashMap<JButton,Utilisateur>();
		this.refusInvitation = new HashMap<JButton,Utilisateur>();
		this.suppressionAmi = new HashMap<JButton,Utilisateur>();
		this.add(new Scrollbar());
		this.setLayout(null);
		
		// retour menu
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		// carré ajout ami par nom prenom
		
		
		JLabel identi = new JLabel("Recherche ami par identité");
				
		identi.setBounds(220, 130,250, 20);
		identi.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(identi);
		
		JLabel labelNom = new JLabel ("Nom : ");
		JLabel labelPrenom = new JLabel ("Prenom : ");
		nom = new JTextField();
		prenom = new JTextField();
		rechercherParNom = new JButton ("Rechercher");
		
		labelNom.setForeground(Color.WHITE);
		labelPrenom.setForeground(Color.WHITE);
		rechercherParNom.setForeground(Color.white);
		rechercherParNom.setBackground(Fenetre.BLEU_CIEL);
		rechercherParNom.addActionListener(this);
		rechercherParNom.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		labelNom.setBounds(90, 180, 150, 30);
		labelPrenom.setBounds(90, 230, 150, 30);
		nom.setBounds(280, 180, 150, 30);
		prenom.setBounds(280, 230, 150, 30);
		rechercherParNom.setBounds(280, 280, 150, 30);
		
		this.add(labelNom);
		this.add(labelPrenom);
		this.add(nom);
		this.add(prenom);
		this.add(rechercherParNom);
		
		int y = 330;
		
		for (JButton boutton : resultRechercheParNom.keySet()){
			
			JLabel ndc = new JLabel (resultRechercheParNom.get(boutton).getNdc());
			ndc.setForeground(Fenetre.BLEU_CIEL);
			boutton.setForeground(Color.white);
			boutton.setBackground(Fenetre.BLEU_CIEL);
			boutton.addActionListener(this);
			boutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			
			ndc.setBounds(90, y, 150, 30);
			boutton.setBounds(280, y, 150, 30);
			
			this.add(ndc);
			this.add(boutton);
			
			y += 60;
		}
		
		// carré recherche ci
		
		
		
		// carré gestion demande d'amis
		
		JLabel gestionDemandes = new JLabel("Gestion des demandes");
		
		gestionDemandes.setBounds(690, 30,250, 20);
		gestionDemandes.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(gestionDemandes);
		
		y = 90;
		
		if (ecran.getU().getDemandeAmisRecues().isEmpty()){
			JLabel noDemande = new JLabel ("Aucune demande d'ami reçue");
			noDemande.setForeground(Color.white);
			noDemande.setBounds(560,y,400,30);
			this.add(noDemande);
			y += 60;
		}else{
			for (Utilisateur u : ecran.getU().getDemandeAmisRecues()){
				JLabel demande = new JLabel (u.getNdc());
				demande.setForeground(Fenetre.BLEU_CIEL);
				demande.setBounds(560,y,100,30);
				
				JButton accepter = new JButton ("Accepter");
				JButton refuser = new JButton ("Refuser");
				
				accepter.setForeground(Color.white);
				accepter.setBackground(Fenetre.BLEU_CIEL);
				accepter.addActionListener(this);
				accepter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				accepter.setBounds(700, y, 100 , 30);
				
				refuser.setForeground(Color.white);
				refuser.setBackground(Fenetre.BLEU_CIEL);
				refuser.addActionListener(this);
				refuser.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				refuser.setBounds(810, y, 100, 30);
				
				this.add(demande);
				this.add(refuser);
				this.add(accepter);
				refusInvitation.put(refuser, u);
				accepteInvitation.put(accepter, u);
				y += 60;
			}
		}
		
		y+= 30;
		
		// carré amis
		
		JLabel amis = new JLabel("Mes amis");
		
		amis.setBounds(690, y,250, 20);
		amis.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(amis);
		
		if (ecran.getU().getAmis().isEmpty()){
			y+= 60;
			JLabel noAmis = new JLabel ("Aucune ami");
			noAmis.setForeground(Color.white);
			noAmis.setBounds(560,y,400,30);
			this.add(noAmis);
		}else{
			for (Utilisateur u : ecran.getU().getAmis().keySet()){
				y+= 60;
				JLabel showAmi = new JLabel (u.getNdc());
				showAmi.setForeground(Fenetre.BLEU_CIEL);
				showAmi.setBounds(560,y,100,30);
				
				JButton supprimer = new JButton ("Supprimer");
				
				supprimer.setForeground(Color.white);
				supprimer.setBackground(Fenetre.BLEU_CIEL);
				supprimer.addActionListener(this);
				supprimer.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				supprimer.setBounds(760, y, 150, 30);
				
				this.add(showAmi);
				this.add(supprimer);
				suppressionAmi.put(supprimer, u);
			}
		}
		
	}
	
	public void paintComponent (Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// carré ajout ami par nom prenom
		
		int hauteurRechercheParNom = 190+(60*resultRechercheParNom.size());
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 140, 450,hauteurRechercheParNom ,50,50);
				
		g.setColor(Color.white);
		g.fillRoundRect(200,120,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,120,250,40,50,50);
		
		g.setColor(Color.WHITE);
		
		for (int i=0; i<resultRechercheParNom.size() ;i++){
			g.fillRoundRect(60, 320+(i*60), 400, 50 , 25, 25);
		}
		
		// carré ajout ami par critère
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 190+hauteurRechercheParNom, 450, 300, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,170+hauteurRechercheParNom,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,170+hauteurRechercheParNom,250,40,50,50);
		
		//carré gestion demande ami
		int hauteurDemande ;
		
		g.setColor(Fenetre.BLEU_CIEL);
		if (accueil.getU().getDemandeAmisRecues().isEmpty()){
			hauteurDemande = 110;
		}else{
			hauteurDemande = 50+(60*accueil.getU().getDemandeAmisRecues().size()); 
		}
		
		g.fillRoundRect(510, 40, 450, hauteurDemande,50,50);
		
		for (int i=0; i<accueil.getU().getDemandeAmisRecues().size() ;i++){
			g.setColor(Color.WHITE);
			g.fillRoundRect(530, 80+(i*60), 400, 50 , 25, 25);
		}
		
		g.setColor(Color.white);
		g.fillRoundRect(670,20,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,20,250,40,50,50);
		
		//carré ami
		
		int hauteurAmi ; 
		
		if (accueil.getU().getAmis().size()==0){
			hauteurAmi = 110;
		}else{
			hauteurAmi = 50+(60*accueil.getU().getAmis().size()); 
		}
		
		g.fillRoundRect(510, hauteurDemande+80, 450, hauteurAmi, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,hauteurDemande+60,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,hauteurDemande+60,250,40,50,50);
		
		for (int i=0; i<accueil.getU().getAmis().size() ;i++){
			g.setColor(Color.WHITE);
			g.fillRoundRect(530, hauteurDemande+120+(i*60), 400, 50 , 25, 25);
		}
		
		if (this.maxHeight<hauteurDemande+120+(accueil.getU().getAmis().size()*60)){
			this.maxHeight=hauteurDemande+120+(accueil.getU().getAmis().size()*60);
		}
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
	}

	public void actionPerformed(ActionEvent e) {
		if (rechercherParNom==e.getSource()){
			try {
				ArrayList<Utilisateur> users = Service.rechercherUtilisateurParNom(nom.getText(),prenom.getText());
				users.remove(accueil.getU());
				users.removeAll(accueil.getU().getAmis().keySet());
				users.removeAll(accueil.getU().getDemandeAmisRecues());
				users.removeAll(accueil.getU().getDemandesAmisSoumises());
				resultRechercheParNom = new HashMap<JButton,Utilisateur>();
				for (Utilisateur u : users){
					resultRechercheParNom.put(new JButton("Ajouter"), u);
				}
				if (resultRechercheParNom.isEmpty()){
					JOptionPane.showMessageDialog(this,"Aucun résultat");
				}
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (resultRechercheParNom.containsKey(e.getSource())){
			Utilisateur dest = resultRechercheParNom.get(e.getSource());
			try {
				Service.demandeAmi(accueil.getU(),dest);
				JOptionPane.showMessageDialog(this, "Demande d'ami envoyée");
				this.resultRechercheParNom = new HashMap<JButton,Utilisateur>();
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (refusInvitation.containsKey(e.getSource())){
			Utilisateur dest = refusInvitation.get(e.getSource());
			try {
				Service.refuserInvitation(accueil.getU(),dest);
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (accepteInvitation.containsKey(e.getSource())){
			Utilisateur dest = accepteInvitation.get(e.getSource());
			try {
				Service.accepterInvitation(accueil.getU(),dest);
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (e.getSource() == retour){
			accueil.refresh();
			fen.changerTitre("Réseau social - Accueil");
		}
		if (suppressionAmi.containsKey(e.getSource())){
			Utilisateur suppr = suppressionAmi.get(e.getSource());
			int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer "+suppr.getNdc()+" de vos amis ?");
			switch (result){
				case JOptionPane.YES_OPTION:
				try {
					Service.supprimerAmitie (accueil.getU(),suppr);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
					break;
				default:
			}
			refresh();
		}
	}
	
	public void refresh (){
		fen.changerEcran(new EcranAmi(fen, accueil, resultRechercheParNom));
	}
}
