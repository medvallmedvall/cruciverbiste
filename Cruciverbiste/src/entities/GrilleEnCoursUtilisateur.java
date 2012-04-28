package entities;

import java.util.Date;

public class GrilleEnCoursUtilisateur {
	private int idGrilleEnCours;
	private int idUtilisateur;
	private int idGrille;
	private Date date;
	
	public GrilleEnCoursUtilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	public GrilleEnCoursUtilisateur(int idUtilisateur, int idGrille, Date date) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idGrille = idGrille;
		this.date = date;
	}
	public GrilleEnCoursUtilisateur(int idGrilleEnCours, int idUtilisateur,
			int idGrille, Date date) {
		super();
		this.idGrilleEnCours = idGrilleEnCours;
		this.idUtilisateur = idUtilisateur;
		this.idGrille = idGrille;
		this.date = date;
	}
	public int getIdGrilleEnCours() {
		return idGrilleEnCours;
	}
	public void setIdGrilleEnCours(int idGrilleEnCours) {
		this.idGrilleEnCours = idGrilleEnCours;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
