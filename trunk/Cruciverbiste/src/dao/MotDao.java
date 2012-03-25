package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import entities.Mot;

public class MotDao extends Dao<Mot> {
	
	public MotDao() {
		super();
	}

	@Override
	public Mot findById(int id) throws SQLException {
		Mot mot = null;
		String query = 	"SELECT * FROM DictionnaireFR m " +
						"LEFT JOIN SynonymeFR s ON m.idMot = s.idMot1 " +
						"WHERE idMot = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.first()) {
			int idMot1 = rs.getInt("idMot1");
			String mot1 = rs.getString("mot");
			//temp
			//int idMot2 = rs.getInt("idMot2");
			//String mot2 = rs.getString("mot2");
			String synonyme = "";
			if (rs.getString("synonyme") != null) {
				synonyme = rs.getString("synonyme");
			}
			//List<Mot> synonList = new LinkedList<Mot>();
			//Mot motSyn = new Mot(idMot2, synonyme, new LinkedList<String>());
			List<String> synonList = new LinkedList<String>();
			synonList.add(synonyme);
			mot = new Mot(idMot1, mot1, synonList);
		}
		return mot;
	}

	@Override
	public Mot create(Mot obj) throws SQLException {
		Mot mot = null;
		String query = "INSERT INTO DictionnaireFR VALUES (NULL, ?)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getMot());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idMot");
			mot = findById(id);
			if (mot != null) {
				//ajout des synonymes
			
			}
		}
		return mot;
	}

	@Override
	public Mot update(Mot obj) throws SQLException {
		Mot mot = null;
		String query = "UPDATE DictionnaireFR SET mot = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getMot());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idMot");
			mot = findById(id);
		}
		return mot;
	}

	@Override
	public void delete(Mot obj) throws SQLException {
		String query = "DELETE FROM DictionnaireFR WHERE idMot = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, obj.getIdMot());
		stmt.executeUpdate();
	}

}
