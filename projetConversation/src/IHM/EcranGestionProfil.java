package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import domaine.CategorieCI;
import domaine.SousCategorieCI;
import domaine.Utilisateur;


public class EcranGestionProfil extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3897710674881354442L;
	
	private EcranUtilisateur accueil;		// écran d'accueil
	
	private JTextField nom;					// nom de l'utilisateur (peux être modifié)		
	private JTextField prenom;				// prenom de l'utilisateur (peux être modifié)
	private JPasswordField password;		// mot de passe l'utilisateur en édition
	private JPasswordField confirmPassword;	// confirmation du mot de passe précédent
	
	private Utilisateur modif;				// Utilisateur temporaire (avant confirmation des modifications
	
	private JComboBox<CategorieCI> comboCate;		// choix d'une catégorie CI pour ajout CI
	private JComboBox<SousCategorieCI> comboCi; 	// choix d'une sous catégorie CI pour ajout CI
	
	private JLabel messageSauvegarde;			// message d'information sur la sauvegarde des mise à jour
	private JButton bouttonSuppression;			// boutton permettant la suppresion d'un CI
	
	private JButton bouttonAjout;				// permet l'ajout d'un CI
	private JButton bouttonRetour ; 			// retour à la page d'accueil
	private JButton bouttonSauvegarde ; 		// sauvegarde les modifications
	private JList<SousCategorieCI> listCI;		// liste des CI de l'utilisateur
	
	public EcranGestionProfil(Fenetre fen, EcranUtilisateur accueil,Utilisateur modif){
		fen.changerTitre("Réseau social - Mon profil");
		this.accueil=accueil;
		this.modif = modif;
		this.setLayout(null);
		
		//identité
		
		JLabel identi = new JLabel("Identité");
		JLabel labelNom = new JLabel ("Nom : ");
		JLabel labelPrenom = new JLabel("Prénom : ");
		JLabel ndc = new JLabel ("Nom de compte : "+accueil.getU().getNdc());
		JLabel labelPassword = new JLabel ("Nouveau mot de passe : ");
		JLabel labelConfirmPassword = new JLabel ("Confirmer mot de passe : ");		
		
		nom = new JTextField(modif.getNom());
		prenom = new JTextField(modif.getPrenom());
		password = new JPasswordField();
		confirmPassword = new JPasswordField();
		
		ndc.setForeground(Color.white);
		ndc.setBounds(90, 60, 150, 30);
		
		labelNom.setForeground(Color.white);
		labelNom.setBounds(90, 110, 200, 30);
		
		labelPrenom.setForeground(Color.white);
		labelPrenom.setBounds(90,160,200,30);
		
		labelPassword.setForeground(Color.white);
		labelPassword.setBounds(90,210,200,30);
		
		labelConfirmPassword.setForeground(Color.white);
		labelConfirmPassword.setBounds(90,260,200,30);
		
		identi.setBounds(220, 35,150, 10);
		identi.setForeground(Fenetre.BLEU_CIEL);
		
		nom.setBounds(280, 110, 150, 30);
		
		prenom.setBounds(280, 160, 150, 30);
		
		password.setBounds(280, 210, 150, 30);
		
		confirmPassword.setBounds(280, 260, 150, 30);
		
		this.add(identi);
		this.add(nom);
		this.add(prenom);
		this.add(ndc);
		this.add(labelPrenom);
		this.add(labelNom);
		this.add(labelPassword);
		this.add(labelConfirmPassword);
		this.add(password);
		this.add(confirmPassword);
		
		// carré ajouter centre d'intérêt
		
		JLabel ajoutCi = new JLabel("Ajout intérêt");
		JLabel labelComboCate = new JLabel ("Catégorie :");
		JLabel labelComboCi = new JLabel ("Centre d'interët :");
		
		comboCate = new JComboBox<CategorieCI> ();
		comboCi = new JComboBox<SousCategorieCI>();
		bouttonAjout = new JButton("Ajouter");
		
		ajoutCi.setBounds(220, 340,150, 20);
		ajoutCi.setForeground(Fenetre.BLEU_CIEL);
		
		labelComboCate.setForeground(Color.white);
		labelComboCate.setBounds(90, 380, 150, 30);
		
		labelComboCi.setForeground(Color.white);
		labelComboCi.setBounds(90, 430, 150, 30);
		
		comboCate.setBounds(240, 380, 150, 30);
		try {
			for (CategorieCI cate : Service.recupererLesCategories()){
				comboCate.addItem(cate);
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		comboCate.setSelectedItem(null);
		comboCate.addActionListener(this);
		
		comboCi.setBounds(240, 430, 150, 30);
		
		bouttonAjout.setBounds(260, 470, 150, 30);
		bouttonAjout.setForeground(Color.white);
		bouttonAjout.setBackground(Fenetre.BLEU_CIEL);
		bouttonAjout.addActionListener(this);
		bouttonAjout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(ajoutCi);
		this.add(labelComboCate);
		this.add(labelComboCi);
		this.add(comboCate);
		this.add(comboCi);
		this.add(bouttonAjout);

		// carré suppression centre d'intéret
		
		JLabel centreInte = new JLabel ("Centres d'intérêts");
		
		Vector<SousCategorieCI> mesCI = new Vector<SousCategorieCI>();
		for (SousCategorieCI sscate : modif.getListeInteret()){
			mesCI.addElement(sscate);
		}
		
		listCI = new JList<SousCategorieCI> (mesCI);
		ScrollPane panelCI = new ScrollPane();
		bouttonSuppression = new JButton("Supprimer");
		
		centreInte.setBounds(740, 35,150, 10);
		centreInte.setForeground(Fenetre.BLEU_CIEL);
		
		listCI.setBackground(Color.white);
		listCI.setForeground(Fenetre.BLEU_CIEL);
		listCI.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		panelCI.add(listCI);
		panelCI.setBounds(500,80,420,160);
		
		bouttonSuppression.setBounds(780,260,150,30);
		bouttonSuppression.setForeground(Color.white);
		bouttonSuppression.setBackground(Fenetre.BLEU_CIEL);
		bouttonSuppression.addActionListener(this);
		bouttonSuppression.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(panelCI);
		this.add(centreInte);
		this.add(bouttonSuppression);
		
		// carre sauvegarde
		
		this.bouttonRetour = new JButton("Retour");
		bouttonRetour.setBounds(500, 400, 170, 30);;
		bouttonRetour.setForeground(Color.white);
		bouttonRetour.setBackground(Fenetre.BLEU_CIEL);
		bouttonRetour.addActionListener(this);
		bouttonRetour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(bouttonRetour);
		
		this.bouttonSauvegarde = new JButton("Sauvegarder");
		bouttonSauvegarde.setBounds(750, 400, 170, 30);
		bouttonSauvegarde.setForeground(Color.white);
		bouttonSauvegarde.setBackground(Fenetre.BLEU_CIEL);
		bouttonSauvegarde.addActionListener(this);
		bouttonSauvegarde.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(bouttonSauvegarde);
			
		this.messageSauvegarde = new JLabel ();
		messageSauvegarde.setBounds(90, 600, 300, 30);
		messageSauvegarde.setForeground(Color.WHITE);
		this.add(messageSauvegarde);
		
		
		
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// carré identité
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 40, 400, 280,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,20,200,40,50,50);
		
		// carré ajout centre d'interet
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 350, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,330,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,330,200,40,50,50);
		
		// carré changement d'ecran
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(460, 350, 500,120,50,50);
		
		// carré suppression centre d'interet
		
			
		g.fillRoundRect(460,40,500,280,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(720,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,20,200,40,50,50);
			
		g.setColor(Color.white);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		// supprime le CI selectionné
		if (e.getSource()==bouttonSuppression){
			SousCategorieCI scci = listCI.getSelectedValue();
			if (scci != null){
				modif.getListeInteret().remove(scci);
				modif.setNom(nom.getText());
				modif.setPrenom(prenom.getText());
			}
		}
		// renseigne les sous cate pour la catégorie selectionnée
		if (e.getSource()==comboCate){
			comboCi.removeAllItems();
			CategorieCI selected = (CategorieCI)comboCate.getSelectedItem();
			ArrayList<SousCategorieCI> sscate;
			try {
				sscate = Service.obtenirLesSousCategories(selected);
				for (SousCategorieCI s : sscate){
					if (!modif.getListeInteret().contains(s)){
						comboCi.addItem(s);
					}
				}
				comboCi.setSelectedItem(null);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		//	ajoute un CI à l'utilisateur
		if (e.getSource()==bouttonAjout && comboCate.getSelectedItem()!= null && comboCi.getSelectedItem() != null){
			SousCategorieCI sousCategorie = (SousCategorieCI)comboCi.getSelectedItem();
			modif.getListeInteret().add(sousCategorie);
			modif.setNom(nom.getText());
			modif.setPrenom(prenom.getText());
		}
		// sauvegarde les modification apportées à l'utilisateur
		if (e.getSource()==bouttonSauvegarde){
			accueil.getU().setNom(nom.getText());
			accueil.getU().setPrenom(prenom.getText());
			accueil.getU().setListeInteret(modif.getListeInteret());
			String pass = new String (password.getPassword());
			String confirmPass = new String (confirmPassword.getPassword());
			if (!pass.equals("")){
				if (pass.equals(confirmPass)){
					accueil.getU().setPassword(pass);
				}else{
					JOptionPane.showMessageDialog(this, "Les mot de passes différents modification non pris en compte");
				}
			}
			try {
				Service.updateProfil(accueil.getU());
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
			String currentTime=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE).format(new Date());
			this.messageSauvegarde.setText(currentTime+" : Sauvegarde effectuée");
			
		}
		// retour à la page d'accueil
		if (e.getSource()==bouttonRetour){
			int result = JOptionPane.showConfirmDialog(this, "Voulez-vous sauvegarder les changements ?");
			switch (result){
				case JOptionPane.YES_OPTION:
					accueil.getU().setNom(nom.getText());
					accueil.getU().setPrenom(prenom.getText());
					accueil.getU().setListeInteret(modif.getListeInteret());
					String pass = new String (password.getPassword());
					String confirmPass = new String (confirmPassword.getPassword());
					if (!pass.equals("")){
						if (pass.equals(confirmPass)){
							accueil.getU().setPassword(pass);
						}else{
							JOptionPane.showMessageDialog(this, "Les mot de passes différents modification non pris en compte");
						}
					}
					try {
						Service.updateProfil(accueil.getU());
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					}
				case JOptionPane.NO_OPTION:
					accueil.refresh();
					break;
				case JOptionPane.CANCEL_OPTION:
					break;
			}
		}
	}
	
}
