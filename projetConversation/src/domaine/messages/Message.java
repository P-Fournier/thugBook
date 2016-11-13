package domaine.messages;

import java.sql.Time;
import java.util.ArrayList;

import domaine.Utilisateur;

public abstract class Message {
	
	private Utilisateur expediteur;
	private int id;
	private String contenu;
	private Time dateEnvoie;
	private ArrayList<Option> options;
}
