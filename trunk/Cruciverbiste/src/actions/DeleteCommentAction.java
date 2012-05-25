package actions;

import java.sql.SQLException;
import java.util.List;
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
	private List<Commentaire> commentaires;
	
	
	/**
	 * Suppression d'un commentaire par le modérateur
	 */
	public String execute() {
		System.out.println(idGrille + " " + idCommentaire);
		Map<String, Object> session = ActionContext.getContext().getSession();
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
		CommentaireDao dao = new CommentaireDao();
		try {
			Commentaire com = dao.findById(idCommentaire);
			if (com == null) {
				addActionError(getText("message.comexistance"));
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
