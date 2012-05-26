package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.GrilleDao;
import entities.GrilleJoueeUtilisateur;

public class GrilleFinie extends ActionSupport{
	
	int idUtilisateur;
	int idGrille;
	Date dateFinie;
	int duree;
	private boolean sauvegardeOK;

	public GrilleFinie() {
		sauvegardeOK=false;
	}
	
	public boolean isSauvegardeOK() {
		return sauvegardeOK;
	}

	public void setSauvegardeOK(boolean sauvegardeOK) {
		this.sauvegardeOK = sauvegardeOK;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object auth = session.get("authentification");
		if ((auth != null) && (((String)auth).equals("true"))){
			int idUser = (Integer)session.get("idUser");
			GrilleJoueeUtilisateur grilleJoueeUtilisateur=new GrilleJoueeUtilisateur(idUser,idGrille,null,0);
			GrilleDao dao=new GrilleDao();
			try {
				sauvegardeOK=(dao.insertFinished(grilleJoueeUtilisateur));
			} catch (SQLException e) {
				addActionError(e.getMessage());
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public Date getDateFinie() {
		return dateFinie;
	}

	public void setDateFinie(Date dateFinie) {
		this.dateFinie = dateFinie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	
}
