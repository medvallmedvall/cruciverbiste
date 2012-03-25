package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import entities.Commentaire;
import entities.Utilisateur;

public class CommentaireDao extends Dao<Commentaire> {

	public List<Commentaire> getByIdGrille(int idGrille) {
		List<Commentaire> commList = new LinkedList<Commentaire>();
		String query =  "SELECT * " +
				"FROM Commentaire c " +
				"INNER JOIN Utilisateur u ON u.idUtilisateur = c.idUtilisateur " +
				"WHERE idGrille = " + idGrille + " " +
				"ORDER BY dateCommentaire DESC";
		ResultSet results;
		try {
			results = this.connection.createStatement()
					.executeQuery(query);
			while(results.next()) {
				int idUtilisateur  = results.getInt("idCommentaire");
				String pseudo = results.getString("pseudo");
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(idUtilisateur);
				utilisateur.setPseudo(pseudo);
				int idCommentaire = results.getInt("idCommentaire");
				String contenu = results.getString("contenu");
				java.sql.Timestamp tm = results.getTimestamp("dateCommentaire");
				Date date = new Date(tm.getTime());

				Commentaire comm = new Commentaire(idCommentaire, contenu,
						date, idGrille, utilisateur);
				commList.add(comm);
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
		return commList;
	}

	@Override
	public Commentaire findById(int id) {
		Commentaire comm = null;
		String query =  "SELECT * FROM Commentaire " +
				"WHERE idCommentaire = " + id;
		ResultSet results;
		try {
			results = this.connection.createStatement()
					.executeQuery(query);
			if (!results.first()) {
				return null;
			}
			int idUtilisateur  = results.getInt("idCommentaire");
			String pseudo = results.getString("pseudo");
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setIdUtilisateur(idUtilisateur);
			utilisateur.setPseudo(pseudo);
			int idCommentaire = results.getInt("idCommentaire");
			String contenu = results.getString("contenu");
			java.sql.Timestamp tm = results.getTimestamp("dateCommentaire");
			Date date = new Date(tm.getTime());
			int idGrille = results.getInt("idGrille");
			comm = new Commentaire(idCommentaire, contenu,
					date, idGrille, utilisateur);
			return comm;

		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	@Override
	public Commentaire create(Commentaire obj) {
		java.util.Date date = obj.getDate();
		Timestamp sqlTmsp = new Timestamp(date.getTime());
		int idUser = obj.getUtilisateur().getIdUtilisateur();
		int idGrille = obj.getIdGrille();
		String contenu = obj.getContenu();
		contenu = contenu.replaceAll("<", "&lt;");
		contenu = contenu.replaceAll(">", "&gt;");
		try {
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO Commentaire " +
					"VALUES(NULL, ? , ? , ? , ? )");
			pstmt.setString(1, contenu);
			pstmt.setInt(2, idUser);
			pstmt.setTimestamp(3, sqlTmsp);
			pstmt.setInt(4, idGrille);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
		return obj;
	}

	@Override
	public Commentaire update(Commentaire obj) {
		int idCommentaire = obj.getIdCommentaire();
		java.util.Date date = obj.getDate();
		Timestamp sqlTmsp = new Timestamp(date.getTime());
		int idUser = obj.getUtilisateur().getIdUtilisateur();
		int idGrille = obj.getIdGrille();
		String contenu = obj.getContenu();
		contenu = contenu.replaceAll("<", "&lt;");
		contenu = contenu.replaceAll(">", "&gt;");
		try {
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE Commentaire " +
							"SET contenu = ?, idUtilisateur = ?, dateCommentaire = ?, idGrille = ? " +
					"WHERE idCommentaire = ?");
			pstmt.setString(1, contenu);
			pstmt.setInt(2, idUser);
			pstmt.setTimestamp(3, sqlTmsp);
			pstmt.setInt(4, idGrille);
			pstmt.setInt(5, idCommentaire);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
		return obj;
	}

	@Override
	public void delete(Commentaire obj) {
		int idCommentaire = obj.getIdCommentaire();
		try {
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
					"DELETE FROM Commentaire " +
					"WHERE idCommentaire = ?");
			pstmt.setInt(1, idCommentaire);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

}
