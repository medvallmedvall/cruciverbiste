package actions;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LocaleAction extends ActionSupport {
	
	private String langue;
	private Locale locale;
	private String redirectUrl;
	
	
	public String getLangue() {
		langue = locale.getLanguage();
		return langue;
	}


	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale (Locale locale) {
		this.locale = locale;
	}
	
	public void prepare() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if ( request != null ) {
			String referer = request.getHeader( "Referer" );
			if (referer != null ) {
				redirectUrl = referer;
			} else {
				redirectUrl = request.getContextPath();
			}
			System.out.println(redirectUrl);
		}
	}

	/**
	 * Changement de langue pour le module internationalisation
	 */
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (langue.equalsIgnoreCase("en")) {
			locale = new Locale("en");
			Locale.setDefault(locale);
		} else if (langue.equalsIgnoreCase("de")) {
			locale = new Locale("de");
			Locale.setDefault(locale);
		} else if (langue.equalsIgnoreCase("es")) {
			locale = new Locale("es");
			Locale.setDefault(locale);
		} else if (langue.equalsIgnoreCase("fr")) {
			locale = new Locale("fr");
			Locale.setDefault(locale);
		}
		prepare();
		session.put("locale", locale);
		session.put("langue", langue);
		return SUCCESS;

	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}

}
