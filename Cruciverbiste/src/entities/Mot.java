package entities;

import java.util.List;

public class Mot {
	private int idMot;
	private String mot;
	private List<Mot> synonymes;
	
	public Mot() {
		idMot = -1;
		mot = "";
		synonymes = null;
	}
	
	public Mot(int idMot, String mot, List<Mot> synonymes) {
		this.idMot = idMot;
		this.mot = mot;
		this.synonymes = synonymes;
	}

	public int getIdMot() {
		return idMot;
	}

	public void setIdMot(int idMot) {
		this.idMot = idMot;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public List<Mot> getSynonymes() {
		return synonymes;
	}

	public void setSynonymes(List<Mot> synonymes) {
		this.synonymes = synonymes;
	}
	
	

}
