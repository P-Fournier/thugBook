package IHM;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class EcranMessage extends JPanel{
	
	private Fenetre fen;
	private EcranUtilisateur ecran;
	
	public EcranMessage (Fenetre fen, EcranUtilisateur ecran){
		this.fen = fen;
		this.ecran = ecran;
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.PINK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
