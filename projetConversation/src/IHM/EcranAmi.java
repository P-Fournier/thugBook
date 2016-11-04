package IHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EcranAmi extends JPanel{
	
	private Fenetre fen;
	private EcranUtilisateur ecran;
	
	public EcranAmi (Fenetre fen,EcranUtilisateur ecran){
		this.fen = fen;
		this.ecran = ecran;
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
