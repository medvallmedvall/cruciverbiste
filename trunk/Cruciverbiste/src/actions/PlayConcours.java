package actions;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import dao.GrilleDao;
import entities.Concours;
import entities.Grille;
import entities.UtilisateurConcours;

public class PlayConcours extends ActionSupport {
	
	private int idConcours;
	private int idUtilisateur;
	private Grille grille;
	
	public String execute() {
		ConcoursDao dao = new ConcoursDao();
		GrilleDao grilledao = new GrilleDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		idUtilisateur = (Integer) session.get("idUser");
		if (session.get("idUser") == null) {
			addActionError(getText("message.acces"));
			return ERROR;
		}
		UtilisateurConcours userconc = null;
		try {
			Concours conc = dao.findById(idConcours);
			grille = grilledao.findById(conc.getIdGrille());
			userconc = dao.create(idConcours, idUtilisateur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			addActionError(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		session.put("idConcours", userconc.getIdConcours());
		session.put("idUtilisateur", userconc.getIdUtilisateur());
		return SUCCESS;
	}


	public int getIdConcours() {
		return idConcours;
	}


	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public Grille getGrille() {
		return grille;
	}

}
