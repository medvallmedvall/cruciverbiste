package dao;

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
	public Grille findById(int id) {
		Grille grille = null;
		String query3 = "SELECT * FROM Grille g " +
						"INNER JOIN Utilisateur u " +
						"ON g.idUtilisateurConcepteur = u.idUtilisateur " +
						"WHERE idGrille = " + id;
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query3);
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
			String query2 = "SELECT * " +
					"FROM MotGrille g " +
					"INNER JOIN DictionnaireFR d ON g.idMot = d.idMot " +
					"INNER JOIN DefinitionFR def ON g.idDefinition = def.idDefinition " +
					"LEFT OUTER JOIN SynonymeFR s ON g.idMot = s.idMot " +
					"WHERE idGrille = " + id + " " +
					"ORDER BY orderId";
			Set<MotGrille> motsGrilles = new LinkedHashSet<MotGrille>();
			results = this.connection.createStatement().executeQuery(query2);
			while(results.next()) {
				int idMot = results.getInt("idMot");
				String motStr = results.getString("mot");
				List<String> synonymes = new LinkedList<String>();
				String synonyme = "";
				if (results.getString("synonyme") != null) {
					synonyme = results.getString("synonyme");
				}
				synonymes.add(synonyme);
				Mot mot = new Mot(idMot, motStr, synonymes);
				int idDefinition = results.getInt("idDefinition");
				String definitionStr = results.getString("definition");
				Definition definition = new Definition(idDefinition, definitionStr);
				int idGrille = id;
				int orientation = results.getInt("idOrientation");
				int coordX = results.getInt("coordX");
				int coordY = results.getInt("coordY");
				motsGrilles.add(new MotGrille(idGrille, orientation, mot, definition, coordX, coordY));
			}
			grille.setMotsGrille(motsGrilles);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
