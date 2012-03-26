package dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;


import entities.Theme;

public class TestThemeDao {
	private ThemeDao dao;

	public TestThemeDao() {
		super();
		dao = (ThemeDao) DaoFactory.getThemeDao();
	}


	@After
	public void tearDown() throws SQLException {
		//vider la table
		dao.clearTable();
	}

	@Test
	public void createThemeOk() throws SQLException {
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createThemeNull() throws SQLException {
		Theme theme = null;
		dao.create(theme);
	}

	@Test(expected=SQLException.class)
	public void createThemeNotUnique() throws SQLException {
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		Theme theme2 = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme2);
		res = dao.create(theme);
	}

	@Test
	public void deleteThemeOk() throws SQLException {
		//ajout
		Theme theme = new Theme(-1, "testTheme2");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		//suppression
		dao.delete(res);
		Theme res2 = dao.findById(res.getIdTheme());
		Assert.assertNull(res2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteThemeNull() throws SQLException {
		Theme theme = null;
		dao.delete(theme);
	}

	@Test
	public void updateThemeOK() throws SQLException {
		//ajout
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		//update
		res.setNomTheme("newNameTheme");
		Theme res2 = dao.update(res);
		Assert.assertNotNull(res2);
		Assert.assertNotSame(res, res2);
		Assert.assertEquals(res, res2);
		Assert.assertFalse(theme.equals(res));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateThemeNull() throws SQLException {
		Theme theme = null;
		dao.update(theme);
	}

	@Test
	public void updateThemeNoInDDB() throws SQLException {
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.update(theme);
		Assert.assertNull(res);
		Assert.assertNotSame(theme, res);
	}

	@Test(expected=SQLException.class)
	public void updateThemeSameName() throws SQLException {
		//ajout
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		//ajout 2eme
		Theme theme2 = new Theme(-1, "testTheme2");
		Assert.assertNotNull(theme2);
		Theme res2 = dao.create(theme2);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme2, res2);
		Assert.assertFalse(theme2.getIdTheme() == res2.getIdTheme());
		//update
		res2.setNomTheme("testTheme");
		dao.update(res2);
	}

	@Test
	public void testGetThemes() throws SQLException {
		List<Theme> list = new LinkedList<Theme>();
		List<Theme> resList = new LinkedList<Theme>();
		//creation
		for (int i = 0; i < 4; i++) {
			String themeName = "testTheme" + i;
			Theme t = new Theme(-1, themeName);
			Theme tRes = dao.create(t);
			list.add(tRes);
		}
		resList = dao.getThemes();
		Assert.assertNotNull(resList);
		Assert.assertEquals(list.size(), resList.size());
		Assert.assertEquals(list, resList);
		for (Theme t : resList) {
			Theme tmp = dao.findById(t.getIdTheme());
			Assert.assertNotNull(tmp);
			Assert.assertEquals(t, tmp);
			boolean ok = false;
			for (Theme t2 : list) {
				if (t.getNomTheme().equals(t2.getNomTheme())) {
					ok = true;
					break;
				}
			}
			if (!ok) {
				Assert.fail("Tous les themes ne sont pas recupereres");
			}
		}
	}

}
