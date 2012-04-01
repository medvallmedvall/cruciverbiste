package dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;

import entities.Grille;

public class TestCommentaireDao {
	private CommentaireDao dao;
	
	public TestCommentaireDao() {
		super();
		dao = (CommentaireDao) DaoFactory.getCommentaireDao();
	}
	
	@After
	public void tearDown() throws SQLException {
		//vider la table
		dao.clearTable();
	}
	
	public void createCommentaireOk() {
		//Grille grille = new Grille(-1, 1, 1, "grille1", 10, 10, new Date(), false, false, new Date(), 1	, 1, utilisateur)
	}
}
