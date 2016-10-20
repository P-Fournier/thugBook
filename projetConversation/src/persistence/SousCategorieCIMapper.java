package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.SousCategorieCI;

public class SousCategorieCIMapper {
	private static SousCategorieCIMapper inst;
	private static int id;
	
	public static SousCategorieCIMapper getInstance(){
		if (inst == null){
			inst = new SousCategorieCIMapper();
		}
		return inst;
	}
	
	public SousCategorieCIMapper (){
		id = 0;
	}
	
	public ArrayList<SousCategorieCI> findByUser (int idUser) throws ClassNotFoundException, SQLException{
		String req = "SELECT idSC FROM AssociationCI WHERE idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idUser);
		ResultSet rs = ps.executeQuery();
		ArrayList<Integer> idSCs = new ArrayList<Integer>();
		while (rs.next()){
			idSCs.add(rs.getInt("idSC"));
		}
		ArrayList<SousCategorieCI> result = new ArrayList<SousCategorieCI> ();
		req = "SELECT nom FROM SousCategorieCI WHERE id = ? ";
		for (Integer idCI : idSCs){
			ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, idCI);
			rs = ps.executeQuery();
			if (rs.next()){
				result.add(new SousCategorieCI (idCI,rs.getString("nom")));
			}
		}
		return result;
	}
}
