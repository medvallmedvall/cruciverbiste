package actions;


import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import entities.Concours;
import entities.Grille;

public class TheConcoursAction extends ActionSupport {
	
	private List<Concours> listConcours;
	private List<Grille> listGrille;

	public List<Concours> getListConcours() {
		return listConcours;
	}

	public void setListConcours(List<Concours> listConcours) {
		this.listConcours = listConcours;
	}
	
	
	public List<Grille> getGrille() {
		return listGrille;
	}

	public void setGrille(List<Grille> listgrille) {
		this.listGrille = listgrille;
	}

	public String execute() {
		ConcoursDao dao = new ConcoursDao();
		listConcours = new ArrayList<Concours>();
		listConcours = dao.getAllConcours();
		return SUCCESS;
	}

}
