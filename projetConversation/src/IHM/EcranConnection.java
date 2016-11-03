package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.ConnectionException;
import main.Service;

public class EcranConnection extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273285102299704821L;
	
	private JTextField user ;
	
	private JPasswordField password ;
	
	private  Fenetre fen;
	
	private JLabel messageErreur;
	
	public EcranConnection (Fenetre fen){
		this.fen = fen;
		this.setLayout(null);
		user = new JTextField();
		password = new JPasswordField();
		messageErreur = new JLabel();
		JLabel labelPassword = new JLabel ("Mot de passe        : ");
		JLabel labelUser = new JLabel("Nom de compte     : ");
		JButton boutonConnection = new JButton ("Connection");
		boutonConnection.addActionListener(this);
		labelUser.setForeground(Color.white);
		labelUser.setBounds(350,200,150,30);
		user.setBounds(500, 200, 150 , 30);
		labelPassword.setForeground(Color.white);
		labelPassword.setBounds(350,250,150,30);
		password.setBounds(500,250,150,30);
		boutonConnection.setForeground(Color.white);
		boutonConnection.setBackground(new Color (44,117,255));
		boutonConnection.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		boutonConnection.setBounds(425, 300, 150, 30);
		messageErreur.setBounds(350, 350, 300, 30);
		this.setBackground(Color.RED);
		this.add(labelUser);
		this.add(user);
		this.add(labelPassword);
		this.add(password);
		this.add(boutonConnection);
		this.add(messageErreur);
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(44,117, 255));
		g.fillRoundRect(300, 150, 400, 250,50,50);
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			fen.changerEcran(Service.connection(user.getText(),new String(password.getPassword())));
			fen.changerTitre("Réseau social - Profil");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ConnectionException e){
			messageErreur.setText("Nom de compte ou mot de passe incorrect");
		}
	}
	
	
	
}
