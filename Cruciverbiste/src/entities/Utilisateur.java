package entities;

import java.util.Date;


public class Utilisateur {

	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String pseudo;
	private String password;
	private String mail;
	private Date dateInscription;
	private Date dateNaissance;

	public Utilisateur(String nom, String prenom, String pseudo,
			String password, String mail, Date dateNaissance) {
		super();

		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.password = password;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
	}
	
	public Utilisateur(String pseudo, String password) {
		super();
		this.pseudo = pseudo;
		this.password = password;
	}

	public Utilisateur() {

	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

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

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
