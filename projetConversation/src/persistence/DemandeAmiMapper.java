package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Utilisateur;
import domaine.notification.NotificationDemandeAmi;
import domaine.notification.NotificationSimple;

public class DemandeAmiMapper {
	
	private static DemandeAmiMapper inst;
	
	public static DemandeAmiMapper getInstance(){
		if (inst == null){
			inst = new DemandeAmiMapper();
		}
		return inst;
	}
	
	/**
	 * permet de soumettre une demande d'ami de l'utilisateur exp à l'utilisateur dest
	 * @param exp
	 * @param dest
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void demandeAmi (Utilisateur exp , Utilisateur dest) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO DemandeAmi Values (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, exp.getIdU());
		ps.setInt(2, dest.getIdU());
		ps.executeUpdate();
		NotificationDemandeAmi n = new NotificationDemandeAmi(exp.getNdc()+" vous a envoyé une demande d'ami",false,dest,exp);
		NotificationMapper.getInstance().insert(n);
	}
	
	/**
	 * restituer les demandes d'amis à destination de l'utilisateur u
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> restituerDemandesRecues(Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "SELECT idExp FROM DemandeAmi WHERE idDest = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		ArrayList<Utilisateur> result = new ArrayList<Utilisateur> ();
		while (rs.next()){
			result.add(UtilisateurMapper.getInstance().findById(rs.getInt("idExp")));
		}
		return result;
	}
	
	/**
	 * restituer les demandes d'amis émise par l'utilisateur u
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> restituerDemandesSoumises(Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "SELECT idDest FROM DemandeAmi WHERE idExp = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		ArrayList<Utilisateur> result = new ArrayList<Utilisateur> ();
		while (rs.next()){
			result.add(UtilisateurMapper.getInstance().findById(rs.getInt("idDest")));
		}
		return result;
	}

	/**
	 * gestion de l'acceptation de la demande d'ami de l'utilisateur exp à l'utilisateur dest
	 * @param dest
	 * @param exp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void accepterDemande(Utilisateur dest, Utilisateur exp) throws ClassNotFoundException, SQLException{
		DemandeAmiMapper.getInstance().delete(exp, dest);
		AmiMapper.getInstance().insert(dest,exp);
		dest.getDemandeAmisRecues().remove(exp);
		NotificationSimple n = new NotificationSimple (dest.getNdc()+" a accepté votre demande d'ami",false,exp);
		NotificationMapper.getInstance().insert(n);
	}

	/**
	 * gestion du refus de la demande d'ami de l'utilisateur exp à l'utilisateur dest
	 * @param dest
	 * @param exp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void refuserDemande(Utilisateur dest, Utilisateur exp) throws ClassNotFoundException, SQLException{
		DemandeAmiMapper.getInstance().delete(exp, dest);
		dest.getDemandeAmisRecues().remove(exp);
		NotificationSimple n = new NotificationSimple (dest.getNdc()+" a refusé votre demande d'ami",false,exp);
		NotificationMapper.getInstance().insert(n);
		
	}
	
	
	/**
	 * suppression de la ligne de la table concernée
	 * @param idDest
	 * @param idExp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void delete(Utilisateur dest , Utilisateur exp) throws ClassNotFoundException, SQLException{
		String req = "DELETE FROM DemandeAmi Where idExp = ? and idDest = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, dest.getIdU());
		ps.setInt(2, exp.getIdU());
		ps.executeUpdate();
	}
	
}
