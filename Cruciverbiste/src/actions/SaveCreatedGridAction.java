package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.LettreGrilleCreationDao;
import dao.MotDao;
import dao.MotGrilleDao;

import entities.Definition;
import entities.LettreGrilleCreation;
import entities.Mot;
import entities.MotGrille;

public class SaveCreatedGridAction extends ActionSupport {
	private int idGrille;
	private String motGrilleString;
	private String lettresString;
	private final String SEPARATOR = "/";
	private final String SEPARATOR2 = ":";
	
	/**
	 * Sauvegarder une grille en cours de création
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

		MotGrilleDao motGrilleDao = new MotGrilleDao();
		try {
			motGrilleDao.deleteByIdGrille(idGrille);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return ERROR;
		}
		if (!motGrilleString.equals("")) {
			String[] motsGrilleS = motGrilleString.split(SEPARATOR);
			for (int i = 0; i < motsGrilleS.length; i++) {
				String[] tab = motsGrilleS[i].split(SEPARATOR2);
				String definition = tab[0];
				int orientation = Integer.parseInt(tab[1]);
				int x = Integer.parseInt(tab[2]);
				int y = Integer.parseInt(tab[3]);
				Mot mot = null;
				Definition def = new Definition(-1, definition, false);
				MotGrille m = new MotGrille(idGrille, orientation, mot, def, x, y);
				try {
					motGrilleDao.create(m);
				} catch (SQLException e) {
					e.printStackTrace();
					addActionError(e.getMessage());
					return ERROR;
				}
			}
		}

		LettreGrilleCreationDao lettreDao = new LettreGrilleCreationDao();
		try {
			lettreDao.deleteByIdGrille(idGrille);
			if (!lettresString.equals("")) {
				String[] letters = lettresString.split(SEPARATOR);
				for (int i = 0; i < letters.length; i++) {
					String[] tab = letters[i].split(SEPARATOR2);
					String let = tab[0];
					int x = Integer.parseInt(tab[1]);
					int y = Integer.parseInt(tab[2]);
					LettreGrilleCreation lettre = 
							new LettreGrilleCreation(idGrille, x, y, let);
					lettreDao.create(lettre);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}
		addActionMessage(getText("message.grillesaved"));
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public String getLettresString() {
		return lettresString;
	}

	public void setLettresString(String lettresString) {
		this.lettresString = lettresString;
	}

	public String getMotGrilleString() {
		return motGrilleString;
	}

	public void setMotGrilleString(String motGrilleString) {
		this.motGrilleString = motGrilleString;
	}
}
