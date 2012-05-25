package entities;

public class Authentification {

	public String pseudo;
	public String password;
	
	public Authentification() {
	    this("anonymous", "");
	}
	
	public Authentification(String pseudo, String password) {
		this.pseudo = pseudo;
		this.password = password;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Authentification [pseudo=" + pseudo + ", password=" + password
				+ "]";
	}
}
