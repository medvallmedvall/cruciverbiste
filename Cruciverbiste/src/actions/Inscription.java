package actions;




import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import dao.UtilisateurDao;

import entities.Utilisateur;

public class Inscription extends ActionSupport {

	String nom;
	String prenom;
	String pseudo;
	String password;
	String mail;
	Date dateNaissance;

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
	

	public String execute() {
		Utilisateur utilisateur = new Utilisateur(getNom(), getPrenom(), getPseudo(),
				getPassword(), getMail(), getDateNaissance());
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		Boolean verEmail = utilisateurDao.verifyUserEmail(utilisateur.getMail());
		Boolean verPseudo = utilisateurDao.verifyUserPseudo(utilisateur.getPseudo());
		if ((verEmail == true) && (verPseudo == true)) {
				utilisateurDao.create(utilisateur);
				return SUCCESS;
		} else {
			if (verEmail == false) {
				addActionError("Le mail que vous avez rentré est déjà utilisé");
			}
			if (verPseudo == false) {
					addActionError("Le pseudo que vous avez rentré est déjà utilisé");
			}
			
			return ERROR;
		}
	}
	
}

