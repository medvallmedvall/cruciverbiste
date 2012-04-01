package dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import entities.Mot;
import entities.Theme;

public class TestMotDao {
	private MotDao dao;

	public TestMotDao() {
		super();
		dao = (MotDao) DaoFactory.getMotDao();
	}


	@After
	public void tearDown() throws SQLException {
		//vider la table
		dao.clearTable();
	}

	@Test
	public void createNewMotOk() throws SQLException {
		Mot mot = new Mot(-1, "testMot1", new LinkedList<String>());
		/*Mot mot = new Mot(-1, "", synonymes)
		Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());*/
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createMotNull() throws SQLException {
		Mot mot = null;
		dao.create(mot);
	}

	@Test(expected=SQLException.class)
	public void createMotExistNewSynonym() throws SQLException {
		/*Theme theme = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		Theme theme2 = new Theme(-1, "testTheme");
		Assert.assertNotNull(theme2);
		res = dao.create(theme);*/
	}

	@Test
	public void deleteMotOk() throws SQLException {
		/*//ajout
		Mot mot = new Mot(-1, "testTheme2", new LinkedList<String>());
		Assert.assertNotNull(theme);
		Theme res = dao.create(theme);
		Assert.assertNotNull(res);
		Assert.assertNotSame(theme, res);
		Assert.assertFalse(theme.getIdTheme() == res.getIdTheme());
		//suppression
		dao.delete(res);
		Theme res2 = dao.findById(res.getIdTheme());
		Assert.assertNull(res2);*/
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deleteMotNull() throws SQLException {
		Mot mot = null;
		dao.delete(mot);
	}

	@Test
	public void updateMotOK() throws SQLException {
		/*//ajout
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
		Assert.assertFalse(theme.equals(res));*/
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void updateMotNull() throws SQLException {
		Mot mot = null;
		dao.update(mot);
	}

	@Test
	public void updateMotNoInDDB() throws SQLException {
		Mot mot = new Mot(-1, "testMot", new LinkedList<String>());
		Assert.assertNotNull(mot);
		Mot res = dao.update(mot);
		Assert.assertNull(res);
		Assert.assertNotSame(mot, res);
	}

	
}
