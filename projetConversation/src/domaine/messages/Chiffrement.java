package domaine.messages;

import domaine.VisiteurOption;

public class Chiffrement extends Option{

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
		
	}


}
