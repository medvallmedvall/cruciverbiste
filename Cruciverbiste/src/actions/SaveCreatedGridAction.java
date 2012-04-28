package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.MotDao;
import dao.MotGrilleDao;

import entities.Definition;
import entities.Mot;
import entities.MotGrille;

public class SaveCreatedGridAction extends ActionSupport {
	private int idGrille;
	private String motGrilleString;
	private String lettresString;
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
		int idUser = (Integer) session.get("idUser");
		List<MotGrille> motsGrille = new LinkedList<MotGrille>();
		String[] motsGrilleS = motGrilleString.split(SEPARATOR);
		MotGrilleDao motGrilleDao = new MotGrilleDao();
		for (int i = 0; i < motsGrilleS.length; i++) {
			String[] tab = motsGrilleS[i].split(SEPARATOR2);
			String definition = tab[0];
			int orientation = Integer.parseInt(tab[1]);
			int x = Integer.parseInt(tab[2]);
			int y = Integer.parseInt(tab[3]);
			Mot mot = null;
			Definition def = new Definition(-1, definition);
			MotGrille m = new MotGrille(idGrille, orientation, mot, def, x, y);
			try {
				motGrilleDao.create(m);
			} catch (SQLException e) {
				e.printStackTrace();
				addActionError(e.getMessage());
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}


}
