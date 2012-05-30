package actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import dao.GrilleDao;
import dao.MotGrilleDao;
import entities.Concours;
import entities.Grille;
import entities.GrilleJoueeUtilisateur;
import entities.Mot;
import entities.MotGrille;
import entities.UtilisateurConcours;

public class ValidateJeuConcours extends ActionSupport {
	//private int idConcours;
	//private int idUtilisateur;
	private int idGrille;
	private boolean sauvegardeOK;
	private UtilisateurConcours userConc;
	
	
	
	public UtilisateurConcours getUserConc() {
		return userConc;
	}
	public void setUserConc(UtilisateurConcours userConc) {
		this.userConc = userConc;
	}
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	
	public boolean isSauvegardeOK() {
		return sauvegardeOK;
	}

	public void setSauvegardeOK(boolean sauvegardeOK) {
		this.sauvegardeOK = sauvegardeOK;
	}
	
	public String execute() {
		ConcoursDao dao = new ConcoursDao();
		GrilleDao grilledao = new GrilleDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if ((!session.containsKey("authentification")) || 
				(!session.containsKey("idUser")) ||
				(!session.get("authentification").equals("true"))) {
			addActionError(getText("message.pasCo"));
			return ERROR;
		}
		Concours conc;
		System.out.println("entrer methode");
		int idConcours = (Integer)session.get("idConcours");
		Integer id = (Integer)session.get("idUser");
		if (id == null) {
			addActionError(getText("message.userinv"));
			return ERROR;
		}
		MotGrilleDao motgrilledao = new MotGrilleDao();
		UtilisateurConcours userconc = new UtilisateurConcours(idConcours, id, false);
		try {
			conc = dao.findById(idConcours);
			if (conc == null) {
				addActionError(getText("message.concoursinv"));
				return ERROR;
			}
			
			if (grilledao.aFiniGrilleConcours(id, conc.getIdConcours())) {
				dao.update(idConcours, userconc.getIdUtilisateur());
			}
			
			System.out.println("que dalle");
			
			
		} catch (SQLException e) {
			addActionError(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		
	}
	

}
