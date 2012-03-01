package hibernate;

// Generated 23 févr. 2012 22:45:53 by Hibernate Tools 3.4.0.CR1

/**
 * CasesNoiresId generated by hbm2java
 */
public class CasesNoiresId implements java.io.Serializable {

	private int idGrille;
	private int coordX;
	private int coordY;

	public CasesNoiresId() {
	}

	public CasesNoiresId(int idGrille, int coordX, int coordY) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CasesNoiresId))
			return false;
		CasesNoiresId castOther = (CasesNoiresId) other;

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
	}

}