package hibernate;

// Generated 23 févr. 2012 22:45:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Grilles generated by hbm2java
 */
public class Grilles implements java.io.Serializable {

	private Integer idGrille;
	private Langues langues;
	private Typegrille typegrille;
	private Utilisateurs utilisateurs;
	private String nomGrille;
	private int largeur;
	private int longueur;
	private Date dateCreation;
	private boolean estFinie;
	private boolean estValidee;
	private Date dateValidation;
	private int niveau;
	private int idTheme;
	private Set commentaireses = new HashSet(0);
	private Set grillemotses = new HashSet(0);
	private Set casesNoireses = new HashSet(0);
	private Set grillesutilisateursjoues = new HashSet(0);
	private Set grillesutilisateursencourses = new HashSet(0);

	public Grilles() {
	}

	public Grilles(Langues langues, Typegrille typegrille,
			Utilisateurs utilisateurs, String nomGrille, int largeur,
			int longueur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme) {
		this.langues = langues;
		this.typegrille = typegrille;
		this.utilisateurs = utilisateurs;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.longueur = longueur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
	}

	public Grilles(Langues langues, Typegrille typegrille,
			Utilisateurs utilisateurs, String nomGrille, int largeur,
			int longueur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme,
			Set commentaireses, Set grillemotses, Set casesNoireses,
			Set grillesutilisateursjoues, Set grillesutilisateursencourses) {
		this.langues = langues;
		this.typegrille = typegrille;
		this.utilisateurs = utilisateurs;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.longueur = longueur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
		this.commentaireses = commentaireses;
		this.grillemotses = grillemotses;
		this.casesNoireses = casesNoireses;
		this.grillesutilisateursjoues = grillesutilisateursjoues;
		this.grillesutilisateursencourses = grillesutilisateursencourses;
	}

	public Integer getIdGrille() {
		return this.idGrille;
	}

	public void setIdGrille(Integer idGrille) {
		this.idGrille = idGrille;
	}

	public Langues getLangues() {
		return this.langues;
	}

	public void setLangues(Langues langues) {
		this.langues = langues;
	}

	public Typegrille getTypegrille() {
		return this.typegrille;
	}

	public void setTypegrille(Typegrille typegrille) {
		this.typegrille = typegrille;
	}

	public Utilisateurs getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Utilisateurs utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public String getNomGrille() {
		return this.nomGrille;
	}

	public void setNomGrille(String nomGrille) {
		this.nomGrille = nomGrille;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isEstFinie() {
		return this.estFinie;
	}

	public void setEstFinie(boolean estFinie) {
		this.estFinie = estFinie;
	}

	public boolean isEstValidee() {
		return this.estValidee;
	}

	public void setEstValidee(boolean estValidee) {
		this.estValidee = estValidee;
	}

	public Date getDateValidation() {
		return this.dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getIdTheme() {
		return this.idTheme;
	}

	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}

	public Set getCommentaireses() {
		return this.commentaireses;
	}

	public void setCommentaireses(Set commentaireses) {
		this.commentaireses = commentaireses;
	}

	public Set getGrillemotses() {
		return this.grillemotses;
	}

	public void setGrillemotses(Set grillemotses) {
		this.grillemotses = grillemotses;
	}

	public Set getCasesNoireses() {
		return this.casesNoireses;
	}

	public void setCasesNoireses(Set casesNoireses) {
		this.casesNoireses = casesNoireses;
	}

	public Set getGrillesutilisateursjoues() {
		return this.grillesutilisateursjoues;
	}

	public void setGrillesutilisateursjoues(Set grillesutilisateursjoues) {
		this.grillesutilisateursjoues = grillesutilisateursjoues;
	}

	public Set getGrillesutilisateursencourses() {
		return this.grillesutilisateursencourses;
	}

	public void setGrillesutilisateursencourses(Set grillesutilisateursencourses) {
		this.grillesutilisateursencourses = grillesutilisateursencourses;
	}

}