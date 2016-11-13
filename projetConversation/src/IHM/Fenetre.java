package IHM;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Fenetre {
	
	static private JFrame fenetre ;
	static private Fenetre inst;
	static private JScrollPane current;
	static Color BLEU_CIEL = new Color (44,117,255);
	
	public static Fenetre getInstance () throws InterruptedException{
		if (inst == null){
			inst = new Fenetre ();
		}
		return inst;
	}
	
	public Fenetre () throws InterruptedException{    
		fenetre = new JFrame();
		
		fenetre.setTitle("Réseau social - Connexion");
	    
	    fenetre.setSize(1000,600);
	    
	    fenetre.setResizable(false);
	   
	    fenetre.setLocationRelativeTo(null);
	    
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		
	    fenetre.setBackground(Color.white);
	    
	    current = new JScrollPane(new EcranConnexion(this));
	    current.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	    fenetre.add(current);
		
		fenetre.setVisible(true);
		
	}
	
	public void changerEcran (JPanel pan){
		if (pan != null){
			fenetre.remove(current);
			current = new JScrollPane(pan);
			current.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			fenetre.add(current);
			fenetre.setVisible(true);
		}
	}

	public void changerTitre(String string) {
		fenetre.setTitle(string);
	}
	
}
