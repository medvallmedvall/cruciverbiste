 package actions;

import java.util.Map;

/*import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;*/

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UtilisateurDao;
import entities.Utilisateur;

public class Connexion extends ActionSupport {

		public String pseudo;
		public String password;
		
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
		
		public String execute() {
			UtilisateurDao utilisateurDao = new UtilisateurDao();
			Utilisateur user = new Utilisateur(getPseudo(), getPassword());
			
			if (utilisateurDao.verifyUtilisateurConnects(user.getPseudo(),  user.getPassword()) == true) {
				Map<String, Object> session = ActionContext.getContext().getSession();
			
				// on renseigne la session
				session.put("authentification","true");
				session.put("nom",user.getNom());
				session.put("pseudo", user.getPseudo());
				return SUCCESS;
			} else {
				addActionError("Vos identifiants ne sont pas corrects");
				return ERROR;
			}
		}
		
		public String logout() throws Exception{
			Map session = ActionContext.getContext().getSession();
			  session.remove("authentification");
			  session.remove("nom");
			  session.remove("pseudo");
			  return SUCCESS;
		}
		

}
