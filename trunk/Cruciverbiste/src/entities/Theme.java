package entities;

public class Theme {
	private int idTheme;
	private String nomTheme;
	
	public Theme(int id, String name) {
		this.idTheme = id;
		this.nomTheme = name;
	}

	public int getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}

	public String getNomTheme() {
		return nomTheme;
	}

	public void setNomTheme(String nomTheme) {
		this.nomTheme = nomTheme;
	}
	
	
}
