package IHM;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2476884677276375150L;
	static private JScrollPane current;
	static Color BLEU_CIEL = new Color (44,117,255);
	
	public Fenetre () throws InterruptedException{    
		
		this.setTitle("Réseau social - Connexion");
	    
		this.setSize(1000,600);
	    
		this.setResizable(false);
	   
		this.setLocationRelativeTo(null);
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		
		this.setBackground(Color.white);
	    
	    current = new JScrollPane(new EcranConnexion(this));
	    current.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	    this.add(current);
		
	    this.setVisible(true);
		
	}
	
	public void changerEcran (Ecran pan){
		if (pan != null){
			this.remove(current);
			current = new JScrollPane(pan);
			current.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			this.add(current);
			this.setVisible(true);
		}
	}

	public void changerTitre(String string) {
		this.setTitle(string);
	}
	
}
