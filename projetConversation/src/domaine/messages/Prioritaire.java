package domaine.messages;


public class Prioritaire extends Option{

	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
	}

}
