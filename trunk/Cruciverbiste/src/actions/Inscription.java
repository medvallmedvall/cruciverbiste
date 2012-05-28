package actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UtilisateurDao;

import entities.Utilisateur;

public class Inscription extends ActionSupport {
	private String nom;
	private String prenom;
	private String pseudo;
	private String password;
	private String mail;
	private Date dateNaissance;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	
	/**
	 * Inscription au site
	 */

	public String execute() {
		//verifier que nom, prenom... non vide, non null
		//email correct avec la regex que vous avez utilise avc javascript
		//pseudo et password taille correct
		Utilisateur utilisateur;
		try {
		utilisateur = new Utilisateur(getNom(), getPrenom(), getPseudo(),
				getPassword(), getMail(), getDateNaissance());
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		Boolean verEmail;
		try {
			verEmail = utilisateurDao.verifyUserEmail(utilisateur.getMail());
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		Boolean verPseudo;
		try {
			verPseudo = utilisateurDao.verifyUserPseudo(utilisateur.getPseudo());
		} catch (SQLException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		if ((verEmail) && (verPseudo)) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			try {
				utilisateurDao.create(utilisateur);
				Utilisateur u = utilisateurDao.getBy(utilisateur.getMail());
				session.put("idUser", u.getIdUtilisateur());
			} catch (SQLException e) {
				addActionError(e.getMessage());
				return INPUT;
			}
			session.put("authentification","true");			
			session.put("nom",utilisateur.getNom());
			session.put("pseudo", utilisateur.getPseudo());
			return SUCCESS;
		}
		if (!verEmail) {
			addActionError(getText("message.mailUsed"));
		}
		if (!verPseudo) {
			addActionError(getText("message.psUsed"));
		}
		return INPUT;
	}

}

