package IHM;

import java.awt.Color;

import javax.swing.JPanel;

import domaine.Utilisateur;

public class EcranAdministrateur extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8499510184767181684L;
	
	private Utilisateur u;
	
	private Fenetre fen;

	public EcranAdministrateur(Utilisateur u, Fenetre fen){
		this.setBackground(Color.RED);
		this.u = u;
		this.fen = fen;
		
	}

}
