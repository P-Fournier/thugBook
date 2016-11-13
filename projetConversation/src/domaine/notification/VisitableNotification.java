package domaine.notification;

import java.sql.SQLException;

public interface VisitableNotification {
	public void accepter (VisiteurNotification v) throws ClassNotFoundException, SQLException;
}
