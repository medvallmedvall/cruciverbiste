package actions;


import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.DefaultTextProvider;

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
	
	@Override
	public String execute() {
		//selectionner la grille avec l'id idGrid
		GrilleDao dao = new GrilleDao();
		try {
			grille = dao.findById(idGrille);
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		//si la grille n'existe pas on retourne une erreur
		if (grille == null) {
			addActionError("La grille n° " + idGrille + " n'existe pas");
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
