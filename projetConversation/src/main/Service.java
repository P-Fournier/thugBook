package main;

import java.sql.SQLException;

import javax.swing.JPanel;

import IHM.EcranAdministrateur;
import IHM.EcranUtilisateur;

import persistence.UtilisateurMapper;
import domaine.Utilisateur;

public class Service {

	public static JPanel connection(String text, String password) throws ClassNotFoundException, SQLException , ConnectionException {
		if (text == null || password == null){
			System.out.println("Il faut renseigner tout les champs");
		}
		
		Utilisateur u = UtilisateurMapper.getInstance().connection(text, password);
		
		if (u == null){
			throw new ConnectionException();
		}
		if (UtilisateurMapper.getInstance().isAdministrateur(u)){
			return new EcranAdministrateur(u);
		}else{
			return new EcranUtilisateur(u);
		}
		
	}
	
	
}
