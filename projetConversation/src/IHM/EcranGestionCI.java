package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import main.Service;

import domaine.CategorieCI;
import domaine.SousCategorieCI;

public class EcranGestionCI extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1039853044810865400L;
	
	private EcranAdministrateur accueil;		// écran d'accueil
	private JButton retour;						// bouton de retour à l'écran d'accueil
	
	private JTextField nomNouvelleCategorie;	// renseigne le nom de catégorie de ci à créer	
	private JButton ajoutNouvelleCategorie;		// créé la nouvelle catégorie
	
	private JComboBox<CategorieCI> choixCategorieCreation;	//choix de la catégorie pour la nouvelle sous catégorie
	private JTextField nomNouvelleSsCategorie;				//nom de la nouvelle sous catégorie
	private JButton ajoutNouvelleSsCategorie;				// crée la nouvelle sous catégorie
	
	private JComboBox<CategorieCI> categoriesExistantes;	//liste des catégories existantes pour édition ou suppression
	private JButton supprimerCategorie;						//supprime la catégorie selectionnée
	private JButton modifierCategorie;						//modifie la catégorie selectionnée
	private JTextField nomCategorie;						// donner un nouveau nom à la catégorie selectionnée
	
	private JComboBox<SousCategorieCI> sousCategoriesExistantes;  //liste des sous catégories existantes pour édition ou suppression
	private JButton supprimerSousCategorie;		//supprimer la sous catégorie selectionnée
	private JButton modifierSousCategorie;		// modifie la sous catégorie selectionnée
	private JTextField nomSousCategorie;		// donner un nouveau nom à la sous catégorie semectionnée
	private JComboBox<CategorieCI> choixCategorieEdition;//changer la catégorie à laquelle est rattaché la sous catégorie
	
	private Vector<CategorieCI> lesCate;	// liste des catégories existantes
	private Vector<SousCategorieCI> lesSousCate;//liste des sous-catégories existantes
	
	
	public EcranGestionCI (Fenetre fen, EcranAdministrateur accueil){
		this.accueil = accueil;
		this.setLayout(null);
		fen.changerTitre("Réseau social - Gestion des centre d'intérêts");
		retour = new JButton ("Retour");
		retour.setBounds (100,40,200,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		//Carré création catégorie
		
		JLabel labelCreationCate = new JLabel("Ajout catégorie CI");
		JLabel labelNomNvlCate = new JLabel ("Nom : ");
		
		labelCreationCate.setBounds(220, 90,250, 20);
		labelCreationCate.setForeground(Fenetre.BLEU_CIEL);
		labelNomNvlCate.setForeground(Color.WHITE);
		labelNomNvlCate.setBounds(90, 150, 150, 30);
		
		nomNouvelleCategorie = new JTextField();
		nomNouvelleCategorie.setBounds(240, 150, 150,30);
		
		ajoutNouvelleCategorie = new JButton("Ajouter");
		ajoutNouvelleCategorie.setBounds (300,200,150,30);
		ajoutNouvelleCategorie.setForeground(Color.WHITE);
		ajoutNouvelleCategorie.setBackground(Fenetre.BLEU_CIEL);
		ajoutNouvelleCategorie.addActionListener(this);
		ajoutNouvelleCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(labelCreationCate);
		this.add(labelNomNvlCate);
		this.add(nomNouvelleCategorie);
		this.add(ajoutNouvelleCategorie);
		
		// Carré création sous catégorie CI
		
		JLabel labelCreationSsCate = new JLabel ("Ajout sous-catégorie CI");
		JLabel labelChoixCategorie = new JLabel ("Catégorie : ");
		JLabel labelNomSsCate = new JLabel ("Nom : ");
		
		labelCreationSsCate.setBounds(690, 40,250, 20);
		labelCreationSsCate.setForeground(Fenetre.BLEU_CIEL);
		
		labelChoixCategorie.setBounds(550, 100,150, 30);
		labelChoixCategorie.setForeground(Color.WHITE);
		
		labelNomSsCate.setBounds(550, 150,150, 30);
		labelNomSsCate.setForeground(Color.WHITE);
		
		lesCate = new Vector<CategorieCI>();
		
		try {
			for (CategorieCI cate : Service.recupererLesCategories()){
				lesCate.addElement(cate);
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		
		choixCategorieCreation = new JComboBox<CategorieCI>(lesCate);
		nomNouvelleSsCategorie = new JTextField();
		ajoutNouvelleSsCategorie = new JButton ("Ajouter");
		
		choixCategorieCreation.setSelectedItem(null);
		choixCategorieCreation.setBounds(700,100,150,30);
		
		nomNouvelleSsCategorie.setBounds(700,150,150,30);
		
		ajoutNouvelleSsCategorie.setBounds (760,200,150,30);
		ajoutNouvelleSsCategorie.setForeground(Color.WHITE);
		ajoutNouvelleSsCategorie.setBackground(Fenetre.BLEU_CIEL);
		ajoutNouvelleSsCategorie.addActionListener(this);
		ajoutNouvelleSsCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(labelNomSsCate);
		this.add(labelChoixCategorie);
		this.add(labelCreationSsCate);
		this.add(ajoutNouvelleSsCategorie);
		this.add(nomNouvelleSsCategorie);
		this.add(choixCategorieCreation);
		//Carré modification catégorie
		
		JLabel labelModifCate = new JLabel ("Edition catégorie CI");
		JLabel labelNomCategorie = new JLabel ("Nom : ");
		
		labelModifCate.setBounds(220, 280,250, 20);
		labelModifCate.setForeground(Fenetre.BLEU_CIEL);
		
		labelNomCategorie.setForeground(Color.WHITE);
		labelNomCategorie.setBounds (80, 460, 150, 30);
		
		categoriesExistantes = new JComboBox<CategorieCI> (lesCate);
		supprimerCategorie = new JButton ("Supprimer");
		modifierCategorie = new JButton("Enregistrer");
		
		categoriesExistantes.setSelectedItem(null);
		categoriesExistantes.setBounds(70,340,200,30);
		nomCategorie = new JTextField();
		
		supprimerCategorie.setBounds (300,350,150,30);
		supprimerCategorie.setForeground(Color.WHITE);
		supprimerCategorie.setBackground(Fenetre.BLEU_CIEL);
		supprimerCategorie.addActionListener(this);
		supprimerCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		modifierCategorie.setBounds (300,400,150,30);
		modifierCategorie.setForeground(Color.WHITE);
		modifierCategorie.setBackground(Fenetre.BLEU_CIEL);
		modifierCategorie.addActionListener(this);
		modifierCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		nomCategorie.setBounds(230, 460, 150, 30);
		
		this.add(labelModifCate);
		this.add(categoriesExistantes);
		this.add(supprimerCategorie);
		this.add(modifierCategorie);
		this.add(labelNomCategorie);
		this.add(nomCategorie);
		
		//Carré modification sous catégorie
		
		JLabel labelModifSousCate = new JLabel ("Edition sous-catégorie CI");
		JLabel labelCateSousCate = new JLabel ("Catégorie : ");
		JLabel labelNomSousCategorie = new JLabel ("Nom : ");
		
		labelModifSousCate.setBounds(690, 280,250, 20);
		labelModifSousCate.setForeground(Fenetre.BLEU_CIEL);
		
		labelNomSousCategorie.setForeground(Color.WHITE);
		labelNomSousCategorie.setBounds (550, 460, 150, 30);
		
		labelCateSousCate.setForeground(Color.WHITE);
		labelCateSousCate.setBounds (550, 510, 150, 30);
		
		lesSousCate = new Vector<SousCategorieCI>();
		
		try {
			for (SousCategorieCI sscate : Service.recupererLesSousCategories()){
				lesSousCate.addElement(sscate);
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		
		sousCategoriesExistantes = new JComboBox<SousCategorieCI> (lesSousCate);
		supprimerSousCategorie = new JButton ("Supprimer");
		modifierSousCategorie = new JButton("Enregistrer");
		nomSousCategorie = new JTextField();
		choixCategorieEdition = new JComboBox<CategorieCI> (lesCate);
		
		sousCategoriesExistantes.setSelectedItem(null);		
		sousCategoriesExistantes.setBounds(540,340,200,30);
				
		supprimerSousCategorie.setBounds (770,350,150,30);
		supprimerSousCategorie.setForeground(Color.WHITE);
		supprimerSousCategorie.setBackground(Fenetre.BLEU_CIEL);
		supprimerSousCategorie.addActionListener(this);
		supprimerSousCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
		modifierSousCategorie.setBounds (300,770,150,30);
		modifierSousCategorie.setForeground(Color.WHITE);
		modifierSousCategorie.setBackground(Fenetre.BLEU_CIEL);
		modifierSousCategorie.addActionListener(this);
		modifierSousCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));

		nomSousCategorie.setBounds(700, 460, 150, 30);		
		
		choixCategorieEdition.setSelectedItem(null);
		choixCategorieEdition.setBounds(700, 510, 150, 30);
		
		this.add(labelModifSousCate);
		this.add(labelCateSousCate);
		this.add(labelNomSousCategorie);
		this.add(modifierSousCategorie);
		this.add(supprimerSousCategorie);
		this.add(nomSousCategorie);
		this.add(choixCategorieEdition);
		this.add(sousCategoriesExistantes);
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		//Carré création catégorie
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40,100,450,150,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,80,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,80,250,40,50,50);
		
		// Carré création sousCatégorieCI
		
		g.fillRoundRect(510,50,450,200,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,30,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,30,250,40,50,50);
		
		// Carré modification catégorie CI
		
		g.fillRoundRect(40, 290, 450, 270, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,270,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,270,250,40,50,50);
		
		// Carré modification sous catégorie CI
		
		g.fillRoundRect(510, 290, 450, 270, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,270,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,270,250,40,50,50);
	}

	public void actionPerformed(ActionEvent event) {
		// retour à l'écran d'accueil
		if (event.getSource()==retour){
			accueil.refresh();
		}
		// ajout d'une nouvelle catégorie
		if (event.getSource()==ajoutNouvelleCategorie){
			CategorieCI nouvelleCategorie = new CategorieCI (nomNouvelleCategorie.getText());
			try {
				Service.ajouterCI(nouvelleCategorie);
				lesCate.addElement(nouvelleCategorie);
				nomNouvelleCategorie.setText(null);
			} catch (MySQLIntegrityConstraintViolationException e){
				JOptionPane.showMessageDialog(this, "Ce nom de catégorie est déjà utilisé");
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			}
		}
		// ajout d'une nouvelle sous catégorie
		if (event.getSource()==ajoutNouvelleSsCategorie){
			String nom = nomNouvelleSsCategorie.getText();
			CategorieCI cate = (CategorieCI)choixCategorieCreation.getSelectedItem();
			if (cate!= null && !nom.equals("")){
				SousCategorieCI nouvelleSousCategorie = new SousCategorieCI (nom,cate);
				try {
					Service.ajouterCI(nouvelleSousCategorie);
					lesSousCate.add(nouvelleSousCategorie);
					nomNouvelleSsCategorie.setText(null);
					choixCategorieCreation.setSelectedItem(null);
				} catch (MySQLIntegrityConstraintViolationException e){
					JOptionPane.showMessageDialog(this, "Ce nom de sous-catégorie est déjà utilisé");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Problème dans la saisie des valeurs");
			}
		}
		// suppression d'une catégorie
		if(event.getSource()==supprimerCategorie){
			CategorieCI cate = (CategorieCI)categoriesExistantes.getSelectedItem();
			if (cate != null){
				try {
					Service.supprimerCI (cate);
					lesCate.remove(cate);
					categoriesExistantes.setSelectedItem(null);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Aucune catégorie selectionnée");
			}
		}
		// suppression d'une sous catégorie
		if (event.getSource()==supprimerSousCategorie){
			SousCategorieCI sousCate = (SousCategorieCI)sousCategoriesExistantes.getSelectedItem();
			if (sousCate != null){
				try {
					Service.supprimerCI(sousCate);
					lesSousCate.remove(sousCate);
					sousCategoriesExistantes.setSelectedItem(null);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Aucune sous-catégorie selectionnée");
			}
		}
		// mise à jour d'une catégorie
		if (event.getSource()==modifierCategorie){
			String nom = nomCategorie.getText();
			CategorieCI cate = (CategorieCI)categoriesExistantes.getSelectedItem();
			if (cate != null){
				if (!cate.getNom().equals(nom)){
					cate.setNom(nom);
					try {
						Service.updateCategorieCI(cate);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(this,e.getMessage());
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(this,e.getMessage());
						e.printStackTrace();
					}
				}
			}else{
				JOptionPane.showMessageDialog(this,"Aucune catégorie selectionnée");
			}
		}
		// modification d'une sous catégorie
		if(event.getSource()==modifierSousCategorie){
			String nom = nomSousCategorie.getText();
			CategorieCI cate = (CategorieCI)choixCategorieEdition.getSelectedItem();
			SousCategorieCI sscate = (SousCategorieCI) sousCategoriesExistantes.getSelectedItem();
			if(sscate != null){
				sscate.setNom(nom);
				if (cate != null){
					sscate.setCategorie(cate);
				}
				try {
					Service.updateCategorieCI(sscate);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(this,e.getMessage());
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this,e.getMessage());
					e.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Aucune sous-catégorie selectionnée");
			}
		}
		//	choix d'une catégorie pour édition
		if (event.getSource()==this.categoriesExistantes){
			CategorieCI selected = (CategorieCI) categoriesExistantes.getSelectedItem();
			if (selected != null){
				nomCategorie.setText(selected.getNom());
			}
		}
		// choix d'une sous catégorie pour edition
		if (event.getSource()==this.sousCategoriesExistantes){
			SousCategorieCI selected = (SousCategorieCI) sousCategoriesExistantes.getSelectedItem();
			if (selected != null){
				nomSousCategorie.setText(selected.getNom());
				choixCategorieEdition.setSelectedItem(selected.getCategorie());
			}
		}
	}

}
