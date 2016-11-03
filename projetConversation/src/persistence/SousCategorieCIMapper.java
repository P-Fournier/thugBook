package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.CategorieCI;
import domaine.SousCategorieCI;

public class SousCategorieCIMapper {
	private static SousCategorieCIMapper inst;
	private static int id;
	
	public static SousCategorieCIMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new SousCategorieCIMapper();
		}
		return inst;
	}
	
	public SousCategorieCIMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
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
	
	public HashMap<CategorieCI, ArrayList<SousCategorieCI>> findByUser (int idUser) throws ClassNotFoundException, SQLException{
		String req = "SELECT idSC FROM AssociationCI WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idUser);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Integer> idSCs = new ArrayList<Integer>();
		
		while (rs.next()){
			
			idSCs.add(rs.getInt("idSC"));
		
		}
		
		HashMap<CategorieCI,ArrayList<SousCategorieCI>> result = new HashMap<CategorieCI,ArrayList<SousCategorieCI>> ();
		
		req = "SELECT nom , idC FROM SousCategorieCI WHERE id = ? ";
		
		for (Integer idSCCI : idSCs){
			
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, idSCCI);
			rs = ps.executeQuery();
			
			if (rs.next()){
				String nom = rs.getString("nom");
				CategorieCI cate = CategorieCIMapper.getInstance().findByIdLazy(rs.getInt("idC"));
				ArrayList<SousCategorieCI> sscate;
				if (result.containsKey(cate)){
					sscate = result.get(cate);
				}else{
					sscate = new ArrayList<SousCategorieCI>();
				}
				sscate.add(new SousCategorieCI(idSCCI,nom));
				
				result.put(cate, sscate);
			}
			
		}
		return result;
	}

	public void insert(CategorieCI cate, SousCategorieCI scci) throws ClassNotFoundException, SQLException {
		String req = "INSERT INTO SousCategorieCI VALUES (?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		scci.setSsCat(id);
		ps.setInt(1, id);
		ps.setString(2, scci.getNom());
		ps.setInt(3, cate.getIdCat());
		ps.executeUpdate();
		id ++;
	}
}
