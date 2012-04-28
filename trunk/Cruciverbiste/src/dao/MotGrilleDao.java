package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import entities.Definition;
import entities.Mot;
import entities.MotGrille;

public class MotGrilleDao extends Dao<MotGrille> {
	
	public MotGrilleDao() {
		super();
	}
	
	public List<MotGrille> getByIdGrille(int idGrille) throws SQLException {
		List<MotGrille> motsGrille = new LinkedList<MotGrille>();
		String query = "SELECT * " +
				"FROM MotGrille g " +
				"INNER JOIN DictionnaireFR d ON g.idMot = d.idMot " +
				"INNER JOIN DefinitionFR def ON g.idDefinition = def.idDefinition " +
				/*"LEFT OUTER JOIN SynonymeFR s ON g.idMot = s.idMot1 " +*/
				"WHERE idGrille = ? " +
				"ORDER BY orderId";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		ResultSet results = pstmt.executeQuery();
		while(results.next()) {
			int idMot = results.getInt("idMot");
			/*String motStr = results.getString("mot");
			List<String> synonymes = new LinkedList<String>();
			String synonyme = "";
			if (results.getString("synonyme") != null) {
				synonyme = results.getString("synonyme");
			}
			synonymes.add(synonyme);
			Mot mot = new Mot(idMot, motStr, synonymes);*/
			MotDao motDao = new MotDao();
			Mot mot = motDao.findById(idMot);
			int idDefinition = results.getInt("idDefinition");
			String definitionStr = results.getString("definition");
			Definition definition = new Definition(idDefinition, definitionStr);
			int orientation = results.getInt("idOrientation");
			int coordX = results.getInt("coordX");
			int coordY = results.getInt("coordY");
			motsGrille.add(new MotGrille(idGrille, orientation, mot, definition, coordX, coordY));
		}
		return motsGrille;
	}

	@Override
	public MotGrille findById(int id) throws SQLException {
		return null;
	}

	@Override
	public MotGrille create(MotGrille obj) throws SQLException {
		//ajout du mot et de la definition si ils n'existent pas
		int idMot = -1;
		int idDef = -1;
		//completer
		
		
		String query = "INSERT INTO MotGrille VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, obj.getIdGrille());
		if (obj.getMotObj() == null) {
			pstmt.setObject(2, null);
		}
		else {
			pstmt.setInt(2, obj.getMotObj().getIdMot());
		}
		pstmt.setInt(3, obj.getIdDefinition());
		pstmt.setInt(4, obj.getOrientation());
		pstmt.setInt(5, obj.getCoordX());
		pstmt.setInt(6, obj.getCoordY());
		pstmt.setInt(7, obj.getOrderId());
		int nb = pstmt.executeUpdate();
		if (nb == 1) {
			return obj;
		}
		return null;
	}

	@Override
	public MotGrille update(MotGrille obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MotGrille obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
