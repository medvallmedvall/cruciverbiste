package entities;

public class CaseNoire {
	private int idGrille;
	private int coordX;
	private int coordY;
	
	public CaseNoire() {
	}

	public CaseNoire(int idGrille, int coordX, int coordY) {
		this.idGrille = idGrille;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int getIdGrille() {
		return this.idGrille;
	}

	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordX;
		result = prime * result + coordY;
		result = prime * result + idGrille;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaseNoire other = (CaseNoire) obj;
		if (coordX != other.coordX)
			return false;
		if (coordY != other.coordY)
			return false;
		if (idGrille != other.idGrille)
			return false;
		return true;
	}

	/*public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CaseNoire))
			return false;
		CaseNoire castOther = (CaseNoire) other;

		return (this.getIdGrille() == castOther.getIdGrille())
				&& (this.getCoordX() == castOther.getCoordX())
				&& (this.getCoordY() == castOther.getCoordY());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdGrille();
		result = 37 * result + this.getCoordX();
		result = 37 * result + this.getCoordY();
		return result;
	}*/
	
	

}
