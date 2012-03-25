package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import entities.Commentaire;
import entities.Definition;
import entities.Grille;
import entities.Mot;
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
	public Grille create(Grille obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grille update(Grille obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Grille obj) {
		// TODO Auto-generated method stub

	}

	public List<Grille> getGrilles(TypeGrille type) {
		List<Grille> mList = new LinkedList<Grille>();
		int idType = type.ordinal() + 1;
		String query = 	"SELECT * FROM Grille g " +
				"INNER JOIN Utilisateur u " +
				"ON g.idUtilisateurConcepteur = u.idUtilisateur " + 
				"WHERE idTypeGrille = " + idType;
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query);
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

	public enum TypeGrille {
		MOTS_FLECHES, MOTS_CROISES;
	}

}
