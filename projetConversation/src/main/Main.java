package main;

import java.sql.SQLException;

import persistence.DBConfig;
import IHM.Fenetre;

public class Main {
	public static void main (String [] args) throws InterruptedException, ClassNotFoundException, SQLException{
		DBConfig.getInstance();
		Fenetre.getInstance();
	
	}
}

