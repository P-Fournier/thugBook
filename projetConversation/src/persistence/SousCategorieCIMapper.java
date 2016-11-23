package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import domaine.CategorieCI;
import domaine.SousCategorieCI;
import domaine.Utilisateur;

public class SousCategorieCIMapper {
	private static SousCategorieCIMapper inst;
	private static int id;
	private static HashMap<Integer,SousCategorieCI> loaded;
	
	public static SousCategorieCIMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new SousCategorieCIMapper();
		}
		return inst;
	}
	
	public SousCategorieCIMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
		loaded = new HashMap<Integer,SousCategorieCI>();
	}
	
	private int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM SousCategorieCI";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	public ArrayList<SousCategorieCI> findByUser (Utilisateur u) throws ClassNotFoundException, SQLException{
		String req = "SELECT idSC FROM AssociationCI WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,u.getIdU());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Integer> idSCs = new ArrayList<Integer>();
		
		while (rs.next()){
			
			idSCs.add(rs.getInt("idSC"));
		
		}
		
		ArrayList<SousCategorieCI> result = new ArrayList<SousCategorieCI> ();
		
		req = "SELECT nom , idC FROM SousCategorieCI WHERE id = ? ";
		
		for (Integer idSCCI : idSCs){
			if (loaded.containsKey(idSCCI)){
				result.add(loaded.get(idSCCI));
			}else{
				ps = DBConfig.getInstance().getConnection().prepareStatement(req);
				ps.setInt(1, idSCCI);
				rs = ps.executeQuery();
				
				if (rs.next()){
					String nom = rs.getString("nom");
					CategorieCI cate = CategorieCIMapper.getInstance().findById(rs.getInt("idC"));
					SousCategorieCI ajout = new SousCategorieCI(idSCCI,nom,cate);
					result.add(ajout);
					loaded.put(ajout.getIdSousCategorie(),ajout);
				}
			}
			
		}
		return result;
	}

	public void insert(SousCategorieCI scci) throws MySQLIntegrityConstraintViolationException,ClassNotFoundException, SQLException {
		String req = "INSERT INTO SousCategorieCI VALUES (?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		scci.setSsCat(id);
		ps.setInt(1, id);
		ps.setString(2, scci.getNom());
		ps.setInt(3, scci.getCategorie().getIdCat());
		ps.executeUpdate();
		loaded.put(id, scci);
		id ++;
	}

	public ArrayList<SousCategorieCI> findByCategorie(CategorieCI categorie) throws ClassNotFoundException, SQLException {
		ArrayList<SousCategorieCI> result = new ArrayList<SousCategorieCI>();
		String req = "SELECT id , nom FROM SousCategorieCI WHERE idC = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, categorie.getIdCat());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			if (loaded.containsKey(rs.getInt("id"))){
				result.add(loaded.get(rs.getInt("id")));
			}else{
				SousCategorieCI sscate = new SousCategorieCI (rs.getInt("id"),rs.getString("nom"),categorie);
				result.add(sscate);
				loaded.put(rs.getInt("id"),sscate);
			}
		}
		return result;
	}

	public ArrayList<SousCategorieCI> all() throws ClassNotFoundException, SQLException {
		ArrayList<SousCategorieCI> result = new ArrayList<SousCategorieCI>();
		String req = "SELECT id , nom , idC FROM SousCategorieCI";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			int idsc = rs.getInt("id");
			if(loaded.containsKey(idsc)){
				result.add(loaded.get(idsc));
			}else{
				String nom = rs.getString("nom");
				CategorieCI cate = CategorieCIMapper.getInstance().findById(rs.getInt("idC"));
				SousCategorieCI create = new SousCategorieCI (idsc,nom,cate);
				loaded.put(idsc,create);
				result.add(create);
			}
		}
		return result;
	}

	public void delete(SousCategorieCI sousCate) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM SousCategorieCI WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, sousCate.getIdSousCategorie());
		ps.executeUpdate();
	}

	public void update(SousCategorieCI sscate) throws ClassNotFoundException, SQLException {
		String req = "UPDATE SousCategorieCI SET nom = ? AND idC = ? WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1,sscate.getNom());
		ps.setInt(2, sscate.getCategorie().getIdCat());
		ps.setInt(3, sscate.getIdSousCategorie());
		ps.executeUpdate();
	}
}
