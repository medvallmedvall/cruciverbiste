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
			int id = rs.getInt(1);
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
		mot = findById(obj.getIdMot());
		return mot;
	}

	@Override
	public void delete(Mot obj) throws SQLException {
		String query = "DELETE FROM DictionnaireFR WHERE idMot = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, obj.getIdMot());
		stmt.executeUpdate();
	}
	
	public List<Mot> getSynonyms(String mot) throws SQLException {
		List<Mot> synonyms = new LinkedList<Mot>();
		String query1 = "SELECT s.idMot2 as idSynonym, d2.mot as synonym " +
						"FROM SynonymeFR s " +
						"LEFT JOIN DictionnaireFR d1 ON d1.idMot = s.idMot1 " +
						"LEFT JOIN DictionnaireFR d2 ON d2.idMot = s.idMot2 " +
						"WHERE d1.mot LIKE ? ";
		PreparedStatement pstmt = connection.prepareStatement(query1);
		pstmt.setString(1, mot);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("idSynonym");
			String syn = rs.getString("synonym");
			Mot motSyn = new Mot(id, syn, new LinkedList<String>());
			synonyms.add(motSyn);
		}
		return synonyms;
	}


	public LinkedList<String> findByMotif(String motif) throws SQLException {
		LinkedList<String> listMots = new LinkedList<String>();
		motif = motif.replace('?', '_');
		String query = "select mot from dictionnairefr where mot like ? " ;
		//Statement stmt = this.connection.createStatement();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, motif);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String mot = rs.getString("mot");
			listMots.add(mot);
			System.out.println(mot);
		}
		return listMots;
	}
	
	public Mot getByMot(String chaine) throws SQLException {
		Mot mot = null;
		String query = "SELECT * FROM DictionnaireFR WHERE mot LIKE ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, chaine);
		ResultSet rs = stmt.executeQuery();
		if (rs.first()) {
			int idMot = rs.getInt("idMot");
			String s = rs.getString("mot");
			mot = new Mot(idMot, s, new LinkedList<String>());
			return mot;
		}
		return mot;
	}

}

