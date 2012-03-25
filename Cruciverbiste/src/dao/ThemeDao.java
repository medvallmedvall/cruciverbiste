package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.Theme;

public class ThemeDao extends Dao<Theme> {

	@Override
	public Theme findById(int id) throws SQLException {
		Theme mTheme = null;
		String query3 = "SELECT * FROM Theme WHERE idTheme = " + id;
		ResultSet results = null;
		results = this.connection.createStatement().executeQuery(query3);
		if (!results.first()) {
			return null;
		}
		String name = results.getString("nomTheme");
		mTheme = new Theme(id, name);
		return mTheme;
	}

	@Override
	public Theme create(Theme obj) throws SQLException {
		Theme theme = null;
		String query = "INSERT INTO Theme VALUES (NULL, ?)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getNomTheme());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idTheme");
			theme = findById(id);
		}
		return theme;
	}

	@Override
	public Theme update(Theme obj) throws SQLException {
		Theme theme = null;
		String query = "UPDATE Theme SET nomTheme = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, obj.getNomTheme());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.first()) {
			int id = rs.getInt("idTheme");
			theme = findById(id);
		}
		return theme;
	}

	@Override
	public void delete(Theme obj) throws SQLException {
		String query = "DELETE FROM Theme WHERE idTheme = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, obj.getIdTheme());
		stmt.executeUpdate();
	}

	public List<Theme> getThemes() throws SQLException {
		List<Theme> mList = new LinkedList<Theme>();
		String query = "SELECT * FROM Theme";
		ResultSet results = null;
		PreparedStatement stmt = connection.prepareStatement(query);
		results = stmt.executeQuery();
		while (results.next()) {
			int idTheme  = results.getInt("idTheme");
			String name  = results.getString("nomTheme");
			Theme theme = new Theme(idTheme, name);
			mList.add(theme);
		}
		return mList;
	}

}
