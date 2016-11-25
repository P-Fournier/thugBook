package domaine.messages;


public abstract class VisiteurOption {
	
	/**
	 * permet l'appel de la bonne méthode visité
	 * @param o
	 */
	public void accepter (Option o){
		o.accepter(this);
	}
	
	public abstract void visiter(AccuseReception a);
	public abstract void visiter(Chiffrement c);
	public abstract void visiter(Prioritaire p);
	public abstract void visiter(DelaiExpiration d);
	
}
