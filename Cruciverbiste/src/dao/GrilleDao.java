package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import entities.CaseNoire;
import entities.Grille;
import entities.MotGrille;

public class GrilleDao extends Dao<Grille> {

	public GrilleDao() {
		super();
	}
	
	
	public List<Grille> getGrilles(TypeGrille type) {
		List<Grille> mList = new LinkedList<Grille>();
		int idType = type.ordinal() + 1;
		String query = "SELECT * FROM grilles WHERE id_typegrille = " + idType;
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query);
			while (results.next()) {
				int idLangue  = results.getInt("id_Langue");
				int idTypeGrille  = results.getInt("id_typegrille");
				int idUtilisateur = results.getInt("idUtilisateurConcepteur");
				String nomGrille = results.getString("nomGrille");
				int largeur = results.getInt("largeur");
				int longueur = results.getInt("longueur");
				Date dateCreation = results.getDate("date_Creation");
				boolean estFinie = results.getBoolean("estFinie");
				boolean estValidee = results.getBoolean("estValidee");
				Date dateValidation = results.getDate("date_validation");
				int niveau = results.getInt("niveau");
				int idTheme = results.getInt("id_theme");
				Grille grille = new Grille(idLangue, idTypeGrille, idUtilisateur,
						nomGrille, largeur, longueur, dateCreation, estFinie,
						estValidee, dateValidation, niveau, idTheme);
				mList.add(grille);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

	@Override
	public Grille findById(int id) {
		Grille grille = null;
		String query3 = "SELECT * FROM grilles WHERE id_grille = " + id;
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query3);
			while(results.next()) {
				return null;
			}
			int idLangue  = results.getInt("id_Langue");
			int idTypeGrille  = results.getInt("id_typegrille");
			int idUtilisateur = results.getInt("idUtilisateurConcepteur");
			String nomGrille = results.getString("nomGrille");
			int largeur = results.getInt("largeur");
			int longueur = results.getInt("longueur");
			Date dateCreation = results.getDate("date_Creation");
			boolean estFinie = results.getBoolean("estFinie");
			boolean estValidee = results.getBoolean("estValidee");
			Date dateValidation = results.getDate("date_validation");
			int niveau = results.getInt("niveau");
			int idTheme = results.getInt("id_theme");
			grille = new Grille(idLangue, idTypeGrille, idUtilisateur,
					nomGrille, largeur, longueur, dateCreation, estFinie,
					estValidee, dateValidation, niveau, idTheme);
			String query2 = "";
			//si c'est un mot croisé
			if (idTypeGrille == 2) {
				String query1 = "SELECT * FROM casesNoires WHERE id_grille = " + id;
				results = this.connection.createStatement().executeQuery(query1);
				Set<CaseNoire> casesNoires = new LinkedHashSet<CaseNoire>();
				while(results.next()) {
					int idGrille = id;
					int coordX = results.getInt("coordX");
					int coordY = results.getInt("coordY");
					casesNoires.add(new CaseNoire(idGrille, coordX, coordY));
				}
				grille.setCasesNoireses(casesNoires);
				query2 = "SELECT * " +
						"FROM grillemots g " +
						"INNER JOIN dictionnairefr d ON g.id_Mot = d.id_Mot " +
						"INNER JOIN definitionsfr def ON g.id_synonyme = def.id_defintion " +
						"WHERE idGrille = " + id + " " +
								"ORDER BY coord_y, coord_x";
			}
			else {
				query2 = "SELECT * " +
						"FROM grillemots g " +
						"INNER JOIN dictionnairefr d ON g.id_Mot = d.id_Mot " +
						"INNER JOIN synonymesfr s ON g.id_synonyme = s.id_synonyme " +
						" WHERE idGrille = " + id + "";
			}
			Set<MotGrille> motsGrilles = new LinkedHashSet<MotGrille>();
			results = this.connection.createStatement().executeQuery(query2);
			while(results.next()) {
				int idGrille = id;
				int orientation = results.getInt("id_Orientation");
				String mot = results.getString("mot");
				String definition = "";
				if (idTypeGrille == 2) {
					definition = results.getString("definition");
				}
				else {
					definition = results.getString("synonyme");
				}
				int coordX = results.getInt("coord_x");
				int coordY = results.getInt("coord_y");
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
	
	public enum TypeGrille {
		MOTS_FLECHES, MOTS_CROISES;
	}

}
