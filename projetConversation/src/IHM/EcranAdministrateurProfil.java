package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import domaine.Utilisateur;
import main.Service;

public class EcranAdministrateurProfil extends Ecran implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2443332206693667322L;
	private Fenetre fen;
	private EcranAdministrateur ecranAdmin;
	private JList<Utilisateur> list;

	public EcranAdministrateurProfil(Fenetre fen, EcranAdministrateur ecranAdmin) {
		this.fen = fen;
		this.ecranAdmin = ecranAdmin;
		JLabel administration = new JLabel();
		administration.setBounds(740, 370, 150, 200);
		administration.setForeground(Color.white);
		this.add(administration);

		try {
			ArrayList<Utilisateur> listeUser = Service.obtenirTousLesUtilisateurs();
			listeUser.remove(ecranAdmin.getU());
			Vector<Utilisateur> ut = new Vector<Utilisateur>();
		    for (Utilisateur u : listeUser){
		    	ut.addElement(u);
		    }
		    list = new JList<Utilisateur>(ut);
		    list.setBounds(0, 300, 300, 300);
		    this.add(list);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {

		// Fenetre bleue à gauche
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRect(0, 0, 250, 600);

		// Fenetre blanche affichage user

		g.setColor(Color.white);
		g.fillRect(720, 360, 200, 40);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
