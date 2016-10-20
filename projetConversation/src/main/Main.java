package main;

import java.sql.SQLException;

import persistence.UtilisateurMapper;
import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class Main {
	public static void main (String [] args){
		try {
			Utilisateur u = UtilisateurMapper.getInstance().connection("TEST","azerty");
			System.out.println(u.getNom()+" "+u.getPrenom());
			for (SousCategorieCI scci : u.getListeInteret()){
				System.out.println(scci.getNom());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
