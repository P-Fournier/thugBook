package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domaine.Utilisateur;

public class EcranAdministrateur extends EcranUtilisateur implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8499510184767181684L;
	
	private JButton gestionBoutton ;		// redirection vers l'ecran Administration profil
	private JButton groupeBoutton ;			// redirection vers l'ecran Administration groupe
	private JButton gestionCI;				// redirection vers l'ecran de gestion des centre d'intérêt
	
	// CONSTRUCTEUR
	
	public EcranAdministrateur(Utilisateur u, Fenetre fen) {
		super (u,fen);
		
		// carré administration
		
		JLabel administration = new JLabel("Espace administrateur");
				
		administration.setBounds(740, 370,180, 20);
		administration.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(administration);
		
		gestionBoutton = new JButton ("Gestion Profil");
		gestionBoutton.setBounds(520, 420, 170, 30);
		gestionBoutton.setBackground(Fenetre.BLEU_CIEL);
		gestionBoutton.setForeground(Color.white);
		gestionBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		gestionBoutton.addActionListener(this);
		this.add(gestionBoutton);
			
		groupeBoutton = new JButton ("Gestion groupe");
		groupeBoutton.setBounds(730, 420, 170, 30);
		groupeBoutton.setBackground(Fenetre.BLEU_CIEL);
		groupeBoutton.setForeground(Color.white);
		groupeBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		groupeBoutton.addActionListener(this);
		this.add(groupeBoutton);
		
		gestionCI = new JButton("Gestion CI");
		gestionCI.setBounds(520, 470, 170, 30);
		gestionCI.setBackground(Fenetre.BLEU_CIEL);
		gestionCI.setForeground(Color.white);
		gestionCI.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		gestionCI.addActionListener(this);
		this.add(gestionCI);
	}
	
	//	CREATION DES ELEMENTS GRAPHIQUES
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(460, 380, 500, 200,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(720,360,200,40,50,50);
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,360,200,40,50,50);
	}
	
	// FONCTIONNALITES DES BOUTTONS
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==gestionBoutton){
			fen.changerEcran(new EcranAdministrateurProfil(fen,this));
		}
		if (e.getSource()==groupeBoutton){
			fen.changerEcran(new EcranAdministrateurGroupe(fen,this));
		}
		if (e.getSource()==gestionCI){
			fen.changerEcran(new EcranGestionCI(fen,this));
		}
	}
	
	@Override
	public void refresh() {
		fen.changerEcran(new EcranAdministrateur(u,fen));
	}

}
