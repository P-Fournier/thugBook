package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConfig {
static Scanner sc = new Scanner (System.in);
	
	static private DBConfig inst;
	
	static public DBConfig getInstance(){
		if (inst==null){
			inst = new DBConfig ();
		}
		return inst;
	}
	
	private String url;
	private String JDBC_DRIVER;
	private String user;
	private String password;
	
	public DBConfig (){
		System.out.println("Connection à la base de donnée");
		this.url = "jdbc:mysql://localhost:3306/java";	//URL de connexion à la bdd ici
		this.JDBC_DRIVER="com.mysql.jdbc.Driver";
		System.out.println("User : ");
		this.user = sc.next();
		System.out.println("\nPassword : ");
		this.password = sc.next();
		System.out.println("\n");
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(url,user,password);
	}
}