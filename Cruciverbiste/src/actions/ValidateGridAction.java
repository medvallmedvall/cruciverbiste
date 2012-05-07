package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import entities.Grille;
import entities.GrilleNonValideeMessage;

public class ValidateGridAction extends ActionSupport{
	private String action;
	private int idGrille;
	private String message;
	private List<Grille> grilleAValiderMF;
	private List<Grille> grilleAValiderMC;
	private Grille grille;
	private List<GrilleNonValideeMessage> messages;
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String auth = null;
		int droit = 0;
		if (session.containsKey("authentification")) {
			auth = (String) session.get("authentification");
		}
		if (session.containsKey("droit")) {
			droit = (Integer) session.get("droit");
		}
		if ((auth == null) || (!auth.equals("true"))) {
			addActionError("Vous n'êtes pas authorisé à acceder à cette page");
			return ERROR;
		}
		//int idUser = (Integer) session.get("idUser");
		if (action == null) {
			addActionError("Vous n'avez pas accès à cette page (null)");
			return ERROR;
		}
		GrilleDao dao = new GrilleDao();
		
		if (action.equalsIgnoreCase("test")) {
			try {
				grille = dao.findById(idGrille);
				messages = dao.getUnvalidateGridMessage(idGrille);
			} catch (SQLException e) {
				addActionError(e.getMessage());
				return ERROR;
			}
			//si la grille n'existe pas on retourne une erreur
			if (grille == null) {
				addActionError("La grille n° " + idGrille + " n'existe pas");
				return ERROR;
			}
		}
		else if (action.equalsIgnoreCase("getGridToValidate") && (droit != 0)) {
			try {
				List<Grille> list = dao.getGridToValidate();
				grilleAValiderMF = new LinkedList<Grille>();
				grilleAValiderMC = new LinkedList<Grille>();
				for(Grille g : list) {
					if (g.getIdTypeGrille() == 1) {
						grilleAValiderMF.add(g);
					}
					else {
						grilleAValiderMC.add(g);
					}
				}
			} catch (SQLException e) {
				addActionError(e.getMessage());
				e.printStackTrace();
				return ERROR;
			}
		}
		else if (action.equalsIgnoreCase("validate") && (droit != 0)) {
			/*try {
				dao.validateGrid(idGrille);
				addActionMessage("grille n°" + idGrille + " validée");
			} catch (SQLException e) {
				addActionError(e.getMessage());
				e.printStackTrace();
				return ERROR;
			}*/
		}
		else if (action.equals("unvalidate") && (droit != 0)) {
			if ((message == null) || (message.isEmpty())) {
				addActionError("Un message doit être spécifié");
				return ERROR;
			}
			try {
				dao.unvalidateGrid(idGrille, message);
				addActionMessage("grille n°" + idGrille + " invalidée");
			} catch (SQLException e) {
				addActionError(e.getMessage());
				e.printStackTrace();
				return ERROR;
			}
		}
		else {
			addActionError("Vous n'avez pas accès à cette page");
			return ERROR;
		}
		return SUCCESS;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Grille> getGrilleAValiderMF() {
		return grilleAValiderMF;
	}

	public void setGrilleAValiderMF(List<Grille> grilleAValiderMF) {
		this.grilleAValiderMF = grilleAValiderMF;
	}

	public List<Grille> getGrilleAValiderMC() {
		return grilleAValiderMC;
	}

	public void setGrilleAValiderMC(List<Grille> grilleAValiderMC) {
		this.grilleAValiderMC = grilleAValiderMC;
	}

	public List<GrilleNonValideeMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<GrilleNonValideeMessage> messages) {
		this.messages = messages;
	}
}
