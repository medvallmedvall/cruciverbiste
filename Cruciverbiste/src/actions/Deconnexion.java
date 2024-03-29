package actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Deconnexion extends ActionSupport {
	
	
	private String redirectUrl;

	
	/**
	 * Se deconneter du site
	 */
	public String execute() throws Exception {
		  prepare();
		  Map <String, Object> session = ActionContext.getContext().getSession();
		  session.clear();
		  return SUCCESS;
	  }
	
	public void prepare() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if ( request != null ) {
			String referer = request.getHeader( "Referer" );
			if ( referer != null ) {
				redirectUrl = referer;
			} else {
				redirectUrl = request.getContextPath();
			}
		}
	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}

}
