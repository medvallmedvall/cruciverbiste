package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import entities.CaseNoire;
import entities.Definition;
import entities.Grille;
import entities.Mot;
import entities.MotGrille;

public class GrilleDao extends Dao<Grille> {

	public GrilleDao() {
		super();
	}

	@Override
	public Grille findById(int id) {
		Grille grille = null;
		String query3 = "SELECT * FROM Grille WHERE idGrille = " + id;
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
			grille = new Grille(id, idLangue, idTypeGrille, idUtilisateur,
					nomGrille, largeur, longueur, dateCreation, estFinie,
					estValidee, dateValidation, niveau, idTheme);
			String query2 = "";
			//si c'est un mot croisé
			//if (idTypeGrille == 2) {
				String query1 = "SELECT * FROM CaseNoire WHERE idGrille = " + id;
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
						"FROM MotGrille g " +
						"INNER JOIN DictionnaireFR d ON g.idMot = d.idMot " +
						"INNER JOIN DefinitionFR def ON g.idDefinition = def.idDefinition " +
						"WHERE idGrille = " + id + " " +
								"ORDER BY coordY, coordX";
			/*}
			else {
				query2 = "SELECT * " +
						"FROM MotGrille g " +
						"INNER JOIN DictionnaireFR d ON g.idMot = d.idMot " + 
						"INNER JOIN SynonymeFR s ON g.idDefinition = s.idSynonyme " +
						" WHERE idGrille = " + id + "";*/
				/*query2 = "SELECT dictionnairefr.CouleurNom, dictionnairefr2.CouleurNom" +
						 "FROM grillemots g " +
						"INNER JOIN dictionnairefr ON g.id_Mot = d.id_Mot " +
							"INNER JOIN dictionnairefr AS dictionnairefr1 ON g.id_synonyme = d.id_Mot";*/
			//}
			Set<MotGrille> motsGrilles = new LinkedHashSet<MotGrille>();
			results = this.connection.createStatement().executeQuery(query2);
			while(results.next()) {
				int idMot = results.getInt("idMot");
				String motStr = results.getString("mot");
				List<Mot> synonymes = new LinkedList<Mot>();
				Mot mot = new Mot(idMot, motStr, synonymes);
				int idDefinition = -1;
				String definitionStr = "";
				//if (idTypeGrille == 2) {
					idDefinition = results.getInt("idDefinition");
					definitionStr = results.getString("definition");
				/*}
				else {
					idDefinition = results.getInt("idSynonyme");
					definitionStr = results.getString("synonyme");
				}*/
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
		String query = "SELECT * FROM Grille WHERE idTypeGrille = " + idType;
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
				Grille grille = new Grille(idGrille, idLangue, idTypeGrille, idUtilisateur,
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
	
	public enum TypeGrille {
		MOTS_FLECHES, MOTS_CROISES;
	}

}
