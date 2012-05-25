package actions;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.UtilisateurDao;
import entities.Droit;

public class RetrieveRightAction extends ActionSupport {
	
	public int user;
	
	public int getIdUtilisateur() {
		return user;
	}
	
	public void setIdUtilisateur(int user) {
		this.user = user;
	}
	
	/**
	 * Retirer les dorits de modération à un utilisateur
	 */
	public String execute() {
		UtilisateurDao dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			dao.retrieveRights(user);
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		session.put("user", user);
		return SUCCESS;
	}

}
