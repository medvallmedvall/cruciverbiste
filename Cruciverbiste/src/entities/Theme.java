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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		if (idTheme != other.idTheme)
			return false;
		if (nomTheme == null) {
			if (other.nomTheme != null)
				return false;
		} else if (!nomTheme.equals(other.nomTheme))
			return false;
		return true;
	}
	
	
}
