package actions;


import com.opensymphony.xwork2.ActionSupport;

import dao.UtilisateurDao;

import entities.Utilisateur;

public class Inscription extends ActionSupport {

	String nom;
	String prenom;
	String pseudo;
	String password;
	String mail;
	String dateNaissance;

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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String execute() {
		/*Utilisateur utilisateur = new Utilisateur(getNom(), getPrenom(), getPseudo(),
				getPassword(), getMail(), getDateNaissance());
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		if (!utilisateurDao.verifyUtilisateurExists(utilisateur)) {
			utilisateurDao.create(utilisateur);
			return SUCCESS;
		} else {
			return ERROR;
		}*/
		
		return SUCCESS;
	}
}
