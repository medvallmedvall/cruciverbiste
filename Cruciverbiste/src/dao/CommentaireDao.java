package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;


import entities.Commentaire;
import entities.Utilisateur;

public class CommentaireDao extends Dao<Commentaire> {

	public List<Commentaire> getByIdGrille(int idGrille) throws SQLException {
		List<Commentaire> commList = new LinkedList<Commentaire>();
		String query =  "SELECT * " +
				"FROM Commentaire c " +
				"INNER JOIN Utilisateur u ON u.idUtilisateur = c.idUtilisateur " +
				"WHERE idGrille = " + idGrille + " " +
				"ORDER BY dateCommentaire DESC";
		ResultSet results;
		PreparedStatement pstmt = connection.prepareStatement(query);
		results = pstmt.executeQuery();
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
		return commList;
	}

	@Override
	public Commentaire findById(int id) throws SQLException {
		Commentaire comm = null;
		String query =  "SELECT * FROM Commentaire " +
				"WHERE idCommentaire = " + id;
		ResultSet results;
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
	}

	@Override
	public Commentaire create(Commentaire obj) throws SQLException {
		Commentaire commentaire = null;
		java.util.Date date = obj.getDate();
		Timestamp sqlTmsp = new Timestamp(date.getTime());
		int idUser = obj.getUtilisateur().getIdUtilisateur();
		int idGrille = obj.getIdGrille();
		String contenu = obj.getContenu();
		contenu = contenu.replaceAll("<", "&lt;");
		contenu = contenu.replaceAll(">", "&gt;");
		String query = "INSERT INTO Commentaire " +
				"VALUES(NULL, ? , ? , ? , ? )";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, contenu);
		pstmt.setInt(2, idUser);
		pstmt.setTimestamp(3, sqlTmsp);
		pstmt.setInt(4, idGrille);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idCommentaire");
			commentaire = findById(id);
		}
		return commentaire;
	}

	@Override
	public Commentaire update(Commentaire obj) throws SQLException {
		Commentaire commentaire = null;
		int idCommentaire = obj.getIdCommentaire();
		java.util.Date date = obj.getDate();
		Timestamp sqlTmsp = new Timestamp(date.getTime());
		int idUser = obj.getUtilisateur().getIdUtilisateur();
		int idGrille = obj.getIdGrille();
		String contenu = obj.getContenu();
		contenu = contenu.replaceAll("<", "&lt;");
		contenu = contenu.replaceAll(">", "&gt;");
		String query = 	"UPDATE Commentaire " +
				"SET contenu = ?, idUtilisateur = ?, " +
				"dateCommentaire = ?, idGrille = ? " +
				"WHERE idCommentaire = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, contenu);
		pstmt.setInt(2, idUser);
		pstmt.setTimestamp(3, sqlTmsp);
		pstmt.setInt(4, idGrille);
		pstmt.setInt(5, idCommentaire);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idCommentaire");
			commentaire = findById(id);
		}
		return commentaire;
	}

	@Override
	public void delete(Commentaire obj) throws SQLException {
		int idCommentaire = obj.getIdCommentaire();
		String query = 	"DELETE FROM Commentaire " +
						"WHERE idCommentaire = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idCommentaire);
		pstmt.executeUpdate();
	}

}
