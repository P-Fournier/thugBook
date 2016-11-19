package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	/*public Discussion findById (int idC){
		//String req = "";
		//PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(arg0);
		
	}*/

	public void supprimer(int idC) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM Discussion WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req); 
		ps.setInt(1, idC);
		ps.executeUpdate();
	}

}
