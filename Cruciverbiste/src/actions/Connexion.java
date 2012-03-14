 package actions;

import java.util.Enumeration;
import java.util.Map;

import javassist.expr.NewArray;

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
		
		/*public void setServletRequest(HttpServletRequest req) {
			
		}*/
		
		public String execute() {
			UtilisateurDao utilisateurDao = new UtilisateurDao();
			Utilisateur user = new Utilisateur(getPseudo(), getPassword());
			System.out.println(user.getPseudo());
			System.out.println(user.getPassword());
			
			if (utilisateurDao.verifyUtilisateurConnects(user)) {
				Map<String, Object> session = ActionContext.getContext().getSession();
				System.out.println("Vous �tes loggu� avec succ�s enjoy :)");
				// on renseigne la session
				session.put("authentification","true");
				session.put("nom",user.getNom());
				session.put("pseudo", user.getPseudo());
				//session.put("erreur", "noErreur");
				return SUCCESS;
			} else {
				System.out.println("Vous avez fait une erreur");
				return ERROR;
			}
		}

}
