package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import persistence.virtualproxy.VirtualDiscussionPrive;

import domaine.Utilisateur;
import domaine.messages.Discussion;
import domaine.messages.Message;

public class DiscussionMapper{

	public static int id;
	public static DiscussionMapper inst;
	public static HashMap<Integer,Discussion> loaded ;
	
	public static DiscussionMapper getInstance() throws ClassNotFoundException, SQLException {
		if (inst == null){
			inst = new DiscussionMapper();
		}
		return inst;
	}
	
	public DiscussionMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
		loaded = new HashMap<Integer,Discussion>();
	}
	
	public int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Discussion";
		PreparedStatement ps  = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}

	/**
	 * supprime la discussion
	 * @param d
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprimer(Discussion d) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM Discussion WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req); 
		ps.setInt(1, d.getId());
		ps.executeUpdate();
		loaded.remove(d.getId());
	}
	
	/**
	 * passe tout les messages de la discussion à vu pour l'utilisateur
	 * @param selected
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void vuPar(Discussion selected, Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "UPDATE AccuseDeReception SET vu = true WHERE idM = ? and idU = ? ";
		for (Message msg : selected.getMessages()){
			PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, msg.getId());
			ps.setInt(2,u.getIdU());
			ps.executeUpdate();
		}
	}

	public Discussion findBy(int id) throws ClassNotFoundException, SQLException {
		if (loaded.containsKey(id)){
			return loaded.get(id);
		}
		String req = "SELECT idD FROM DiscussionUtilisateur WHERE idD = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Discussion result;
		if (rs.next()){
			result = new VirtualDiscussionPrive (id);
		}else{
			result = GroupeDiscussionMapper.getInstance().findById(id);
		}
		loaded.put(id,result);
		return result;
	}

}
