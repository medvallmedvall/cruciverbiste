package actions;

import com.opensymphony.xwork2.ActionSupport;

public class RedirectionForumAction extends ActionSupport{
	private String urlSujetsRecents;
	private int t;
	private int f;
	
	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}
	
	/**
	 * Action permettant d'accéder au sujet récent du forum en un clic
	 * à partir de la page d'accueil
	 */
	public String execute(){
		urlSujetsRecents = ("http://localhost:8081/phpBB3/viewtopic.php?f=" + f + "&t=" + t);
		return SUCCESS;
	}

	public String getUrlSujetsRecents() {
		return urlSujetsRecents;
	}

	public void setUrlSujetsRecents(String urlSujetsRecents) {
		this.urlSujetsRecents = urlSujetsRecents;
	}
}
