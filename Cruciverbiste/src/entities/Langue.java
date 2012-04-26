package entities;

public class Langue {
	private int idLangue;
	private String nomLangue;
	
	public Langue(int idLangue, String nomLangue) {
		super();
		this.idLangue = idLangue;
		this.nomLangue = nomLangue;
	}

	public int getIdLangue() {
		return idLangue;
	}

	public void setIdLangue(int idLangue) {
		this.idLangue = idLangue;
	}

	public String getNomLangue() {
		return nomLangue;
	}

	public void setNomLangue(String nomLangue) {
		this.nomLangue = nomLangue;
	}	
}
