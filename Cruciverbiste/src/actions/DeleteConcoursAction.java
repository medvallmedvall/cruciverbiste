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

public class DeleteConcoursAction extends ActionSupport {
	
	private int idGrille;
	private Date dateDebut;
	private Date dateFin;
	private int idConcours;
	private List<Concours> listConcours;
	private Grille grille;
	
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public void setListConcours(List<Concours> listConcours) {
		this.listConcours = listConcours;
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
	
	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}
	
	public List<Concours> getListConcours() {
		return listConcours;
	}
	
	public String execute() {
		//Concours conc = new Concours(getIdGrille(), getDateDebut(), getDateFin());
		Map<String, Object> session = ActionContext.getContext().getSession();
		ConcoursDao dao = new ConcoursDao();
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
		try {
			Concours conc = dao.findById(idConcours);
			if (conc == null) {
				addActionError(getText("message.concoursinv"));
				return ERROR;
			}
			GrilleDao grilledao = new GrilleDao();
			grille = grilledao.findById(conc.getIdGrille());
			if (grille == null) {
				addActionError(getText("message.grilleinexistante"));
				return ERROR;
			}
			grilledao.updateGrilleConcours(grille);
			dao.delete(conc);
			
		} catch (SQLException e) {
			addActionError(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		
	}

}
