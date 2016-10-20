package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Utilisateur;

public class AmiMapper {
	
	private static AmiMapper inst;
	
	public static AmiMapper getInstance(){
		if (inst == null){
			inst = new AmiMapper();
		}
		return inst;
	}
	
	/**
	 * insere une relation d'amitié dans la table
	 * @param idU1
	 * @param idU2
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insert(Utilisateur u1 , Utilisateur u2) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Ami VALUES (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u1.getIdU());
		ps.setInt(2, u2.getIdU());
		ps.executeUpdate();
		u1.getAmis().add(u2);
		u2.getAmis().add(u1);
	}
	
	/**
	 * restiue tout les amis de l'utilisateur dans la table
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> restituerAmis (Utilisateur u) throws ClassNotFoundException, SQLException{
		ArrayList<Utilisateur> result = new ArrayList<Utilisateur> ();
		String req = "SELECT idA FROM Ami WHERE idB = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			result.add(UtilisateurMapper.getInstance().findById(rs.getInt("idA")));
		}
		req = "SELECT idB FROM Ami WHERE idA = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		rs = ps.executeQuery();
		while (rs.next()){
			result.add(UtilisateurMapper.getInstance().findById(rs.getInt("idB")));
		}
		return result;
	}
	
	/**
	 * supprime une relation d'amitié entre deux utilisateur
	 * @param u1
	 * @param u2
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void suppressionAmi (Utilisateur u1 , Utilisateur u2) throws ClassNotFoundException, SQLException{
		String req = "DELETE FROM Ami WHERE idA = ? and idB = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u1.getIdU());
		ps.setInt(2, u2.getIdU());
		ps.executeUpdate();
		
		req = "DELETE FROM Ami WHERE idA = ? and idB = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(2, u1.getIdU());
		ps.setInt(1, u2.getIdU());
		ps.executeUpdate();
		
		u1.getAmis().remove(u2);
		u2.getAmis().remove(u1);
	}
}
