package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import main.Service;

import domaine.Utilisateur;
import domaine.messages.GroupeDiscussion;

public class EcranGroupe extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2877396567372360083L;
	
	private EcranUtilisateur accueil;				// écran d'accueil
	private JList<GroupeDiscussion> listeGroupes;	// liste des groupes de l'utilisateur
	private JButton retour;							// permet le retour à l'écran d'accueil
	private JTextField nomDuGroupe;					// permet de donner un nom à un groupe à créer
	private JButton créationGroupe;					// permet de créer le groupe
	private JLabel afficheInformationDeCréation;	// donne des information sur la créatio du groupe
	private JList<Utilisateur> membres;				// liste les membres du groupe
	private JButton supprimerUtilisateur;			// supprime du groupe l'utilisateur selectionné (seulement si modérateur)
	private JComboBox<Utilisateur> choixAjout;		// choisir un utilisateur à ajouter au groupe (seulement si modérateur)
	private JComboBox<Utilisateur> choixNomine;		// choisir un utilisateur à nommé modérateur du groupe (seulement si modérateur)
	private JButton ajouter;						// ajoute l'utilisateur au groupe
	private JButton nomination;						// nomme l'utilisateur modérateur
	private JButton quitter;						// permet de quitter le groupe 
	private JButton discussion;						// permet de rejoindre la conversation
	private Vector<GroupeDiscussion> groupes;		// liste des groupes de l'utilisateur 
	private JLabel nomGroupe;						// label affichant le nom du groupe selectionné
	private JLabel moderateur;						// label affichant le nom du modérateur du groupe selectionné							
	private Vector<Utilisateur> recupMembres;		// liste des membres du groupe
	
	public EcranGroupe (Fenetre fen , EcranUtilisateur accueil){
		this.accueil = accueil;
		this.setLayout(null);
		fen.changerTitre("Réseau social - Mes groupes");
		
		// Retour
		
		retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(retour);
		
		// Création groupe
		
		JLabel creerGroupe = new JLabel ("Créer groupe");
		JLabel labelNomDuGroupe = new JLabel ("Nom du groupe : ");
		
		nomDuGroupe = new JTextField();
		créationGroupe = new JButton ("Créer le groupe");
		afficheInformationDeCréation = new JLabel ();
		
		creerGroupe.setBounds(220,100,250, 20);
		creerGroupe.setForeground(Fenetre.BLEU_CIEL);
			
		labelNomDuGroupe.setBounds(80, 150, 200, 30);
		labelNomDuGroupe.setForeground(Color.WHITE);
		
		nomDuGroupe.setBounds(240, 150, 200, 30);
		
		créationGroupe.setBounds(240, 250, 200, 30);
		créationGroupe.setForeground(Color.WHITE);
		créationGroupe.setBackground(Fenetre.BLEU_CIEL);
		créationGroupe.addActionListener(this);
		créationGroupe.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		afficheInformationDeCréation.setForeground(Color.white);
		afficheInformationDeCréation.setBounds(80, 300, 400, 30);
		
		this.add(creerGroupe);
		this.add(labelNomDuGroupe);
		this.add(nomDuGroupe);	
		this.add(créationGroupe);
		this.add(afficheInformationDeCréation);
		
		// Mes groupes
		
		JLabel mesGroupes = new JLabel ("Mes groupes");
		
		groupes = new Vector<GroupeDiscussion> ();
		for (GroupeDiscussion grp : accueil.getU().getGroupeDiscussion()){
			groupes.add(grp);
		}
		
		listeGroupes = new JList<GroupeDiscussion> (groupes);
		
		mesGroupes.setBounds(220, 340,250, 20);
		mesGroupes.setForeground(Fenetre.BLEU_CIEL);
		
		listeGroupes.setBackground(Color.white);
		listeGroupes.setForeground(Fenetre.BLEU_CIEL);
		listeGroupes.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		listeGroupes.addListSelectionListener(this);
		
		this.add(mesGroupes);
		
		ScrollPane panelGrp = new ScrollPane();
		
		panelGrp.add(listeGroupes);
		
		panelGrp.setBounds(60,390,410,130);
		
		this.add(panelGrp);
		
		// carré modifier Groupe
		
		JLabel modifierGroupe = new JLabel ("Modifier groupe");
			
		nomGroupe = new JLabel("Nom : ");
		moderateur = new JLabel ("Modérateur : ");
		recupMembres = new Vector<Utilisateur>();
		membres = new JList<Utilisateur>(recupMembres);
		supprimerUtilisateur = new JButton ("Supprimer");
		choixAjout = new JComboBox<Utilisateur>();
		ajouter = new JButton ("Ajouter membre");
		choixNomine = new JComboBox<Utilisateur>();
		nomination = new JButton("Nommer modérateur");
		discussion = new JButton ("Conversation");
		quitter = new JButton ("Quitter groupe");
		
		modifierGroupe.setBounds(690, 30,250, 20);
		modifierGroupe.setForeground(Fenetre.BLEU_CIEL);
			
		nomGroupe.setBounds(540,80,200,30);
		nomGroupe.setForeground(Color.WHITE);
		
		moderateur.setBounds(540,130,200,30);
		moderateur.setForeground(Color.WHITE);
		
		membres.setBackground(Color.white);
		membres.setForeground(Fenetre.BLEU_CIEL);
		membres.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		membres.addListSelectionListener(this);
		ScrollPane panelMembres = new ScrollPane();
			
		panelMembres.add(membres);
		panelMembres.setBounds(540, 180, 390, 130);
		
		supprimerUtilisateur.setForeground(Color.white);
		supprimerUtilisateur.setBackground(Fenetre.BLEU_CIEL);
		supprimerUtilisateur.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		supprimerUtilisateur.addActionListener(this);
		supprimerUtilisateur.setBounds(540, 340,390, 30);
		supprimerUtilisateur.setEnabled(false);
		
		choixAjout.addActionListener(this);
		choixAjout.setEnabled(false);		
		choixAjout.setBounds(540, 390, 180, 30);
		
		ajouter.addActionListener(this);
		ajouter.setEnabled(false);		
		ajouter.setBounds(750, 390, 180, 30);		
		ajouter.setForeground(Color.white);
		ajouter.setBackground(Fenetre.BLEU_CIEL);
		ajouter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		choixNomine.setSelectedItem(null);
		choixNomine.addActionListener(this);
		choixNomine.setEnabled(false);		
		choixNomine.setBounds(540,440,180,30);
		
		nomination.addActionListener(this);
		nomination.setEnabled(false);		
		nomination.setForeground(Color.white);
		nomination.setBackground(Fenetre.BLEU_CIEL);
		nomination.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		nomination.setBounds(750, 440, 180, 30);
				
		discussion.addActionListener(this);		
		discussion.setForeground(Color.white);
		discussion.setBackground(Fenetre.BLEU_CIEL);
		discussion.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		discussion.setBounds(540, 490, 180, 30);
				
		quitter.addActionListener(this);
		quitter.setForeground(Color.white);
		quitter.setBackground(Fenetre.BLEU_CIEL);
		quitter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));		
		quitter.setBounds(750, 490, 180, 30);
		
		this.add(modifierGroupe);
		this.add(nomGroupe);	
		this.add(moderateur);
		this.add(panelMembres);
		this.add(supprimerUtilisateur);				
		this.add(choixAjout);		
		this.add(ajouter);		
		this.add(choixNomine);		
		this.add(nomination);				
		this.add(discussion);
		this.add(quitter);
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
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(510, 40, 450, 500, 50, 50);
		
			
		g.setColor(Color.white);
		g.fillRoundRect(670,20,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,20,250,40,50,50);
	}

	public void actionPerformed(ActionEvent e) {
		// Retour à l'écran d'accueil
		if (e.getSource()==retour){
			accueil.refresh();
		}
		// supprimer le membre selectionné
		if (e.getSource()==supprimerUtilisateur ){
			Utilisateur u = membres.getSelectedValue();
			if (u == null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un membre pour le supprimer");
			}else{
				GroupeDiscussion selected = listeGroupes.getSelectedValue();
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer "+u.getNdc()+" du " +
					"groupe "+selected.getNom());
				switch (result){
				case JOptionPane.YES_OPTION:
					try {
						Service.supprimerDuGroupe(u,selected);
						recupMembres.remove(u);
						membres.setListData(recupMembres);
						choixNomine.removeItem(u);
						if (selected.getModerateur().getAmis().containsKey(u)){
							choixAjout.addItem(u);
						}
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
		// ajoute l'utilisateur au groupe
		if (e.getSource()==ajouter){
			Utilisateur u = (Utilisateur)choixAjout.getSelectedItem();
			if (u == null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un ami pour l'ajouter");
			}else{
				GroupeDiscussion selected = listeGroupes.getSelectedValue();
				try {
					Service.ajouterAuGroupe(u,selected);
					recupMembres.addElement(u);
					membres.setListData(recupMembres);
					choixNomine.addItem(u);
					choixAjout.removeItem(u);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		// nommé l'utilisateur modérateur du groupes
		if (e.getSource()==nomination){
			Utilisateur u = (Utilisateur)choixNomine.getSelectedItem();
			if (u==null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un membre du groupe pour " +
						"le nominer en tant que modérateur");
			}else{
				GroupeDiscussion selected = listeGroupes.getSelectedValue();
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir céder votre rôle de " +
						"modérateur à "+u.getNdc()+". Vous perdrez automatiquement tout vos droits " +
								"de modération.");
				switch (result){
				case JOptionPane.YES_OPTION:
					try {
						Service.changerModerateur(u,selected);
						moderateur.setText("Moderateur : "+u.getNdc());
						recupMembres.remove(u);
						recupMembres.addElement(accueil.getU());
						membres.setListData(recupMembres);
						nomination.setEnabled(false);
						ajouter.setEnabled(false);
						supprimerUtilisateur.setEnabled(false);
						choixAjout.setEnabled(false);
						choixNomine.setEnabled(false);
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
		// quitter le groupe
		if (e.getSource()==this.quitter){
			Utilisateur u = accueil.getU();
			GroupeDiscussion selected = listeGroupes.getSelectedValue();
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
						groupes.remove(selected);
						listeGroupes.setListData(groupes);
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
		// créer un groupe
		if (e.getSource()==créationGroupe){
			if (nomDuGroupe.getText().equals("")){
				afficheInformationDeCréation.setText("Vous devez rentrer un nom pour créer le groupe");
			}else{
				try {
					GroupeDiscussion cree = Service.creerGroupe(nomDuGroupe.getText(),accueil.getU());
					accueil.getU().getGroupeDiscussion().add(cree);
					groupes.addElement(cree);
					listeGroupes.setListData(groupes);
				}catch (MySQLIntegrityConstraintViolationException e1){
					afficheInformationDeCréation.setText("Ce nom de groupe existe déjà");
				}catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
		// accéder à la discussion du groupe
		if (e.getSource()==discussion){
			accueil.fen.changerEcran(new EcranDiscussions(accueil.fen,accueil));
		}
	}

	/**
	 * permet d'afficher les info du groupe selectionné et de donner les droits selon son statut 
	 * (modérateur / utilisateur)
	 */
	public void valueChanged(ListSelectionEvent e) {
		GroupeDiscussion selected = listeGroupes.getSelectedValue();
		if (!e.getValueIsAdjusting()){
			if (e.getSource() == listeGroupes && selected != null){
				
				nomGroupe.setText("Nom du groupe : "+selected.getNom());
				moderateur.setText("Nom du modérateur : "+selected.getModerateur().getNdc());
				
				this.recupMembres = new Vector<Utilisateur>(selected.getListeUser());
				
				membres.setListData(recupMembres);
				
				choixAjout.removeAll();
				choixNomine.removeAll();
				
				if (selected.getModerateur()==accueil.getU()){
					nomination.setEnabled(true);
					ajouter.setEnabled(true);
					supprimerUtilisateur.setEnabled(true);
					choixAjout.setEnabled(true);
					choixNomine.setEnabled(true);
					
					for (Utilisateur u : selected.getModerateur().getAmis().keySet()){
						if (!selected.getListeUser().contains(u)){
							choixAjout.addItem(u);
						}
					}
					
					for (Utilisateur u : selected.getListeUser()){
						choixNomine.addItem(u);
					}
				}else{
					nomination.setEnabled(false);
					ajouter.setEnabled(false);
					supprimerUtilisateur.setEnabled(false);
					choixAjout.setEnabled(false);
					choixNomine.setEnabled(false);
				}
			}
		}
	}

}
