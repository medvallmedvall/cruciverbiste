package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import entities.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {

	public UtilisateurDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilisateur findById(int id) {
		return null;
	}

	@Override
	public Utilisateur create(Utilisateur obj) {
		// TODO Auto-generated method stub

		try {
			Statement sql = this.connection.createStatement();
			String prenom = obj.getPrenom();
			String nom = obj.getNom();
			//String dateNaissance = obj.getDateNaissance();
			Date d = new Date();
			String jour = d.getDate() + "";
			int m = d.getMonth() + 1;
			String mois = m +"";
			int y = d.getYear() + 1900;
			String year = y + "";
			if ((m >= 1) || (m <= 9)) {
				mois = "0" + mois;
			}
			if ((d.getDate() >= 1) || (d.getDate()) <= 9) {
				jour = "0" + jour;
			}
		
			String	dateInscription = "" + jour + "/"+ mois + "/" + year+"";
			String pseudo = obj.getPseudo();
			String password = obj.getPassword();
			String mail = obj.getMail();
			/*String req = "INSERT INTO Utilisateur (nom,"
					+ " prenom, pseudo, password, mail, dateInscription,"
					+ " dateNaissance)" + " VALUES ('"
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
					+ dateInscription
					+ "','"
					+ dateNaissance + "')";
			sql.executeUpdate(req);*/

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
	
	public boolean verifyUtilisateurExists(Utilisateur obj) {
		String query = "select * from Utilisateur";
		Boolean b = false; 
		List <Utilisateur> listUsers = new ArrayList<Utilisateur>();
		try {
			ResultSet rs = this.connection.createStatement().executeQuery(query);
			if (rs == null) {
				b = false;
			}
			while (rs.next()) {
				String nom_utilisateur = rs.getString("nom");
				String prenom_utilisateur = rs.getString("prenom");
				String pseudo_utilisateur = rs.getString("pseudo");
				String pass = rs.getString("password");
				String mail = rs.getString("mail");
				String naissance = rs.getString("datenaissance");
				/*Utilisateur user = new Utilisateur(nom_utilisateur, 
						prenom_utilisateur, pseudo_utilisateur, mail, pass, naissance);
				listUsers.add(user);*/
				
			}
			for (Utilisateur u : listUsers) {
				if ((obj.getPseudo().equals(u.getPseudo())) || (obj.getMail().equals(u.getMail()))) {
					b = true;
				} else {
					b = false;
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}

}

