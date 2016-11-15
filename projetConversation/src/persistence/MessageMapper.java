package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domaine.messages.Message;

public class MessageMapper {
	
	private static int id;
	private static MessageMapper inst;
	
	public MessageMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new MessageMapper();
		}
		return inst;
	}
	
	public MessageMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
	}
	
	public int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Message";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	} 
	
	public void insert(Message m){
		
	}
}
