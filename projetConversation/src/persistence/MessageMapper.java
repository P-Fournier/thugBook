package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Utilisateur;
import domaine.VisiteurOption;
import domaine.messages.AccuseReception;
import domaine.messages.Chiffrement;
import domaine.messages.DelaiExpiration;
import domaine.messages.Discussion;
import domaine.messages.Message;
import domaine.messages.Option;
import domaine.messages.Prioritaire;

public class MessageMapper extends VisiteurOption{
	
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

	public Discussion findByIdDiscussionUtilisateur(int idD) throws ClassNotFoundException, SQLException {
		ArrayList<Message> lesMessages = new ArrayList<Message>();
		String req = "SELECT m.id, m.idExp, m.contenu, m.dateEnvoie FROM Message m WHERE m.idDiscussion = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idD);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			int idM = rs.getInt("m.id");
			Utilisateur exp = UtilisateurMapper.getInstance().findById(rs.getInt("m.idExp"));
			String contenu = rs.getString("m.contenu");
			String dateEnvoie = rs.getString("m.dateEnvoie").substring(0,19);
			ArrayList<Option> options = MessageMapper.getInstance().findOptionById(idM);
			lesMessages.add(new Message(idM,exp,contenu,dateEnvoie,options));
		}
		Discussion result = new Discussion (idD);
		result.setMessages(lesMessages);
		return result;
	} 
	
	public ArrayList<Option> findOptionById(int idM) throws ClassNotFoundException, SQLException{
		ArrayList<Option> result = new ArrayList<Option> ();
		String req = "SELECT idM FROM Chiffrement WHERE idM = ?";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			result.add(new Chiffrement());
		}
		req = "SELECT a.idU , a.vu FROM AccuseDeReception a WHERE a.idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1, idM);
		rs = ps.executeQuery();
		HashMap<Utilisateur, Boolean> destinataires = new HashMap<Utilisateur,Boolean>();
		while (rs.next()){
			Utilisateur u = UtilisateurMapper.getInstance().findById(rs.getInt("a.idU"));
			destinataires.put(u, rs.getBoolean("a.vu"));
		}
		if (!destinataires.isEmpty()){
			result.add(new AccuseReception(destinataires));
		}
		req = "SELECT idM FROM Prioritaire WHERE idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		rs = ps.executeQuery();
		if (rs.next()){
			result.add(new Prioritaire());
		}
		req = "SELECT dateExpiration FROM DelaiExpiration WHERE idM = ?";
		ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		ps.setInt(1,idM);
		rs = ps.executeQuery();
		if (rs.next()){
			String dateExpiration = rs.getString("dateExpiration").substring(0, 19);
			result.add(new DelaiExpiration(dateExpiration));
		}
		return result;
	}

	public void insert(Discussion selected, Message msg) throws ClassNotFoundException, SQLException {
		String req = "INSERT INTO Message VALUES (?,?,?,?,?)";
		PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
		msg.setId(id);
		ps.setInt(1, id);
		ps.setInt(2,msg.getExpediteur().getIdU());
		ps.setString(3, msg.getContenu());
		ps.setString(4, msg.getDateEnvoie());
		ps.setInt(5, selected.getId());
		ps.executeUpdate();
		
		for (Option o : msg.getOptions()){
			MessageMapper.getInstance().accepter(o);
		}
		
		id ++;
	}

	@Override
	public void visiter(AccuseReception a)  {
		String req = "INSERT INTO AccuseDeReception VALUES (?,?,false)";
		for (Utilisateur u : a.getDestinataires().keySet()){
			System.out.println(u);
			PreparedStatement ps;
			try {
				ps = DBConfig.getInstance().getConnection().prepareStatement(req);
				ps.setInt(1, id);
				ps.setInt(2, u.getIdU());
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void visiter(Chiffrement c) {
		String req = "INSERT INTO Chiffrement VALUES (?)";
		try {
			PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void visiter(Prioritaire p) {
		String req = "INSERT INTO Prioritaire VALUES (?)";
		try {
			PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visiter(DelaiExpiration d) {
		String req = "INSERT INTO DelaiExpiration VALUES (?,?)";
		try {
			PreparedStatement ps = DBConfig.getInstance().getConnection().prepareStatement(req);
			ps.setInt(1, id);
			ps.setString(2, d.getDateExpiration());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
