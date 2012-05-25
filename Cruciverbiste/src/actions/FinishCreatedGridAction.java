package actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FinishCreatedGridAction extends ActionSupport{
	private int idGrille;
	private String data;
	
	
	/**
	 * Termine la création de grille
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
		int idUser = (Integer) session.get("idUser");
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
