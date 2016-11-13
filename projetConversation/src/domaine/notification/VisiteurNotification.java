package domaine.notification;

import java.sql.SQLException;

public abstract class VisiteurNotification {
	public void visiter (VisitableNotification v) throws ClassNotFoundException, SQLException{
		v.accepter(this);
	}
	
	public abstract void visiter (NotificationSimple n);
	public abstract void visiter (NotificationDiscussion n) throws ClassNotFoundException, SQLException;
	public abstract void visiter (NotificationDemandeAmi n) throws ClassNotFoundException, SQLException;
}
