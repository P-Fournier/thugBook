package domaine.messages;


public class Chiffrement extends Option{

	// FONCTION(S)
	
	@Override
	public void accepter(VisiteurOption v) {
		v.visiter(this);
		
	}


}
