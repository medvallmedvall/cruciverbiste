package hibernate;

// Generated 23 févr. 2012 22:45:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Utilisateurs generated by hbm2java
 */
public class Utilisateurs implements java.io.Serializable {

	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String pseudo;
	private String password;
	private String mail;
	private Date dateInscription;
	private Set commentaireses = new HashSet(0);
	private Set grillesutilisateursencourses = new HashSet(0);
	private Set droitses = new HashSet(0);
	private Set grilleses = new HashSet(0);
	private Set grillesutilisateursjoues = new HashSet(0);

	public Utilisateurs() {
	}

	public Utilisateurs(String nom, String password, String mail,
			Date dateInscription) {
		this.nom = nom;
		this.password = password;
		this.mail = mail;
		this.dateInscription = dateInscription;
	}

	public Utilisateurs(String nom, String prenom, String pseudo,
			String password, String mail, Date dateInscription,
			Set commentaireses, Set grillesutilisateursencourses, Set droitses,
			Set grilleses, Set grillesutilisateursjoues) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.password = password;
		this.mail = mail;
		this.dateInscription = dateInscription;
		this.commentaireses = commentaireses;
		this.grillesutilisateursencourses = grillesutilisateursencourses;
		this.droitses = droitses;
		this.grilleses = grilleses;
		this.grillesutilisateursjoues = grillesutilisateursjoues;
	}

	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Set getCommentaireses() {
		return this.commentaireses;
	}

	public void setCommentaireses(Set commentaireses) {
		this.commentaireses = commentaireses;
	}

	public Set getGrillesutilisateursencourses() {
		return this.grillesutilisateursencourses;
	}

	public void setGrillesutilisateursencourses(Set grillesutilisateursencourses) {
		this.grillesutilisateursencourses = grillesutilisateursencourses;
	}

	public Set getDroitses() {
		return this.droitses;
	}

	public void setDroitses(Set droitses) {
		this.droitses = droitses;
	}

	public Set getGrilleses() {
		return this.grilleses;
	}

	public void setGrilleses(Set grilleses) {
		this.grilleses = grilleses;
	}

	public Set getGrillesutilisateursjoues() {
		return this.grillesutilisateursjoues;
	}

	public void setGrillesutilisateursjoues(Set grillesutilisateursjoues) {
		this.grillesutilisateursjoues = grillesutilisateursjoues;
	}

}
