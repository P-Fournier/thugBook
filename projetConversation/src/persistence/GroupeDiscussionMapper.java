package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.GroupeDiscussion;
import domaine.Utilisateur;

public class GroupeDiscussionMapper {
	
	public static GroupeDiscussionMapper inst;
	public static int id;
	
	public static GroupeDiscussionMapper getInstance() throws ClassNotFoundException, SQLException{
		if(inst == null){
			inst = new GroupeDiscussionMapper();
		}
		return inst;
	}
	
	public GroupeDiscussionMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
	}
	
	public int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM GroupeDiscussion";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	/**
	 * créer un groupe de discussion en base de donnée
	 * @param grp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insert(GroupeDiscussion grp) throws SQLException, ClassNotFoundException{
		String req = "INSERT INTO Groupe VALUES (?,?,?) ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		grp.setId(id);
		ps.setInt(1,id);
		ps.setInt(2,grp.getModerateur().getIdU());
		ps.setString(3, grp.getNom());
		ps.executeUpdate();
		req = "INSERT INTO AssociationGroupe VALUES (?,?)";
		for (Utilisateur u : grp.getListeUser()){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1,id);
			ps.setInt(2, u.getIdU());
			ps.executeUpdate();
		}
		id ++;
	}
	
	/**
	 * restitue les groupes de discussions d'un utilisateur
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<GroupeDiscussion> restituerGroupe (Utilisateur u) throws ClassNotFoundException, SQLException{
		ArrayList<GroupeDiscussion> result = new ArrayList<GroupeDiscussion> ();
		String req = "SELECT idG FROM AssociationGroupe WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			result.add(GroupeDiscussionMapper.getInstance().findById(rs.getInt("idG")));
		}
		return result;
	}
	
	/**
	 * restitue un groupe de discussion par son id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public GroupeDiscussion findById (int id) throws ClassNotFoundException, SQLException{
		String req = "SELECT idM , nom FROM Groupe WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			Utilisateur moderateur = UtilisateurMapper.getInstance().findById(rs.getInt("idM"));
			String nom = rs.getString("nom");
			GroupeDiscussion result = new GroupeDiscussion (id,nom,moderateur);
			req = "SELECT idU FROM AssociationGroupe WHERE idG = ? ";
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				Utilisateur u = UtilisateurMapper.getInstance().findById(rs.getInt("idU"));
				result.addUser(u);
			}
			return result;
		}
		return null;
	}
	
}
