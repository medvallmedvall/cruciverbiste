package actions;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PlayCrosswordAction extends ActionSupport {
	private int idGrid;
	//private Grid grid
	
	public PlayCrosswordAction() {
		super();
		idGrid = -1;
	}
	
	public String execute() throws Exception {
		//selectionner la grille avec l'id idGrid
		//si elle existe remplir le champ grid avec
		return SUCCESS;
		//sinon retourner return ERROR;
	}
	
	public int getIdGrid() {
		return idGrid;
	}
	
	public void setIdGrid(int idGrid) {
		this.idGrid = idGrid;
	}
}
