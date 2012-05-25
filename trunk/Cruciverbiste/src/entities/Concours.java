package entities;

import java.util.Date;

public class Concours {
	
	private int idGrille;
	private Date dateDebut;
	private Date dateFin;
	private int idConcours;
	
	
	public Concours(int idGrille, Date dateDebut, Date dateFin) {
		this.idGrille = idGrille;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	public Concours(int idConcours, int idGrille, Date dateDebut, Date dateFin) {
		this.idConcours = idConcours;
		this.idGrille = idGrille;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	public Concours() {
		
	}
	
	public int getIdGrille() {
		return idGrille;
	}
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getIdConcours() {
		return idConcours;
	}
	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}
	

}
