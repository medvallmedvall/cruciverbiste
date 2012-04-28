package entities;

public class LettreEnCour {
	private int idGrilleEnCours;
	private String lettre;
	private int coordX;
	private int coordY;
	
	public LettreEnCour() {
		// TODO Auto-generated constructor stub
	}
	
	public LettreEnCour(int idGrilleEnCours, String lettre, int coordX, int coordY) {
		super();
		this.idGrilleEnCours = idGrilleEnCours;
		this.lettre = lettre;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public LettreEnCour(String lettre, int coordX, int coordY) {
		super();
		this.lettre = lettre;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int getIdGrilleEnCours() {
		return idGrilleEnCours;
	}
	public void setIdGrilleEnCours(int idGrilleEnCours) {
		this.idGrilleEnCours = idGrilleEnCours;
	}
	public String getLettre() {
		return lettre;
	}
	public void setLettre(String lettre) {
		this.lettre = lettre;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
