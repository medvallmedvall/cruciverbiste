package actions;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import dao.UtilisateurDao;
import entities.Droit;
import entities.Utilisateur;

public class Connexion extends ActionSupport {

	private String pseudo;
	private String password;
	private String redirectUrl;

	
	/**
	 * Connexion de l'utilisateur au site
	 */
	public String execute() {
		
		if ((pseudo == null) || (pseudo.equals(""))) {
			addActionError(getText("message.psnul"));
			return ERROR;
		}
		if ((password == null) || (password.equals(""))) {
			addActionError(getText("message.passnul"));
			return ERROR;
		}
		
		try {
			prepare();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		pseudo = pseudo.trim();
		UtilisateurDao dao = (UtilisateurDao) DaoFactory.getUtilisateurDao();
		Utilisateur user;
		try {
			user = dao.verifyUtilisateurConnects(pseudo, password);
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		if (user == null) {
			addActionError(getText("message.idcorr"));
			return ERROR;
		}
		
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		// on renseigne la session
		session.put("authentification", "true");
		session.put("idUser", user.getIdUtilisateur());
		session.put("nom",user.getNom());
		session.put("pseudo", user.getPseudo());
		session.put("droit", user.getIdDroit());
		return SUCCESS;

	}
	
	public void prepare() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if ( request != null ) {
			String referer = request.getHeader( "Referer" );
			if ( referer != null ) {
				redirectUrl = referer;
			} else {
				redirectUrl = request.getContextPath();
			}
		}
	}

	public String logout() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}

}
