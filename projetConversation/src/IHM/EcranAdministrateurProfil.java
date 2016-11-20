package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.Utilisateur;
import main.Service;

public class EcranAdministrateurProfil extends Ecran implements ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2443332206693667322L;
	private Fenetre fen;
	private EcranAdministrateur ecranAdmin;
	private JList<Utilisateur> list;
	private JButton supprimerBoutton;
	private Utilisateur selected;
	private JButton createBoutton ; 
	private JTextField fieldCreateNDC ;
	private JTextField fieldCreateNom ;
	private JTextField fieldCreatePrenom ;
	private JPasswordField fieldCreatePassword ;
	
	
	
	
	
	public EcranAdministrateurProfil(Fenetre fen, EcranAdministrateur ecranAdmin, Utilisateur selected) {
		this.fen = fen;
		this.ecranAdmin = ecranAdmin;
		this.setLayout(null);
		this.selected = selected;

		fieldCreateNDC = new JTextField();
		fieldCreateNom = new JTextField();
		fieldCreatePrenom = new JTextField();
		fieldCreatePassword = new JPasswordField();
		fieldCreateNDC.setBounds(450, 430, 150 , 30);
		fieldCreateNom.setBounds(700, 430, 150 , 30);
		fieldCreatePrenom.setBounds(450, 480, 150 , 30);
		fieldCreatePassword.setBounds(700, 480, 150 , 30);
		this.add(fieldCreateNDC);
		this.add(fieldCreateNom);
		this.add(fieldCreatePrenom);
		this.add(fieldCreatePassword);
		
		JLabel administration = new JLabel("Espace Administrateur ");
		administration.setBounds(70, 10, 300, 50);
		administration.setForeground(Color.white);

		JLabel creationUtilisateur = new JLabel("Espace Création de compte ");
		creationUtilisateur.setBounds(550, 390, 200, 20);
		creationUtilisateur.setForeground(Color.white);
		
		JLabel nomDeCompte = new JLabel("Nom de compte :"); 
		//nomDeCompte.setBounds(350, 430, , height);
		
		supprimerBoutton = new JButton("Supprimer l'utilisateur");
		supprimerBoutton.setBounds(50, 320, 200, 30);
		supprimerBoutton.setBackground(Fenetre.BLEU_CIEL);
		supprimerBoutton.setForeground(Color.white);
		supprimerBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		supprimerBoutton.addActionListener(this);
		
		createBoutton = new JButton("Créer l'utilisateur");
		createBoutton.setBounds(550, 520, 200, 30);
		createBoutton.setBackground(Fenetre.BLEU_CIEL);
		createBoutton.setForeground(Color.white);
		createBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		createBoutton.addActionListener(this);

		
		this.add(createBoutton);
		this.add(supprimerBoutton);
		this.add(administration);
		this.add(creationUtilisateur);
		
		try {
			ArrayList<Utilisateur> listeUser = Service.obtenirTousLesUtilisateurs();
			listeUser.remove(ecranAdmin.getU());
			Vector<Utilisateur> ut = new Vector<Utilisateur>();
			for (Utilisateur u : listeUser) {
				ut.addElement(u);
			}
			list = new JList<Utilisateur>(ut);
			list.setBackground(Color.white);
			list.setBounds(100, 200, 100, 100);
			ScrollPane scrollUtilisateur = new ScrollPane();
			scrollUtilisateur.add(list);
			scrollUtilisateur.setBounds(100, 200, 100, 100);

			this.add(scrollUtilisateur);

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {

		//	Fenetre bleue à gauche
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRect(0, 0, 300, 600);

		//	Fenêtre créer utilisateur 
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(350, 370, 600, 200, 50, 50);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == supprimerBoutton) {
			try {
				Service.supprimerUtilisateur(list.getSelectedValue().getNdc());
			} catch (ClassNotFoundException x) {
				JOptionPane.showMessageDialog(this, x.getMessage());
				x.printStackTrace();
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(this, x.getMessage());
				x.printStackTrace();
			}
			selected = list.getSelectedValue();
			refresh();
		}
	}

	@Override
	public void refresh() {
		fen.changerEcran(new EcranAdministrateurProfil(fen, ecranAdmin, selected));
	}

	public void valueChanged(ListSelectionEvent e) {
		JLabel etiquette = new JLabel(" ");
	}
}
