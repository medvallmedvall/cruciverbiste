package entities;

public class Definition {
	private int idDefinition;
	private String definition;
	private boolean validate;
	
	public Definition() {
		idDefinition = -1;
		definition = "";
	}
	
	/*public Definition(int idDefinition, String definition) {
		this.idDefinition = idDefinition;
		this.definition = definition;
	}*/

	public Definition(int idDefinition, String definition,
			boolean validate) {
		this.idDefinition = idDefinition;
		this.definition = definition;
		this.validate = validate;
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

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	
}
