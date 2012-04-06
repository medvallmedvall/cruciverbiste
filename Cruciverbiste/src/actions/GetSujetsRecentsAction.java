package actions;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import dao.ForumDao;
import entities.Sujet;

public class GetSujetsRecentsAction extends ActionSupport {
	private List<Sujet> sujets = new LinkedList<Sujet>();
	
	public String execute() {
		ForumDao fofo = new ForumDao();
		try {
			sujets = fofo.getSujetsRecents();
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
}
