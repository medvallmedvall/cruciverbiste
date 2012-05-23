package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.LettreEnCour;

public class LettreEnCoursDao extends Dao<LettreEnCour>{

	@Override
	public LettreEnCour findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String findByid(int idGrille,int idGrilleEnCours) throws SQLException{
		String listLettreEnCour = null;
		String query3 = "SELECT * FROM LettreEnCours l INNER JOIN GrilleEnCoursUtilisateur g " +
				" WHERE g.idGrille = " + idGrille +
				" AND g.idGrilleEnCours = l.idGrilleEnCours" +
				" AND l.idGrilleEnCours = " + idGrilleEnCours;
		ResultSet results = null;
		PreparedStatement pstmt = connection.prepareStatement(query3);
		results = pstmt.executeQuery();
		if (results.first()) {
			int idGrilleEnCour=results.getInt("idGrilleEnCours");
			listLettreEnCour="idGrilleEnCour=" + idGrilleEnCour + "&listeLettre=";
			String lettre=results.getString("lettre");
			int coordX=results.getInt("coordX");
			int coordY=results.getInt("coordY");
			listLettreEnCour= listLettreEnCour + lettre + ":" + coordX + ":" + coordY + "/";	
			while (results.next()) {
				String lettre2=results.getString("lettre");
				int coordX2=results.getInt("coordX");
				int coordY2=results.getInt("coordY");
				listLettreEnCour= listLettreEnCour + lettre2 + ":" + coordX2 + ":" + coordY2 + "/";	
			}
			return listLettreEnCour;
		}else
			return null;
	}
	
	@Override
	public LettreEnCour create(LettreEnCour obj) throws SQLException {
		LettreEnCour lettreEnCours=null;
		String requete="INSERT INTO LettreEnCours VALUES (?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(requete);
		pstmt.setInt(1, obj.getIdGrilleEnCours());
		pstmt.setString(2, obj.getLettre());
		pstmt.setInt(3, obj.getCoordX());
		pstmt.setInt(4, obj.getCoordY());
		if(pstmt.executeUpdate()==1)
			return obj;
		return lettreEnCours;
	}

	@Override
	public LettreEnCour update(LettreEnCour obj) throws SQLException {
		return null;
	}

	@Override
	public void delete(LettreEnCour obj) throws SQLException {
		
	}
	
	public void delete(int idGrilleEnCours) throws SQLException {
		String query = "DELETE FROM LettreEnCours WHERE idGrilleEnCours = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, idGrilleEnCours);
		stmt.executeUpdate();
	}
}