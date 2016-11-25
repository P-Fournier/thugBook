package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.ScrollPane;
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
import javax.swing.JLabel;
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

import domaine.Utilisateur;
import domaine.messages.AccuseReception;
import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;
import domaine.messages.Discussion;
import domaine.messages.GroupeDiscussion;
import domaine.messages.DiscussionPrive;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;

public class EcranDiscussions extends JPanel implements ActionListener, ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466304023765997464L;
	
	private EcranUtilisateur accueil;					// écran d'accueil
	private HashMap<Discussion,ArrayList<Utilisateur>> destinataires;	// garde en mémoire les utilisateur destinataires d'un message envoyé dans la discussion passé en clé
	
	private JButton retour;			// retour à l'écran d'accueil 
	
	@SuppressWarnings("rawtypes")
	private JList choix;	// liste permettant de selectionner la discussion à laquelle on souhaite accéder
	private ScrollPane affichageDiscussion;		//permet l'affichage de la discussion
	private Vector<Message> lesMessages;		//ensemble des messages à afficher
	private Vector<GroupeDiscussion> groupes;
	private Vector<Utilisateur> amis;
	private JButton goAmi;
	private JButton goGroupe;
	private Discussion choose;
	private ScrollPane membres;
	private JPanel panelGauche;
	
	private JTextArea saisie;			// permet de saisir le message à envoyer
	private JButton envoie;				// permet l'envoie du message
	private JCheckBox accuse;			// donner l'option accusé de reception au message envoyé
	private JCheckBox prioritaire;		// donner l'option prioritaire au message envoyé
	private JCheckBox chiffrement;		// donner l'option de chiffrement au message envoyé
	private JCheckBox delaiExpiration;	// donner l'option de délai d'expiration au message envoyé
	private JList<Message> mesMessages;	// les messages de la discussion choisi
	
	@SuppressWarnings("rawtypes")
	public EcranDiscussions(Fenetre fen, EcranUtilisateur accueil) {
		this.membres = null;
		this.accueil = accueil;
		this.destinataires = new HashMap<Discussion,ArrayList<Utilisateur>>();
		fen.changerTitre("Réseau social - Mes discussions");
		this.setLayout(null);
	
		groupes = new Vector<GroupeDiscussion>(accueil.getU().getGroupeDiscussion());
		amis = new Vector<Utilisateur>(accueil.getU().getAmis().keySet());
		
		for (Utilisateur u : accueil.getU().getAmis().keySet()){
			Discussion discussionPrive = accueil.getU().getAmis().get(u);
			ArrayList<Utilisateur> dest = new ArrayList<Utilisateur>();
			dest.add(u);
			destinataires.put(discussionPrive,dest);
		}
		
		for (GroupeDiscussion grp: accueil.getU().getGroupeDiscussion()){
			ArrayList<Utilisateur> dest = new ArrayList<Utilisateur>();
			for (Utilisateur u : grp.getListeUser()){
				dest.add(u);
			}
			dest.add(grp.getModerateur());
			dest.remove(accueil.getU());
			destinataires.put(grp,dest);
		}
		
		// Boutton retour
		
		retour = new JButton ("Retour");
		retour.setBounds (100,40,200,30);
		retour.setForeground(Color.WHITE);
		retour.setBackground(Fenetre.BLEU_CIEL);
		retour.addActionListener(this);
		retour.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		
		this.add(retour);
		
		// panel choix discussion
		
		panelGauche = new JPanel ();
		ScrollPane choixDiscussion = new ScrollPane();
		
		panelGauche.setBackground(Fenetre.BLEU_CIEL);
		panelGauche.setBounds(0, 0, 400, 600);
		panelGauche.setLayout(null);
		
		choixDiscussion.setBounds(40, 150, 320, 200);
		
		choix = new JList();
		
		choix.setForeground(Fenetre.BLEU_CIEL);
		choix.addListSelectionListener(this);
		
		choixDiscussion.add(choix);
		panelGauche.add(choixDiscussion);
		
		goAmi = new JButton("Amis");
		goGroupe = new JButton ("Groupes");
		
		goAmi.setBounds (40,100,150,30);
		goAmi.setForeground(Color.WHITE);
		goAmi.setBackground(Fenetre.BLEU_CIEL);
		goAmi.addActionListener(this);
		goAmi.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		goGroupe.setBounds (210,100,150,30);
		goGroupe.setForeground(Color.WHITE);
		goGroupe.setBackground(Fenetre.BLEU_CIEL);
		goGroupe.addActionListener(this);
		goGroupe.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		panelGauche.add(goAmi);
		panelGauche.add(goGroupe);
		
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
		envoie.setEnabled(false);
		
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

	public EcranDiscussions(Fenetre fen, EcranUtilisateur accueil,
			Discussion discussion) {
		this(fen,accueil);
		envoie.setEnabled(true);
		if (accueil.getU().getGroupeDiscussion().contains(discussion)){
			GroupeDiscussion grp = (GroupeDiscussion) discussion;
			membres = new ScrollPane();
			
			Vector<Utilisateur> lesMembres = new Vector<Utilisateur>(grp.getListeUser());
			lesMembres.addElement(grp.getModerateur());
			
			JList<Utilisateur> listMembres = new JList<Utilisateur>(lesMembres);
			
			membres.add(listMembres);
			membres.setBounds(40, 400, 320, 100);
			
			JLabel labelMembres = new JLabel ("Membres du groupe");
			
			labelMembres.setBounds(140, 350,120, 30);
			labelMembres.setForeground(Color.WHITE);
			
			panelGauche.add(membres);
			panelGauche.add(labelMembres);
			
			choose = (GroupeDiscussion) grp;
		}else{
			choose = (DiscussionPrive) discussion;
			membres = null;
		}
		try {
			Service.vuPar(choose,accueil.getU());
			lesMessages.clear();
			for (Message msg : choose.getMessages()){
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

	/**
	 * tracer des formes sur l'écran
	 */
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
	}

	/**
	 * pour associer des fonctions aux composants
	 */
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		// permet de selectionner la liste des amis
		if (e.getSource()==goAmi){
			choix.setListData(amis);
		}
		// permet de selectionner la liste des groupes
		if (e.getSource()==goGroupe){
			choix.setListData(groupes);
		}
		// permet le retour à la page d'accueil
		if (e.getSource()==retour){
			accueil.refresh();
		}
		// permet l'envoie d'un message
		if (e.getSource()==envoie){
			if (saisie.getText()!= null){
				ArrayList<Option> options = new ArrayList<Option>();
				if (chiffrement.isSelected()){
					options.add(new Chiffrement());
				}
				if (accuse.isSelected()){
					HashMap<Utilisateur,Boolean> envoieAccuse = new HashMap<Utilisateur,Boolean> ();
					/*Discussion selected = discussions.get(choix.getSelectedValue());
					for (Utilisateur u : destinataires.get(selected)){
						envoieAccuse.put(u, false);
					}*/
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
					ArrayList<Utilisateur> dest = destinataires.get(choose);
					dest.remove(accueil.getU());
					Service.envoieMessage(choose,msg,dest);
					lesMessages.add(msg);
					mesMessages.setListData(lesMessages);
					saisie.setText(null);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(this,e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
	}

	/*va permettre de changer l'affichage des messages et de passé tout les messages de
	la discussion ayant un accusé de reception à vu pour l'utilisateur courant */
	public void valueChanged(ListSelectionEvent event) {
		Object selected = choix.getSelectedValue();
		if (membres != null){
			panelGauche.remove(membres);
		}
		if (selected != null){
			envoie.setEnabled(true);
			if (accueil.getU().getAmis().containsKey(selected)){
				choose = accueil.getU().getAmis().get(selected);
				membres = null;
			}else{
				GroupeDiscussion grp = (GroupeDiscussion) selected;
				membres = new ScrollPane();
				
				Vector<Utilisateur> lesMembres = new Vector<Utilisateur>(grp.getListeUser());
				lesMembres.addElement(grp.getModerateur());
				
				JList<Utilisateur> listMembres = new JList<Utilisateur>(lesMembres);
				
				membres.add(listMembres);
				membres.setBounds(40, 400, 320, 100);
				
				JLabel labelMembres = new JLabel ("Membres du groupe");
				
				labelMembres.setBounds(140, 350,120, 30);
				labelMembres.setForeground(Color.WHITE);
				
				panelGauche.add(membres);
				panelGauche.add(labelMembres);
				
				choose = (GroupeDiscussion) grp;
			}
			try {
				Service.vuPar(choose,accueil.getU());
				lesMessages.clear();
				for (Message msg : choose.getMessages()){
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
		}else{
			envoie.setEnabled(false);
		}
	}
}
