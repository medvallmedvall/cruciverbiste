package hibernate;

// Generated 23 févr. 2012 22:45:53 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Droits generated by hbm2java
 */
public class Droits implements java.io.Serializable {

	private Integer idDroit;
	private String nomDroit;
	private Set utilisateurses = new HashSet(0);

	public Droits() {
	}

	public Droits(String nomDroit) {
		this.nomDroit = nomDroit;
	}

	public Droits(String nomDroit, Set utilisateurses) {
		this.nomDroit = nomDroit;
		this.utilisateurses = utilisateurses;
	}

	public Integer getIdDroit() {
		return this.idDroit;
	}

	public void setIdDroit(Integer idDroit) {
		this.idDroit = idDroit;
	}

	public String getNomDroit() {
		return this.nomDroit;
	}

	public void setNomDroit(String nomDroit) {
		this.nomDroit = nomDroit;
	}

	public Set getUtilisateurses() {
		return this.utilisateurses;
	}

	public void setUtilisateurses(Set utilisateurses) {
		this.utilisateurses = utilisateurses;
	}

}
