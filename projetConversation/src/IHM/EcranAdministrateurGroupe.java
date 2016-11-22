package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class EcranAdministrateurGroupe extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4498251357712963802L;
	private Fenetre fen;
	private EcranAdministrateur ecranAdmin;
	
	private JButton retourBoutton; 
	private JButton modifierGroupeBoutton ; 
	private JButton supprimerUtilisateurGroupeBoutton ;
	private JButton passerModerateurBoutton ;
	private JButton supprimerGroupeBoutton ;
	
	public EcranAdministrateurGroupe (Fenetre fen, EcranAdministrateur ecranAdmin){
		this.fen = fen ;
		this.ecranAdmin = ecranAdmin; 
		this.setLayout(null);
		
		retourBoutton = new JButton("Retour");
		retourBoutton.setBounds(50, 550, 200, 30);
		retourBoutton.setBackground(Fenetre.BLEU_CIEL);
		retourBoutton.setForeground(Color.white);
		retourBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		retourBoutton.addActionListener(this);
		
		this.add(retourBoutton);
		
	}
	
	public void paintComponent(Graphics g) {
		
		// Fenetre bleue à gauche
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRect(0, 0, 300, 600);
	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == retourBoutton){
			ecranAdmin.refresh();
		}
	}

}
