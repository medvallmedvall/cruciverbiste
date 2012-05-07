package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import entities.Commentaire;
import entities.Grille;
import entities.GrilleNonValideeMessage;
import entities.MotGrille;
import entities.Utilisateur;

public class GrilleDao extends Dao<Grille> {

	public GrilleDao() {
		super();
	}

	@Override
	public Grille findById(int id) throws SQLException {
		Grille grille = null;
		String query3 = "SELECT * FROM Grille g " +
				"INNER JOIN Utilisateur u " +
				"ON g.idUtilisateurConcepteur = u.idUtilisateur " +
				"WHERE idGrille = " + id;
		ResultSet results = null;
		PreparedStatement pstmt = connection.prepareStatement(query3);
		results = pstmt.executeQuery();
		if (!results.first()) {
			return null;
		}
		int idLangue  = results.getInt("idLangue");
		int idTypeGrille  = results.getInt("idTypeGrille");
		int idUtilisateur = results.getInt("idUtilisateurConcepteur");
		String nomGrille = results.getString("nomGrille");
		int largeur = results.getInt("largeur");
		int longueur = results.getInt("hauteur");
		Date dateCreation = results.getDate("dateCreation");
		boolean estFinie = results.getBoolean("estFinie");
		boolean estValidee = results.getBoolean("estValidee");
		Date dateValidation = results.getDate("dateValidation");
		int niveau = results.getInt("niveau");
		int idTheme = results.getInt("idTheme");
		String pseudo = results.getString("pseudo");
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdUtilisateur(idUtilisateur);
		utilisateur.setPseudo(pseudo);
		grille = new Grille(id, idLangue, idTypeGrille,
				nomGrille, largeur, longueur, dateCreation, estFinie,
				estValidee, dateValidation, niveau, idTheme, utilisateur);
		CommentaireDao commDao = new CommentaireDao();
		List<Commentaire> commList = commDao.getByIdGrille(id);
		grille.setCommentaires(commList);
		MotGrilleDao motsDao = new MotGrilleDao();
		List<MotGrille> motsGrille = motsDao.getByIdGrille(id);
		grille.setMotsGrille(motsGrille);
		return grille;
	}

