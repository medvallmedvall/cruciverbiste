package dao;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import entities.Commentaire;
import entities.Utilisateur;

public class CommentaireDao extends Dao<Commentaire> {
	
	public List<Commentaire> getByIdGrille(int idGrille) {
		List<Commentaire> commList = new LinkedList<Commentaire>();
		String query =  "SELECT * " +
						"FROM Commentaire c " +
						"INNER JOIN Utilisateur u ON u.idUtilisateur = c.idUtilisateur " +
					    "WHERE idGrille = " + idGrille;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commList;
	}

	@Override
	public Commentaire findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commentaire create(Commentaire obj) {
		Date date = obj.getDate();
		int idUser = obj.getUtilisateur().getIdUtilisateur();
		int idGrille = obj.getIdGrille();
		String contenu = obj.getContenu();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sDate = format.format(date);
		String query = "INSERT INTO Commentaire VALUES(" +
				"NULL, '" + contenu + "', '" + idUser + "', '" + sDate + "', '" + idGrille + "')";
		try {
			this.connection.createStatement()
					.executeQuery(query);
		} catch (SQLException e) {
			throw new IllegalStateException(e.getMessage());
		}
		return obj;
	}

	@Override
	public Commentaire update(Commentaire obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Commentaire obj) {
		// TODO Auto-generated method stub
		
	}

}
