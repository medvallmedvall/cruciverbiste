package actions;


import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;

import entities.Grille;

@SuppressWarnings("serial")
public class PlayAction extends ActionSupport {
	private int idGrid;
	//private Grilles grille;
	private Grille grille;
	
	public PlayAction() {
		super();
		idGrid = 2;
	}
	
	public String execute() throws Exception {
		//selectionner la grille avec l'id idGrid
		//GrillesHome home = new GrillesHome();
		//grille = home.findById(idGrid);
		////si la grille n'existe pas on retourne une erreur
		GrilleDao dao = new GrilleDao();
		grille = dao.findById(idGrid);
		if (grille == null) {
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
	
	public Grille getGrille() {
		return grille;
	}
	
	/*public Grilles getGrille() {
		return grille;
	}*/
}
