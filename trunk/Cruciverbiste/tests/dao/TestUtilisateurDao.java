package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import entities.Utilisateur;

public class TestUtilisateurDao {
	private UtilisateurDao dao;

	public TestUtilisateurDao() {
		super();
		dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
	}

	@After
	public void tearDown() throws SQLException {
		//vider la table
		dao.clearTable();
	}

	@Test
	public void findByIdOk() throws SQLException {
		//insertion
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//verification
		Utilisateur uRes2 = dao.findById(uRes.getIdUtilisateur());
		Assert.assertNotNull(uRes2);
		Assert.assertNotSame(uRes, uRes2);
		Assert.assertEquals(uRes, uRes2);
	}

	@Test
	public void findByIdInvalid() throws SQLException {
		Utilisateur res = dao.findById(-1);
		Assert.assertNull(res);
	}

	@Test
	public void createUtilisateurOK() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		Assert.assertFalse(u.getIdUtilisateur() == uRes.getIdUtilisateur());
		Assert.assertEquals(u.getNom(), uRes.getNom());
		Assert.assertEquals(u.getPrenom(), uRes.getPrenom());
		Assert.assertEquals(u.getPseudo(), uRes.getPseudo());
		Assert.assertEquals(u.getPassword(), uRes.getPassword());
		Assert.assertEquals(u.getMail(), uRes.getMail());
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Assert.assertEquals(f.format(u.getDateNaissance()), f.format(uRes.getDateNaissance()));
		Utilisateur uRes1 = dao.findById(uRes.getIdUtilisateur());
		Assert.assertNotNull(uRes1);
		Assert.assertNotSame(uRes, uRes1);
		Assert.assertEquals(uRes, uRes1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createUtilisateurNull() throws SQLException {
		Utilisateur u = null;
		Utilisateur uRes = dao.create(u);
		Assert.assertNull(uRes);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurSamePseudo() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		Utilisateur u2 = new Utilisateur("Lolaaa", "Justine", "mPseudo", "bbbbb", "kk@gmail.fr", new Date());
		Assert.assertNotNull(u2);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurSameEmail() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		Utilisateur u2 = new Utilisateur("Lolaaa", "Justine", "justine", "bbbbb", "truc@hot.fr", new Date());
		Assert.assertNotNull(u2);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurNomNull() throws SQLException {
		Utilisateur u = new Utilisateur(null, "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurNomEmpty() throws SQLException {
		Utilisateur u = new Utilisateur("", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPrenomNull() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", null, "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPrenomEmpty() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPseudoNull() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", null, "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPseudoEmpty() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createUtilisateurPseudoShort() throws SQLException {
		/*Minimum 4 caracteres*/
		Utilisateur u = new Utilisateur("Machin", "Micheline", "pse", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPasswordNull() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", null, "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurPasswordEmpty() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createUtilisateurPasswordShort() throws SQLException {
		//Minimum 5 caracteres
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "pass", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
	}


	@Test(expected=SQLException.class)
	public void createUtilisateurEmailNull() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaa", null, new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurEmailEmpty() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=IllegalArgumentException.class)
	public void createUtilisateurEmailInvalid() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "unMailInvalide", new Date());
		Assert.assertNotNull(u);
	}

	@Test(expected=SQLException.class)
	public void createUtilisateurDateNaissanceNull() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", null);
		Assert.assertNotNull(u);
	}

	@Test
	public void deleteutilisateurOK() throws SQLException{
		//ajout
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//suppression
		dao.delete(uRes);
		//recherche
		Utilisateur uRes3 = dao.findById(uRes.getIdUtilisateur());
		Assert.assertNull(uRes3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void deleteUtilisateurNull() {
		Utilisateur u = null;
		dao.delete(u);
	}

	@Test
	public void deleteUtilisateurNotInDB() {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaa", "truc@hot.fr", new Date());
		dao.delete(u);
	}

	@Test
	public void updateUtilisateurOK() throws SQLException, ParseException {
		//ajout
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		String newNom = "Machine";
		String newPrenom = "Michel";
		String newPassword = "bbbbb";
		String newMail = "bidule@gmail.com";
		Date newDateNaiss = format.parse("1989-02-04");
		uRes.setNom(newNom);
		uRes.setPrenom(newPrenom);
		uRes.setPassword(newPassword);
		uRes.setMail(newMail);
		uRes.setDateNaissance(newDateNaiss);
		Utilisateur uRes2 = dao.update(uRes);
		//verification
		Assert.assertNotNull(uRes2);
		Assert.assertNotSame(uRes, uRes2);
		Assert.assertFalse(uRes2.getIdUtilisateur() == uRes.getIdUtilisateur());
		Assert.assertEquals(uRes2.getNom(), newNom);
		Assert.assertEquals(uRes2.getPrenom(), newPrenom);
		Assert.assertEquals(uRes2.getPassword(), newPassword);
		Assert.assertEquals(uRes2.getMail(), newMail);
		Assert.assertEquals(uRes2.getDateNaissance(), newDateNaiss);
		//recuperation dans la base
		Utilisateur uRes3 = dao.findById(uRes2.getIdUtilisateur());
		Assert.assertNotNull(uRes3);
		Assert.assertNotSame(uRes2, uRes3);
		Assert.assertEquals(uRes2, uRes3);
	}

	@Test(expected=IllegalArgumentException.class)
	public void updateUtilisateurNull() {
		Utilisateur u = null;
		dao.update(u);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurNomNull() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setNom(null);
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurNomEmpty() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setNom("");
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPrenomNull() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPrenom(null);
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPrenomEmpty() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPrenom("");
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPseudo() throws ParseException, SQLException {
		//le pseudo ne doit pas être modifiable
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPseudo("newPseudo");
		dao.update(uRes);
	}



	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPasswordNull() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPassword(null);
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPasswordEmpty() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPassword("");
		dao.update(uRes);
	}

	@Test(expected=InvalidUpdateException.class)
	public void updateUtilisateurPasswordShort() throws SQLException, ParseException {
		//Minimum 5 caracteres
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setPassword("pass");
		dao.update(uRes);
	}

	public void updateUtilisateurMail() throws ParseException, SQLException {
		//l'email ne doit pas être modifiable
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setMail("newMail@hotmail.fr");
		dao.update(uRes);
	}

	@Test(expected=IllegalArgumentException.class)
	public void updateUtilisateurDateNaissanceNull() throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaiss = format.parse("1989-02-03");
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mich_pseudo", "aaaaa", "truc@hot.fr", dateNaiss);
		Assert.assertNotNull(u);
		Utilisateur uRes = dao.create(u);
		Assert.assertNotNull(uRes);
		Assert.assertNotSame(u, uRes);
		//update
		uRes.setDateNaissance(null);
		dao.update(uRes);
	}

	@Test
	public void verifyUserEmailOk() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur res = dao.create(u);
		Assert.assertNotNull(res);
		boolean b = dao.verifyUserEmail("truc@hot.fr");
		Assert.assertTrue(b);
	}

	@Test
	public void verifyUserEmailNotInDB() {
		String aMail = "truc@hot.fr";
		boolean b = dao.verifyUserEmail(aMail);
		Assert.assertFalse(b);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUserEmailNull() {
		dao.verifyUserEmail(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUserEmailEmpty() {
		dao.verifyUserEmail("");
	}

	@Test
	public void verifyUserPseudoOk() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur res = dao.create(u);
		Assert.assertNotNull(res);
		boolean b = dao.verifyUserPseudo("mPseudo");
		Assert.assertTrue(b);
	}

	@Test
	public void verifyUserPseudoNotInDB() {
		boolean b = dao.verifyUserPseudo("mPseudo");
		Assert.assertFalse(b);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUserPseudoNull() {
		dao.verifyUserPseudo(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUserPseudoEmpty() {
		dao.verifyUserPseudo("");
	}

	@Test
	public void verifyUtilisateurConnectsOk() throws SQLException {
		Utilisateur u = new Utilisateur("Machin", "Micheline", "mPseudo", "aaaaa", "truc@hot.fr", new Date());
		Assert.assertNotNull(u);
		Utilisateur res = dao.create(u);
		Assert.assertNotNull(res);
		boolean b = dao.verifyUtilisateurConnects("mPseudo", "aaaaa");
		Assert.assertTrue(b);
	}

	@Test
	public void verifyUtilisateurConnectsNotOk() {
		String aPseudo = "mPseudo";
		String aPassword = "mPassword";
		boolean b = dao.verifyUtilisateurConnects(aPseudo, aPassword);
		Assert.assertFalse(b);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUtilisateurConnectsPseudoNull() {
		dao.verifyUtilisateurConnects(null, "aaaaa");
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUtilisateurConnectsPseudoEmpty() {
		dao.verifyUtilisateurConnects("", "aaaaa");
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUtilisateurConnectsPasswordNull() {
		dao.verifyUtilisateurConnects("mPseudo", null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void verifyUtilisateurConnectsPasswordEmpty() {
		dao.verifyUtilisateurConnects("mPseudo", "");
	}

}
