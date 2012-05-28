package actions;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;

import entities.Grille;

public class MyGridsAction extends ActionSupport{
	private List<Grille> mesGrilles;
	
	/**
	 * La liste des grilles crees par l'utilisateur
	 */
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String auth = null;
		if (session.containsKey("authentification")) {
			auth = (String) session.get("authentification");
		}
		if ((auth == null) || (!auth.equals("true"))) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
		if (session.get("idUser") == null) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
		int idUser = (Integer) session.get("idUser");
		GrilleDao dao = new GrilleDao();
		try {
			setMesGrilles(dao.getGridCreateByUser(idUser));
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
		

	public List<Grille> getMesGrilles() {
		return mesGrilles;
	}

	public void setMesGrilles(List<Grille> mesGrilles) {
		this.mesGrilles = mesGrilles;
	}
	
	

}
