package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import dao.ForumDao;
import entities.Sujet;

public class GetSujetsRecentsAction extends ActionSupport {
	private List<Sujet> sujets = new LinkedList<Sujet>();
	
	
	/**
	 * Recupère les sujets récents du forum
	 */
	public String execute() {
		ForumDao fofo = new ForumDao();
		try {
			sujets = fofo.getSujetsRecents();
			sujets.size();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			addActionError(e.getMessage());
			return ERROR;
		}		
		return SUCCESS;
	}
	
	public List<Sujet> getSujetsRecents() {
		return sujets;
	}
	
	public void setSujtesRecents(List<Sujet> sujets) {
		this.sujets = sujets;
	}
}
