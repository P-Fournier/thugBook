package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranAdministrateurGroupe extends Ecran implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4498251357712963802L;
	private Fenetre fen;
	private EcranAdministrateur ecranAdmin;
	
	public EcranAdministrateurGroupe (Fenetre fen, EcranAdministrateur ecranAdmin){
		this.fen = fen ;
		this.ecranAdmin = ecranAdmin; 
	}
	@Override
	public void refresh() {
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
