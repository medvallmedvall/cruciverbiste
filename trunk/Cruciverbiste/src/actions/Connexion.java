package actions;

import java.sql.SQLException;
import java.util.Map;

/*import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;*/

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.UtilisateurDao;
import entities.Utilisateur;

public class Connexion extends ActionSupport {

	private String pseudo;
	private String password;

	public String execute() {
		if ((pseudo == null) || (pseudo.equals(""))) {
			addActionError("Le pseudo est null ou vide");
			return ERROR;
		}
		if ((password == null) || (password.equals(""))) {
			addActionError("Le mot de passe est null ou vide");
			return ERROR;
		}
		pseudo = pseudo.trim();
		UtilisateurDao dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
		Utilisateur user;
		try {
			user = dao.verifyUtilisateurConnects(pseudo, password);
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		if (user == null) {
			addActionError("Vos identifiants ne sont pas corrects");
			return ERROR;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		// on renseigne la session
		session.put("authentification", "true");
		session.put("idUser", user.getIdUtilisateur());
		session.put("nom",user.getNom());
		session.put("pseudo", user.getPseudo());
		return SUCCESS;

	}

	public String logout() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
