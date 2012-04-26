package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.Langue;

public class LangueDao extends Dao<Langue> {

	@Override
	public Langue findById(int id) throws SQLException {
		Langue mLangue = null;
		String query = "SELECT * FROM Langue WHERE idLangue = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if (!results.first()) {
			return null;
		}
		String name = results.getString("nomLangue");
		mLangue = new Langue(id, name);
		return mLangue;
	}
	
	public List<Langue> getLangues() throws SQLException {
		List<Langue> langues = new LinkedList<Langue>();
		String query = "SELECT * FROM Langue";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		while(results.next()) {
			int id = results.getInt("idLangue");
			String name = results.getString("nomLangue");
			Langue l = new Langue(id, name);
			langues.add(l);
		}
		return langues;
	}

	@Override
	public Langue create(Langue obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Langue update(Langue obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Langue obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
