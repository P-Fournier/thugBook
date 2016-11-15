package IHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;


import domaine.Utilisateur;

public class EcranAdministrateur extends EcranUtilisateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8499510184767181684L;
	
	public EcranAdministrateur(Utilisateur u, Fenetre fen) {
		super (u,fen);
		this.maxHeight=700;
		// carré mon univers
		
		JLabel administration = new JLabel("Espace administrateur");
				
		administration.setBounds(220, 450,180, 20);
		administration.setForeground(Fenetre.BLEU_CIEL);
				
		this.add(administration);
				
	}
	
	@Override
	public void refresh(){
		fen.changerEcran(new EcranAdministrateur(this.u,this.fen));
	}
	
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		// carré administrateur
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 460, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,440,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,440,200,40,50,50);
	}

}
