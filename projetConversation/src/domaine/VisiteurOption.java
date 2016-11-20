package domaine;

import domaine.messages.*;
import domaine.messages.Option;

public abstract class VisiteurOption {
	public void accepter (Option o){
		o.accepter(this);
	}
	public abstract void visiter(AccuseReception a);
	public abstract void visiter(Chiffrement c);
	public abstract void visiter(Prioritaire p);
	public abstract void visiter(DelaiExpiration d);
	
}
