package domaine.messages;

import domaine.VisiteurOption;

public class Prioritaire extends Option{

	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

}
