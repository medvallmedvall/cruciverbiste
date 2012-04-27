package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.MotDao;

public class RechercheMot extends ActionSupport{
	
	private String motif;
	LinkedList<String> listMots;
	private int idGrille;
	
	
	
	public int getIdGrille() {
		return idGrille;
	}
	
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	
	
	public String getMotif() {
		return motif;
	}
	
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	public LinkedList<String> getlistMots() {
		return listMots;
	}
	
	public void setlistMots(LinkedList<String> mots) {
		listMots = mots;
	}
	
	public String execute() {
		listMots = new LinkedList<String>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		System.out.println("motif" + motif + ", grille: " + idGrille);
		
		if (motif.length() <= 1) {
			addActionError("la longueur du motif doit etre supérieure ou égale à 2");
			return ERROR;
		}
		
		if (motif.indexOf("?") == -1) {
			addActionError("Il faut au moins un ? dans votre motif");
			return ERROR;
		}
		MotDao dao = (MotDao) DaoFactory.getMotDao();
		System.out.println("rentre OK");
		//affiche(listMots);
		try {
			listMots = dao.findByMotif(motif);
			session.put("recherche", "true");
			
			if (session.get("recherche") == "true") {
				if (!listMots.isEmpty()) {
					session.put("listMots", listMots);
					return SUCCESS;
				} else {
					listMots = new LinkedList<String>();
					//addActionError("Aucun mot ne correspond à ce motif");
					return ERROR;
				}
			} else {
				listMots.clear();
				return ERROR;
			}
			
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	
	public String getMots() {
		String res = "";
		for (String s : listMots) {
			res+= s + "\n";
		}
		return res;
	}

	public void affiche(LinkedList<String> liste) {
		// TODO Auto-generated method stub
		for (String s : liste) {
			System.out.println("Mot " + s);
		}
	}

}
