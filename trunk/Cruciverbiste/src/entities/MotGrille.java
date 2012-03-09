package entities;


public class MotGrille {
	private int idGrille;
	private int orientation;
	/*private String mot;
	private String definition;*/
	private Mot mot;
	private Definition definition;
	private int coordX;
	private int coordY;

	public MotGrille() {
	}
	
	/*public MotGrille(int idGrille, int orientation, String mot,
			String definition, int x, int y) {
		this.idGrille = idGrille;
		this.orientation = orientation;
		this.mot = mot;
		this.definition = definition;
		this.coordX = x;
		this.coordY = y;
	}*/
	
	public MotGrille(int idGrille, int orientation, Mot mot,
			Definition definition, int x, int y) {
		this.idGrille = idGrille;
		this.orientation = orientation;
		this.mot = mot;
		this.definition = definition;
		this.coordX = x;
		this.coordY = y;
	}



	public int getIdGrille() {
		return this.idGrille;
	}

	public void setId(int id) {
		this.idGrille = id;
	}

	public int getOrientation() {
		return this.orientation;
	}

	public void setOrientationmots(int orientation) {
		this.orientation = orientation;
	}

	public int getCoordX() {
		return this.coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return this.coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void setMot(Mot mot) {
		this.mot = mot;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}
	
	public String getMot() {
		return mot.getMot();
	}
	
	public String getDefinition() {
		return definition.getDefinition();
	}

}
