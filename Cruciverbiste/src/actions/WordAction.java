package actions;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.MotDao;
import entities.Mot;

public class WordAction extends ActionSupport {
	private String action;
	private String mot;
	List<Mot> synonyms;
	
	public String execute() {
		if (action == null) {
			addActionError("L'action est null");
			return ERROR;
		}
		if (action.equals("")) {
			addActionError("L'action est vide");
			return ERROR;
		}
		if (action.equalsIgnoreCase("getSynonyms")) {
			if (mot == null) {
				addActionError("Le mot est null");
				return ERROR;
			}
			if (mot.equals("")) {
				addActionError("Le mot est vide");
				return ERROR;
			}
			MotDao dao = new MotDao();
			try {
				synonyms = dao.getSynonyms(mot);
				return SUCCESS;
			} catch (SQLException e) {
				addActionError(e.getMessage());
				e.printStackTrace();
				return ERROR;
			}
		}
		return ERROR;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public List<Mot> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<Mot> synonyms) {
		this.synonyms = synonyms;
	}

	
}
