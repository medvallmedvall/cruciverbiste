package actions;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ConcoursDao;
import dao.GrilleDao;
import dao.MotGrilleDao;
import entities.Concours;
import entities.Grille;
import entities.GrilleJoueeUtilisateur;
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
			
		//	UtilisateurConcours userconcours = dao.create(conc.getIdConcours(), idUtilisateur);
			//int idUser = (Integer)session.get("idUser");
			GrilleJoueeUtilisateur grilleJoueeUtilisateur=new GrilleJoueeUtilisateur(id,conc.getIdGrille(),null,0);
			try {
				sauvegardeOK=(grilledao.insertFinished(grilleJoueeUtilisateur));
			} catch (SQLException e) {
				addActionError(e.getMessage());
				return ERROR;
			}
			Grille grille = grilledao.findById(conc.getIdGrille());
			if (grille == null) {
				addActionError(getText("message.grilleinv"));
				return ERROR;
			}
			if (grilleJoueeUtilisateur.getIdGrille() == grille.getIdGrille()) {
				for (MotGrille g : motgrilledao.getByIdGrille(grilleJoueeUtilisateur.getIdGrille())) {
					for (MotGrille mg : motgrilledao.getByIdGrille(grille.getIdGrille())) {
						userconc.setaReussi(g == mg);
					}
				}
			}
			return SUCCESS;
			
			
		} catch (SQLException e) {
			addActionError(e.getMessage());
			e.printStackTrace();
			return ERROR;
		}
		
		
	}
	

}
