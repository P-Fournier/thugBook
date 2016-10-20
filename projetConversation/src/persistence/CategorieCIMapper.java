package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domaine.CategorieCI;
import domaine.SousCategorieCI;

public class CategorieCIMapper {
	private static CategorieCIMapper inst;
	private static int id;
	
	public static CategorieCIMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new CategorieCIMapper();
		}
		return inst;
	}
	
	public CategorieCIMapper() throws ClassNotFoundException, SQLException{
		id = getCurrentId();
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
		id ++;
	}
}
