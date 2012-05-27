package entities;

public class UtilisateurConcours {
	
	private int idConcours;
	private int idUtilisateur;
	private boolean aReussi;
	
	public UtilisateurConcours(int idConcours, int idUtilisateur, boolean aReussi) {
		super();
		this.idConcours = idConcours;
		this.idUtilisateur = idUtilisateur;
		this.aReussi = aReussi;
	}
	
	public UtilisateurConcours() {
		
	}

	public int getIdConcours() {
		return idConcours;
	}

	public void setIdConcours(int idConcours) {
		this.idConcours = idConcours;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public boolean isaReussi() {
		return aReussi;
	}

	public void setaReussi(boolean aReussi) {
		this.aReussi = aReussi;
	}
	
	
	
	
	
	
	

}
