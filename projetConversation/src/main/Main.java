package main;

import java.sql.SQLException;

import persistence.DBConfig;
import IHM.EcranDiscussion;
import IHM.Fenetre;
import java.util.*;

import domaine.Utilisateur;
import domaine.messages.ComparateurMessage;
import domaine.messages.Discussion;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;


public class Main {
	public static void main (String [] args) throws InterruptedException, ClassNotFoundException, SQLException{
		
		DBConfig.getInstance();
		new Fenetre();
	}
}

