package actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import dao.GrilleDao.TypeGrille;
import entities.Grille;

@SuppressWarnings("serial")
public class GetGridListAction extends ActionSupport {
	private int idTypeGrid;
	private List<Grille> grilles;
	
	public GetGridListAction() {
		super();
		idTypeGrid = -1;
	}
	
	public String execute() throws Exception {
		//on recupere les grilles
		GrilleDao dao = new GrilleDao();
		if (idTypeGrid == 1) {
			grilles = dao.getGrilles(TypeGrille.MOTS_FLECHES);
		}
		else if (idTypeGrid == 2) {
			grilles = dao.getGrilles(TypeGrille.MOTS_CROISES);
		}
		else {
			return ERROR;
		}
		//grilles = home.getGrillesMotsFleches();
		if ((grilles == null) || (grilles.isEmpty())) {
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
	
	public List<Grille> getGrilles() {
		return grilles;
	}
}
