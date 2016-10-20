package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class UtilisateurMapper {
	
	private static UtilisateurMapper inst;
	private static int id;
	
	public static UtilisateurMapper getInstance(){
		if (inst == null){
			inst = new UtilisateurMapper();
		}
		return inst;
	}
	
	public UtilisateurMapper(){
		id = 0;
	}
	
	public Utilisateur connection (String ndc , String password) throws ClassNotFoundException, SQLException{
		String req = "SELECT id , nom , prenom FROM Utilisateur WHERE  ndc = ? and password = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,ndc);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			int id = rs.getInt("id");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			ArrayList<SousCategorieCI> ci = SousCategorieCIMapper.getInstance().findByUser(id);
			Utilisateur u = new Utilisateur (id,nom,prenom,ndc,password,ci);
			return u;
		}else{
			return null;
		}
	}
}
