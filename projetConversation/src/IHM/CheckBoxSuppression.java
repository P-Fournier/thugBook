package IHM;

import javax.swing.JCheckBox;

import domaine.CategorieCI;
import domaine.SousCategorieCI;

public class CheckBoxSuppression extends JCheckBox {
	private SousCategorieCI cible;
	private CategorieCI cate;
	
	public CheckBoxSuppression (SousCategorieCI cible,CategorieCI cate){
		super(cible.getNom());
		this.cible = cible;
		this.cate = cate;
	}

	public SousCategorieCI getCible() {
		return cible;
	}

	public void setCible(SousCategorieCI cible) {
		this.cible = cible;
	}

	public CategorieCI getCate() {
		return cate;
	}

	public void setCate(CategorieCI cate) {
		this.cate = cate;
	}
	
	
}
