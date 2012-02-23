package actions;

import hibernate.Grilles;
import hibernate.GrillesHome;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PlayAction extends ActionSupport {
	private int idGrid;
	private Grilles grille;
	
	public PlayAction() {
		super();
		idGrid = -1;
	}
	
	public String execute() throws Exception {
		//selectionner la grille avec l'id idGrid
		GrillesHome home = new GrillesHome();
		grille = home.findById(idGrid);
		//si la grille n'existe pas on retourne une erreur
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
	
	public Grilles getGrille() {
		return grille;
	}
}
