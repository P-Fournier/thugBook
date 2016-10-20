package main;

import java.sql.SQLException;
import java.util.*;

import persistence.AmiMapper;
import persistence.DemandeAmiMapper;
import persistence.UtilisateurMapper;
import domaine.Utilisateur;

public class Main {
	public static void main (String [] args){
		try {
			Utilisateur u1 = UtilisateurMapper.getInstance().connection("TEST","azerty");
			Utilisateur u2 = UtilisateurMapper.getInstance().connection("LOL","AZERTY");
			
			for (Utilisateur u : u2.getAmis()){
				System.out.println(u);
			}
			
			AmiMapper.getInstance().suppressionAmi(u1, u2);
			
			System.out.println("Suppression effective");
			
			System.out.println("taille : "+u1.getAmis().size());
			
			for (Utilisateur u : u1.getAmis()){
				System.out.println(u);
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
