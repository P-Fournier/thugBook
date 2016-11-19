package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Service;

import domaine.GroupeDiscussion;
import domaine.Utilisateur;

public class EcranGroupe extends Ecran implements ActionListener, ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2877396567372360083L;
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private JList<GroupeDiscussion> listeGroupes;
	private JButton retour;
	private JTextField nomDuGroupe;
	private JButton créationGroupe;
	private JLabel afficheInformationDeCréation;
	private GroupeDiscussion selected;
	private JList<Utilisateur> membres;
	private JButton supprimerUtilisateur;
	private JComboBox<Utilisateur> choixAjout;
	private JComboBox<Utilisateur> choixNomine;
	private JButton ajout;
	private JButton nomination;
	private JButton quitter;
	private JButton discussion;
	
	public EcranGroupe (Fenetre fen , EcranUtilisateur accueil, GroupeDiscussion selected){
		this.fen = fen;
		this.accueil = accueil;
		this.selected = selected;
		this.setLayout(null);

		this.add(new Scrollbar());
		
		// Retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		// Création groupe
		
		JLabel creerGroupe = new JLabel ("Créer groupe");
				
		creerGroupe.setBounds(220,100,250, 20);
		creerGroupe.setForeground(Fenetre.BLEU_CIEL);
		this.add(creerGroupe);
			
		JLabel labelNomDuGroupe = new JLabel ("Nom du groupe : ");
			
		labelNomDuGroupe.setBounds(80, 150, 200, 30);
		labelNomDuGroupe.setForeground(Color.WHITE);
		this.add(labelNomDuGroupe);
			
		this.nomDuGroupe = new JTextField();
		nomDuGroupe.setBounds(240, 150, 200, 30);
		this.add(nomDuGroupe);
				
		this.créationGroupe = new JButton ("Créer le groupe");
				
		créationGroupe.setBounds(240, 250, 200, 30);
		créationGroupe.setForeground(Color.WHITE);
		créationGroupe.setBackground(Fenetre.BLEU_CIEL);
		créationGroupe.addActionListener(this);
		créationGroupe.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(créationGroupe);
			
		this.afficheInformationDeCréation = new JLabel ();
		afficheInformationDeCréation.setForeground(Color.white);
			
		afficheInformationDeCréation.setBounds(80, 300, 400, 30);
		this.add(afficheInformationDeCréation);
		
		// Mes groupes
		
		JLabel mesGroupes = new JLabel ("Mes groupes");
		
		mesGroupes.setBounds(220, 340,250, 20);
		mesGroupes.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(mesGroupes);
		
		Vector<GroupeDiscussion> groupes = new Vector<GroupeDiscussion> ();
		for (GroupeDiscussion grp : accueil.getU().getGroupeDiscussion()){
			groupes.add(grp);
		}
		
		this.listeGroupes = new JList<GroupeDiscussion> (groupes);
		
		listeGroupes.setBackground(Color.white);
		listeGroupes.setForeground(Fenetre.BLEU_CIEL);
		listeGroupes.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		listeGroupes.addListSelectionListener(this);
		ScrollPane panelGrp = new ScrollPane();
		
		panelGrp.add(listeGroupes);
		
		panelGrp.setBounds(60,390,410,130);
		
		this.add(panelGrp);
		
		// carré modifier Groupe
		
		if(selected != null){
			JLabel modifierGroupe = new JLabel ("Modifier groupe");
			
			modifierGroupe.setBounds(690, 30,250, 20);
			modifierGroupe.setForeground(Fenetre.BLEU_CIEL);
			this.add(modifierGroupe);
			
			JLabel nomGroupe = new JLabel("Nom : "+selected.getNom());
			nomGroupe.setBounds(540,80,200,30);
			nomGroupe.setForeground(Color.WHITE);
			
			this.add(nomGroupe);
			
			JLabel moderateur = new JLabel ("Modérateur : "+selected.getModerateur().getNdc());
			moderateur.setBounds(540,130,200,30);
			moderateur.setForeground(Color.WHITE);
			
			this.add(moderateur);
			
			Vector<Utilisateur> recupMembres = new Vector<Utilisateur>();
			
			for (Utilisateur u : selected.getListeUser()){
				recupMembres.addElement(u);
			}
			
			membres = new JList<Utilisateur>(recupMembres);
			
			membres.setBackground(Color.white);
			membres.setForeground(Fenetre.BLEU_CIEL);
			membres.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			membres.addListSelectionListener(this);
			ScrollPane panelMembres = new ScrollPane();
			
			panelMembres.add(membres);
			panelMembres.setBounds(540, 180, 390, 130);
			
			this.add(panelMembres);
			
			if (selected.getModerateur()==accueil.getU()){
				this.supprimerUtilisateur = new JButton ("Supprimer");
				supprimerUtilisateur.setForeground(Color.white);
				supprimerUtilisateur.setBackground(Fenetre.BLEU_CIEL);
				supprimerUtilisateur.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				supprimerUtilisateur.addActionListener(this);
				supprimerUtilisateur.setBounds(540, 340,390, 30);
				this.add(supprimerUtilisateur);
				
				choixAjout = new JComboBox<Utilisateur>();
				for (Utilisateur u : accueil.getU().getAmis().keySet()){
					if (!selected.getListeUser().contains(u)){
						choixAjout.addItem(u);
					}
				}
				choixAjout.setSelectedItem(null);
				choixAjout.addActionListener(this);
				
				choixAjout.setBounds(540, 390, 180, 30);
				
				this.add(choixAjout);
				
				ajout = new JButton ("Ajouter membre");
				ajout.addActionListener(this);
				
				ajout.setBounds(750, 390, 180, 30);
				
				ajout.setForeground(Color.white);
				ajout.setBackground(Fenetre.BLEU_CIEL);
				ajout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				this.add(ajout);
				
				choixNomine = new JComboBox<Utilisateur>();
				for (Utilisateur u : selected.getListeUser()){
					choixNomine.addItem(u);
				}
				choixNomine.setSelectedItem(null);
				choixNomine.addActionListener(this);
				
				choixNomine.setBounds(540,440,180,30);
				
				this.add(choixNomine);
				
				nomination = new JButton("Nommer modérateur");
				nomination.addActionListener(this);
				
				nomination.setForeground(Color.white);
				nomination.setBackground(Fenetre.BLEU_CIEL);
				nomination.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				nomination.setBounds(750, 440, 180, 30);
				
				this.add(nomination);
				
				discussion = new JButton ("Conversation");
				
				discussion.addActionListener(this);
				
				discussion.setForeground(Color.white);
				discussion.setBackground(Fenetre.BLEU_CIEL);
				discussion.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				discussion.setBounds(540, 490, 180, 30);
				
				this.add(discussion);
				
				quitter = new JButton ("Quitter groupe");
				
				quitter.addActionListener(this);
				
				quitter.setForeground(Color.white);
				quitter.setBackground(Fenetre.BLEU_CIEL);
				quitter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				quitter.setBounds(750, 490, 180, 30);
				
				this.add(quitter);
				
			}else{
				discussion = new JButton ("Conversation");
				
				discussion.addActionListener(this);
				
				discussion.setForeground(Color.white);
				discussion.setBackground(Fenetre.BLEU_CIEL);
				discussion.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				discussion.setBounds(540, 340, 180, 30);
				
				this.add(discussion);
				
				quitter = new JButton ("Quitter groupe");
				
				quitter.addActionListener(this);
				
				quitter.setForeground(Color.white);
				quitter.setBackground(Fenetre.BLEU_CIEL);
				quitter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				
				quitter.setBounds(750, 340, 180, 30);
				
				this.add(quitter);
			}
		}
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		// carré création groupe
				
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(40, 110, 450, 200, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,90,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,90,250,40,50,50);
		
		// Carré mes groupes 
		
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(40, 350 , 450, 200, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,330,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,330,250,40,50,50);
		
		// Carré modifier groupe
		if (selected != null){
			g.setColor(Fenetre.BLEU_CIEL);
			if (selected.getModerateur()==accueil.getU()){
				g.fillRoundRect(510, 40, 450, 500, 50, 50);
			}else{
				g.fillRoundRect(510, 40, 450, 400, 50, 50);
			}
			
			g.setColor(Color.white);
			g.fillRoundRect(670,20,250,40,50,50);
			g.setColor(Fenetre.BLEU_CIEL);
			g.drawRoundRect(670,20,250,40,50,50);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==retour){
			accueil.refresh();
		}
		if (e.getSource()==supprimerUtilisateur ){
			Utilisateur u = membres.getSelectedValue();
			if (u == null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un membre pour le supprimer");
			}else{
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer "+u.getNdc()+" du " +
					"groupe "+selected.getNom());
				switch (result){
				case JOptionPane.YES_OPTION:
					try {
						Service.supprimerDuGroupe(u,selected);
						refresh();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					}
					break;
				}
			}
		}
		if (e.getSource()==this.ajout){
			Utilisateur u = (Utilisateur)choixAjout.getSelectedItem();
			if (u == null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un ami pour l'ajouter");
			}else{
				try {
					Service.ajouterAuGroupe(u,selected);
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
		if (e.getSource()==nomination){
			Utilisateur u = (Utilisateur)choixNomine.getSelectedItem();
			if (u==null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un membre du groupe pour " +
						"le nominer en tant que modérateur");
			}else{
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir céder votre rôle de " +
						"modérateur à "+u.getNdc()+". Vous perdrez automatiquement tout vos droits " +
								"de modération.");
				switch (result){
				case JOptionPane.YES_OPTION:
					try {
						Service.changerModerateur(u,selected);
						refresh();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					}
					break;
				}
			}
		}
		if (e.getSource()==this.quitter){
			Utilisateur u = accueil.getU();
			if (u==selected.getModerateur()&&!selected.getListeUser().isEmpty()){
				JOptionPane.showMessageDialog(this, "Un modérateur ne peux quitter son groupe que si il" +
						" est le dernier membre du groupe. Cédez vos droits de modération ou soyez le" +
						" dernier membre du groupe");
			}else{
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir quitter ce groupe de discussion ?");
				switch(result){
				case JOptionPane.YES_OPTION:
					try {
						if (u==selected.getModerateur()){
							Service.supprimerGroupe(selected);
							u.getGroupeDiscussion().remove(selected);
						}else{
							Service.supprimerDuGroupe(u, selected);
						}
						selected = null;
						refresh();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
						e1.printStackTrace();
					}
					break;
				}
			}
		}
		if (e.getSource()==créationGroupe){
			if (nomDuGroupe.getText().equals("")){
				afficheInformationDeCréation.setText("Vous devez rentrer un nom pour créer le groupe");
			}else{
				try {
					if (!Service.existenceNomDeGroupe(nomDuGroupe.getText())){
						try {
							Service.creerGroupe(nomDuGroupe.getText(),accueil.getU());
							refresh();
						}catch (ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(this, e1.getMessage());
							e1.printStackTrace();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(this, e1.getMessage());
							e1.printStackTrace();
						}
					}else{ 
						afficheInformationDeCréation.setText("Ce nom de groupe existe déjà");
					}
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource()==discussion){
			fen.changerEcran(new EcranDiscussion(fen,this,selected.getDiscussion(),accueil.getU()));
		}
	}

	public void refresh() {
		fen.changerEcran(new EcranGroupe(fen, accueil,selected));		
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == listeGroupes){
			selected = listeGroupes.getSelectedValue();
			fen.changerEcran(new EcranGroupe(fen,accueil,selected));
		}
		
	}

}
