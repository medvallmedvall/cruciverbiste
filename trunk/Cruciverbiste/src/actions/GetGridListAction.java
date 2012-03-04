package actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.ThemeDao;
import dao.GrilleDao.TypeGrille;
import entities.Grille;
import entities.Theme;

@SuppressWarnings("serial")
public class GetGridListAction extends ActionSupport {
	private int idTypeGrid;
	private List<Grille> grillesList;
	private List<Theme> themesList;
	
	public GetGridListAction() {
		super();
		idTypeGrid = -1;
	}
	
	public String execute() throws Exception {
		//on recupere les grilles
		GrilleDao grilleDao = new GrilleDao();
		if (idTypeGrid == 1) {
			grillesList = grilleDao.getGrilles(TypeGrille.MOTS_FLECHES);
		}
		else if (idTypeGrid == 2) {
			grillesList = grilleDao.getGrilles(TypeGrille.MOTS_CROISES);
		}
		else {
			addActionError("Type de grille inconnu");
			return ERROR;
		}
		if ((grillesList == null) || (grillesList.isEmpty())) {
			addActionError("Impossible d'avoir la liste de grilles");
			return ERROR;
		}
		//on recupere la liste 
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
}
