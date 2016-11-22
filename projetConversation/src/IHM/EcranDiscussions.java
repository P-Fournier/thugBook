package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Service;

import domaine.GroupeDiscussion;
import domaine.Utilisateur;
import domaine.messages.AccuseReception;
import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;
import domaine.messages.Discussion;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;

public class EcranDiscussions extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466304023765997464L;
	private EcranUtilisateur accueil;
	private HashMap<String,Discussion> discussions;
	private HashMap<Discussion,ArrayList<Utilisateur>> destinataires;
	private JButton retour; 
	private JList<String> choix;
	private ScrollPane affichageDiscussion;
	private Vector<Message> lesMessages;
	private JTextArea saisie;
	private JButton envoie;
	private JCheckBox accuse;
	private JCheckBox prioritaire;
	private JCheckBox chiffrement;
	private JCheckBox delaiExpiration;
	private JList<Message> mesMessages;
	
	/**
	 * pour donner les valeurs aux composants labels ...
	 * @param fen
	 * @param accueil
	 */
	public EcranDiscussions(Fenetre fen, EcranUtilisateur accueil) {
		
		this.accueil = accueil;
		this.destinataires = new HashMap<Discussion,ArrayList<Utilisateur>>();
		
		fen.changerTitre("Réseau social - Mes discussions");
		this.setLayout(null);
		
		this.add(new Scrollbar());
		this.discussions = new HashMap<String,Discussion>();
		
		Vector<String> nomDiscussion = new Vector<String>();
		
		for (Utilisateur u : accueil.getU().getAmis().keySet()){
			nomDiscussion.addElement(u.getNdc());
			Discussion discussionPrive = accueil.getU().getAmis().get(u);
			discussions.put(u.getNdc(), discussionPrive);
			ArrayList<Utilisateur> dest = new ArrayList<Utilisateur>();
			dest.add(u);
			destinataires.put(discussionPrive,dest);
		}
		
		for (GroupeDiscussion grp: accueil.getU().getGroupeDiscussion()){
			nomDiscussion.addElement(grp.getNom());
			discussions.put(grp.getNom(), grp.getDiscussion());
			ArrayList<Utilisateur> dest = new ArrayList<Utilisateur>();
			for (Utilisateur u : grp.getListeUser()){
				dest.add(u);
			}
			dest.add(grp.getModerateur());
			dest.remove(accueil.getU());
			destinataires.put(grp.getDiscussion(),dest);
		}
		
		// Boutton retour
		
		this.retour = new JButton ("Retour");
		retour.setBounds (100,40,200,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		this.add(retour);
		
		
		// panel choix discussion
		
		JPanel panelGauche = new JPanel ();
		panelGauche.setBackground(Fenetre.BLEU_CIEL);
		
		panelGauche.setBounds(0, 0, 400, 600);
		
		panelGauche.setLayout(null);
		
		ScrollPane choixDiscussion = new ScrollPane();
		
		choixDiscussion.setBounds(40, 100, 320, 400);
		
		choix = new JList<String>(nomDiscussion);
		choix.setForeground(Fenetre.BLEU_CIEL);
		choix.addListSelectionListener(this);
		
		choixDiscussion.add(choix);
		
		panelGauche.add(choixDiscussion);
		
		
		this.add(panelGauche);
		
			
			// panel discussion
			
			affichageDiscussion = new ScrollPane();
			
			affichageDiscussion.setBounds(400,0,600,400);
			
			lesMessages = new Vector<Message>();
			
			mesMessages = new JList<Message>(lesMessages);
			
			mesMessages.setCellRenderer(new RenduListeMessage());
			
			affichageDiscussion.add(mesMessages);
			
			this.add(affichageDiscussion);
			
			// champ saisie
			
			JPanel panelEnvoie = new JPanel();
			panelEnvoie.setBackground(Fenetre.BLEU_CIEL);
			panelEnvoie.setBounds(400,400,600,200);
			panelEnvoie.setLayout(null);
			
			saisie = new JTextArea();
			saisie.setBounds(10,10,380, 180);
			saisie.setLineWrap(true);
			saisie.setWrapStyleWord(true);
			
			envoie = new JButton ("Envoyer");
			envoie.setBackground(Fenetre.BLEU_CIEL);
			envoie.setForeground(Color.white);
			envoie.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			envoie.addActionListener(this);
			envoie.setBounds(410, 150, 180, 30);
			
			
			prioritaire = new JCheckBox ("Prioritaire");
			prioritaire.setForeground(Color.WHITE);
			prioritaire.setBackground(Fenetre.BLEU_CIEL);
			prioritaire.setBounds(410, 0, 180, 30);
			
			delaiExpiration = new JCheckBox ("Délai d'expiration");
			delaiExpiration.setForeground(Color.WHITE);
			delaiExpiration.setBackground(Fenetre.BLEU_CIEL);
			delaiExpiration.setBounds(410, 30, 180, 30);
			
			accuse = new JCheckBox ("Accusé de réception");
			accuse.setForeground(Color.WHITE);
			accuse.setBackground(Fenetre.BLEU_CIEL);
			accuse.setBounds(410, 60, 180, 30);
			
			chiffrement = new JCheckBox ("Chiffrement");
			chiffrement.setForeground(Color.WHITE);
			chiffrement.setBackground(Fenetre.BLEU_CIEL);
			chiffrement.setBounds(410, 90, 180, 30);
			
			panelEnvoie.add(prioritaire);
			panelEnvoie.add(delaiExpiration);
			panelEnvoie.add(accuse);
			panelEnvoie.add(chiffrement);
			panelEnvoie.add(envoie);
			panelEnvoie.add(saisie);
			
			this.add(panelEnvoie);
			
		
	}
	
	/**
	 * tracer des forme sur l'écran
	 */
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
	}

	/**
	 * pour associer des fonctions aux composants
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==retour){
			accueil.refresh();
		}
		if (e.getSource()==envoie){
			ArrayList<Option> options = new ArrayList<Option>();
			if (chiffrement.isSelected()){
				options.add(new Chiffrement());
			}
			if (accuse.isSelected()){
				HashMap<Utilisateur,Boolean> envoieAccuse = new HashMap<Utilisateur,Boolean> ();
				Discussion selected = discussions.get(choix.getSelectedValue());
				for (Utilisateur u : destinataires.get(selected)){
					envoieAccuse.put(u, false);
				}
				envoieAccuse.remove(accueil.getU());
				for (Utilisateur u :envoieAccuse.keySet()){
					System.out.println(u.getNdc());
				}
				options.add(new AccuseReception(envoieAccuse));
			}
			if (prioritaire.isSelected()){
				options.add(new Prioritaire());
			}
			if (delaiExpiration.isSelected()){
				String nbJour = JOptionPane.showInputDialog(this, "Saisissez le délai d'expiration (en jour)");
				try{
					int nbJourClean = Integer.parseInt(nbJour);
					GregorianCalendar cal = new GregorianCalendar();
					cal.add(Calendar.DAY_OF_MONTH,nbJourClean); 
					String dateButoir=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(cal.getTime());
					options.add(new DelaiExpiration(dateButoir));
				}catch (IllegalArgumentException exc){
					JOptionPane.showMessageDialog(this, "ErreurSaisie");
				}
				
			}
			String dateCourante=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).format(new Date());
			Message msg = new Message(accueil.getU(), saisie.getText(),dateCourante, options);
			try {
				Discussion selected = discussions.get(choix.getSelectedValue());
				ArrayList<Utilisateur> dest = destinataires.get(selected);
				dest.remove(accueil.getU());
				Service.envoieMessage(selected,msg,dest);
				lesMessages.add(msg);
				mesMessages.setListData(lesMessages);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage());
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this,e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	public void valueChanged(ListSelectionEvent event) {
		Discussion selected = discussions.get(choix.getSelectedValue());
		try {
			Service.vuPar(selected,accueil.getU());
			lesMessages.clear();
			for (Message msg : selected.getMessages()){
				lesMessages.addElement(msg);
			}
			mesMessages.setListData(lesMessages);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
