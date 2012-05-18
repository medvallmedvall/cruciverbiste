package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import entities.GrilleEnCoursUtilisateur;
import entities.LettreEnCour;

public class GrilleEnCoursUtilisateurDao extends Dao<GrilleEnCoursUtilisateur> {

	@Override
	public GrilleEnCoursUtilisateur findById(int idGrilleEnCours)
			throws SQLException {
		GrilleEnCoursUtilisateur grilleEnCoursUtilisateur = null;
		String requete = "SELECT * FROM GrilleEnCoursUtilisateur WHERE idGrilleEnCours=?";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setInt(1, idGrilleEnCours);
		ResultSet res = pstmt.executeQuery();
		if (res.first()) {
			grilleEnCoursUtilisateur = new GrilleEnCoursUtilisateur(
					res.getInt("idGrilleEnCours"), res.getInt("idUtilisateur"),
					res.getInt("idGrille"), res.getDate("dateJeu"));
			System.out.println("id=" + grilleEnCoursUtilisateur.getIdGrille());
		}
		return grilleEnCoursUtilisateur;
	}

	@Override
	public GrilleEnCoursUtilisateur create(GrilleEnCoursUtilisateur obj)
			throws SQLException {
		GrilleEnCoursUtilisateur grilleEnCoursUtilisateur = null;
		String requete = "INSERT INTO GrilleEnCoursUtilisateur VALUES (null,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(requete,
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, obj.getIdUtilisateur());
		pstmt.setInt(2, obj.getIdGrille());
		Timestamp sqlTmsp = new Timestamp(obj.getDate().getTime());
		pstmt.setTimestamp(3, sqlTmsp);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt(1);
			grilleEnCoursUtilisateur = findById(id);
		}
		return grilleEnCoursUtilisateur;
	}

	@Override
	public GrilleEnCoursUtilisateur update(GrilleEnCoursUtilisateur obj)
			throws SQLException {
		return null;
	}

	@Override
	public void delete(GrilleEnCoursUtilisateur obj) throws SQLException {

	}

	public GrilleEnCoursUtilisateur findByGrilleUser(int idGrille,
			int idUtilisateur) throws SQLException {
		GrilleEnCoursUtilisateur grilleEnCoursUtilisateur = null;
		String requete = "SELECT * FROM GrilleEnCoursUtilisateur WHERE idGrille=? and idUtilisateur=?";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setInt(1, idGrille);
		pstmt.setInt(2, idUtilisateur);
		ResultSet res = pstmt.executeQuery();
		if (res.first()) {
			grilleEnCoursUtilisateur = new GrilleEnCoursUtilisateur(
					res.getInt("idGrilleEnCours"), res.getInt("idUtilisateur"),
					res.getInt("idGrille"), res.getDate("dateJeu"));
		}
		return grilleEnCoursUtilisateur;
	}

	public boolean Sauvegarder(int idGrille, int idUtilisateur,
			List<LettreEnCour> liste) throws SQLException {
		GrilleEnCoursUtilisateur grilleEnCoursUtilisateur = findByGrilleUser(
				idGrille, idUtilisateur);
		if (grilleEnCoursUtilisateur == null) {
			System.out.println("null");
			Date date = new Date();
			grilleEnCoursUtilisateur = new GrilleEnCoursUtilisateur(
					idUtilisateur, idGrille, date);
			grilleEnCoursUtilisateur = create(grilleEnCoursUtilisateur);

			int idGrilleEnCours = grilleEnCoursUtilisateur.getIdGrilleEnCours();
			LettreEnCoursDao lettreEnCoursDao = new LettreEnCoursDao();
			for (LettreEnCour l : liste) {
				l.setIdGrilleEnCours(idGrilleEnCours);
				lettreEnCoursDao.create(l);
			}
		}else{
			System.out.println("machi null");
			int idGrilleEnCours = grilleEnCoursUtilisateur.getIdGrilleEnCours();
			LettreEnCoursDao lettreEnCoursDao = new LettreEnCoursDao();
			lettreEnCoursDao.delete(idGrilleEnCours);
			for (LettreEnCour l : liste) {
				l.setIdGrilleEnCours(idGrilleEnCours);
				lettreEnCoursDao.create(l);
			}
		}
		return true;
	}
}