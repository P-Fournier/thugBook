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
	private EcranAdministrateur accueil;
	private JButton retour;
	private JTextField nomNouvelleCategorie;
	private JButton ajoutNouvelleCategorie;
	private JComboBox<CategorieCI> choixCategorieCreation;
	private JTextField nomNouvelleSsCategorie;
	private JButton ajoutNouvelleSsCategorie;
	private JComboBox<CategorieCI> categoriesExistantes;
	private JButton supprimerCategorie;
	private JButton modifierCategorie;
	private JTextField nomCategorie;
	private JComboBox<SousCategorieCI> sousCategoriesExistantes;
	private JButton supprimerSousCategorie;
	private JButton modifierSousCategorie;
	private JTextField nomSousCategorie;
	private JComboBox<CategorieCI> choixCategorieEdition;
	private Vector<CategorieCI> lesCate;
	private Vector<SousCategorieCI> lesSousCate;
	
	
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
		
		labelCreationCate.setBounds(220, 90,250, 20);
		labelCreationCate.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(labelCreationCate);
		
		JLabel labelNomNvlCate = new JLabel ("Nom : ");
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
		
		
		this.add(labelNomNvlCate);
		this.add(nomNouvelleCategorie);
		this.add(ajoutNouvelleCategorie);
		
		// Carré création sous catégorie CI
		
		JLabel labelCreationSsCate = new JLabel ("Ajout sous-catégorie CI");
		
		labelCreationSsCate.setBounds(690, 40,250, 20);
		labelCreationSsCate.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(labelCreationSsCate);
		
		JLabel labelChoixCategorie = new JLabel ("Catégorie : ");
		
		labelChoixCategorie.setBounds(550, 100,150, 30);
		labelChoixCategorie.setForeground(Color.WHITE);
				
		this.add(labelChoixCategorie);
		
		JLabel labelNomSsCate = new JLabel ("Nom : ");
		
		labelNomSsCate.setBounds(550, 150,150, 30);
		labelNomSsCate.setForeground(Color.WHITE);
				
		this.add(labelNomSsCate);
		
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
		
		this.choixCategorieCreation = new JComboBox<CategorieCI>(lesCate);
		choixCategorieCreation.setSelectedItem(null);
		
		
		choixCategorieCreation.setBounds(700,100,150,30);
		this.add(choixCategorieCreation);
		
		nomNouvelleSsCategorie = new JTextField();
		nomNouvelleSsCategorie.setBounds(700,150,150,30);
		this.add(nomNouvelleSsCategorie);
		
		ajoutNouvelleSsCategorie = new JButton ("Ajouter");
		ajoutNouvelleSsCategorie.setBounds (760,200,150,30);
		ajoutNouvelleSsCategorie.setForeground(Color.WHITE);
		ajoutNouvelleSsCategorie.setBackground(Fenetre.BLEU_CIEL);
		ajoutNouvelleSsCategorie.addActionListener(this);
		ajoutNouvelleSsCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(ajoutNouvelleSsCategorie);
		
		//Carré modification catégorie
		
		JLabel labelModifCate = new JLabel ("Edition catégorie CI");
		
		labelModifCate.setBounds(220, 280,250, 20);
		labelModifCate.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(labelModifCate);
		
		categoriesExistantes = new JComboBox<CategorieCI> (lesCate);
		categoriesExistantes.setSelectedItem(null);
		categoriesExistantes.setBounds(70,340,200,30);
		
		this.add(categoriesExistantes);
		
		supprimerCategorie = new JButton ("Supprimer");
		supprimerCategorie.setBounds (300,350,150,30);
		supprimerCategorie.setForeground(Color.WHITE);
		supprimerCategorie.setBackground(Fenetre.BLEU_CIEL);
		supprimerCategorie.addActionListener(this);
		supprimerCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(supprimerCategorie);
		
		modifierCategorie = new JButton("Enregistrer");
		
		modifierCategorie.setBounds (300,400,150,30);
		modifierCategorie.setForeground(Color.WHITE);
		modifierCategorie.setBackground(Fenetre.BLEU_CIEL);
		modifierCategorie.addActionListener(this);
		modifierCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(modifierCategorie);
		
		JLabel labelNomCategorie = new JLabel ("Nom : ");
		
		labelNomCategorie.setForeground(Color.WHITE);
		labelNomCategorie.setBounds (80, 460, 150, 30);
		
		this.add(labelNomCategorie);
		
		
		nomCategorie = new JTextField();
		nomCategorie.setBounds(230, 460, 150, 30);
		
		this.add(nomCategorie);
		
		//Carré modification sous catégorie
		
		JLabel labelModifSousCate = new JLabel ("Edition sous-catégorie CI");
		
		labelModifSousCate.setBounds(690, 280,250, 20);
		labelModifSousCate.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(labelModifSousCate);
				
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
		sousCategoriesExistantes.setSelectedItem(null);		
		sousCategoriesExistantes.setBounds(540,340,200,30);
				
		this.add(sousCategoriesExistantes);
				
		supprimerSousCategorie = new JButton ("Supprimer");
		supprimerSousCategorie.setBounds (770,350,150,30);
		supprimerSousCategorie.setForeground(Color.WHITE);
		supprimerSousCategorie.setBackground(Fenetre.BLEU_CIEL);
		supprimerSousCategorie.addActionListener(this);
		supprimerSousCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
		this.add(supprimerSousCategorie);
				
		modifierSousCategorie = new JButton("Enregistrer");
				
		modifierSousCategorie.setBounds (300,770,150,30);
		modifierSousCategorie.setForeground(Color.WHITE);
		modifierSousCategorie.setBackground(Fenetre.BLEU_CIEL);
		modifierSousCategorie.addActionListener(this);
		modifierSousCategorie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
		this.add(modifierSousCategorie);
				
		JLabel labelNomSousCategorie = new JLabel ("Nom : ");
				
		labelNomSousCategorie.setForeground(Color.WHITE);
		labelNomSousCategorie.setBounds (550, 460, 150, 30);
				
		this.add(labelNomSousCategorie);
				
				
		nomSousCategorie = new JTextField();
		nomSousCategorie.setBounds(700, 460, 150, 30);
				
		this.add(nomSousCategorie);
		
		JLabel labelCateSousCate = new JLabel ("Catégorie : ");
		
		labelCateSousCate.setForeground(Color.WHITE);
		labelCateSousCate.setBounds (550, 510, 150, 30);
				
		this.add(labelCateSousCate);
		
		choixCategorieEdition = new JComboBox<CategorieCI> (lesCate);
		choixCategorieEdition.setSelectedItem(null);
		choixCategorieEdition.setBounds(700, 510, 150, 30);
		
		this.add(choixCategorieEdition);
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
		if (event.getSource()==retour){
			accueil.refresh();
		}
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
		if (event.getSource()==this.categoriesExistantes){
			CategorieCI selected = (CategorieCI) categoriesExistantes.getSelectedItem();
			if (selected != null){
				nomCategorie.setText(selected.getNom());
			}
		}
		if (event.getSource()==this.sousCategoriesExistantes){
			SousCategorieCI selected = (SousCategorieCI) sousCategoriesExistantes.getSelectedItem();
			if (selected != null){
				nomSousCategorie.setText(selected.getNom());
				choixCategorieEdition.setSelectedItem(selected.getCategorie());
			}
		}
	}

}
