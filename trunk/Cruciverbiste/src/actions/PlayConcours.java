package actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;

public class PlayConcours extends ActionSupport {
	
	private int idConcours;
	private int idUtilisateur;
	
	
	public String execute() {
		ConcoursDao dao = new ConcoursDao();
		Map<String, Object> session = ActionContext.getContext().getSession();
		idUtilisateur = (Integer) session.get("idUser");
		idConcours = (Integer)session.get("concours"); 
		dao.create(idConcours, idUtilisateur);
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

}
