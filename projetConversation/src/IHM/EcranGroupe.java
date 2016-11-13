package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
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

import domaine.GroupeDiscussion;
import domaine.Utilisateur;

public class EcranGroupe extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2877396567372360083L;
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private HashMap<JButton,Utilisateur> supprUtilisateur;
	private HashMap<JButton,GroupeDiscussion> supprDuGroupe;
	private HashMap<JButton,JComboBox<Utilisateur>> choixNominé;
	private HashMap<JButton,GroupeDiscussion> nomination;
	private HashMap<JButton,JComboBox<Utilisateur>> choixAjouté;
	private HashMap<JButton,GroupeDiscussion> ajout;
	private HashMap<JButton,GroupeDiscussion> quitterGroupe;
	private HashMap<JButton,GroupeDiscussion> rejoindreDiscussion;
	private int maxHeight ;
	private int maxWidth ;
	private JButton retour;
	private JTextField nomDuGroupe;
	private JButton créationGroupe;
	private JLabel afficheInformationDeCréation;
	
	
	public EcranGroupe (Fenetre fen , EcranUtilisateur accueil){
		this.fen = fen;
		this.accueil = accueil;
		this.maxHeight = this.getHeight();
		this.maxWidth = this.getWidth();
		this.setLayout(null);

		this.add(new Scrollbar());
		this.choixNominé = new HashMap<JButton,JComboBox<Utilisateur>>();
		this.choixAjouté = new HashMap<JButton,JComboBox<Utilisateur>>();
		this.nomination = new HashMap<JButton,GroupeDiscussion>();
		this.ajout = new HashMap<JButton,GroupeDiscussion>();
		this.quitterGroupe = new HashMap<JButton,GroupeDiscussion> ();
		this.rejoindreDiscussion = new HashMap<JButton,GroupeDiscussion> ();
		this.supprUtilisateur = new HashMap<JButton,Utilisateur>();
		this.supprDuGroupe = new HashMap<JButton,GroupeDiscussion>();
		
		// Retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		
		// Mes groupes
		
		JLabel mesGroupes = new JLabel ("Mes groupes");
		
		mesGroupes.setBounds(220, 130,250, 20);
		mesGroupes.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(mesGroupes);
		
		int hauteurCourante = 170;
		
		if (accueil.getU().getGroupeDiscussion().isEmpty()){
			JLabel pasDeGroupe = new JLabel ("Vous n'êtes dans aucun groupe de discussion");
			pasDeGroupe.setBounds(60, hauteurCourante, 350, 30);
			pasDeGroupe.setForeground(Color.WHITE);
			this.add(pasDeGroupe);
			hauteurCourante += 50;
		}
		
		for (GroupeDiscussion grp : accueil.getU().getGroupeDiscussion()){
			
			JComboBox<Utilisateur> choixN = new JComboBox<Utilisateur>();
			JButton validerChoixN = new JButton ("Nommer modérateur");
			
			hauteurCourante += 10;
			JLabel nomGroupe = new JLabel("Nom : "+grp.getNom());
			nomGroupe.setBounds(80,hauteurCourante,200,30);
			nomGroupe.setForeground(Color.WHITE);
			
			this.add(nomGroupe);
			
			hauteurCourante += 50 ;
			
			JLabel moderateur = new JLabel ("Modérateur : "+grp.getModerateur().getNdc());
			moderateur.setBounds(80,hauteurCourante,200,30);
			moderateur.setForeground(Color.WHITE);
			
			this.add(moderateur);
			
			hauteurCourante += 50;
			if (grp.getListeUser().isEmpty()){
				JLabel noUser = new JLabel ("Aucun utilisateur dans ce groupe");
				noUser.setBounds(90,hauteurCourante,300,30);
				noUser.setForeground(Fenetre.BLEU_CIEL);
				this.add(noUser);
				hauteurCourante += 50;
			}else{
				for (Utilisateur u : grp.getListeUser()){
					choixN.addItem(u);
					JLabel user = new JLabel (u.getNdc());
					user.setBounds(90,hauteurCourante,300,30);
					user.setForeground(Fenetre.BLEU_CIEL);
					this.add(user);
					
					if (grp.getModerateur() == accueil.getU()){
						JButton suppression = new JButton (new ImageIcon("src/img/croix.png"));
						
						suppression.setBorder(null);
						suppression.addActionListener(this);
						supprUtilisateur.put(suppression, u);
						supprDuGroupe.put(suppression, grp);
						suppression.setBounds(400, hauteurCourante+10, 10,10);
						
						this.add(suppression);
					}
					
					hauteurCourante += 50;
					
					
				}
				
				
			}
			if (grp.getModerateur() == accueil.getU()){
				choixN.setBounds(80,hauteurCourante,150,30);
				choixN.setSelectedItem(null);
				validerChoixN.setBounds(250, hauteurCourante, 200, 30);
				validerChoixN.setForeground(Color.WHITE);
				validerChoixN.setBackground(Fenetre.BLEU_CIEL);
				validerChoixN.addActionListener(this);
				validerChoixN.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				this.add(validerChoixN);
				this.add(choixN);
				choixNominé.put(validerChoixN, choixN);
				nomination.put(validerChoixN, grp);
				hauteurCourante += 50;
				
				JComboBox<Utilisateur> choixA = new JComboBox<Utilisateur>();
				JButton validerChoixA = new JButton ("Ajouter utilisateur");
				for (Utilisateur u : accueil.getU().getAmis().keySet()){
					if (!grp.getListeUser().contains(u)){
						choixA.addItem(u);
					}
				}
				choixA.setSelectedItem(null);
				
				choixA.setBounds(80,hauteurCourante,150,30);
				choixA.setSelectedItem(null);
				validerChoixA.setBounds(250, hauteurCourante, 200, 30);
				validerChoixA.setForeground(Color.WHITE);
				validerChoixA.setBackground(Fenetre.BLEU_CIEL);
				validerChoixA.addActionListener(this);
				validerChoixA.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
				this.add(validerChoixA);
				this.add(choixA);
				choixAjouté.put(validerChoixA, choixA);
				ajout.put(validerChoixA, grp);
				hauteurCourante += 50;
			}
			
			JButton quitter = new JButton ("Quitter groupe");
			JButton discussion = new JButton ("Aller à la discussion");
			
			quitterGroupe.put(quitter, grp);
			rejoindreDiscussion.put(discussion, grp);
			
			discussion.setBounds(80, hauteurCourante, 180, 30);
			discussion.setForeground(Color.WHITE);
			discussion.setBackground(Fenetre.BLEU_CIEL);
			discussion.addActionListener(this);
			discussion.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			this.add(discussion);
			
			quitter.setBounds(270, hauteurCourante, 180, 30);
			quitter.setForeground(Color.WHITE);
			quitter.setBackground(Fenetre.BLEU_CIEL);
			quitter.addActionListener(this);
			quitter.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			this.add(quitter);
			hauteurCourante += 50;
			
		}
		
		// Création groupe
		
		JLabel creerGroupe = new JLabel ("Créer groupe");
		
		creerGroupe.setBounds(690, 30,250, 20);
		creerGroupe.setForeground(Fenetre.BLEU_CIEL);
		this.add(creerGroupe);
		
		JLabel labelNomDuGroupe = new JLabel ("Nom du groupe : ");
		
		labelNomDuGroupe.setBounds(550, 90, 200, 30);
		labelNomDuGroupe.setForeground(Color.WHITE);
		this.add(labelNomDuGroupe);
		
		this.nomDuGroupe = new JTextField();
		nomDuGroupe.setBounds(700, 90, 200, 30);
		this.add(nomDuGroupe);
		
		this.créationGroupe = new JButton ("Créer le groupe");
		
		créationGroupe.setBounds(700, 140, 200, 30);
		créationGroupe.setForeground(Color.WHITE);
		créationGroupe.setBackground(Fenetre.BLEU_CIEL);
		créationGroupe.addActionListener(this);
		créationGroupe.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(créationGroupe);
		
		this.afficheInformationDeCréation = new JLabel ();
		afficheInformationDeCréation.setForeground(Color.white);
	
		afficheInformationDeCréation.setBounds(550, 190, 400, 30);
		this.add(afficheInformationDeCréation);
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		// carré mes groupes
		
		int hauteurMesGroupes = 50;
		
		for (GroupeDiscussion grp : accueil.getU().getGroupeDiscussion()){
			if (grp.getListeUser().isEmpty()){
				hauteurMesGroupes += 200;
			}else{
				hauteurMesGroupes += (grp.getListeUser().size())*50+150;
			}
			if (grp.getModerateur()==accueil.getU()){
				hauteurMesGroupes += 100;
			}
		
		}
		
		if (accueil.getU().getGroupeDiscussion().isEmpty()){
			g.setColor(Fenetre.BLEU_CIEL);
			g.fillRoundRect(40, 140, 450,100 ,50,50);
		}else{
			g.setColor(Fenetre.BLEU_CIEL);
			g.fillRoundRect(40, 140, 450,hauteurMesGroupes ,50,50);
		}
				
		g.setColor(Color.white);
		g.fillRoundRect(200,120,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,120,250,40,50,50);
		
		g.setColor(Color.WHITE);
		
		int hauteurCourante = 170;
		
		for (GroupeDiscussion grp : accueil.getU().getGroupeDiscussion()){
			int hauteurGroupe;
			if (grp.getListeUser().isEmpty()){
				hauteurGroupe = 200;
			} else {
				hauteurGroupe = (grp.getListeUser().size())*50+150;
			}
			if (grp.getModerateur()==accueil.getU()){
				hauteurGroupe += 100;
			}
			g.drawRoundRect(60, hauteurCourante, 410, hauteurGroupe, 50, 50);
			if (grp.getListeUser().isEmpty()){
				g.fillRoundRect(80, hauteurCourante+100, 370, 50, 25, 25);
			} else {
				g.fillRoundRect(80, hauteurCourante+100, 370, 50*grp.getListeUser().size(), 25, 25);
			}
			
			hauteurCourante += hauteurGroupe + 10;
		}
		
		this.maxHeight = hauteurCourante +100;
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
		
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(510, 40 , 450, 200, 50, 50);
		
		g.setColor(Color.white);
		g.fillRoundRect(670,20,250,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(670,20,250,40,50,50);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==retour){
			accueil.refresh();
		}
		if (supprUtilisateur.containsKey(e.getSource())){
			Utilisateur u = supprUtilisateur.get(e.getSource());
			GroupeDiscussion grp = supprDuGroupe.get(e.getSource());
			int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir supprimer "+u.getNdc()+" du " +
					"groupe "+grp.getNom());
			switch (result){
			case JOptionPane.YES_OPTION:
				try {
					Service.supprimerDuGroupe(u,grp);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
				refresh();
				break;
			}
			
		}
		if (ajout.containsKey(e.getSource())){
			GroupeDiscussion grp = ajout.get(e.getSource());
			Utilisateur u = (Utilisateur)choixAjouté.get(e.getSource()).getSelectedItem();
			if (u == null){
				JOptionPane.showMessageDialog(this, "Vous devez choisir un ami pour l'ajouter");
			}else{
				try {
					Service.ajouterAuGroupe(u,grp);
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
		if (nomination.containsKey(e.getSource())){
			GroupeDiscussion grp = nomination.get(e.getSource());
			Utilisateur u = (Utilisateur)choixNominé.get(e.getSource()).getSelectedItem();
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
						Service.changerModerateur(u,grp);
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
		if (quitterGroupe.containsKey(e.getSource())){
			GroupeDiscussion grp = quitterGroupe.get(e.getSource());
			Utilisateur u = accueil.getU();
			if (u==grp.getModerateur()&&!grp.getListeUser().isEmpty()){
				JOptionPane.showMessageDialog(this, "Un modérateur ne peux quitter son groupe que si il" +
						" est le dernier membre du groupe. Cédez vos droits de modération ou soyez le" +
						" dernier membre du groupe");
			}else{
				int result = JOptionPane.showConfirmDialog(this, "Êtes-vous sur de vouloir quitter ce groupe de discussion ?");
				switch(result){
				case JOptionPane.YES_OPTION:
					try {
						if (u==grp.getModerateur()){
							Service.supprimerGroupe(grp);
							u.getGroupeDiscussion().remove(grp);
						}else{
							Service.supprimerDuGroupe(u, grp);
						}
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
		if (rejoindreDiscussion.containsKey(e.getSource())){
			/////!\\\\\\ A coder
		}
	}

	private void refresh() {
		fen.changerEcran(new EcranGroupe(fen, accueil));		
	}

}
