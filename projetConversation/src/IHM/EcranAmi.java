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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Service;

import domaine.Utilisateur;

public class EcranAmi extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4630560523226763002L;
	
	private EcranUtilisateur accueil;			// ecran d'accueil par lequel on est arrivé sur cet écran
	private JButton retour;						// boutton permettant le retour à la page d'accueil
	
	private JTextField nom;						// champs permettant la recherche 
	private JTextField prenom;					// par nom et prénom
	private JButton rechercherParNom;			// boutton permettant de rechercher les résultats correspondant à la recherche
	private JComboBox<Utilisateur> choixAjout ;	// résultat retourne dans la recherche pour selection de l'utilisateur pour envoie d'une demande d'ami
	private JButton ajout;						// boutton permettant l'envoie d'une demande d'ami suite au choix
	
	private JList<Utilisateur> demandes;		// liste de l'ensemble des demandes d'amis recues
	private JButton accepter;					// boutton permettant d'ajouté en ami l'utilisateur selection dans la liste des demandes  
	private JButton refuser;					// boutton permettant de refuser la demande d'ami
	
	private JList<Utilisateur> amis;			// liste des amis de l'utilisateur
	private JButton supprimer;					// boutton permettant de supprimer l'utilisateur choisit
	
	private Vector<Utilisateur> lesChoix;		// permet de renseigner la comboBox choixAjout
	private Vector<Utilisateur> lesDemandes;	// permet de renseigner la JList demandes
	
	public EcranAmi (Fenetre fen,EcranUtilisateur ecran) {
		
		fen.changerTitre("Réseau social - Mes amis");
		this.accueil = ecran;
		this.add(new Scrollbar());
		this.setLayout(null);
		
		// retour menu
		
		retour = new JButton ("Retour");
		
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(retour);
		
		// carré ajout ami par nom prenom
		
		
		JLabel identi = new JLabel("Recherche ami par identité");
		JLabel labelNom = new JLabel ("Nom : ");
		JLabel labelPrenom = new JLabel ("Prenom : ");
		
		nom = new JTextField();
		prenom = new JTextField();
		rechercherParNom = new JButton ("Rechercher");
		ajout = new JButton ("Ajouter");
		lesChoix = new Vector<Utilisateur>();
		choixAjout = new JComboBox<Utilisateur>(lesChoix);
		
		identi.setForeground(Fenetre.BLEU_CIEL);
		identi.setBounds(220, 110,250, 20);
		labelNom.setForeground(Color.WHITE);
		labelNom.setBounds(90, 160, 150, 30);
		labelPrenom.setForeground(Color.WHITE);
		labelPrenom.setBounds(90, 210, 150, 30);
		
		rechercherParNom.setForeground(Color.white);
		rechercherParNom.setBackground(Fenetre.BLEU_CIEL);
		rechercherParNom.addActionListener(this);
		rechercherParNom.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		ajout.setForeground(Color.white);
		ajout.setBackground(Fenetre.BLEU_CIEL);
		ajout.addActionListener(this);
		ajout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		ajout.setBounds(280, 310, 150, 30);
		
		nom.setBounds(280, 160, 150, 30);
		
		prenom.setBounds(280, 210, 150, 30);
		
		rechercherParNom.setBounds(280, 260, 150, 30);
		
		choixAjout.setBounds(90, 310, 150, 30);
		
		this.add(identi);
		this.add(labelNom);
		this.add(labelPrenom);
		this.add(nom);
		this.add(prenom);
		this.add(rechercherParNom);
		this.add(choixAjout);
		this.add(ajout);
		
		// carré recherche ci
		
		//!\	A IMPLEMENTER
		
		// carré gestion demande d'amis
		
		JLabel gestionDemandes = new JLabel("Gestion des demandes");
		
		lesDemandes = new Vector<Utilisateur>();
		for (Utilisateur u : accueil.getU().getDemandeAmisRecues()){
			lesDemandes.addElement(u);
		}
		demandes = new JList<Utilisateur>(lesDemandes);
		accepter = new JButton ("Accepter");
		refuser = new JButton("Refuser");
		
		gestionDemandes.setBounds(690, 30,250, 20);
		gestionDemandes.setForeground(Fenetre.BLEU_CIEL);
		
		demandes.setForeground(Fenetre.BLEU_CIEL);
		demandes.setBounds(530, 80, 410, 80);
		
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
		
		this.add(gestionDemandes);
		this.add(accepter);
		this.add(refuser);
		this.add(demandes);
		
		// carré amis
		
		JLabel labelMesAmis = new JLabel("Mes amis");
				
		Vector<Utilisateur> mesAmis = new Vector<Utilisateur>();
		for (Utilisateur u : accueil.getU().getAmis().keySet()){
			mesAmis.addElement(u);
		}
		amis = new JList<Utilisateur>(mesAmis);
		supprimer = new JButton("Supprimer");
		
		labelMesAmis.setBounds(690, 260,250, 20);
		labelMesAmis.setForeground(Fenetre.BLEU_CIEL);
		
		
		amis.setForeground(Fenetre.BLEU_CIEL);
		amis.setBounds(530, 310, 410, 80);
		
		supprimer.setForeground(Color.white);
		supprimer.setBackground(Fenetre.BLEU_CIEL);
		supprimer.addActionListener(this);
		supprimer.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		supprimer.setBounds(750, 410, 170, 30);
		
		this.add(labelMesAmis);
		this.add(amis);
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

	// FONCTIONNALITES DES BOUTONS 
	
	public void actionPerformed(ActionEvent e) {
		// recherche par nom
		if (rechercherParNom==e.getSource()){
			try {
				ArrayList<Utilisateur> users = Service.rechercherUtilisateurParNom(nom.getText(),prenom.getText());
				users.remove(accueil.getU());
				users.removeAll(accueil.getU().getAmis().keySet());
				users.removeAll(accueil.getU().getDemandeAmisRecues());
				users.removeAll(accueil.getU().getDemandesAmisSoumises());
				lesChoix.clear();
				for (Utilisateur u : users){
					lesChoix.addElement(u);
				}
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		// ajout d'une demande d'ami
		if (ajout==e.getSource()){
			Utilisateur dest = (Utilisateur) choixAjout.getSelectedItem();
			if (dest != null){
				try {
					Service.demandeAmi(accueil.getU(),dest);
					JOptionPane.showMessageDialog(this, "Demande d'ami envoyée");
					lesChoix.clear();
					choixAjout.setSelectedItem(null);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		// refus d'une demande d'ami
		if (refuser == e.getSource()){
			Utilisateur dest = demandes.getSelectedValue();
			if (dest != null){
				try {
					Service.refuserInvitation(accueil.getU(),dest);
					lesDemandes.remove(dest);
					demandes.setListData(lesDemandes);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		// ajout d'un ami
		if (accepter == e.getSource()){
			Utilisateur dest = demandes.getSelectedValue();
			if (dest != null){
				try {
					Service.accepterInvitation(accueil.getU(),dest);
					lesDemandes.add(dest);
					demandes.setListData(lesDemandes);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		// retour à l'écran d'accueil
		if (e.getSource() == retour){
			accueil.refresh();
		}
		// suppression d'un utilisateur
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
				Vector<Utilisateur> mesAmis = new Vector<Utilisateur>();
				for (Utilisateur u : accueil.getU().getAmis().keySet()){
					mesAmis.addElement(u);
				}
				amis.setListData(mesAmis);
			}
		}
	}
	
}
