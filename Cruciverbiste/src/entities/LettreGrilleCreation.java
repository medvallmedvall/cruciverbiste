package entities;

public class LettreGrilleCreation {
	private int idGrille;
	private int coordX;
	private int coordY;
	private String lettre;
	
	public LettreGrilleCreation(int idGrille, int coordX, int coordY,
			String lettre) {
		super();
		this.idGrille = idGrille;
		this.coordX = coordX;
		this.coordY = coordY;
		this.lettre = lettre;
	}
	
	public int getIdGrille() {
		return idGrille;
	}


	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
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


	public String getLettre() {
		return lettre;
	}


	public void setLettre(String lettre) {
		this.lettre = lettre;
	}

}
