package entities;

import java.util.List;

public class Mot {
	private int idMot;
	private String mot;
	private List<String> synonymes;
	
	public Mot() {
		idMot = -1;
		mot = "";
		synonymes = null;
	}
	
	public Mot(int idMot, String mot, List<String> synonymes) {
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

	public List<String> getSynonymes() {
		return synonymes;
	}

	public void setSynonymes(List<String> synonymes) {
		this.synonymes = synonymes;
	}
	
	

}
