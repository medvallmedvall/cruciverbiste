package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.CommentaireDao;
import entities.Commentaire;
import entities.Utilisateur;

@SuppressWarnings("serial")
public class CommentAction extends ActionSupport {
	private int idUserC;
	private int idGrille;
	private String commentaire;
	private String urlGrille;
	
	
	/**
	 * Poster un commentaire
	 */
	public String execute() {
		if ((commentaire == null) || (commentaire.equals(""))) {
			addActionError("Le champ commentaire est null ou vide.");
			return ERROR;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		if ((!session.containsKey("authentification")) || 
				(!session.containsKey("idUser")) ||
				(!session.get("authentification").equals("true"))) {
			addActionError(getText("message.pasCo"));
			return ERROR;
		}
		
		idUserC = (Integer) session.get("idUser");
		Date date = new Date();
		Utilisateur ut = new Utilisateur();
		ut.setIdUtilisateur(idUserC);
		Commentaire com = new Commentaire(-1, commentaire, date,
				idGrille, ut);
		CommentaireDao dao = new CommentaireDao();
		try {
			dao.create(com);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			addActionError(e.getMessage());
			return ERROR;
		}
		urlGrille = "jouer?idGrille=" + idGrille;
		return SUCCESS;
	}
	
	public int getIdUserC() {
		return idUserC;
	}
	public void setIdUserC(int idUser) {
		this.idUserC = idUser;
	}
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String contenu) {
		this.commentaire = contenu;
	}
	
	public String getUrlGrille() {
		return urlGrille;
	}
	
	public void setUrlGrille(String url) {
		this.urlGrille = url;
	}
}
