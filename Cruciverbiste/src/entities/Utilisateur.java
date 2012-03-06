package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import entities.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {

	static Locale locale = Locale.getDefault();
	static Date actuelle = new Date();

	// * Definition du format utilise pour les dates
	static DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

	// * Donne la date au format "aaaa-mm-jj"
	public static String date() {
		String dat = dateFormat.format(actuelle);
		return dat;
	}

	public UtilisateurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilisateur findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur create(Utilisateur obj) {
		// TODO Auto-generated method stub

		try {
			Statement sql = this.connection.createStatement();
			String prenom = obj.getPrenom();
			String nom = obj.getNom();
			Date dateNaissance = obj.getDateNaissance();
			Date dateInscription = new Date();
			String pseudo = obj.getPseudo();
			String password = obj.getPassword();
			String mail = obj.getMail();
			try {

				String req = "INSERT INTO Utilisateurs (idUtilisateur, nom,"
						+ " prenom, pseudo, password, mail, date_inscription,"
						+ " date_naissance)" + " VALUES (5, '"
						+ nom
						+ "','"
						+ prenom
						+ "','"
						+ pseudo
						+ "','"
						+ password
						+ "','"
						+ mail
						+ "','"
						+ dateInscription.getYear()
						+ "-"
						+ dateInscription.getMonth()
						+ "-"
						+ dateInscription.getDay()
						+ "','"
						+ dateNaissance.getYear()
						+ "-"
						+ dateNaissance.getMonth()
						+ "-"
						+ dateNaissance.getDay() + "')";
				sql.executeUpdate(req);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Utilisateur update(Utilisateur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Utilisateur obj) {
		// TODO Auto-generated method stub

	}

}
