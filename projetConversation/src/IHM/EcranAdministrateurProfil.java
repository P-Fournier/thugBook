package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import domaine.Utilisateur;
import main.Service;
import persistence.ConnexionException;

public class EcranAdministrateurProfil extends JPanel implements ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2443332206693667322L;
	private EcranAdministrateur ecranAdmin;
	private JList<Utilisateur> list;

	
	private JButton supprimerBoutton;
	private JButton donnerDroitAdmin;
	private JButton createBoutton;

	private JButton validerBoutton;
	private JButton retourBoutton ; 
	
	
	private JTextField fieldCreateNDC;
	private JTextField fieldCreateNom;
	private JTextField fieldCreatePrenom;
	private JTextField fieldModifNDC;
	private JTextField fieldModifNom;
	private JTextField fieldModifPrenom;
	private JPasswordField fieldCreatePassword;
	private JPasswordField fieldConfirmationPassword;
	private JPasswordField fieldModifPassword;
	private JPasswordField fieldModifConfirmationPassword;
	private Vector<Utilisateur> ut;

	public EcranAdministrateurProfil(Fenetre fen, EcranAdministrateur ecranAdmin) {
		fen.changerTitre("Réseau social - gestion utilisateur");
		this.ecranAdmin = ecranAdmin;
		this.setLayout(null);

		fieldCreateNDC = new JTextField();
		fieldCreateNom = new JTextField();
		fieldCreatePrenom = new JTextField();
		fieldCreatePassword = new JPasswordField();
		fieldConfirmationPassword = new JPasswordField();
		fieldCreateNDC.setBounds(500, 430, 150, 30);
		fieldCreatePassword.setBounds(500, 480, 150, 30);
		fieldCreateNom.setBounds(750, 430, 150, 30);
		fieldCreatePrenom.setBounds(750, 480, 150, 30);
		fieldConfirmationPassword.setBounds(500, 530, 150, 30);
		this.add(fieldCreateNDC);
		this.add(fieldCreateNom);
		this.add(fieldCreatePrenom);
		this.add(fieldCreatePassword);
		this.add(fieldConfirmationPassword);

		JLabel administration = new JLabel("Espace Administrateur ");
		JLabel creationUtilisateur = new JLabel("Espace création de compte ");
		JLabel nomDeCompte = new JLabel("Identifiant :");
		JLabel nom = new JLabel("Nom :");
		JLabel prenom = new JLabel("Prenom :");
		JLabel password = new JLabel("Password :");
		JLabel confirmationPassword = new JLabel("Confirmer :");
		JLabel confirmationPassword2 = new JLabel("Password");

		nomDeCompte.setBounds(400, 430, 100, 30);
		password.setBounds(400, 480, 100, 30);
		confirmationPassword.setBounds(400, 530, 100, 30);
		confirmationPassword2.setBounds(400, 545, 100, 30);
		nom.setBounds(680, 430, 100, 30);
		prenom.setBounds(680, 480, 100, 30);
		administration.setBounds(70, 10, 300, 50);
		creationUtilisateur.setBounds(570, 390, 200, 20);

		password.setForeground(Color.white);
		nomDeCompte.setForeground(Color.white);
		prenom.setForeground(Color.white);
		nom.setForeground(Color.white);
		administration.setForeground(Color.white);
		creationUtilisateur.setForeground(Fenetre.BLEU_CIEL);
		confirmationPassword.setForeground(Color.white);
		confirmationPassword2.setForeground(Color.white);

		createBoutton = new JButton("Créer utilisateur");
		createBoutton.setBounds(750, 530, 150, 30);
		createBoutton.setBackground(Fenetre.BLEU_CIEL);
		createBoutton.setForeground(Color.white);
		createBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		createBoutton.addActionListener(this);

		supprimerBoutton = new JButton("Supprimer l'utilisateur");
		supprimerBoutton.setBounds(50, 400, 200, 30);
		supprimerBoutton.setBackground(Fenetre.BLEU_CIEL);
		supprimerBoutton.setForeground(Color.white);
		supprimerBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		supprimerBoutton.setEnabled(false);
		supprimerBoutton.addActionListener(this);

		donnerDroitAdmin = new JButton("Nommer Administrateur");
		donnerDroitAdmin.setBounds(50, 500, 200, 30);
		donnerDroitAdmin.setBackground(Fenetre.BLEU_CIEL);
		donnerDroitAdmin.setForeground(Color.white);
		donnerDroitAdmin.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		donnerDroitAdmin.setEnabled(false);
		donnerDroitAdmin.addActionListener(this);
		
		retourBoutton = new JButton("Retour");
		retourBoutton.setBounds(50, 550, 200, 30);
		retourBoutton.setBackground(Fenetre.BLEU_CIEL);
		retourBoutton.setForeground(Color.white);
		retourBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		retourBoutton.addActionListener(this);
	
		// Insertion des JButton
		this.add(createBoutton);
		this.add(donnerDroitAdmin);
		this.add(supprimerBoutton);
		this.add(retourBoutton);
		// Insertion des JLabel
		this.add(administration);
		this.add(creationUtilisateur);
		this.add(nomDeCompte);
		this.add(nom);
		this.add(prenom);
		this.add(password);
		this.add(confirmationPassword);
		this.add(confirmationPassword2);

		try {
			ArrayList<Utilisateur> listeUser = Service.obtenirTousLesUtilisateurs();
			listeUser.remove(ecranAdmin.getU());
			ut = new Vector<Utilisateur>();
			for (Utilisateur u : listeUser) {
				ut.addElement(u);
			}
			list = new JList<Utilisateur>(ut);
			list.setBackground(Color.white);
			list.setBounds(50, 70, 200, 300);
			list.addListSelectionListener(this);

			ScrollPane scrollUtilisateur = new ScrollPane();
			scrollUtilisateur.add(list);
			scrollUtilisateur.setBounds(50, 70, 200, 300);

			this.add(scrollUtilisateur);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}

		// Affichage de l'utilisateur selectionné

		// Bouton de validation

		validerBoutton = new JButton("Valider les modifications");
		validerBoutton.setBounds(50, 450, 200, 30);
		validerBoutton.setBackground(Fenetre.BLEU_CIEL);
		validerBoutton.setForeground(Color.white);
		validerBoutton.setBorder(new CompoundBorder(new LineBorder(Color.white), new EmptyBorder(5, 15, 5, 15)));
		validerBoutton.setEnabled(false);
		validerBoutton.addActionListener(this);

		// Création des boites de modification
		fieldModifPrenom = new JTextField();
		fieldModifPrenom.setEnabled(false);
		fieldModifNom = new JTextField();
		fieldModifNom.setEnabled(false);
		fieldModifNDC = new JTextField();
		fieldModifNDC.setEnabled(false);
		fieldModifPassword = new JPasswordField();
		fieldModifPassword.setEnabled(false);
		fieldModifConfirmationPassword = new JPasswordField();
		fieldModifConfirmationPassword.setEnabled(false);
		fieldModifNDC.setBounds(500, 90, 150, 30);
		fieldModifNom.setBounds(500, 170, 150, 30);
		fieldModifPrenom.setBounds(500, 250, 150, 30);
		fieldModifPassword.setBounds(750, 90, 150, 30);
		fieldModifConfirmationPassword.setBounds(750, 170, 150, 30);
		
		// Création des JLabel

		JLabel jlabelModifNdc = new JLabel("Nom de compte :");
		JLabel jlabelModifNom = new JLabel("Nom :");
		JLabel jlabelModifPrenom = new JLabel("Prenom :");
		JLabel jlabelModifPassword = new JLabel("Password :");
		JLabel jlabelModifConfirmationPassword = new JLabel("Confirmer :");
		JLabel jlabelModifConfirmationPassword2 = new JLabel("password");
		JLabel jlabelmodifUtilisateur = new JLabel("Espace modification utilisateur ");

		jlabelModifNdc.setBounds(375, 90, 150, 30);
		jlabelModifNom.setBounds(375, 170, 150, 30);
		jlabelModifPrenom.setBounds(375, 250, 150, 30);
		jlabelModifPassword.setBounds(660, 90, 150, 30);
		jlabelModifConfirmationPassword.setBounds(660, 170, 150, 30);
		jlabelModifConfirmationPassword2.setBounds(660, 185, 150, 30);
		jlabelmodifUtilisateur.setBounds(570, 15, 250, 30);

		jlabelModifNdc.setForeground(Color.white);
		jlabelModifNom.setForeground(Color.white);
		jlabelModifPrenom.setForeground(Color.white);
		jlabelModifPassword.setForeground(Color.white);
		jlabelModifConfirmationPassword.setForeground(Color.white);
		jlabelModifConfirmationPassword2.setForeground(Color.white);
		jlabelmodifUtilisateur.setForeground(Fenetre.BLEU_CIEL);

		// Ajout
		this.add(jlabelmodifUtilisateur);
		this.add(jlabelModifNdc);
		this.add(jlabelModifNom);
		this.add(jlabelModifPrenom);
		this.add(jlabelModifPassword);
		this.add(jlabelModifConfirmationPassword);
		this.add(jlabelModifConfirmationPassword2);
		this.add(fieldModifNom);
		this.add(fieldModifConfirmationPassword);
		this.add(fieldModifPrenom);
		this.add(fieldModifNDC);
		this.add(fieldModifPassword);
		this.add(validerBoutton);
	}

	public void paintComponent(Graphics g) {

		// Fenetre bleue à gauche
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRect(0, 0, 300, 600);

		// Fenêtre créer utilisateur
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(350, 400, 600, 190, 50, 50);

		g.setColor(Color.white);
		g.fillRoundRect(550, 380, 250, 40, 50, 50);

		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(550, 380, 250, 40, 50, 50);

		// Fenêtre écran de modif de l'utilisateur
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(350, 30, 600, 330, 50, 50);

		g.setColor(Color.white);
		g.fillRoundRect(550, 10, 250, 40, 50, 50);

		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(550, 10, 250, 40, 50, 50);
	}

	public void actionPerformed(ActionEvent e) {
		Utilisateur selected = list.getSelectedValue(); 
		if (e.getSource() == supprimerBoutton) {
			try {
				Service.supprimerUtilisateur(selected);
				ut.remove(selected);
				list.setListData(ut);
			} catch (ClassNotFoundException x) {
				JOptionPane.showMessageDialog(this, x.getMessage());
				x.printStackTrace();
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(this, x.getMessage());
				x.printStackTrace();
			}
		}
		if (e.getSource() == createBoutton) {
			try {
				Utilisateur create = Service.creerUtilisateur(fieldCreateNDC.getText(), fieldCreatePassword.getPassword(),
						fieldConfirmationPassword.getPassword(), fieldCreateNom.getText(), fieldCreatePrenom.getText());
				ut.addElement(create);
				list.setListData(ut);
				fieldCreateNDC.setText(null);
				fieldCreateNom.setText(null);
				fieldCreatePrenom.setText(null);
				fieldCreatePassword.setText(null);
				fieldConfirmationPassword.setText(null);
			} catch (MySQLIntegrityConstraintViolationException e1){
				JOptionPane.showMessageDialog(this, "Ce nom de compte est déjà utilisé");
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			} catch (ConnexionException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessageErreur());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (e.getSource() == validerBoutton) {
			if (fieldModifNDC.getText().equals("")){
				JOptionPane.showMessageDialog(this,"Le nom de compte est vide");
			}
			else {
				selected.setNdc(fieldModifNDC.getText());
				selected.setNom(fieldModifNom.getText());
				selected.setPrenom(fieldModifPrenom.getText());
				String password = new String(fieldModifPassword.getPassword());
				String confirmPass = new String(fieldModifConfirmationPassword.getPassword());				
				if (password !=null) {
					if (password.equals(confirmPass)) {
						selected.setPassword(password);
					} 
					else {
						JOptionPane.showMessageDialog(this, "Les mot de passes différents modification non pris en compte");
					}
				}
				try {
					JOptionPane.showMessageDialog(this,selected.getPassword());
					Service.updateProfil(selected);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					e1.printStackTrace();
				}
				String currentTime = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE).format(new Date());
				JOptionPane.showMessageDialog(this, currentTime + " : Sauvegarde effectuée");
			}
		}
		if (e.getSource() == retourBoutton){
			ecranAdmin.refresh() ; 
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == list && !e.getValueIsAdjusting()) {
			Utilisateur selected = list.getSelectedValue();
			if (selected != null){
				fieldModifNDC.setEnabled(true);
				fieldModifNDC.setText(selected.getNdc());
				fieldModifNom.setEnabled(true);
				fieldModifNom.setText(selected.getNom());
				fieldModifPassword.setEnabled(true);
				fieldModifConfirmationPassword.setEnabled(true);
				fieldModifPrenom.setEnabled(true);
				fieldModifPrenom.setText(selected.getPrenom());
				validerBoutton.setEnabled(true);
				supprimerBoutton.setEnabled(true);
				donnerDroitAdmin.setEnabled(true);
			}else{
				fieldModifNDC.setEnabled(false);
				fieldModifNDC.setText(null);
				fieldModifNom.setEnabled(false);
				fieldModifNom.setText(null);
				fieldModifPassword.setEnabled(false);
				fieldModifConfirmationPassword.setEnabled(false);
				fieldModifPrenom.setEnabled(false);
				fieldModifPrenom.setText(null);
				validerBoutton.setEnabled(false);
				supprimerBoutton.setEnabled(false);
				donnerDroitAdmin.setEnabled(false);
			}
		}
	}
}
