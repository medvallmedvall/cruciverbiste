package entities;

import java.util.Date;

public class GrilleJoueeUtilisateur {

	int idUtilisateur;
	int idGrille;
	Date dateFinie;
	int duree;
	
	public GrilleJoueeUtilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	public GrilleJoueeUtilisateur(int idUtilisateur, int idGrille,
			Date dateFinie, int duree) {
		this.idUtilisateur = idUtilisateur;
		this.idGrille = idGrille;
		this.dateFinie = dateFinie;
		this.duree = duree;
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

	public Date getDateFinie() {
		return dateFinie;
	}

	public void setDateFinie(Date dateFinie) {
		this.dateFinie = dateFinie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "GrilleJoueeUtilisateur [idUtilisateur=" + idUtilisateur
				+ ", idGrille=" + idGrille + ", dateFinie=" + dateFinie
				+ ", duree=" + duree + "]";
	}
	
}
