package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Utilisateur;
import domaine.messages.Discussion;

public class AmiMapper {
	
	private static AmiMapper inst;
	
	public static AmiMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new AmiMapper();
		}
		return inst;
	}
	
	public AmiMapper () throws ClassNotFoundException, SQLException{
		DiscussionMapper.getInstance();
	}
	
	/**
	 * insere une relation d'amitié dans la table
	 * @param idU1
	 * @param idU2
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insert(Utilisateur u1 , Utilisateur u2) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Discussion VALUES (?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, DiscussionMapper.id);
		ps.executeUpdate();
		
		Discussion discute = new Discussion (DiscussionMapper.id);
		u1.getAmis().put(u2,discute);
		u2.getAmis().put(u1,discute);
		
		req = "INSERT INTO DiscussionUtilisateur VALUES (?,?)";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, DiscussionMapper.id);
		ps.setInt(2,u1.getIdU());
		ps.executeUpdate();
		
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, DiscussionMapper.id);
		ps.setInt(2,u2.getIdU());
		ps.executeUpdate();
		
		DiscussionMapper.id ++;
	}
	
	/**
	 * restiue tout les amis de l'utilisateur dans la table
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HashMap<Utilisateur,Discussion> restituerAmis (Utilisateur u) throws ClassNotFoundException, SQLException{
		ArrayList<Integer> idDiscussions = new ArrayList<Integer> ();
		HashMap<Utilisateur,Discussion> result = new HashMap<Utilisateur,Discussion>();
		String req = "SELECT idD FROM DiscussionUtilisateur WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			idDiscussions.add(rs.getInt("idD"));
		}
		
		req = "SELECT idU FROM DiscussionUtilisateur WHERE idD = ? and idU != ?";
		for (Integer i : idDiscussions){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, i);
			ps.setInt(2, u.getIdU());
			rs = ps.executeQuery();
			rs.next();
			Utilisateur ami = UtilisateurMapper.getInstance().findById(rs.getInt("idU"));
			Discussion discussion = MessageMapper.getInstance().findByIdDiscussion(i);
			result.put(ami,discussion);
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
	public void suppressionAmi (Utilisateur u , Utilisateur suppr) throws ClassNotFoundException, SQLException{
		String req = "SELECT d.idD FROM DiscussionUtilisateur d WHERE d.idU = ? and "+
				"d.idD IN (SELECT e.idD FROM DiscussionUtilisateur e WHERE e.idU = ?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ps.setInt(2, suppr.getIdU());
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		int idDiscussionASupprimer = rs.getInt("d.idD");
		
		req = "DELETE FROM Discussion WHERE id = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idDiscussionASupprimer);
		ps.executeUpdate();
		
		u.getAmis().remove(suppr);
		suppr.getAmis().remove(u);
		
	}
}
