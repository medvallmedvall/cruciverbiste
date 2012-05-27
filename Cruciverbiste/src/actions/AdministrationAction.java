package actions;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.UtilisateurDao;

import entities.Utilisateur;


public class AdministrationAction extends ActionSupport {
	
	private List<Utilisateur> users;

	
	public List<Utilisateur> getUsers() {
		return users;
	}
	/**
	 * Accéder à la page d'administration
	 */
	public String execute() {
		UtilisateurDao dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if ((!session.containsKey("authentification")) || 
				(!session.containsKey("idUser")) ||
				(!session.get("authentification").equals("true"))) {
			addActionError(getText("message.pasCo"));
			return ERROR;
		}
		if (!(session.containsKey("droit")) ||
				((Integer) session.get("droit") < 3)) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
		try {
			users = dao.getUtilisateurs();
		} catch (SQLException e) {
			addActionError(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		
		
	}
	
	

}
