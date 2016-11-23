package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domaine.Utilisateur;
import domaine.messages.Discussion;
import domaine.messages.Message;

public class DiscussionMapper {

	public static int id;
	public static DiscussionMapper inst;
	
	public static DiscussionMapper getInstance() throws ClassNotFoundException, SQLException {
		if (inst == null){
			inst = new DiscussionMapper();
		}
		return inst;
	}
	
	public DiscussionMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
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

	public void supprimer(Discussion d) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM Discussion WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req); 
		ps.setInt(1, d.getId());
		ps.executeUpdate();
	}

	public void vuPar(Discussion selected, Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "UPDATE AccuseDeReception SET vu = true WHERE idM = ? and idU = ? ";
		for (Message msg : selected.getMessages()){
			PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, msg.getId());
			ps.setInt(2,u.getIdU());
			ps.executeUpdate();
		}
	}

}
