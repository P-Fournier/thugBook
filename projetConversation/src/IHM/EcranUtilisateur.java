package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Scrollbar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domaine.CategorieCI;
import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class EcranUtilisateur extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8523863168389588727L;
	
	//private ArrayList<JPanel> ecransUtilisateur ;
	
	private JLabel nom;
	
	private JLabel prenom;
	
	private JLabel ndc;
	
	private Utilisateur u;
	
	private int maxHeight;
	
	private int maxWidth;

	public EcranUtilisateur (Utilisateur u){
		
		this.u = u ;
		
		maxWidth = this.getWidth();
		
		//INFORMATIONS GENERALES
		this.add(new Scrollbar());
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		nom = new JLabel ("Nom : "+u.getNom());
		prenom = new JLabel("Prénom : "+u.getPrenom());
		ndc = new JLabel ("Nom de compte : "+u.getNdc());
		ndc.setForeground(Color.white);
		ndc.setBounds(50, 50, 150, 30);
		nom.setForeground(Color.white);
		nom.setBounds(50, 100, 150, 30);
		prenom.setForeground(Color.white);
		prenom.setBounds(50,150,150,30);
		
		this.add(nom);
		this.add(prenom);
		this.add(ndc);
		
		JLabel identi = new JLabel("Identité");
		
		identi.setBounds(220, 35,150, 10);
		identi.setForeground(new Color(44,117,255));
		
		this.add(identi);
		
		JLabel centreInte = new JLabel ("Centres d'intérêts");
		
		centreInte.setBounds(740, 35,150, 10);
		centreInte.setForeground(new Color(44,117,255));
		
		this.add(centreInte);
		
		
		int y = 60;
		
		for (CategorieCI cate : u.getListeInteret().keySet()){
			
			JLabel labelCate = new JLabel (cate.getNom());
			labelCate.setBounds(520, y, 150, 30);
			labelCate.setForeground(new Color(44,117,255));
			this.add(labelCate);
			for (SousCategorieCI sscate : u.getListeInteret().get(cate)){
				y+= 50;
				JLabel labelSousCate = new JLabel (sscate.getNom());
				labelSousCate.setBounds(650, y, 150, 30);
				labelSousCate.setForeground(Color.white);
				this.add(labelSousCate);
			}
			y+= 60;
			
		}
		
	}
	
	public void paintComponent (Graphics g){
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(new Color(44,117,255));
		g.fillRoundRect(40, 40, 400, 150,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(200,20,200,40,50,50);
		g.setColor(new Color(44,117,255));
		g.drawRoundRect(200,20,200,40,50,50);
		
		g.fillRoundRect(460,40,500,(50*u.nbCI())+(60*u.getListeInteret().size())+10,50,50);
		
		g.setColor(Color.white);
		g.fillRoundRect(720,20,200,40,50,50);
		g.setColor(new Color(44,117,255));
		g.drawRoundRect(720,20,200,40,50,50);
		
		g.setColor(Color.white);
		
		int y = 50;
		
		for (CategorieCI cate : u.getListeInteret().keySet()){
			int longueurCate = (50*(u.getListeInteret().get(cate).size()+1));
			g.drawRoundRect(470,y,480,longueurCate,50,50);
			g.fillRoundRect(500,y+10,100,30,25,25);
			y = longueurCate+y+10; // 10 pour le décalage
		}
		
		if (y>maxHeight){
			maxHeight = y+10;
		}
		
		this.setPreferredSize(new Dimension(maxWidth,maxHeight));
		
	}

}
