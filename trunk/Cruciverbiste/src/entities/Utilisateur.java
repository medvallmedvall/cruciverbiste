package entities;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utilisateur {

	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String pseudo;
	private String password;
	private String mail;
	private Date dateInscription;
	private Date dateNaissance;
	private int idDroit;
	
	public Utilisateur(String pseudo, String password) {
		if (pseudo == null || password == null) {
			throw new IllegalArgumentException("Le pseudo et le mot de passe sont nuls");
		}
		/*if (pseudo.length() <= 4 || password.length() <= 2) {
			throw new IllegalArgumentException("Le pseudo et le mot de passe sont courts");
		}*/
		this.pseudo = pseudo;
		this.password = password;
	}

	public Utilisateur(String nom, String prenom, String pseudo,
			String password, String mail, Date dateNaissance) {
		this(pseudo, password);
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(mail.toUpperCase());
		if (!m.matches()) {
			throw new IllegalArgumentException("Le mail est invalide");
		}
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.password = password;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
	}
	
	
	 public Utilisateur(int id, String nom, String prenom, String pseudo,
             String password, String mail, Date dateNaissance, 
             Date dateInscription) {
	 this(pseudo, password);
     this.idUtilisateur = id;
     this.nom = nom;
     this.prenom = prenom;
     this.pseudo = pseudo;
     this.password = password;
     this.mail = mail;
     this.dateNaissance = dateNaissance;
     this.dateInscription = dateInscription;
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
	
	public int getIdDroit() {
		return idDroit;
	}
	
	public void setIdDroit(int idDroit) {
		this.idDroit = idDroit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateInscription == null) ? 0 : dateInscription.hashCode());
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result
				+ ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (dateInscription == null) {
			if (other.dateInscription != null)
				return false;
		} else if (!dateInscription.equals(other.dateInscription))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (idUtilisateur == null) {
			if (other.idUtilisateur != null)
				return false;
		} else if (!idUtilisateur.equals(other.idUtilisateur))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}
	
	

}
