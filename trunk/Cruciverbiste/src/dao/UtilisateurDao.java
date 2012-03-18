package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import entities.DateConverter;
import entities.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {
	
	public DateConverter converter;
	

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
		
		/*
		 * Date mDate = new Date();
		System.out.println(mDate);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			String s = format.format(mDate);
			System.out.println(s);
			Date d = format.parse("03-02-1989");
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 * 
		 * 
		 * 
		 * */
		

		try {
			Statement sql = this.connection.createStatement();
			String prenom = obj.getPrenom();
			String nom = obj.getNom();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateNaissance = obj.getDateNaissance();
			Date dateInscription = new Date();
			String s = format.format(dateInscription);
			System.out.println(s);
			String t = format.format(dateNaissance);
			//Date newNaissance = DateConverter.StringToDate(t);
			
			//System.out.println(newNaissance.toString() + " " + newInscription.toString());
			
			
//			String jour = dateInscription.getDate() + "";
//			int m = dateInscription.getMonth() + 1;
//			String mois = m +"";
//			int y = dateInscription.getYear() + 1900;
//			String year = y + "";
//			if ((m >= 1) || (m <= 9)) {
//				mois = "0" + mois;
//			}
//			if ((dateInscription.getDate() >= 1) || (dateInscription.getDate()) <= 9) {
//				jour = "0" + jour;
//			}
		
			//String	dateInscription = "" + jour + "/"+ mois + "/" + year+"";
			String pseudo = obj.getPseudo();
			String password = obj.getPassword();
			String mail = obj.getMail();
			String req = "INSERT INTO Utilisateur (nom,"
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
					+ s
					+ "','"
					+ t + "')";
			sql.executeUpdate(req);

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
	
	//Verifier que l'e-mail n'existe pas
	public boolean verifyUserEmail(String mail) {
		String query = "select * from Utilisateur where mail = '" + mail + "' ";
		Boolean b = false;
		try {
			ResultSet rs = this.connection.createStatement().executeQuery(query);
			System.out.println("rentremail");
			if (rs.first()) {
				b = false;
				System.out.println(b);
			} else {
				b = true;
				System.out.println(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return b;
		
	}
	
	//Verifier que le pseudo n'existe pas
	public boolean verifyUserPseudo(String pseudo) {
		String query = "select * from Utilisateur where pseudo = '" + pseudo + "' ";
		Boolean b = false;
		try {
			ResultSet rs = this.connection.createStatement().executeQuery(query);
			System.out.println("rentrePseudo");
			if (rs.first()) {
				b = false;
				System.out.println(b);
			} else {
				b = true;
				System.out.println(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return b;
		
	}
	
	//Verification de l'existence de l'utilisateur lors de l'inscription
	
//	public boolean verifyUtilisateurExists(Utilisateur obj) {
//		boolean b = false;	
//		for (Utilisateur u : getUtilisateurs()) {
//				if ((obj.getPseudo().equalsIgnoreCase(u.getPseudo())) || (obj.getMail().equalsIgnoreCase(u.getMail()))) {
//					b = true;
//				} else {
//					b = false;
//				}
//			}
//		return b;
//		
//	}
	
	//Tous les utilisateurs du site
	public List<Utilisateur> getUtilisateurs() {
		String query = "select * from Utilisateur";
		List <Utilisateur> listUsers = new LinkedList<Utilisateur>();
		try {
			ResultSet rs = this.connection.createStatement().executeQuery(query);
			
			while (rs.next()) {
				String nom_utilisateur = rs.getString("nom");
				String prenom_utilisateur = rs.getString("prenom");
				String pseudo_utilisateur = rs.getString("pseudo");
				String pass = rs.getString("password");
				String mail = rs.getString("mail");
				Date naissance = rs.getDate("dateNaissance");
				Utilisateur user = new Utilisateur(nom_utilisateur, 
						prenom_utilisateur, pseudo_utilisateur, pass, mail, naissance);
				listUsers.add(user);
			}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}
	
	
	//Verification des param�tres de connexion de l'utilisateur
	public boolean verifyUtilisateurConnects(String pseudo, String password) {
		String query = "select * from Utilisateur where pseudo = '" + pseudo + "' and password = '" + password + "'";
		Boolean b = false;
		try {
			ResultSet rs = this.connection.createStatement().executeQuery(query);
			if (rs.first()) {
				b = true;
			} else {
				b = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return b;
	}
	



}
