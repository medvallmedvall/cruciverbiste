package actions;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.UtilisateurDao;
import entities.Droit;
import entities.Utilisateur;

public class GiveRightAction extends ActionSupport {
	
	
	public int user;
	public Droit droit;
	public int statut;
	
	public int getIdUtilisateur() {
		return user;
	}
	
	public void setIdUtilisateur(int user) {
		this.user = user;
	}
	
	public Droit getDroit() {
		return droit;
	}
	
	public int getStatut() {
		return statut;
	}
	/**
	 * Donne les droits de modération à un utilisateur
	 */
	public String execute() {
		UtilisateurDao dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			dao.giveRights(user);
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		session.put("user", user);
		return SUCCESS;
	}
	
	
	
	
	

}
