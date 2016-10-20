package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Utilisateur;

public class NotificationMapper {
	
	public static NotificationMapper inst;
	public static int id;
	
	public static NotificationMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new NotificationMapper();
		}
		return inst;
	}
	
	public NotificationMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
	}
	
	private int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Notification";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	/**
	 * ajoute la notification msg à destination de l'utilisateur u à la base
	 * @param u
	 * @param msg
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insert (Utilisateur u , String msg) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Notification VALUES (?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,id);
		ps.setString(2, msg);
		ps.setInt(3,u.getIdU());
		ps.executeQuery();
	}
	
	/**
	 * restitue les notifications de l'utilisateur u
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> restituerNotification(Utilisateur u) throws ClassNotFoundException, SQLException{
		ArrayList<String> result = new ArrayList<String> ();
		String req = "SELECT msg FROM Notification WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			result.add(rs.getString("msq"));
		}
		return result;
	}
	
}
