package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import entities.Concours;

public class CreerConcoursAction extends ActionSupport {
	private int idGrille;
	private Date dateDebut;
	private Date dateFin;
	private int idConcours;
	
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
	 * Creatio du jeu concours par l'administarteur ou le modérateur
	 */
	public String execute() {
		Concours conc = new Concours(getIdGrille(), getDateDebut(), getDateFin());
		System.out.println("Date deb " + getDateDebut());
		System.out.println("Date fin " + getDateFin());
		System.out.println(getIdGrille());
		Map<String, Object> session = ActionContext.getContext().getSession();
		ConcoursDao dao = new ConcoursDao();
		if (dao.verifyGrilleConcours(idGrille)) {
			addActionError(getText("message.grilledejautilise"));
			return ERROR;
		} else {
			try {
				dao.create(conc);
			} catch (SQLException e) {
				addActionError(e.getMessage());
				return ERROR;
			}
		}
		session.put("concours", idConcours);
		return SUCCESS;
		
		
	}

}
