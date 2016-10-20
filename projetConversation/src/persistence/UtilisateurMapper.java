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
	
	public static UtilisateurMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new UtilisateurMapper();
		}
		return inst;
	}
	
	public UtilisateurMapper() throws ClassNotFoundException, SQLException{
		id = getCurrentId();
	}
	
	private int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Utilisateur";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	public Utilisateur findById (int id) throws ClassNotFoundException, SQLException{
		String req = "SELECT nom , prenom , ndc FROM Utilisateur WHERE id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String ndc = rs.getString("ndc");
			ArrayList<SousCategorieCI> ci = SousCategorieCIMapper.getInstance().findByUser(id);
			Utilisateur u = new Utilisateur (id,nom,prenom,ndc,ci);
			return u;
		}
		return null;
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
			ArrayList<Utilisateur> demande = DemandeAmiMapper.getInstance().restituerDemande(u);
			u.setDemandeAmis(demande);
			ArrayList<Utilisateur> amis = AmiMapper.getInstance().restituerAmis(u);
			u.setAmis(amis);
			return u;
		}else{
			return null;
		}
	}

	public void insert (String ndc, String password, String nom, String prenom) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Utilisateur Values (?,?,?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ps.setString(2,ndc);
		ps.setString(3,nom);
		ps.setString(4, prenom);
		ps.setString(5, password);
		ps.executeUpdate();
		id ++;
	}
	
}
