package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import persistence.ConnexionException;

import main.Service;

public class EcranConnexion extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273285102299704821L;
	
	private JTextField user ;			// nom de compte de l'utilisateur cherchant à se connecter
	
	private JPasswordField password ;	// mot de passe de l'utilisant cherchant à se connecter
	
	private  Fenetre fen;				// Frame du programme
	
	private JLabel messageErreur;		// message d'erreur en cas de problème lors de la connexion (ex: mauvais mot de passe)
	
	public EcranConnexion (Fenetre fen){
		this.fen = fen;
		this.setLayout(null);
		fen.changerTitre("Réseau social - Connexion");
		
		user = new JTextField();
		password = new JPasswordField();
		messageErreur = new JLabel();
		
		JLabel labelPassword = new JLabel ("Mot de passe        : ");
		JLabel labelUser = new JLabel("Nom de compte     : ");
		JButton boutonConnection = new JButton ("Connexion");
		
		labelUser.setForeground(Color.white);
		labelUser.setBounds(350,200,150,30);
		
		labelPassword.setForeground(Color.white);
		labelPassword.setBounds(350,250,150,30);
		
		boutonConnection.addActionListener(this);
		boutonConnection.setForeground(Color.white);
		boutonConnection.setBackground(new Color (44,117,255));
		boutonConnection.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		boutonConnection.setBounds(425, 300, 150, 30);
		
		user.setBounds(500, 200, 150 , 30);
		password.setBounds(500,250,150,30);
		
		messageErreur.setForeground(Color.white);
		messageErreur.setBounds(350, 350, 300, 30);
		
		this.add(labelUser);
		this.add(user);
		this.add(labelPassword);
		this.add(password);
		this.add(boutonConnection);
		this.add(messageErreur);
	}
	
	public void paintComponent (Graphics g){
		// remise à zéro
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//création fenetre
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(300, 150, 400, 250,50,50);
		
		//décoration
		
		Font font = new Font("Courier", Font.BOLD, 200);
		g.setFont(font);
		g.drawString("{", 75, 500);
		g.drawString("}",800,500);
		font = new Font ("Courrier",Font.BOLD,100);
		g.setFont(font);
		g.drawString("MIAGEBook", 175, 500);
	}
	
	// FONCTIONNALITE BOUTTON 
	
	public void actionPerformed(ActionEvent arg0) {
		try {
			// redirige vers le bon écran d'accueil utilisateur/administrateur
			fen.changerEcran(Service.connexion(user.getText(),new String(password.getPassword()),fen));
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}catch (ConnexionException e){
			// En cas de problème à la connexion on affiche un message dans le JLabel
			messageErreur.setText(e.getMessage());
		}
	}
	
}
