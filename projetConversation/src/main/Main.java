package main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;

import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;

import persistence.DBConfig;
import IHM.Fenetre;

public class Main {
	public static void main (String [] args) throws InterruptedException, ClassNotFoundException, SQLException{
		DBConfig.getInstance();
		Fenetre.getInstance();
	
	}
}

