package actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.GrilleDao.TypeGrille;
import dao.ThemeDao;
import entities.Grille;
import entities.GrilleJoueeUtilisateur;
import entities.Theme;

@SuppressWarnings("serial")
public class GetGridListAction extends ActionSupport {

	private int idTypeGrid;
	private List<Grille> grillesList;
	private List<Theme> themesList;
	private Map<Grille, Boolean> grillesMap;
	private List<GrilleJoueeUtilisateur> grilleJoueeUtilisateurs;

	public GetGridListAction() {
		super();
		idTypeGrid = -1;
	}

	
	/**
	 * Recupère la liste des grilles de mots
	 */
	public String execute() throws Exception {
		// on recupere la liste des grilles par le user
		GrilleDao dao = new GrilleDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		int idUser = -1;
		if (session.get("idUser") != null) {
			idUser = (Integer) session.get("idUser");
			grilleJoueeUtilisateurs = dao.getFinishedGrilles(idUser);
		}
		// on recupere les grilles
		GrilleDao grilleDao = new GrilleDao();
		if (idTypeGrid == 1) {
			grillesList = grilleDao.getGrilles(TypeGrille.MOTS_FLECHES);
			grillesMap = new HashMap<Grille, Boolean>();
			for (Grille g : grillesList) {
				grillesMap.put(g, false);
			}			
		}
		else if (idTypeGrid == 2) {
			grillesList = grilleDao.getGrilles(TypeGrille.MOTS_CROISES);
			grillesMap = new HashMap<Grille, Boolean>();
			grillesMap = new HashMap<Grille, Boolean>();
			for (Grille g : grillesList) {
				grillesMap.put(g, false);
			}
		} else {
			addActionError("Type de grille inconnu");
			return ERROR;
		}
		if ((grillesList == null) || (grillesList.isEmpty())) {
			addActionError("Impossible d'avoir la liste de grilles");
			return ERROR;
		}
		if (session.get("idUser") != null) {
			for (int j = 0; j < grillesList.size(); j++) {
				for (int i = 0; i < grilleJoueeUtilisateurs.size(); i++) {
					Grille mGrille = grillesList.get(j);
					int idGrille2 = grilleJoueeUtilisateurs.get(i).getIdGrille();
					if (mGrille.getIdGrille() == idGrille2) {
						grillesMap.put(mGrille, true);
					}						
				} 
			} 
		}
		ThemeDao themeDao = new ThemeDao();
		themesList = themeDao.getThemes();
		if ((themesList == null) || (themesList.isEmpty())) {
			addActionError("Impossible d'avoir la liste de themes");
			return ERROR;
		}
		return SUCCESS;
	}

	public int getIdTypeGrid() {
		return idTypeGrid;
	}

	public void setIdTypeGrid(int idTypeGrid) {
		this.idTypeGrid = idTypeGrid;
	}

	public List<Grille> getGrillesList() {
		return grillesList;
	}

	public List<Theme> getThemesList() {
		return themesList;
	}

	public Map<Grille, Boolean> getGrillesMap() {
		return grillesMap;
	}
}