package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.LettreGrilleCreation;

public class LettreGrilleCreationDao extends Dao<LettreGrilleCreation> {

	@Override
	public LettreGrilleCreation findById(int id) throws SQLException {
		return null;
	}
	
	public List<LettreGrilleCreation> getByIdGrille(int idGrille) throws SQLException {
		List<LettreGrilleCreation> lettres = 
				new LinkedList<LettreGrilleCreation>();
		String query = 	"SELECT * FROM LettreGrilleCreation " +
						"WHERE idGrille = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int coordX = rs.getInt("coordX");
			int coordY = rs.getInt("coordY");
			String l = rs.getString("lettre");
			LettreGrilleCreation lgc = new LettreGrilleCreation(idGrille,
					coordX, coordY, l);
			lettres.add(lgc);
		}
		return lettres;
	}

	@Override
	public LettreGrilleCreation create(LettreGrilleCreation obj)
			throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La lettreGrilleCreation est null");
		}
		LettreGrilleCreation lettre = null;
		String query = "INSERT INTO LettreGrilleCreation VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, obj.getIdGrille());
		pstmt.setInt(2, obj.getCoordX());
		pstmt.setInt(3, obj.getCoordY());
		pstmt.setString(4, obj.getLettre());
		pstmt.executeUpdate();
		lettre = new LettreGrilleCreation(obj.getIdGrille(), obj.getCoordX(),
				obj.getCoordY(), obj.getLettre());
		return lettre;
	}

	@Override
	public LettreGrilleCreation update(LettreGrilleCreation obj)
			throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La lettreGrilleCreation est null");
		}
		String query = 	"UPDATE LettreGrilleCreation SET lettre = ? " +
						"WHERE idGrille = ? AND coordX = ? " +
						"AND coordY = ? ";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, obj.getLettre());
		pstmt.setInt(2, obj.getIdGrille());
		pstmt.setInt(3, obj.getCoordX());
		pstmt.setInt(4, obj.getCoordY());
		return obj;
	}

	@Override
	public void delete(LettreGrilleCreation obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La lettreGrilleCreation est null");
		}
		String query = 	"DELETE FROM LettreGrilleCreation " +
						"WHERE idGrille = ? AND coordX = ? " +
						"AND coordY = ? AND lettre = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, obj.getIdGrille());
		pstmt.setInt(2, obj.getCoordX());
		pstmt.setInt(3, obj.getCoordY());
		pstmt.setString(4, obj.getLettre());
		pstmt.executeUpdate();
	}

	public void deleteByIdGrille(int idGrille) throws SQLException {
		String query = 	"DELETE FROM LettreGrilleCreation " +
						"WHERE idGrille = ? ";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		pstmt.executeUpdate();
	}

}
