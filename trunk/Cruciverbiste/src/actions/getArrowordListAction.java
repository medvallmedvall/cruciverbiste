package actions;

import hibernate.Grilles;
import hibernate.GrillesHome;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class getArrowordListAction extends ActionSupport {
	private int idGrid;
	private List<Grilles> grilles;
	
	public getArrowordListAction() {
		super();
		idGrid = -1;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		//on recupere les grilles
		GrillesHome home = new GrillesHome();
		//grilles = home.getGrillesMotsFleches();
		if ((grilles == null) || (grilles.isEmpty())) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public int getIdGrid() {
		return idGrid;
	}
	
	public void setIdGrid(int idGrid) {
		this.idGrid = idGrid;
	}
	
	public List<Grilles> getGrilles() {
		return grilles;
	}
}
