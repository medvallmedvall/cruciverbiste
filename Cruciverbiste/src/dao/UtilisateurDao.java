package dao;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import entities.Commentaire;
import entities.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {


	public UtilisateurDao() {
		super();
	}

	@Override

	public Utilisateur findById(int id) throws SQLException {
		Utilisateur user = null;
		String req = " select * from Utilisateur where idUtilisateur = " + id;
		ResultSet rs = this.connection.createStatement().executeQuery(req);
		if (!rs.first()) {
			return null;
		}
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String pseudo = rs.getString("pseudo");
		String password = rs.getString("password");
		String mail = rs.getString("mail");
		Date dateN = rs.getDate("dateNaissance");
		Date dateInscription = rs.getDate("dateInscription");
		user = new Utilisateur(id, nom, prenom, pseudo, password, 
				mail, dateN, dateInscription);
			
		return user;
	}

	@Override
	public Utilisateur create(Utilisateur obj) throws SQLException{
			Utilisateur user = null;
			Connection forum = ConnexionForum.getInstance();
			Statement stmt = forum.createStatement();
			String prenom = obj.getPrenom();
			String nom = obj.getNom();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateNaissance = obj.getDateNaissance();
			Date dateInscription = new Date();
			String s = format.format(dateInscription);
			String t = format.format(dateNaissance);
			String pseudo = obj.getPseudo();
			String password = obj.getPassword();
			String mail = obj.getMail();
			
			//le mot de passe doit être crypte dans phpBB_users
			
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalStateException(e.getMessage());
			}
			md.update(password.getBytes());
			byte[] thedigest = md.digest();
			String passwordCrypted = new String(thedigest);
			
			String query = "INSERT INTO phpbb_users (group_id,username_clean, username, user_permissions, user_sig, user_occ, user_interests, user_password, user_email, user_birthday) VALUES(" +
					  2
					+ ",'"
					+ pseudo
					+ "','"
					+ pseudo.toLowerCase()
					+ "','"
					+ null
					+ "','"
					+ null
					+ "','"
					+ null
					+ "','"
					+ null
					+ "','"
					+ passwordCrypted
					+ "','"
					+ mail
					+ "','"
					+  t + "')";
			
			if ((!this.verifyUserPseudo(pseudo)) || (!this.verifyUserEmail(mail))) {
				return null;
			}
			
			String req = "INSERT INTO Utilisateur (nom, prenom, pseudo, password, mail, dateInscription, dateNaissance) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = this.connection.prepareStatement(req);
			
			ps.setObject(1, nom);
			ps.setObject(2, prenom);
			ps.setObject(3, pseudo);
			ps.setObject(4, password);
			ps.setObject(5, mail);
			ps.setObject(6, s);
			ps.setObject(7, t);
			
			ps.executeUpdate();
			
			String requete = "select idUtilisateur from Utilisateur where nom = '" + nom + "'";
			
			ResultSet rs = this.connection.createStatement().executeQuery(requete);
			
			if (rs.first()) {
				int id = rs.getInt("idUtilisateur");
				user = findById(id);
			}
			stmt.executeUpdate(query);
			return user;

	}

	@Override
	public Utilisateur update(Utilisateur obj) throws SQLException{
		// TODO Auto-generated method stub
		Utilisateur user = null;
		int idUtilisateur = obj.getIdUtilisateur();
		String prenom = obj.getPrenom();
		String nom = obj.getNom();
		String pseudo = obj.getPseudo();
		String password = obj.getPassword();
		String mail = obj.getMail();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaissance = obj.getDateNaissance();
		String t = format.format(dateNaissance);
		
		String query = 	"UPDATE Utilisateur " +
				"SET nom = ?, prenom = ?, " +
				"pseudo = ?, passWord = ?, " +
				" mail = ?, dateNaissance = ?" +
				"WHERE idUtilisateur = " + obj.getIdUtilisateur() + "";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, nom);
		pstmt.setString(2, prenom);
		pstmt.setString(3, pseudo);
		pstmt.setString(4, password);
		pstmt.setString(5, mail);
		pstmt.setString(6, t);
		//pstmt.setInt(7, idUtilisateur);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idUtilisateur");
			user = findById(id);
		}
		return user;
	}

	@Override
	public void delete(Utilisateur obj) {
		// TODO Auto-generated method stub

	}

	//Verifier que l'e-mail n'existe pas

		public boolean verifyUserEmail(String mail) throws SQLException{
			String query = "select * from Utilisateur where mail =?";
			Boolean b = false;
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				ps.setObject(1, mail);
				ResultSet rs = ps.executeQuery();
				if (rs.first()) {
					b = false;
				} else {
					b = true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return b;
		}
	
		//Verifier que le pseudo n'existe pas
	public boolean verifyUserPseudo(String pseudo)  throws SQLException{
		String query = "select * from Utilisateur where pseudo =?";
		Boolean b = false;
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setObject(1, pseudo);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				b = false;
			} else {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}


	public Utilisateur getBy(String mail) throws SQLException {
		String query = "select * from Utilisateur where mail = '"  + mail + "'" ;
		String pass = new String();
		String pseudo = new String();
		String nom = new String();
		String prenom = new String();
		Date dateNaissance = null;
		Utilisateur user = null;
		ResultSet rs = this.connection.createStatement().executeQuery(query);
		while (rs.next()) {
			pass = rs.getString("password");
			pseudo = rs.getString("pseudo");
			nom = rs.getString("nom");
			prenom = rs.getString("prenom");
			dateNaissance = rs.getDate("dateNaissance");
			user = new Utilisateur(nom, prenom, pseudo, pass, mail, dateNaissance);

		}
		return user;
	}

	//Tous les utilisateurs du site
	public List<Utilisateur> getUtilisateurs() throws SQLException {
		String query = "select * from Utilisateur";
		List <Utilisateur> listUsers = new LinkedList<Utilisateur>();
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
		return listUsers;
	}
	
	//Verification des param�tres de connexion de l'utilisateur
	public Utilisateur verifyUtilisateurConnects(String pseudo, String password) throws SQLException {
		if (pseudo == null) {
			throw new IllegalArgumentException("Le pseudo est null");
		}
		if (password == null) {
			throw new IllegalArgumentException("Le mot de passe est null");
		}
		String query =  "SELECT * FROM Utilisateur u " +
						"LEFT JOIN DroitUtilisateur d " +
						"ON u.idUtilisateur = d.idUtilisateur " +
						"WHERE pseudo = ? AND password = ?";
		PreparedStatement ps = this.connection.prepareStatement(query);
		ps.setObject(1, pseudo);
		ps.setObject(2, password);
		ResultSet rs = ps.executeQuery();
		Utilisateur utilisateur = null;
		if (rs.first()) {
			int id = rs.getInt("idUtilisateur");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("mail");
			Date dateNaissance = rs.getDate("dateNaissance");
			Date dateInscription = rs.getDate("dateInscription");
			int idDroit =  rs.getInt("idDroit");
			utilisateur = new Utilisateur(id, nom, prenom, pseudo, password, 
					email, dateNaissance, dateInscription, idDroit);
		}
		return utilisateur;
	}

}
