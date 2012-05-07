package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Definition;

public class DefinitionDao extends Dao<Definition> {

	@Override
	public Definition findById(int id) throws SQLException {
		Definition def = null;
		String query = "SELECT * FROM DefinitionFR WHERE idDefinition = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		if (!results.first()) {
			return null;
		}
		String definition = results.getString("definition");
		boolean validate = results.getBoolean("existe");
		def = new Definition(id, definition, validate);
		return def;
	}

	@Override
	public Definition create(Definition obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("la definition est null");
		}
		String chaine = obj.getDefinition();
		Definition def = getByDefinition(chaine);
		if (def != null) {
			return def;
		}
		String query = "INSERT INTO  DefinitionFR VALUES(NULL, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, obj.getDefinition());
		pstmt.setBoolean(2, false);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt(1);
			def = findById(id);
		}
		return def;
	}

	public Definition getByDefinition(String chaine) throws SQLException {
		Definition def = null;
		String query = "SELECT * FROM DefinitionFR WHERE definition LIKE ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, chaine);
		ResultSet rs = stmt.executeQuery();
		if (rs.first()) {
			int idDef = rs.getInt("idDefinition");
			String s = rs.getString("definition");
			boolean validate = rs.getBoolean("existe");
			def = new Definition(idDef, s, validate);
			return def;
		}
		return def;
	}

	@Override
	public Definition update(Definition obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La definition est null");
		}
		Definition def = null;
		String query = "UPDATE DefinitionFR SET definition = ? WHERE idDefinition = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getDefinition());
		stmt.setInt(2, obj.getIdDefinition());
		stmt.executeUpdate();
		def = findById(obj.getIdDefinition());
		return def;
	}

	@Override
	public void delete(Definition obj) throws SQLException {
		if (obj == null) {
			throw new IllegalArgumentException("La definition est null");
		}
		String query = "DELETE FROM DefinitionFR WHERE idDefinition = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, obj.getIdDefinition());
		stmt.executeUpdate();
	}

}
