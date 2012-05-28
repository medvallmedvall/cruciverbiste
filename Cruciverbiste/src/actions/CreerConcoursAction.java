package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import dao.GrilleDao;
import entities.Concours;
import entities.Grille;

public class CreerConcoursAction extends ActionSupport {
	private int idGrille;
	private Date dateDebut;
	private Date dateFin;
	private int idConcours;
	private Grille grille;
	
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	public int getIdConcours() {
		return idConcours;
	}
	
	/**
	 * Creation du jeu concours par l'administrateur ou le modérateur
	 */
	public String execute() {
		Concours conc = new Concours(getIdGrille(), getDateDebut(), getDateFin());
		Map<String, Object> session = ActionContext.getContext().getSession();
		ConcoursDao dao = new ConcoursDao();
		GrilleDao grilledao = new GrilleDao();
		if ((!session.containsKey("authentification")) || 
				(!session.containsKey("idUser")) ||
				(!session.get("authentification").equals("true"))) {
			addActionError(getText("message.pasCo"));
			return ERROR;
		}
		if (!(session.containsKey("droit")) ||
				((Integer) session.get("droit") <= 0)) {
			addActionError(getText("message.autorisation"));
			return ERROR;
		}
			if (dao.verifyGrilleConcours(idGrille)) {
				addActionError(getText("message.grilledejautilise"));
				return ERROR;
			} else {
				if (conc.getDateDebut().after(conc.getDateFin())) {
					addActionError(getText("message.concoursinv"));
					return ERROR;
				}
				try {
					dao.create(conc);
				} catch (SQLException e) {
					addActionError(e.getMessage());
					return ERROR;
				}
			}
		
		//session.put("concours", idConcours);
		return SUCCESS;
		
		
	}

}
