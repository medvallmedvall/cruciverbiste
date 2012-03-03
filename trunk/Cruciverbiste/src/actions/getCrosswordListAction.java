package actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.GrilleDao.TypeGrille;
import entities.Grille;

@SuppressWarnings("serial")
public class getCrosswordListAction extends ActionSupport {
	private int idGrid;
	private List<Grille> grilles;

	public getCrosswordListAction() {
		super();
		idGrid = -1;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		//on recupere les grilles
		GrilleDao dao = new GrilleDao();
		grilles = dao.getGrilles(TypeGrille.MOTS_FLECHES);
		if ((grilles == null) || (grilles.isEmpty())) {
			return ERROR;
		}
		return SUCCESS;
	}

	public int getIdGrid() {
		return idGrid;
	}

	public void setIdGrid(int idGrid) {
		this.idGrid = idGrid;
	}

	public List<Grille> getGrilles() {
		return grilles;
	}

}
