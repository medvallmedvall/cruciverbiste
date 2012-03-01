package hibernate;

// Generated 23 févr. 2012 22:45:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Grillesutilisateursjoue generated by hbm2java
 */
public class Grillesutilisateursjoue implements java.io.Serializable {

	private GrillesutilisateursjoueId id;
	private Grilles grilles;
	private Utilisateurs utilisateurs;
	private Date dateTermine;
	private Date duree;

	public Grillesutilisateursjoue() {
	}

	public Grillesutilisateursjoue(GrillesutilisateursjoueId id,
			Grilles grilles, Utilisateurs utilisateurs) {
		this.id = id;
		this.grilles = grilles;
		this.utilisateurs = utilisateurs;
	}

	public Grillesutilisateursjoue(GrillesutilisateursjoueId id,
			Grilles grilles, Utilisateurs utilisateurs, Date dateTermine,
			Date duree) {
		this.id = id;
		this.grilles = grilles;
		this.utilisateurs = utilisateurs;
		this.dateTermine = dateTermine;
		this.duree = duree;
	}

	public GrillesutilisateursjoueId getId() {
		return this.id;
	}

	public void setId(GrillesutilisateursjoueId id) {
		this.id = id;
	}

	public Grilles getGrilles() {
		return this.grilles;
	}

	public void setGrilles(Grilles grilles) {
		this.grilles = grilles;
	}

	public Utilisateurs getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Utilisateurs utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Date getDateTermine() {
		return this.dateTermine;
	}

	public void setDateTermine(Date dateTermine) {
		this.dateTermine = dateTermine;
	}

	public Date getDuree() {
		return this.duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

}