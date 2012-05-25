package entities;

public class Droit {
	
	private int iddroit;
	private int idutilisateur;
	
	public Droit(int iddroit, int idutilisateur) {
		this.iddroit = iddroit;
		this.idutilisateur = idutilisateur;
	}

	public int getIddroit() {
		return iddroit;
	}

	public void setIddroit(int iddroit) {
		this.iddroit = iddroit;
	}

	public int getIdUtilisateur() {
		return idutilisateur;
	}

	public void setIdUtilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
	
	

}
