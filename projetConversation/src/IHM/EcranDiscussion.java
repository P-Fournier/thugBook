package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domaine.Utilisateur;
import domaine.messages.ComparateurMessage;
import domaine.messages.Discussion;
import domaine.messages.Message;

public class EcranDiscussion extends Ecran implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1881498595437036000L;
	private Fenetre fen;
	private Ecran provenance;
	private Discussion discussion;
	private Utilisateur user;
	private int maxWidth;
	private int maxHeight;
	private JButton retour;
	
	public EcranDiscussion (Fenetre fen, Ecran provenance, Discussion discussion, Utilisateur user){
		fen.changerTitre("Réseau social - Discussion");
		this.fen = fen;
		this.provenance = provenance;
		this.discussion = discussion;
		this.user = user;
		this.maxWidth = this.getWidth();
		this.maxHeight = this.getHeight();
		this.setLayout(null);

		//Bouton retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,150,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		// Tri des messages
		
		ArrayList<Message> messages = discussion.getMessages();
		messages.sort(new ComparateurMessage());
		
		// Affichage des messages
		
		int hauteurCourante = 100;
		
		for (Message m : discussion.getMessages()){
			JLabel msg = new JLabel (m.getExpediteur().getNdc()+" ("
					+m.getDateEnvoie()+") : "+m.getContenu());
			// décorer le JLabel
			msg.setBounds(50,hauteurCourante,900,300);
			msg.setForeground(Fenetre.BLEU_CIEL);
			this.add(msg);
			hauteurCourante = hauteurCourante + 50;
		}
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		
		this.setPreferredSize(new Dimension (this.maxWidth,this.maxHeight));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == retour){
			provenance.refresh();
		}
	}
	
	public void refresh(){
		fen.changerEcran(new EcranDiscussion (fen,provenance,discussion,user));
	}
	
	
	
	
}
