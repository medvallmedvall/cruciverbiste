package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Concours;
import entities.UtilisateurConcours;

public class ConcoursDao extends Dao<Concours>{
	
	public ConcoursDao() {
		super();
	}

	@Override
	public Concours findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		Concours conc = null;
		String query = "select * from concours where idconcours = ?" ;
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet results = pstmt.executeQuery();
		if (!results.first()) {
			return null;
		}
		int idGrille = results.getInt("idgrille");
		Date datedeb = results.getDate("datedeb");
		Date datefin = results.getDate("datefin");
		conc = new Concours(id, idGrille, datedeb, datefin);
		return conc;
	}

	@Override
	public Concours create(Concours obj) throws SQLException {
		// TODO Auto-generated method stub
		Concours concours = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int idGrille = obj.getIdGrille();
		Date datedeb = obj.getDateDebut();
		Date datefin = obj.getDateFin();
		String s = format.format(datedeb);
		String t = format.format(datefin);
		String query = "insert into concours(idGrille, datedeb, datefin) values(?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setObject(1, idGrille);
		pstmt.setObject(2, s);
		pstmt.setObject(3, t);
		pstmt.executeUpdate();
		concours = new Concours(idGrille,datedeb,datefin);
		return concours;
		
	}

	@Override
	public Concours update(Concours obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Concours obj) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from concours where idconcours = " + obj.getIdConcours();
		PreparedStatement pstmt = connection.prepareStatement(query);
		while (!this.getUtilisateurs(obj.getIdConcours()).isEmpty()) {
			for (UtilisateurConcours c : this.getUtilisateurs(obj.getIdConcours())) {
				String req = "delete from UtilisateurConcours where idUtilisateur = " + c.getIdUtilisateur() +  " and idConcours = " + c.getIdConcours();
				this.connection.prepareStatement(req).executeUpdate();
			}
		}
		pstmt.executeUpdate();
	}
	
	public List<UtilisateurConcours> getUtilisateurs(int idConcours) {
		String query = "select * from utilisateurconcours where idConcours = ?";
		List<UtilisateurConcours> list = new ArrayList<UtilisateurConcours>();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(query);
			stmt.setObject(1, idConcours);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int idUtilisateur = rs.getInt("idUtilisateur");
				Boolean aReussi = rs.getBoolean("aReussi");
				UtilisateurConcours user = new UtilisateurConcours(idConcours, idUtilisateur, aReussi);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
		
		
	}
	
	
	
	public boolean verifyGrilleConcours(int idGrille){
		String query = "select * from concours where idgrille = ?";
		PreparedStatement ps;
		Boolean b = false;
		try {
			ps = this.connection.prepareStatement(query);
			ps.setObject(1, idGrille);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				b =  true;
			} else {
				b =  false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
		
	}
	
	
	public UtilisateurConcours create(int idConcours, int idUtilisateur) throws SQLException {
		UtilisateurConcours userConcours = null;
		String query = "insert into utilisateurconcours values(?,?,?)";
		PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setObject(1, idConcours);
		stmt.setObject(2, idUtilisateur);
		stmt.setObject(3, false);
		stmt.executeUpdate();
		userConcours = new UtilisateurConcours(idConcours, idUtilisateur, false);
		return userConcours;
	}
	
	public void update(int idConcours, int idUtilisateur) {
		String query = "update utilisateurconcours set estfini = 1 where idConcours = " + idConcours 
				+ "and idUtilisateur = " + idUtilisateur + "";
		ResultSet rs = null;
		try {
			rs = this.connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Concours> getAllConcours() {
		List<Concours> listConcours = new ArrayList<Concours>();
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String query = "select * from concours";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idConcours = rs.getInt("idConcours");
				Date dateDeb = rs.getDate("datedeb");
				Date dateFin = rs.getDate("datefin");
				int idGrille = rs.getInt("idGrille");
				Concours conc = new Concours(idConcours, idGrille, dateDeb , dateFin);
				listConcours.add(conc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listConcours;
		
		
	}

}
