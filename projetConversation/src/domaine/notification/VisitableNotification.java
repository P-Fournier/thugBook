package domaine.notification;

import java.sql.SQLException;

public interface VisitableNotification {
	/**
	 * utilisé par le VisiteurNotification
	 * @param v
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void accepter (VisiteurNotification v) throws ClassNotFoundException, SQLException;
}
