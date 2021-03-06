package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistence.virtualproxy.VirtualGroupeDiscussion;

import domaine.Utilisateur;
import domaine.messages.GroupeDiscussion;

public class GroupeDiscussionMapper {
	
	public static GroupeDiscussionMapper inst;
	
	
	public static GroupeDiscussionMapper getInstance() throws ClassNotFoundException, SQLException{
		if(inst == null){
			inst = new GroupeDiscussionMapper();
		}
		return inst;
	}
	
	public GroupeDiscussionMapper () throws ClassNotFoundException, SQLException{
		DiscussionMapper.getInstance();
	}
	
	/**
	 * créer un groupe de discussion en base de donnée
	 * @param grp
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insert(GroupeDiscussion grp) throws SQLException, ClassNotFoundException{
		String req = "INSERT INTO DiscussionGroupe VALUES (?,?,?) ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		grp.setId(DiscussionMapper.id);
		ps.setInt(1,DiscussionMapper.id);
		ps.setInt(2,grp.getModerateur().getIdU());
		ps.setString(3, grp.getNom());
		ps.executeUpdate();
		req = "INSERT INTO AssociationGroupe VALUES (?,?)";
		for (Utilisateur u : grp.getListeUser()){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1,DiscussionMapper.id);
			ps.setInt(2, u.getIdU());
			ps.executeUpdate();
		}
		DiscussionMapper.id ++;
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
		String req = "SELECT idM , nom FROM DiscussionGroupe WHERE idD = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			int idM = rs.getInt("idM");
			Utilisateur moderateur = UtilisateurMapper.getInstance().findById(idM);
			String nom = rs.getString("nom");
			VirtualGroupeDiscussion result = new VirtualGroupeDiscussion (id,nom,moderateur);
			return result;
		}
		return null;
	}
	
	/**
	 * trouver les membres du groupe
	 * @param grp
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Utilisateur> trouverMembres(GroupeDiscussion grp) throws ClassNotFoundException, SQLException{
		ArrayList<Utilisateur> result = new ArrayList<Utilisateur>();
		String req = "SELECT idU FROM AssociationGroupe WHERE idG = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, grp.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Utilisateur membres = UtilisateurMapper.getInstance().findById(rs.getInt("idU"));
			result.add(membres);
		}
		return result;
	}

	/**
	 * supprime l'utilisateur du groupe
	 * @param u
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprimerUtilisateur(Utilisateur u, GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM AssociationGroupe WHERE idG = ? and idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, grp.getId());
		ps.setInt(2,u.getIdU());
		ps.executeUpdate();
		grp.getListeUser().remove(u);
		u.getGroupeDiscussion().remove(grp);
	}

	/**
	 * ajoute l'utilisateur au groupe
	 * @param u
	 * @param grp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void ajouterAuGroupe(Utilisateur u, GroupeDiscussion grp) throws ClassNotFoundException, SQLException {
		String req = "INSERT INTO AssociationGroupe VALUES (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,grp.getId());
		ps.setInt(2, u.getIdU());
		ps.executeUpdate();
		grp.getListeUser().add(u);
	}

	/**
	 * nommer l'utilisateur modérateur du groupe
	 * @param u
	 * @param grp
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void changerModerateur(Utilisateur u, GroupeDiscussion grp) throws SQLException, ClassNotFoundException {
		Utilisateur ancienModerateur = grp.getModerateur();
		grp.getListeUser().remove(u);
		grp.getListeUser().add(ancienModerateur);
		grp.setModerateur(u);
		String req = "UPDATE DiscussionGroupe SET idM = ? WHERE idD = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ps.setInt(2, grp.getId());
		ps.executeUpdate();
	}

	/**
	 * créer le groupe en base
	 * @param nomDuGroupe
	 * @param moderateur
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public GroupeDiscussion creerGroupe(String nomDuGroupe, Utilisateur moderateur) throws ClassNotFoundException, SQLException {
		String req= "INSERT INTO Discussion VALUES (?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, DiscussionMapper.id);
		ps.executeUpdate();
		
		req = "INSERT INTO DiscussionGroupe VALUES (?,?,?)";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,DiscussionMapper.id);
		ps.setInt(2,moderateur.getIdU());
		ps.setString(3,nomDuGroupe);
		ps.executeUpdate();
		
		req = "INSERT INTO AssociationGroupe VALUES (?,?)";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,DiscussionMapper.id);
		ps.setInt(2, moderateur.getIdU());
		ps.executeUpdate();
		
		GroupeDiscussion grp = new GroupeDiscussion (DiscussionMapper.id,nomDuGroupe,moderateur);
		
		DiscussionMapper.loaded.put(DiscussionMapper.id, grp);
		
		DiscussionMapper.id ++;
		
		return grp;
	}

	/**
	 * test si le nom de groupe est déjà attribué
	 * @param nom
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean existenceNomDeGroupe(String nom) throws ClassNotFoundException, SQLException {
		String req = "SELECT * FROM DiscussionGroupe WHERE nom = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,nom);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
}
