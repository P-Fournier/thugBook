package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Service;

import domaine.Utilisateur;

public class EcranAmi extends Ecran implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4630560523226763002L;
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private JButton retour;
	private JTextField nom;
	private JTextField prenom;
	private JButton rechercherParNom;
	private JComboBox<Utilisateur> choixAjout ;
	private JButton ajout;
	private JList<Utilisateur> demandes;
	private JButton accepter;
	private JButton refuser;
	private JList<Utilisateur> amis;
	private JButton supprimer;
	
	public EcranAmi (Fenetre fen,EcranUtilisateur ecran,JComboBox<Utilisateur> choixAjout) {
		
		this.fen = fen;
		fen.changerTitre("Réseau social - Mes amis");
		this.accueil = ecran;
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
				
		identi.setBounds(220, 110,250, 20);
		identi.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(identi);
		
		JLabel labelNom = new JLabel ("Nom : ");
		JLabel labelPrenom = new JLabel ("Prenom : ");
		nom = new JTextField();
		prenom = new JTextField();
		rechercherParNom = new JButton ("Rechercher");
		ajout = new JButton ("Ajouter");
		
		labelNom.setForeground(Color.WHITE);
		labelPrenom.setForeground(Color.WHITE);
		rechercherParNom.setForeground(Color.white);
		rechercherParNom.setBackground(Fenetre.BLEU_CIEL);
		rechercherParNom.addActionListener(this);
		rechercherParNom.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		ajout.setForeground(Color.white);
		ajout.setBackground(Fenetre.BLEU_CIEL);
		ajout.addActionListener(this);
		ajout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.choixAjout = choixAjout;
		
		labelNom.setBounds(90, 160, 150, 30);
		labelPrenom.setBounds(90, 210, 150, 30);
		nom.setBounds(280, 160, 150, 30);
		prenom.setBounds(280, 210, 150, 30);
		rechercherParNom.setBounds(280, 260, 150, 30);
		choixAjout.setBounds(90, 310, 150, 30);
		ajout.setBounds(280, 310, 150, 30);
		
		this.add(labelNom);
		this.add(labelPrenom);
		this.add(nom);
		this.add(prenom);
		this.add(rechercherParNom);
		this.add(choixAjout);
		this.add(ajout);
		
		// carré recherche ci
		
		
		
		// carré gestion demande d'amis
		
		JLabel gestionDemandes = new JLabel("Gestion des demandes");
		
		gestionDemandes.setBounds(690, 30,250, 20);
		gestionDemandes.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(gestionDemandes);
		
		Vector<Utilisateur> mesDemandes = new Vector<Utilisateur>();
		for (Utilisateur u : accueil.getU().getDemandeAmisRecues()){
			mesDemandes.addElement(u);
		}
		demandes = new JList<Utilisateur>(mesDemandes);
		
		demandes.setForeground(Fenetre.BLEU_CIEL);
		demandes.setBounds(530, 80, 410, 80);
		this.add(demandes);
		
		accepter = new JButton ("Accepter");
		refuser = new JButton("Refuser");
		
		refuser.setForeground(Color.white);
		refuser.setBackground(Fenetre.BLEU_CIEL);
		refuser.addActionListener(this);
		refuser.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		refuser.setBounds(550, 180, 170, 30);
		
		accepter.setForeground(Color.white);
		accepter.setBackground(Fenetre.BLEU_CIEL);
		accepter.addActionListener(this);
		accepter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		accepter.setBounds(750, 180, 170, 30);
		
		this.add(accepter);
		this.add(refuser);
		
		// carré amis
		
		JLabel labelMesAmis = new JLabel("Mes amis");
		
		labelMesAmis.setBounds(690, 260,250, 20);
		labelMesAmis.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(labelMesAmis);
		
		Vector<Utilisateur> mesAmis = new Vector<Utilisateur>();
		for (Utilisateur u : accueil.getU().getAmis().keySet()){
			mesAmis.addElement(u);
		}
		amis = new JList<Utilisateur>(mesAmis);
		
		amis.setForeground(Fenetre.BLEU_CIEL);
		amis.setBounds(530, 310, 410, 80);
		this.add(amis);
		
		supprimer = new JButton("Supprimer");
		
		supprimer.setForeground(Color.white);
		supprimer.setBackground(Fenetre.BLEU_CIEL);
		supprimer.addActionListener(this);
		supprimer.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		supprimer.setBounds(750, 410, 170, 30);
		
		this.add(supprimer);
	}
	
	public void paintComponent (Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// carré ajout ami par nom prenom
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 120, 450,250 ,50,50);
				
		g.setColor(Color.white);
		g.fillRoundRect(200,100,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,100,250,40,50,50);
		
		g.setColor(Color.WHITE);
		
		// carré ajout ami par critère
		
		g.setColor(Fenetre.BLEU_CIEL);
		//g.fillRoundRect(40, 190+hauteurRechercheParNom, 450, 300, 50, 50);
		
		g.setColor(Color.white);
		//g.fillRoundRect(200,170+hauteurRechercheParNom,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		//g.drawRoundRect(200,170+hauteurRechercheParNom,250,40,50,50);
		
		//carré gestion demande ami
		
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(510, 40, 450, 200,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,20,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,20,250,40,50,50);
		
		//carré ami
		
		
		g.fillRoundRect(510, 270, 450, 200, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,250,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,250,250,40,50,50);
	
	}

	public void actionPerformed(ActionEvent e) {
		if (rechercherParNom==e.getSource()){
			try {
				ArrayList<Utilisateur> users = Service.rechercherUtilisateurParNom(nom.getText(),prenom.getText());
				users.remove(accueil.getU());
				users.removeAll(accueil.getU().getAmis().keySet());
				users.removeAll(accueil.getU().getDemandeAmisRecues());
				users.removeAll(accueil.getU().getDemandesAmisSoumises());
				for (Utilisateur u : users){
					choixAjout.addItem(u);
				}
				choixAjout.setSelectedItem(null);
				refresh();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (ajout==e.getSource()){
			Utilisateur dest = (Utilisateur) choixAjout.getSelectedItem();
			if (dest != null){
				try {
					Service.demandeAmi(accueil.getU(),dest);
					JOptionPane.showMessageDialog(this, "Demande d'ami envoyée");
					choixAjout = new JComboBox<Utilisateur>();
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
		if (refuser == e.getSource()){
			Utilisateur dest = demandes.getSelectedValue();
			if (dest != null){
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
		}
		if (accepter == e.getSource()){
			Utilisateur dest = demandes.getSelectedValue();
			if (dest != null){
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
		}
		if (e.getSource() == retour){
			accueil.refresh();
		}
		if (supprimer==e.getSource()){
			Utilisateur suppr = amis.getSelectedValue();
			if (suppr != null){
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
	}
	
	public void refresh (){
		fen.changerEcran(new EcranAmi(fen, accueil, choixAjout));
	}
}
