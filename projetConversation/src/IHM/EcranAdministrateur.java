package IHM;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import domaine.Utilisateur;

public class EcranAdministrateur extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8499510184767181684L;
	
	private ArrayList<JPanel> ecransAdmin;

	public EcranAdministrateur(Utilisateur u){
		this.setBackground(Color.RED);
	}

}
