package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import persistence.virtualproxy.VirtualUtilisateur;

import domaine.SousCategorieCI;
import domaine.Utilisateur;
import domaine.messages.GroupeDiscussion;

public class UtilisateurMapper {
	
	private static UtilisateurMapper inst;
	private static int id;
	private static HashMap<Integer,Utilisateur> loaded;
	public static UtilisateurMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new UtilisateurMapper();
		}
		return inst;
	}
	
	public UtilisateurMapper() throws ClassNotFoundException, SQLException{
		id = getCurrentId();
		loaded = new HashMap<Integer,Utilisateur>();
	}
	
	private int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Utilisateur";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	public ArrayList<Utilisateur> getAllUtilisateur() throws ClassNotFoundException, SQLException{
		String req ="SELECT id FROM Utilisateur" ; 
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		while(rs.next()){
			int id = rs.getInt("id");
			listeUtilisateur.add(findById(id));
		}
		return listeUtilisateur ;
		
	}
	
	/**
	 * retourn un utilisateur par son id avec info incomplète
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Utilisateur findById (int idC) throws ClassNotFoundException, SQLException{
		if (loaded.containsKey(idC)){
			return loaded.get(idC);
		}
		String req = "SELECT nom , prenom , ndc , password FROM Utilisateur WHERE id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idC);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String ndc = rs.getString("ndc");
			String password = rs.getString("password");
			Utilisateur u = new VirtualUtilisateur (idC,nom,prenom,ndc,password);
			loaded.put(idC, u);
			return u;
		}
		return null;
	}
	
	/**
	 * récupère un utilisateur à la connection, avec toutes ses info
	 * @param ndc
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Utilisateur connection (String ndc , String password) throws ClassNotFoundException, SQLException{
		String req = "SELECT id , nom , prenom FROM Utilisateur WHERE  ndc = ? and password = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,ndc);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			int idC = rs.getInt("id");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			VirtualUtilisateur u = new VirtualUtilisateur (idC,nom,prenom,ndc,password);
			loaded.put(idC, u);
			return u;
		}else{
			return null;
		}
	}

	
	
	/**
	 * insere un utilisateur en base (utilisé par l'admin)
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insert (Utilisateur u) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO Utilisateur Values (?,?,?,?,?)";
		
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, id);
		ps.setString(2,u.getNdc());
		ps.setString(3,u.getNom());
		ps.setString(4, u.getPrenom());
		ps.setString(5, u.getPassword());
		ps.executeUpdate();
		u.setIdU(id);
		loaded.put(id, u);
		id ++;
	}
	
	/**
	 * teste si l'utilisateur passé en paramètre est l'administrateur
	 * @param u
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isAdministrateur (Utilisateur u) throws ClassNotFoundException, SQLException{
		String req = "SELECT COUNT(*) FROM Administrateur WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return (rs.getInt(1)>0);
		}
		return false;
	}
	
	/**
	 * mis à jour du profil de l'utilisateur
	 * @param u
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateProfil(Utilisateur u) throws ClassNotFoundException, SQLException {
		String req = "UPDATE Utilisateur SET nom = ? , prenom = ? , password = ? WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,u.getNom());
		ps.setString(2, u.getPrenom());
		ps.setString(3,u.getPassword());
		ps.setInt(4, u.getIdU());
		ps.executeUpdate();
		
		req = "DELETE FROM AssociationCI WHERE idU = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, u.getIdU());
		ps.executeUpdate();
		
		req = "INSERT INTO AssociationCI VALUES (?,?)";
		for (SousCategorieCI sscate : u.getListeInteret()){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, u.getIdU());
			ps.setInt(2, sscate.getIdSousCategorie());
			ps.executeUpdate();
		}
	}

	public ArrayList<Utilisateur> findByNom(String nom, String prenom) throws ClassNotFoundException, SQLException {
		String req = "SELECT id , ndc , password FROM Utilisateur WHERE nom = ? AND prenom = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,nom);
		ps.setString(2,prenom);
		ResultSet rs = ps.executeQuery();
		ArrayList<Utilisateur> result = new ArrayList<Utilisateur>();
		while (rs.next()){
			int idC = rs.getInt("id");
			if (loaded.containsKey(idC)){
				result.add(loaded.get(idC));
			}else{
				Utilisateur u = new VirtualUtilisateur(idC,nom,prenom,rs.getString("ndc"),rs.getString("password"));
				result.add(u);
				loaded.put(idC, u);
			}
		}
		return result;
	}
	
	public void delete(Utilisateur u) throws ClassNotFoundException, SQLException{
		for (Utilisateur ami : u.getAmis().keySet()){
			DiscussionMapper.getInstance().supprimer(u.getAmis().get(ami));
			ami.getAmis().remove(u);
		}
		
		for (GroupeDiscussion grp : u.getGroupeDiscussion()){
			if (grp.getModerateur()==u){
				DiscussionMapper.getInstance().supprimer(grp);
			}
		}
		
		String req = "delete from Utilisateur where id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,u.getIdU());
		ps.executeUpdate() ; 
	}
}
