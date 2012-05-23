package actions;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.CommentaireDao;
import entities.Commentaire;

@SuppressWarnings("serial")
public class DeleteCommentAction extends ActionSupport {
	private int idCommentaire;
	private int idGrille;
	private String urlGrille;
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if ((!session.containsKey("authentification")) || 
				(!session.containsKey("idUser")) ||
				(!session.get("authentification").equals("true"))) {
			addActionError("Vous n'êtes pas connecte");
			return ERROR;
		}
		if (!(session.containsKey("droit")) ||
				((Integer) session.get("droit") <= 0)) {
			addActionError("Vous n'êtes pas autorisé à acceder à cette page");
			return ERROR;
		}
		CommentaireDao dao = new CommentaireDao();
		try {
			Commentaire com = dao.findById(idCommentaire);
			if (com == null) {
				addActionError("Le commentaire n'existe pas");
				return ERROR;
			}
			dao.delete(com);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			addActionError(e.getMessage());
			return ERROR;
		}
		urlGrille = "jouer?idGrille=" + idGrille;
		return SUCCESS;
	}

	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	
	public String getUrlGrille() {
		return urlGrille;
	}
	
	public void setUrlGrille(String url) {
		this.urlGrille = url;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	
	
}
