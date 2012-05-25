package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;

import entities.Grille;

public class InProgress extends ActionSupport{
	private Map<Grille,Date> grillesEnCours;

	
	/**
	 * La liste des grilles en cours de jeu de l'utilisateur
	 */
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String auth = null;
		if (session.containsKey("authentification")) {
			auth = (String) session.get("authentification");
		}
		if ((auth == null) || (!auth.equals("true"))) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
		int idUser = (Integer) session.get("idUser");
		GrilleDao dao = new GrilleDao();
		try {
			setGrillesEnCours(dao.getGridSavedByUser(idUser));
		} catch (SQLException e) {
			e.printStackTrace();
			
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	public Map<Grille, Date> getGrillesEnCours() {
		return grillesEnCours;
	}

	public void setGrillesEnCours(Map<Grille, Date> grillesEnCours) {
		this.grillesEnCours = grillesEnCours;
	}

}
