package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import entities.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {


	public UtilisateurDao() {
		super();
	}

	@Override
	public Utilisateur findById(int id) throws SQLException {
		Utilisateur utilisateur = null;
		String query = "SELECT * FROM Utilisateur WHERE idUtilisateur = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (!rs.first()) {
			return null;
		}
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String pseudo = rs.getString("pseudo");
		String pass = rs.getString("password");
		String email = rs.getString("mail");
		Date dateNaissance = rs.getDate("dateNaissance");
		Date dateInscription = rs.getDate("dateInscription");
		utilisateur = new Utilisateur(id, nom, prenom, pseudo, pass, 
				email, dateNaissance, dateInscription);
		return utilisateur;
	}

	@Override
	public Utilisateur create(Utilisateur obj) throws SQLException {
		Statement stmt = this.forum.createStatement();
		String prenom = obj.getPrenom();
		String nom = obj.getNom();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaissance = obj.getDateNaissance();
		Date dateInscription = new Date();
		String s = format.format(dateInscription);
		System.out.println(s);
		String t = format.format(dateNaissance);
		String pseudo = obj.getPseudo();
		String password = obj.getPassword();
		String mail = obj.getMail();
		//			String req = "INSERT INTO Utilisateur (nom,"
		//					+ " prenom, pseudo, password, mail, dateInscription,"
		//					+ " dateNaissance)" + " VALUES ('"
		//					+ nom
		//					+ "','"
		//					+ prenom
		//					+ "','"
		//					+ pseudo
		//					+ "','"
		//					+ password
		//					+ "','"
		//					+ mail
		//					+ "','"
		//					+ s
		//					+ "','"
		//					+ t + "')";
		String query = "INSERT INTO phpbb_users (group_id,username_clean, username, user_permissions, user_sig, user_occ, user_interests, user_password, user_email, user_birthday) VALUES(" +
				2
				+ ",'"
				+ pseudo
				+ "','"
				+ nom
				+ "','"
				+ null
				+ "','"
				+ null
				+ "','"
				+ null
				+ "','"
				+ null
				+ "','"
				+ password
				+ "','"
				+ mail
				+ "','"
				+  t + "')";



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
		stmt.executeUpdate(query);

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
	public boolean verifyUserEmail(String mail) throws SQLException {
		String query = "select * from Utilisateur where mail =?";
		Boolean b = false;

		PreparedStatement ps = this.connection.prepareStatement(query);
		ps.setObject(1, mail);
		ResultSet rs = ps.executeQuery();
		if (rs.first()) {
			b = false;
		} else {
			b = true;
		}

		return b;

	}

	//Verifier que le pseudo n'existe pas
	public boolean verifyUserPseudo(String pseudo) throws SQLException {
		String query = "select * from Utilisateur where pseudo =?";
		Boolean b = false;
		PreparedStatement ps = this.connection.prepareStatement(query);
		ps.setObject(1, pseudo);
		ResultSet rs = ps.executeQuery();
		if (rs.first()) {
			b = false;
		} else {
			b = true;
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
	public Utilisateur verifyUtilisateurConnects(String pseudo, 
			String password) throws SQLException{
		String query = 	"SELECT * FROM Utilisateur " +
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
			utilisateur = new Utilisateur(id, nom, prenom, pseudo, password, 
					email, dateNaissance, dateInscription);
		}
		return utilisateur;
	}





	/*public boolean verifyUtilisateurConnects(String pseudo, String password){
			Boolean b = false;
			String q = "select * from Utilisateur where pseudo = ? and password = ?";

			try {
				PreparedStatement ps = this.connection.prepareStatement(q);
				ps.setObject(1, pseudo);
				ps.setObject(2, password);
				ResultSet rs = ps.executeQuery();
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



	//Verification des param�tres de connexion de l'utilisateur
			public boolean verifyUtilisateurConnects(Utilisateur u){
				Boolean b = false;
				String q = "select * from Utilisateur where pseudo = ? and password = ?";

				try {
					PreparedStatement ps = this.connection.prepareStatement(q);
					ps.setObject(1, u.getPseudo());
					ps.setObject(2, u.getPassword());
					ResultSet rs = ps.executeQuery();
					if (rs.first()) {
						u.setIdUtilisateur(rs.getInt("idUtilisateur"));
						u.setNom(rs.getString("nom"));
						b = true;
					} else {
						b = false;
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				}
				return b;
			}*/
}
