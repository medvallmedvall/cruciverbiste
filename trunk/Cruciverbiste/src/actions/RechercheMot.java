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
	
	
	/**
	 * Action qui permet de rechercher un mot � partir d'un motif
	 */
	public String execute() {
		listMots = new LinkedList<String>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (motif == null)
		{
			addActionError(getText("message.motifnul"));
			return INPUT;
		}
		
		if (motif.trim().length() <= 1) {
			addActionError(getText("message.motifsup"));
			return INPUT;
		}
		
		if (motif.indexOf("?") == -1) {
			addActionError(getText("message.motifint"));
			return INPUT;
		}
		MotDao dao = (MotDao) DaoFactory.getMotDao();
		System.out.println("rentre OK");
		try {
			listMots = dao.findByMotif(motif);
			if (!listMots.isEmpty()) {
				session.put("recherche", "true");
				session.put("motif", motif);
				session.put("listMots", listMots);
				return SUCCESS;
			} else {
				session.put("recherche", "false");
				session.put("motif", motif);
				listMots.clear();
				addActionError(getText("message.aucunmotif"));
				//session.put("listMots", listMots);
				return INPUT;
			}
			
			
		} catch (SQLException e) {
			addActionError(e.getMessage());
			e.printStackTrace();
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


}
