package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.LettreGrilleCreationDao;
import dao.MotGrilleDao;
import entities.Definition;
import entities.LettreGrilleCreation;
import entities.Mot;
import entities.MotGrille;

public class SubmitGridAction extends ActionSupport {
	private int idGrille;
	private String motGrilleString;
	private final String SEPARATOR = "/";
	private final String SEPARATOR2 = ":";
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String auth = null;
		if (session.containsKey("authentification")) {
			auth = (String) session.get("authentification");
		}
		if ((auth == null) || (!auth.equals("true"))) {
			addActionError("Vous n'êtes pas authorisé à acceder à cette page");
			return ERROR;
		}
		//int idUser = (Integer) session.get("idUser");
		//List<MotGrille> motsGrille = new LinkedList<MotGrille>();
		String[] motsGrilleS = motGrilleString.split(SEPARATOR);
		MotGrilleDao motGrilleDao = new MotGrilleDao();
		try {
			motGrilleDao.deleteByIdGrille(idGrille);
		} catch (SQLException e1) {
			addActionError(e1.getMessage());
			e1.printStackTrace();
			return ERROR;
		}
		for (int i = 0; i < motsGrilleS.length; i++) {
			String[] tab = motsGrilleS[i].split(SEPARATOR2);
			String definition = tab[0];
			String motS = tab[1];
			System.out.println(motS);
			int orientation = Integer.parseInt(tab[2]);
			int x = Integer.parseInt(tab[3]);
			int y = Integer.parseInt(tab[4]);
			Mot mot = new Mot(-1, motS, new LinkedList<String>());
			Definition def = new Definition(-1, definition, false);
			MotGrille m = new MotGrille(idGrille, orientation, mot, def, x, y);
			try {
				motGrilleDao.create(m);
			} catch (SQLException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return ERROR;
			}
			GrilleDao grilleDao = new GrilleDao();
			try {
				grilleDao.finishGrid(idGrille);
			} catch (SQLException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return ERROR;
			}
		}
		LettreGrilleCreationDao lettreDao = new LettreGrilleCreationDao();
		try {
			lettreDao.deleteByIdGrille(idGrille);
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}
		
		addActionMessage("Grille soumise en attente de validation");
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public String getMotGrilleString() {
		return motGrilleString;
	}

	public void setMotGrilleString(String motGrilleString) {
		this.motGrilleString = motGrilleString;
	}
}
