package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domaine.GroupeDiscussion;
import domaine.Utilisateur;
import domaine.messages.Discussion;

public class EcranListeDiscussions extends Ecran implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466304023765997464L;
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private int maxHeight;
	private int maxWidth;
	private HashMap<String,Discussion> discussions;
	private JButton retour; 
	
	/**
	 * pour donner les valeurs aux composants labels ...
	 * @param fen
	 * @param accueil
	 */
	public EcranListeDiscussions(Fenetre fen, EcranUtilisateur accueil) {
		
		this.fen = fen;
		this.accueil = accueil;
		fen.changerTitre("Réseau social - Mes discussions");
		this.setLayout(null);
		
		this.maxHeight = this.getHeight();
		this.maxWidth = this.getWidth();
		this.add(new Scrollbar());
		this.discussions = new HashMap<String,Discussion>();
		
		for (Utilisateur u : accueil.getU().getAmis().keySet()){
			String nomDiscussion = u.getNdc();
			Discussion discussionPrive = accueil.getU().getAmis().get(u);
			discussions.put(nomDiscussion, discussionPrive);
		}
		
		for (GroupeDiscussion grp: accueil.getU().getGroupeDiscussion()){
			discussions.put(grp.getNom(), grp.getDiscussion());
		}
		
		/////// Tri hashMap
		
		// Boutton retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		JLabel test = new JLabel ("Je suis le test !");
		test.setForeground(Fenetre.BLEU_CIEL);
		test.setBounds(600,100,200,30);
		this.add(test);
		
	}
	
	/**
	 * tracer des forme sur l'écran
	 */
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		g.setColor(Fenetre.BLEU_CIEL);
		
		g.fillRoundRect(40, 100, 450, 100, 50, 50);
		
		g.drawRoundRect(40, 250, 450, 100, 50, 50);
		
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
	}

	/**
	 * pour associer des fonctions aux composants
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==retour){
			accueil.refresh();
		}
	}
	
	/**
	 * rafraichir après modification
	 */
	public void refresh(){
		fen.changerEcran(new EcranListeDiscussions(fen,accueil));
	}
}
