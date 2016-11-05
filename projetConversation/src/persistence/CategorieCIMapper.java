package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.CategorieCI;
import domaine.SousCategorieCI;

public class CategorieCIMapper {
	
	private static CategorieCIMapper inst;
	private static int id;
	
	private static HashMap<Integer,CategorieCI> lazyLoaded;
	
	public static CategorieCIMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new CategorieCIMapper();
		}
		return inst;
	}
	
	public CategorieCIMapper() throws ClassNotFoundException, SQLException{
		id = getCurrentId();
		lazyLoaded = new HashMap<Integer,CategorieCI>();
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
		for (SousCategorieCI scci : cate.getListeSousCategorie()){
			SousCategorieCIMapper.getInstance().insert(cate,scci);
		}
		lazyLoaded.put(id, cate);
		id ++;
	}

	public CategorieCI findByIdLazy(int idC) throws ClassNotFoundException, SQLException {
		if (lazyLoaded.containsKey(idC)){
			return lazyLoaded.get(idC);
		}
		String req = "SELECT nom FROM CategorieCI WHERE id = ? ";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idC);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			CategorieCI result = new CategorieCI (idC,rs.getString("nom"));
			lazyLoaded.put(idC,result);
			return result;
		}
		return null;
	}

	public ArrayList<String> allLibelle() throws ClassNotFoundException, SQLException {
		ArrayList<String> result = new ArrayList<String>();
		String req = "SELECT nom FROM CategorieCI";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			result.add(rs.getString("nom"));
		}
		return result;
	}

	public CategorieCI findByNomLazy(String nom) throws ClassNotFoundException, SQLException {
		String req = "SELECT id FROM CategorieCI WHERE nom = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setString(1, nom);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			int id = rs.getInt("id");
			if (lazyLoaded.containsKey(id)){
				return lazyLoaded.get(id);
			}
			return new CategorieCI (id,nom);
		}
		return null;
	}
	
	
}
