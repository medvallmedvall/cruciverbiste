package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Commentaire {
	private int idCommentaire;
	private String contenu;
	private Date date;
	private int idGrille;
	private Utilisateur utilisateur;
	
	public Commentaire() {
		
	}
	
	public Commentaire(int idCommentaire, String contenu, Date date,
			int idGrille, Utilisateur utilisateur) {
		this.idCommentaire = idCommentaire;
		this.contenu = contenu;
		this.date = date;
		this.idGrille = idGrille;
		this.utilisateur = utilisateur;
	}

	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate() {
		return date;
	}

	public String getDateFormatee() {
		SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMMM 'à' HH'h'mm", Locale.FRENCH);
		return format.format(getDate());
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdGrille() {
		return idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
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
	
}
