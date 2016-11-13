package domaine.critere;

import java.util.ArrayList;

public abstract class CritereCompose extends Critere{
	
	protected ArrayList<Critere> criteres;
	
	@Override
	public int nbCritereSimple() {
		int result = 0;
		for (Critere c : criteres){
			result += c.nbCritereSimple();
		}
		return result;
	}
}
