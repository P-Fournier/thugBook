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
	private JButton gestionBoutton ;
	private JButton groupeBoutton ;
	
	public EcranAdministrateur(Utilisateur u, Fenetre fen) {
		super (u,fen);
		
		// carré administration
		
		JLabel administration = new JLabel("Espace administrateur");
				
		administration.setBounds(740, 370,180, 20);
		administration.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(administration);
		
		//	Boutton Gestion Profil Utilisateur
		gestionBoutton = new JButton ("Gestion Profil");
		gestionBoutton.setBounds(480, 420, 170, 30);
		gestionBoutton.setBackground(Fenetre.BLEU_CIEL);
		gestionBoutton.setForeground(Color.white);
		gestionBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		gestionBoutton.addActionListener(this);
		this.add(gestionBoutton);
			
		//	Boutton Gestion Groupe Utilisateur
		groupeBoutton = new JButton ("Gestion groupe");
		groupeBoutton.setBounds(670, 420, 170, 30);
		groupeBoutton.setBackground(Fenetre.BLEU_CIEL);
		groupeBoutton.setForeground(Color.white);
		groupeBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		groupeBoutton.addActionListener(this);
		this.add(groupeBoutton);
	}
	
	@Override
	public void refresh(){
		fen.changerEcran(new EcranAdministrateur(this.u,this.fen));
	}
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		// carré administrateur
		// Fenetre bleue de remplissage 
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(460, 380, 500, 200,50,50);
		// Fenetre titre 
		g.setColor(Color.white);
		g.fillRoundRect(720,360,200,40,50,50);
		// Contour titre 
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,360,200,40,50,50);
	}
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==gestionBoutton){
			fen.changerEcran(new EcranAdministrateurProfil(fen,this,null));
		}
		if (e.getSource()==groupeBoutton){
			fen.changerEcran(new EcranAdministrateurGroupe(fen,this));
		}
	}

}
