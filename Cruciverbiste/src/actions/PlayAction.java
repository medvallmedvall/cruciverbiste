package actions;


import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;

import entities.Grille;

@SuppressWarnings("serial")
public class PlayAction extends ActionSupport {
	private int idGrille;
	//private Grilles grille;
	private Grille grille;
	
	public PlayAction() {
		super();
		idGrille = -1;
	}
	
	public String execute() throws Exception {
		//selectionner la grille avec l'id idGrid
		GrilleDao dao = new GrilleDao();
		grille = dao.findById(idGrille);
		//si la grille n'existe pas on retourne une erreur
		if (grille == null) {
			addActionError("La grille (" + idGrille + ") est nulle");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public int getIdGrille() {
		return idGrille;
	}
	
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
}
