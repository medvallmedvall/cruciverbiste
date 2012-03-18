package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.Theme;

public class ThemeDao extends Dao<Theme> {

	@Override
	public Theme findById(int id) {
		Theme mTheme = null;
		String query3 = "SELECT * FROM Theme WHERE idTheme = " + id;
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query3);
			if (!results.first()) {
				return null;
			}
			String name = results.getString("nomTheme");
			mTheme = new Theme(id, name);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mTheme;
	}

	@Override
	public Theme create(Theme obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Theme update(Theme obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Theme obj) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Theme> getThemes() {
		List<Theme> mList = new LinkedList<Theme>();
		String query = "SELECT * FROM Theme";
		ResultSet results = null;
		try {
			results = this.connection.createStatement().executeQuery(query);
			while (results.next()) {
				int idTheme  = results.getInt("idTheme");
				String name  = results.getString("nomTheme");
				Theme theme = new Theme(idTheme, name);
				mList.add(theme);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

}