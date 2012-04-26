package entities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Grille {
	private int idGrille;
	/*private Langues langues;
	private Typegrille typegrille;*/
	private Utilisateur utilisateur;
	private int idLangue;
	private int idTypeGrille;
	private String nomGrille;
	private int largeur;
	private int hauteur;
	private Date dateCreation;
	private boolean estFinie;
	private boolean estValidee;
	private Date dateValidation;
	private int niveau;
	private int idTheme;
	private List<MotGrille> motsGrille;
	private List<Commentaire> commentaires;

	public Grille() {
	}
	
	public Grille(int idGrille, int idLangue, int typeGrille,
			String nomGrille, int largeur,
			int hauteur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme,
			Utilisateur utilisateur) {
		this.idGrille = idGrille;
		this.idLangue = idLangue;
		this.idTypeGrille = typeGrille;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
		this.utilisateur = utilisateur;
	}
	
	public Grille(int idGrille, int idLangue, int typeGrille,
			String nomGrille, int largeur,
			int hauteur, Date dateCreation, boolean estFinie,
			boolean estValidee, Date dateValidation, int niveau, int idTheme,
			List<MotGrille> motsGrille, Utilisateur utilisateur) {
		this.idGrille = idGrille;
		this.idLangue = idLangue;
		this.idTypeGrille = typeGrille;
		this.nomGrille = nomGrille;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.dateCreation = dateCreation;
		this.estFinie = estFinie;
		this.estValidee = estValidee;
		this.dateValidation = dateValidation;
		this.niveau = niveau;
		this.idTheme = idTheme;
		this.motsGrille = motsGrille;
		this.utilisateur = utilisateur;
	}

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
	
	public String getDateCreationF() {
		SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM", Locale.FRENCH);
		return format.format(getDateCreation());
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
	
	public String getDateValidationF() {
		if (dateValidation == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM", Locale.FRENCH);
		return format.format(getDateValidation());
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

	public List<MotGrille> getMotsGrille() {
		return this.motsGrille;
	}

	public void setMotsGrille(List<MotGrille> mots) {
		this.motsGrille = mots;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getPseudo() {
		return this.utilisateur.getPseudo();
	}
	
	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}
	
	public void setCommentaires(List<Commentaire> listComm) {
		this.commentaires = listComm;
	}
	
	public int getIdLangue() {
		return idLangue;
	}
	
	public void setIdLangue(int idLangue) {
		this.idLangue = idLangue;
	}

}
