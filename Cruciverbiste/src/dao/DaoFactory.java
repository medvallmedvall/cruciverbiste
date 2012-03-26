package dao;

import entities.Theme;

public class DaoFactory {
	
	public static Dao<Theme> getThemeDao() {
		return new ThemeDao();
	}
}
