package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.CategorieCI;

public class CategorieCIMapper {
	
	private static CategorieCIMapper inst;
	private static int id;
	
	private static HashMap<Integer,CategorieCI> loaded;
	
	public static CategorieCIMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new CategorieCIMapper();
		}
		return inst;
	}
	
	public CategorieCIMapper() throws ClassNotFoundException, SQLException{
		id = getCurrentId();
		loaded = new HashMap<Integer,CategorieCI>();
	}
	
	private int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM CategorieCI";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}
	
	public void insert (CategorieCI cate) throws ClassNotFoundException, SQLException{
		String req = "INSERT INTO CategorieCI VALUES (?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		cate.setIdCat(id);
		ps.setInt(1,id);
		ps.setString(2, cate.getNom());
		ps.executeUpdate();
		loaded.put(id, cate);
		id ++;
	}

	public CategorieCI findById(int idC) throws ClassNotFoundException, SQLException {
		if (loaded.containsKey(idC)){
			return loaded.get(idC);
		}
		String req = "SELECT nom FROM CategorieCI WHERE id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idC);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			CategorieCI result = new CategorieCI (idC,rs.getString("nom"));
			loaded.put(idC,result);
			return result;
		}
		return null;
	}

	public ArrayList<CategorieCI> all() throws ClassNotFoundException, SQLException {
		ArrayList<CategorieCI> result = new ArrayList<CategorieCI>();
		String req = "SELECT id , nom FROM CategorieCI";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			if (loaded.containsKey(rs.getInt("id"))){
				result.add(loaded.get(rs.getInt("id")));
			}else{
				CategorieCI cate = new CategorieCI (rs.getInt("id"),rs.getString("nom"));
				loaded.put(rs.getInt("id"), cate);
				result.add(cate);
			}
		}
		return result;
	}

	public void delete(CategorieCI cate) throws ClassNotFoundException, SQLException {
		String req = "DELETE FROM CategorieCI WHERE id = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, cate.getIdCat());
		ps.executeUpdate();
	}

	public void update(CategorieCI cate) throws ClassNotFoundException, SQLException {
		String req = "UPDATE CategorieCI SET nom = ? WHERE id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(2, cate.getNom());
		ps.setInt(1,cate.getIdCat());
	}
	
}
