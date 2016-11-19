package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Utilisateur;
import domaine.messages.AccuseReception;
import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;
import domaine.messages.Discussion;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;

public class MessageMapper {
	
	@SuppressWarnings("unused")
	private static int id;
	private static MessageMapper inst;
	
	public static MessageMapper getInstance() throws ClassNotFoundException, SQLException{
		if (inst == null){
			inst = new MessageMapper();
		}
		return inst;
	}
	
	public MessageMapper () throws ClassNotFoundException, SQLException{
		id = getCurrentId();
	}
	
	public int getCurrentId() throws SQLException, ClassNotFoundException{
		String req = "SELECT max(id) FROM Message";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt(1)+1;
		}else{
			return 1;
		}
	}

	public Discussion findByIdDiscussionUtilisateur(int idD, Utilisateur u) throws ClassNotFoundException, SQLException {
		ArrayList<Message> lesMessages = new ArrayList<Message>();
		String req = "SELECT m.id, m.idExp, m.contenu, m.dateEnvoie , v.vu FROM Message m JOIN MessageVu v " +
				"ON v.idM = m.id WHERE m.idDiscussion = ? AND v.idU = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idD);
		ps.setInt(2, u.getIdU());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			int idM = rs.getInt("m.id");
			Utilisateur exp = UtilisateurMapper.getInstance().findById(rs.getInt("m.idExp"));
			String contenu = rs.getString("m.contenu");
			String dateEnvoie = rs.getString("m.dateEnvoie");
			Boolean vu = rs.getBoolean("v.vu");
			ArrayList<Option> options = MessageMapper.getInstance().findOptionById(idM);
			lesMessages.add(new Message(idM,exp,contenu,dateEnvoie,vu,options));
		}
		Discussion result = new Discussion (idD);
		result.setMessages(lesMessages);
		return result;
	} 
	
	public ArrayList<Option> findOptionById(int idM) throws ClassNotFoundException, SQLException{
		ArrayList<Option> result = new ArrayList<Option> ();
		String req = "SELECT v.idU , v.vu FROM AccuseDeReception a JOIN MessageVu v on v.idM = a.idM  WHERE v.idM = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idM);
		ResultSet rs = ps.executeQuery();
		HashMap<Utilisateur, Boolean> destinataires = new HashMap<Utilisateur,Boolean>();
		while (rs.next()){
			Utilisateur u = UtilisateurMapper.getInstance().findById(rs.getInt("v.idU"));
			destinataires.put(u, rs.getBoolean("v.vu"));
		}
		if (!destinataires.isEmpty()){
			result.add(new AccuseReception(destinataires));
		}
		req = "SELECT dateExpiration FROM DelaiExpiration WHERE idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		rs = ps.executeQuery();
		if (rs.next()){
			String dateExpiration = rs.getString("dateExpiration").substring(0, 19);
			result.add(new DelaiExpiration(dateExpiration));
		}
		req = "SELECT idM FROM Chiffrement WHERE idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		rs = ps.executeQuery();
		if (rs.next()){
			result.add(new Chiffrement());
		}
		req = "SELECT idM FROM Prioritaire WHERE idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		rs = ps.executeQuery();
		if (rs.next()){
			result.add(new Prioritaire());
		}
		return result;
	}
}
