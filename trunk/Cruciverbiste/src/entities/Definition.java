package entities;

public class Definition {
	private int idDefinition;
	private String definition;
	
	public Definition() {
		idDefinition = -1;
		definition = "";
	}
	
	public Definition(int idDefinition, String definition) {
		this.idDefinition = idDefinition;
		this.definition = definition;
	}

	public int getIdDefinition() {
		return idDefinition;
	}

	public void setIdDefinition(int idDefinition) {
		this.idDefinition = idDefinition;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
