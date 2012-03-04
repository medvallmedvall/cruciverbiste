package entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Grille {
	private Integer idGrille;
	/*private Langues langues;
	private Typegrille typegrille;
	private Utilisateurs utilisateurs;*/
	private int idLangue;
	private int idTypeGrille;
	private int idUtilisateur;
	private String nomGrille;
	private int largeur;
	private int hauteur;
	private Date dateCreation;
	private boolean estFinie;
	private boolean estValidee;
	private Date dateValidation;
	private int niveau;
	private int idTheme;
	private Set<CaseNoire> casesNoires;
	private Set<MotGrille> motsGrille;
	/*private Set commentaireses = new HashSet(0);
	private Set grillemotses = new HashSet(0);
	private Set casesNoireses = new HashSet(0);
	private Set grillesutilisateursjoues = new HashSet(0);
	private Set grillesutilisateursencourses = new HashSet(0);*/

	public Grille() {
	}
	
	public Grille(int idGrille, int idLangue, int typeGrille,
			int idUtilisateur, String nomGrille, int largeur,
			int hauteur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme) {
		this.idGrille = idGrille;
		this.idLangue = idLangue;
		this.idTypeGrille = typeGrille;
		this.idUtilisateur = idUtilisateur;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
	}
	
	public Grille(int idGrille, int idLangue, int typeGrille,
			int idUtilisateur, String nomGrille, int largeur,
			int hauteur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme,
			Set<CaseNoire> casesNoires, Set<MotGrille> motsGrille) {
		this.idGrille = idGrille;
		this.idLangue = idLangue;
		this.idTypeGrille = typeGrille;
		this.idUtilisateur = idUtilisateur;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
		this.casesNoires = casesNoires;
		this.motsGrille = motsGrille;
		/*this.commentaireses = commentaireses;
		this.grillemotses = grillemotses;
		this.casesNoireses = casesNoireses;
		this.grillesutilisateursjoues = grillesutilisateursjoues;
		this.grillesutilisateursencourses = grillesutilisateursencourses;*/
	}

	/*public Grille(Langues langues, Typegrille typegrille,
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
			Set<CaseNoire> casesNoireses) {
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
	}*/

	/*public Grilles(Langues langues, Typegrille typegrille,
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
	}*/

	public Integer getIdGrille() {
		return this.idGrille;
	}

	public void setIdGrille(Integer idGrille) {
		this.idGrille = idGrille;
	}

	/*public int getLangues() {
		return this.langues;
	}

	public void setLangues(Langues langues) {
		this.langues = langues;
	}*/

	public int getIdTypeGrille() {
		return this.idTypeGrille;
	}

	public void setIdTypeGrille(int idTypeGrille) {
		this.idTypeGrille = idTypeGrille;
	}

	public int getUtilisateurs() {
		return this.idUtilisateur;
	}

	public void setUtilisateurs(int utilisateurs) {
		this.idUtilisateur = utilisateurs;
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

	public int getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
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

	/*public Set getCommentaireses() {
		return this.commentaireses;
	}

	public void setCommentaireses(Set commentaireses) {
		this.commentaireses = commentaireses;
	}*/

	public Set<MotGrille> getMotsGrille() {
		return this.motsGrille;
	}

	public void setMotsGrille(Set<MotGrille> mots) {
		this.motsGrille = mots;
	}

	public Set<CaseNoire> getCasesNoires() {
		return this.casesNoires;
	}

	public void setCasesNoireses(Set<CaseNoire> casesNoires) {
		this.casesNoires = casesNoires;
	}

	/*public Set getGrillesutilisateursjoues() {
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
	}*/

}
