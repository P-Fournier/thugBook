package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private JTextField nom;
	private JTextField prenom;
	private JPasswordField password;
	private JPasswordField confirmPassword;
	private HashMap<CategorieCI,ArrayList<SousCategorieCI>> ciASupprimer ;
	private Utilisateur modif;
	private int maxHeight;
	private int maxWidth;
	private JComboBox<CategorieCI> comboCate;
	private JComboBox<SousCategorieCI> comboCi; 
	private JLabel messageSauvegarde;
	private JButton bouttonSuppression;
	private JButton bouttonAjout;
	private JButton bouttonRetour ; 
	private JButton bouttonSauvegarde ; 
	
	public EcranGestionProfil(Fenetre fen, EcranUtilisateur accueil,Utilisateur modif){
		this.fen = fen;
		this.accueil=accueil;
		this.maxWidth = this.getWidth();
		this.maxHeight = 700;
		this.ciASupprimer = new HashMap<CategorieCI,ArrayList<SousCategorieCI>>();
		this.modif = modif;
		
		this.add(new Scrollbar());
		this.setLayout(null);
		
		//identité
		
		JLabel identi = new JLabel("Identité");
		
		identi.setBounds(220, 35,150, 10);
		identi.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(identi);
		
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
		nom.setBounds(280, 110, 150, 30);
		prenom.setBounds(280, 160, 150, 30);
		password.setBounds(280, 210, 150, 30);
		confirmPassword.setBounds(280, 260, 150, 30);
		
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
		
		ajoutCi.setBounds(220, 340,150, 20);
		ajoutCi.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(ajoutCi);
		
		JLabel labelComboCate = new JLabel ("Catégorie :");
		JLabel labelComboCi = new JLabel ("Centre d'interët :");
		this.comboCate = new JComboBox<CategorieCI> ();
		this.comboCi = new JComboBox<SousCategorieCI>();
		this.bouttonAjout = new JButton("Ajouter");
		
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
		bouttonAjout.setBounds(280, 470, 150, 30);
		bouttonAjout.setForeground(Color.white);
		bouttonAjout.setBackground(Fenetre.BLEU_CIEL);
		bouttonAjout.addActionListener(this);
		bouttonAjout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(labelComboCate);
		this.add(labelComboCi);
		this.add(comboCate);
		this.add(comboCi);
		this.add(bouttonAjout);

		// carré suppression centre d'intéret
		
		JLabel centreInte = new JLabel ("Centres d'intérêts");
		
		centreInte.setBounds(740, 35,150, 10);
		centreInte.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(centreInte);
		
		int y = 60;
		int x = 520;
		
		if (modif.getListeInteret().size()==0){
			JLabel vide = new JLabel ("Pas de centre d'intérêt");
			vide.setBounds(520, 60, 190, 30);
			vide.setForeground(Color.white);
			this.add(vide);
		}else{
			for (CategorieCI cate : modif.getListeInteret().keySet()){
				
				JLabel labelCate = new JLabel (cate.getNom());
				labelCate.setBounds(520, y, 150, 30);
				labelCate.setForeground(Fenetre.BLEU_CIEL);
				this.add(labelCate);
				for (SousCategorieCI sscate : modif.getListeInteret().get(cate)){
					if (modif.getListeInteret().get(cate).indexOf(sscate)%2 == 0){
						y+= 50;
						x = 520;
					}else{
						x = 710;
					}
					CheckBoxSuppression checkBoxSousCate = new CheckBoxSuppression (sscate,cate);
					checkBoxSousCate.setBounds(x, y, 190, 30);
					checkBoxSousCate.setForeground(Color.white);
					checkBoxSousCate.setBackground(Fenetre.BLEU_CIEL);
					checkBoxSousCate.addActionListener(this);
					this.add(checkBoxSousCate);
					
				}
				y+= 60;
				
			}
			this.bouttonSuppression = new JButton("Supprimer");
			bouttonSuppression.setBounds(800,y-10,150,30);
			bouttonSuppression.setForeground(Color.white);
			bouttonSuppression.setBackground(Fenetre.BLEU_CIEL);
			bouttonSuppression.addActionListener(this);
			bouttonSuppression.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			this.add(bouttonSuppression);
			
			// carre sauvegarde
			
			this.bouttonRetour = new JButton("Retour");
			bouttonRetour.setBounds(60, 550, 170, 30);;
			bouttonRetour.setForeground(Color.white);
			bouttonRetour.setBackground(Fenetre.BLEU_CIEL);
			bouttonRetour.addActionListener(this);
			bouttonRetour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			this.add(bouttonRetour);
			
			this.bouttonSauvegarde = new JButton("Sauvegarder");
			bouttonSauvegarde.setBounds(250, 550, 170, 30);
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
		g.fillRoundRect(40, 530, 400,120,50,50);
		
		// carré suppression centre d'interet
		
		int hauteurBoite = 10; 
		
		if (modif.getListeInteret().size()==0){
			hauteurBoite += 60;
		}else{
			for (CategorieCI cate : modif.getListeInteret().keySet()){
				hauteurBoite += 60+((modif.getListeInteret().get(cate).size()/2)*50)+((modif.getListeInteret().get(cate).size()%2)*50);
			}
		}
			
		g.fillRoundRect(460,40,500,hauteurBoite+50,50,50);
		
		if (hauteurBoite>maxHeight){
			maxHeight = hauteurBoite+10;
		}
		
		g.setColor(Color.white);
		g.fillRoundRect(720,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,20,200,40,50,50);
			
		g.setColor(Color.white);
				
		int y = 50;
				
		for (CategorieCI cate : modif.getListeInteret().keySet()){
			int longueurCate = 50*((modif.getListeInteret().get(cate).size()/2)+(modif.getListeInteret().get(cate).size()%2)+1);
			g.drawRoundRect(470,y,480,longueurCate,50,50);
			g.fillRoundRect(500,y+10,100,30,25,25);
			y = longueurCate+y+10; // 10 pour le décalage
		}
			
		if (y>maxHeight+10){
			maxHeight = y+10;
		}
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof CheckBoxSuppression){
			CheckBoxSuppression source = (CheckBoxSuppression) e.getSource();
			ArrayList<SousCategorieCI> sscate;
			if (source.isSelected()){
				if(ciASupprimer.containsKey(source.getCate())){
					sscate = ciASupprimer.get(source.getCate());
				}else{
					sscate = new ArrayList<SousCategorieCI>();
				}
				sscate.add(source.getCible());
				ciASupprimer.put(source.getCate(), sscate);
			}else{
				sscate = ciASupprimer.get(source.getCate());
				sscate.remove(source.getCible());
				if (sscate.isEmpty()){
					ciASupprimer.remove(source.getCate());
				}else{
					ciASupprimer.put(source.getCate(), sscate);
				}
			}
		}
		if (e.getSource()==bouttonSuppression){
			Service.deleteCI(modif.getListeInteret(),ciASupprimer);
			modif.setNom(nom.getText());
			modif.setPrenom(prenom.getText());
			refresh();
		}
		if (e.getSource()==comboCate){
			comboCi.removeAllItems();
			CategorieCI selected = (CategorieCI)comboCate.getSelectedItem();
			for (SousCategorieCI sscate : selected.getListeSousCategorie()){
				if (!modif.getListeInteret().get(selected).contains(sscate)){
					comboCi.addItem(sscate);
				}
			}
			comboCi.setSelectedItem(null);
		}
		if (e.getSource()==bouttonAjout && comboCate.getSelectedItem()!= null && comboCi.getSelectedItem() != null){
			CategorieCI categorie = (CategorieCI)comboCate.getSelectedItem();
			SousCategorieCI sousCategorie = (SousCategorieCI)comboCi.getSelectedItem();
			ArrayList<SousCategorieCI> lstCi;
			if(modif.getListeInteret().containsKey(categorie)){
				lstCi = modif.getListeInteret().get(categorie);
			}else{
				lstCi = new ArrayList<SousCategorieCI>();
			}
			lstCi.add(sousCategorie);
			modif.getListeInteret().put(categorie, lstCi);
			modif.setNom(nom.getText());
			modif.setPrenom(prenom.getText());
			refresh();
		}
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
					fen.changerEcran(new EcranUtilisateur(accueil.getU(), fen));
					fen.changerTitre("Réseau social - Accueil");
					break;
				case JOptionPane.CANCEL_OPTION:
					break;
			}
		}
	}
	
	public void refresh(){
		fen.changerEcran(new EcranGestionProfil(fen, accueil,modif));
	}

	
	
}
