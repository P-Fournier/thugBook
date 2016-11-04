package IHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EcranProfilUtilisateur extends JPanel{
	
	private Fenetre fen;
	private EcranUtilisateur accueil;
	
	public EcranProfilUtilisateur(Fenetre fen, EcranUtilisateur accueil){
		this.fen = fen;
		this.accueil=accueil;
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
