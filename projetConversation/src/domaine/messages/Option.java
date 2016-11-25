package domaine.messages;


public abstract class Option {
	
	/**
	 * utiliser par le visiteurOption
	 * @param v
	 */
	public abstract void accepter (VisiteurOption v);
}
