package domaine.notification;

import java.sql.SQLException;

public abstract class VisiteurNotification {
	
	/**
	 * sert à l'appel de la bonne fonction visiter
	 * @param v
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void visiter (VisitableNotification v) throws ClassNotFoundException, SQLException{
		v.accepter(this);
	}
	
	
	public abstract void visiter (NotificationSimple n);
	public abstract void visiter (NotificationDiscussion n) throws ClassNotFoundException, SQLException;
	public abstract void visiter (NotificationDemandeAmi n) throws ClassNotFoundException, SQLException;
}