	@Override
	public Grille create(Grille obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La grille est null");
		}
		Grille grille = null;
		String query = "INSERT INTO Grille VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		Date date = new Date();
		stmt.setString(1, obj.getNomGrille());
		stmt.setInt(2, obj.getLargeur());
		stmt.setInt(3, obj.getHauteur());
		stmt.setInt(4, obj.getIdLangue());
		stmt.setInt(5, obj.getIdTypeGrille());
		stmt.setInt(6, obj.getUtilisateur().getIdUtilisateur());
		//date creation
		stmt.setDate(7, new java.sql.Date(date.getTime()));
		//est finie
		stmt.setBoolean(8, false);
		//estValidee
		stmt.setBoolean(9, false);
		//date validation
		stmt.setDate(10, null);
		stmt.setInt(11, obj.getNiveau());
		stmt.setInt(12, obj.getIdTheme());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt(1);
			grille = findById(id);
		}
		return grille;
	}

	@Override
	public Grille update(Grille obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La grille est null");
		}
		Grille grille = null;
		String query = "UPDATE Grille SET nomGrille = ?, largeur = ?, " +
				"hauteur = ?, idLangue = ?, estFinie = ?, estValidee = ?," +
				" dateValidation = ?, niveau = ?, idTheme = ? " +
				"WHERE idGrille = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getNomGrille());
		stmt.setInt(2, obj.getLargeur());
		stmt.setInt(3, obj.getHauteur());
		stmt.setInt(4, obj.getIdLangue());
		stmt.setBoolean(5, obj.isEstFinie());
		stmt.setBoolean(6, obj.isEstValidee());
		stmt.setDate(7, new java.sql.Date(obj.getDateValidation().getTime()));
		stmt.setInt(8, obj.getNiveau());
		stmt.setInt(9, obj.getIdTheme());
		stmt.setInt(10, obj.getIdGrille());
		stmt.executeUpdate();
		grille = findById(obj.getIdGrille());
		if (grille == null) {
			return null;
		}
		//mise a jour des mots
		List<MotGrille> listMot = grille.getMotsGrille();
		MotGrilleDao dao = new MotGrilleDao();
		for (MotGrille mot : listMot) {
			dao.create(mot);
		}
		return grille;
	}

	@Override
	public void delete(Grille obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La grille est null");
		}
		String query = "DELETE FROM Grille WHERE idGrille = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, obj.getIdGrille());
		stmt.executeUpdate();
	}

	public List<Grille> getGrilles(TypeGrille type) throws SQLException {
		List<Grille> mList = new LinkedList<Grille>();
		int idType = type.ordinal() + 1;
		String query = 	"SELECT * FROM Grille g " +
				"INNER JOIN Utilisateur u " +
				"ON g.idUtilisateurConcepteur = u.idUtilisateur " + 
				"WHERE idTypeGrille = ? AND estValidee = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idType);
		pstmt.setBoolean(2, true);
		ResultSet results = pstmt.executeQuery();
		while (results.next()) {
			int idGrille = results.getInt("idGrille");
			int idLangue  = results.getInt("idLangue");
			int idTypeGrille  = results.getInt("idTypeGrille");
			int idUtilisateur = results.getInt("idUtilisateurConcepteur");
			String nomGrille = results.getString("nomGrille");
			int largeur = results.getInt("largeur");
			int longueur = results.getInt("hauteur");
			Date dateCreation = results.getDate("dateCreation");
			boolean estFinie = results.getBoolean("estFinie");
			boolean estValidee = results.getBoolean("estValidee");
			Date dateValidation = results.getDate("dateValidation");
			int niveau = results.getInt("niveau");
			int idTheme = results.getInt("idTheme");
			String pseudo = results.getString("pseudo");
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setIdUtilisateur(idUtilisateur);
			utilisateur.setPseudo(pseudo);
			Grille grille = new Grille(idGrille, idLangue, idTypeGrille,
					nomGrille, largeur, longueur, dateCreation, estFinie,
					estValidee, dateValidation, niveau, idTheme, utilisateur);
			mList.add(grille);
		}
		return mList;
	}
	
	public List<Grille> getGridCreateByUser(int idUser) throws SQLException {
		List<Grille> grilles = new LinkedList<Grille>();
		String query = "SELECT * FROM Grille " +
						"WHERE idUtilisateurConcepteur = ? " +
						"ORDER BY dateCreation DESC";//AND estFini = 0 AND estValidee = 0";
		ResultSet results = null;
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idUser);
		results = pstmt.executeQuery();
		while (results.next()) {
			int idGrille = results.getInt("idGrille");
			int idLangue  = results.getInt("idLangue");
			int idTypeGrille  = results.getInt("idTypeGrille");
			int idUtilisateur = results.getInt("idUtilisateurConcepteur");
			String nomGrille = results.getString("nomGrille");
			int largeur = results.getInt("largeur");
			int longueur = results.getInt("hauteur");
			Date dateCreation = results.getDate("dateCreation");
			boolean estFinie = results.getBoolean("estFinie");
			boolean estValidee = results.getBoolean("estValidee");
			Date dateValidation = results.getDate("dateValidation");
			int niveau = results.getInt("niveau");
			int idTheme = results.getInt("idTheme");
			//String pseudo = results.getString("pseudo");
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setIdUtilisateur(idUtilisateur);
			//utilisateur.setPseudo(pseudo);
			Grille grille = new Grille(idGrille, idLangue, idTypeGrille,
					nomGrille, largeur, longueur, dateCreation, estFinie,
					estValidee, dateValidation, niveau, idTheme, utilisateur);
			/*CommentaireDao commDao = new CommentaireDao();
			List<Commentaire> commList = commDao.getByIdGrille(idGrille);
			grille.setCommentaires(commList);*/
			MotGrilleDao motsDao = new MotGrilleDao();
			List<MotGrille> motsGrille = motsDao.getByIdGrille(idGrille);
			grille.setMotsGrille(motsGrille);
			grilles.add(grille);
		}
		return grilles;
	}

	public enum TypeGrille {
		MOTS_FLECHES, MOTS_CROISES;
	}

	public void finishGrid(int idGrille) throws SQLException {
		String query = "UPDATE Grille SET estFinie = true " +
				"WHERE idGrille = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		pstmt.executeUpdate();
	}
	
	public void validateGrid(int idGrille) throws SQLException {
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String query = "UPDATE Grille SET estValidee = true, " +
				"dateValidation = ? " +
				"WHERE idGrille = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setDate(1, sqlDate);
		pstmt.setInt(2, idGrille);
		pstmt.executeUpdate();
	}
	
	public void unvalidateGrid(int idGrille, String message) 
			throws SQLException {
		if (message == null) {
			throw new IllegalArgumentException("Le message est null");
		}
		if (message.equals("")) {
			throw new IllegalArgumentException("Le message est vide");
		}
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String query = 	"INSERT INTO GrilleNonValideeMessage " +
						"VALUES(NULL, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		pstmt.setString(2, message);
		pstmt.setDate(3, sqlDate);
		pstmt.executeUpdate();
	}
	
	public List<GrilleNonValideeMessage> getUnvalidateGridMessage(int idGrille)
			throws SQLException {
		String query = 	"SELECT * FROM GrilleNonValideeMessage " +
						"WHERE idGrille = ?";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, idGrille);
		ResultSet rs = pstmt.executeQuery();
		List<GrilleNonValideeMessage> list = 
				new LinkedList<GrilleNonValideeMessage>();
		while(rs.next()) {
			int idMessage = rs.getInt("idMessage");
			String message = rs.getString("message");
			Date date = rs.getDate("date");
			GrilleNonValideeMessage m = 
					new GrilleNonValideeMessage(idMessage,
							idGrille, message, date);
			list.add(m);
		}
		return list;
	}

	public List<Grille> getGridToValidate() throws SQLException {
		String query = 	"SELECT * FROM Grille " +
						"WHERE estFinie = true AND estValidee = false";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet results = pstmt.executeQuery();
		List<Grille> mList = new LinkedList<Grille>();
		while (results.next()) {
			int idGrille = results.getInt("idGrille");
			int idLangue  = results.getInt("idLangue");
			int idTypeGrille  = results.getInt("idTypeGrille");
			int idUtilisateur = results.getInt("idUtilisateurConcepteur");
			String nomGrille = results.getString("nomGrille");
			int largeur = results.getInt("largeur");
			int longueur = results.getInt("hauteur");
			Date dateCreation = results.getDate("dateCreation");
			boolean estFinie = results.getBoolean("estFinie");
			boolean estValidee = results.getBoolean("estValidee");
			Date dateValidation = results.getDate("dateValidation");
			int niveau = results.getInt("niveau");
			int idTheme = results.getInt("idTheme");
			//String pseudo = results.getString("pseudo");
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setIdUtilisateur(idUtilisateur);
			//utilisateur.setPseudo(pseudo);
			Grille grille = new Grille(idGrille, idLangue, idTypeGrille,
					nomGrille, largeur, longueur, dateCreation, estFinie,
					estValidee, dateValidation, niveau, idTheme, utilisateur);
			mList.add(grille);
		}
		return mList;
	}

}
