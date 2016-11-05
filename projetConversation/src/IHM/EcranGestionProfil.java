package IHM;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Service;

import java.sql.SQLException;
import java.util.*;

import domaine.CategorieCI;
import domaine.SousCategorieCI;


public class EcranGestionProfil extends JPanel implements ActionListener{
	
	private Fenetre fen;
	private EcranUtilisateur accueil;
	private JTextField nom;
	private JTextField prenom;
	private HashMap<CategorieCI,ArrayList<SousCategorieCI>> ciASupprimer ;
	private int maxHeight;
	private int maxWidth;
	private JComboBox comboCate;
	private JComboBox comboCi; 
	private JButton bouttonSuppression;
	private JButton bouttonAjout;
	private static EcranGestionProfil inst;
	
	public static EcranGestionProfil getInstance(Fenetre fen , EcranUtilisateur accueil){
		inst = new EcranGestionProfil(fen,accueil);
		return inst;
	}
	
	public EcranGestionProfil(Fenetre fen, EcranUtilisateur accueil){
		this.fen = fen;
		this.accueil=accueil;
		this.maxWidth = this.getWidth();
		this.maxHeight = this.getHeight();
		this.ciASupprimer = new HashMap<CategorieCI,ArrayList<SousCategorieCI>>();
		
		
		this.add(new Scrollbar());
		this.setLayout(null);
		
		//identité
		
		JLabel identi = new JLabel("Identité");
		
		identi.setBounds(220, 35,150, 10);
		identi.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(identi);
		
		JLabel labelNom = new JLabel ("Nom : ");
		JLabel labelPrenom = new JLabel("Prénom : ");
		JLabel ndc = new JLabel ("Nom de compte : "+accueil.getU().getNdc());
		nom = new JTextField(accueil.getU().getNom());
		prenom = new JTextField(accueil.getU().getPrenom());
		ndc.setForeground(Color.white);
		ndc.setBounds(90, 60, 150, 30);
		labelNom.setForeground(Color.white);
		labelNom.setBounds(90, 110, 150, 30);
		labelPrenom.setForeground(Color.white);
		labelPrenom.setBounds(90,160,150,30);
		nom.setBounds(190, 110, 150, 30);
		prenom.setBounds(190, 160, 150, 30);
		
		this.add(nom);
		this.add(prenom);
		this.add(ndc);
		this.add(labelPrenom);
		this.add(labelNom);
		
		// carré ajoute centre d'intérêt
		
		JLabel ajoutCi = new JLabel("Ajout intérêt");
		
		ajoutCi.setBounds(220, 235,150, 10);
		ajoutCi.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(ajoutCi);
		
		JLabel labelComboCate = new JLabel ("Catégorie :");
		JLabel labelComboCi = new JLabel ("Centre d'interët :");
		this.comboCate = new JComboBox ();
		this.comboCi = new JComboBox();
		this.bouttonAjout = new JButton("Ajouter");
		
		labelComboCate.setForeground(Color.white);
		labelComboCate.setBounds(90, 270, 150, 30);
		labelComboCi.setForeground(Color.white);
		labelComboCi.setBounds(90, 320, 150, 30);
		comboCate.setBounds(240, 270, 150, 30);
		try {
			for (String s : Service.recupererLesCategories()){
				comboCate.addItem(s);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboCate.setSelectedItem(null);
		comboCate.addActionListener(this);
		comboCi.setBounds(240, 320, 150, 30);
		bouttonAjout.setBounds(280, 360, 150, 30);
		bouttonAjout.setForeground(Color.white);
		bouttonAjout.setBackground(Fenetre.BLEU_CIEL);
		bouttonAjout.addActionListener(this);
		bouttonAjout.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
		
		this.add(labelComboCate);
		this.add(labelComboCi);
		this.add(comboCate);
		this.add(comboCi);
		this.add(bouttonAjout);

		// carré suppression centre d'intéret
		
		JLabel centreInte = new JLabel ("Centres d'intérêts");
		
		centreInte.setBounds(740, 35,150, 10);
		centreInte.setForeground(Fenetre.BLEU_CIEL);
		
		this.add(centreInte);
		
		int y = 60;
		int x = 520;
		
		if (accueil.getU().getListeInteret().size()==0){
			JLabel vide = new JLabel ("Pas de centre d'intérêt");
			vide.setBounds(520, 60, 190, 30);
			vide.setForeground(Color.white);
			this.add(vide);
		}else{
			for (CategorieCI cate : accueil.getU().getListeInteret().keySet()){
				
				JLabel labelCate = new JLabel (cate.getNom());
				labelCate.setBounds(520, y, 150, 30);
				labelCate.setForeground(Fenetre.BLEU_CIEL);
				this.add(labelCate);
				for (SousCategorieCI sscate : accueil.getU().getListeInteret().get(cate)){
					if (accueil.getU().getListeInteret().get(cate).indexOf(sscate)%2 == 0){
						y+= 50;
						x = 520;
					}else{
						x = 710;
					}
					CheckBoxSuppression checkBoxSousCate = new CheckBoxSuppression (sscate,cate);
					checkBoxSousCate.setBounds(x, y, 190, 30);
					checkBoxSousCate.setForeground(Color.white);
					checkBoxSousCate.setBackground(Fenetre.BLEU_CIEL);
					checkBoxSousCate.addActionListener(this);
					this.add(checkBoxSousCate);
					
				}
				y+= 60;
				
			}
			this.bouttonSuppression = new JButton("Supprimer");
			bouttonSuppression.setBounds(800,y-10,150,30);
			bouttonSuppression.setForeground(Color.white);
			bouttonSuppression.setBackground(Fenetre.BLEU_CIEL);
			bouttonSuppression.addActionListener(this);
			bouttonSuppression.setBorder(new CompoundBorder(new LineBorder(Color.white),new EmptyBorder(5,15,5,15)));
			this.add(bouttonSuppression);
		}
		
		
	}
	
	public void paintComponent (Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// carré identité
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 40, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,20,200,40,50,50);
		
		// carré ajout centre d'interet
		
		g.setColor(Fenetre.BLEU_CIEL);
		g.fillRoundRect(40, 240, 400, 170,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,220,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(200,220,200,40,50,50);
		
		// carré suppression centre d'interet
		
		int hauteurBoite = 10; 
		
		if (accueil.getU().getListeInteret().size()==0){
			hauteurBoite += 60;
		}else{
			for (CategorieCI cate : accueil.getU().getListeInteret().keySet()){
				hauteurBoite += 60+((accueil.getU().getListeInteret().get(cate).size()/2)*50)+((accueil.getU().getListeInteret().get(cate).size()%2)*50);
			}
		}
			
		g.fillRoundRect(460,40,500,hauteurBoite+50,50,50);
		
		if (hauteurBoite>maxHeight){
			maxHeight = hauteurBoite+10;
		}
		
		g.setColor(Color.white);
		g.fillRoundRect(720,20,200,40,50,50);
		g.setColor(Fenetre.BLEU_CIEL);
		g.drawRoundRect(720,20,200,40,50,50);
			
		g.setColor(Color.white);
				
		int y = 50;
				
		for (CategorieCI cate : accueil.getU().getListeInteret().keySet()){
			int longueurCate = 50*((accueil.getU().getListeInteret().get(cate).size()/2)+(accueil.getU().getListeInteret().get(cate).size()%2)+1);
			g.drawRoundRect(470,y,480,longueurCate,50,50);
			g.fillRoundRect(500,y+10,100,30,25,25);
			y = longueurCate+y+10; // 10 pour le décalage
		}
			
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof CheckBoxSuppression){
			CheckBoxSuppression source = (CheckBoxSuppression) e.getSource();
			ArrayList<SousCategorieCI> sscate;
			if (source.isSelected()){
				if(ciASupprimer.containsKey(source.getCate())){
					sscate = ciASupprimer.get(source.getCate());
				}else{
					sscate = new ArrayList<SousCategorieCI>();
				}
				sscate.add(source.getCible());
				ciASupprimer.put(source.getCate(), sscate);
			}else{
				sscate = ciASupprimer.get(source.getCate());
				sscate.remove(source.getCible());
				if (sscate.isEmpty()){
					ciASupprimer.remove(source.getCate());
				}else{
					ciASupprimer.put(source.getCate(), sscate);
				}
			}
		}
		if (e.getSource()==bouttonSuppression){
			Service.deleteCI(accueil.getU(),ciASupprimer);
			this.accueil=EcranUtilisateur.getInstance(accueil.getU(), fen);
			this.inst = EcranGestionProfil.getInstance(fen, accueil);
			fen.changerEcran(inst);
		}
		if (e.getSource()==comboCate){
			comboCi.removeAllItems();
			try {
				for (String s : Service.recupererLesSousCategories((String)comboCate.getSelectedItem())){
					comboCi.addItem(s);
				}
				comboCi.setSelectedItem(null);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource()==bouttonAjout){
			try {
				CategorieCI categorie = Service.getCategorieByNomLazy((String)comboCate.getSelectedItem());
				SousCategorieCI sousCategorie = Service.getSousCategorieByNom((String)comboCi.getSelectedItem());
				ArrayList<SousCategorieCI> lstCi;
				if(accueil.getU().getListeInteret().containsKey(categorie)){
					lstCi = accueil.getU().getListeInteret().get(categorie);
				}else{
					lstCi = new ArrayList<SousCategorieCI>();
				}
				lstCi.add(sousCategorie);
				accueil.getU().getListeInteret().put(categorie, lstCi);
				this.accueil=EcranUtilisateur.getInstance(accueil.getU(), fen);
				this.inst = EcranGestionProfil.getInstance(fen, accueil);
				fen.changerEcran(inst);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	
}
