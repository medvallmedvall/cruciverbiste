package actions;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import dao.CommentaireDao;
import entities.Commentaire;
import entities.Utilisateur;

public class CommentAction extends ActionSupport {
	private int idUser;
	private int idGrille;
	private String contenu;
	private String urlGrille;
	
	public String execute() {
		Date date = new Date();
		Utilisateur ut = new Utilisateur();
		ut.setIdUtilisateur(idUser);
		Commentaire com = new Commentaire(-1, contenu, date, idGrille, ut);
		CommentaireDao dao = new CommentaireDao();
		try {
			dao.create(com);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public String getUrlGrille() {
		return urlGrille;
	}
	
	public void setUrlGrille(String url) {
		this.urlGrille = url;
	}
}
