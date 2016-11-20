package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Utilisateur;
import domaine.messages.Discussion;
import domaine.notification.Notification;
import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationDiscussion;
import domaine.notification.NotificationSimple;
import domaine.notification.VisiteurNotification;

public class NotificationMapper extends VisiteurNotification{
	
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
	public void insert (Notification n) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Notification VALUES (?,?,?,?,now())";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,id);
		ps.setString(2, n.getMessage());
		ps.setInt(3,n.getDestinataire().getIdU());
		ps.setBoolean(4,false);
		ps.executeUpdate();
		
		visiter(n);
		
		id ++;
	}
	
	/**
	 * restitue les notifications de l'utilisateur u
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Notification> restituerNotification(Utilisateur u) throws ClassNotFoundException, SQLException{
		ArrayList<Notification> result = new ArrayList<Notification> ();
		String req = "SELECT id , message , vue , dateEnvoie FROM Notification WHERE idU = ? order by vue , dateEnvoie desc";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			int idN = rs.getInt("id");
			String message = rs.getString("message");
			boolean vue = rs.getBoolean("vue");
			String dateEnvoie = rs.getString("dateEnvoie");
			Discussion discussion = NotificationMapper.getInstance().findDiscussionNotifiée(idN);
			if (discussion != null){
				result.add(new NotificationDiscussion(message,vue,idN,dateEnvoie,u,discussion));
			}else{
				Utilisateur demandeur = NotificationMapper.getInstance().findDemandeur(idN);
				if (demandeur != null){
					result.add(new NotificationDemandeAmi(message,vue,idN,dateEnvoie,u,demandeur));
				}else{
					result.add(new NotificationSimple(message,vue,idN,dateEnvoie,u));
				}
			}
			}
		return result;
	}

	private Utilisateur findDemandeur(int idN) throws ClassNotFoundException, SQLException {
		String req = "SELECT idD FROM NotificationDemandeAmi WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idN);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			int idD = rs.getInt("idD");
			return UtilisateurMapper.getInstance().findById(idD);
		}else{
			return null;
		}
	}

	private Discussion findDiscussionNotifiée(int idN) throws ClassNotFoundException, SQLException {
		String req = "SELECT idD FROM NotificationDiscussion WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idN);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return MessageMapper.getInstance().findByIdDiscussion(rs.getInt("idD"));
		}
		return null;
	}

	public void update(Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM Notification WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ps.executeUpdate();
		
		req = "INSERT INTO Notification VALUES (?,?,?,?,?)";
		for (Notification n : u.getNotifications()){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, n.getId());
			ps.setString(2, n.getMessage());
			ps.setInt (3,u.getIdU());
			ps.setBoolean(4, n.isVue());
			ps.setString(5, n.getDateEnvoie());
			ps.executeUpdate();
		}
	}
	
	@Override
	public void visiter(NotificationSimple n) {
		// rien de plus à faire pour la notification simple
	}
	
	@Override
	public void visiter(NotificationDiscussion n) throws ClassNotFoundException, SQLException {
		String req = "INSERT INTO NotificationDiscussion VALUES (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ps.setInt(2, n.getDiscussion().getId());
		ps.executeUpdate();
	}

	@Override
	public void visiter(NotificationDemandeAmi n) throws ClassNotFoundException, SQLException {
		String req = "INSERT INTO NotificationDemandeAmi VALUES (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ps.setInt(2, n.getDemandeur().getIdU());
		ps.executeUpdate();
	}
	
}
