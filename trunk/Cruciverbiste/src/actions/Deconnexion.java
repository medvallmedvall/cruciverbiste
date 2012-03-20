package actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Deconnexion extends ActionSupport {
	
	public String execute() throws Exception { 
		  Map <String, Object> session = ActionContext.getContext().getSession();
		  session.remove("authentification");
		  session.remove("nom");
		  session.remove("pseudo");
		  session.remove("idUser");
		  return SUCCESS;
		  }

}
