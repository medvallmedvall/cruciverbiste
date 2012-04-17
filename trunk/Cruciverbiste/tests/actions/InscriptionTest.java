package actions;

import java.util.Date;
import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class InscriptionTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/Inscription.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("Inscription", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception {
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		 
	    // parametres de la requette 
	    request.setAttribute("nom", null);
	    request.setAttribute("prenom", null);
	    request.setAttribute("pseudo", null);
	    request.setAttribute("password", null);
	    request.setAttribute("mail", null);
	    request.setAttribute("dateNaissance", null);
	    
	 
	 // proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/Inscription.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action 
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("input", result);
	    assertTrue(session.isEmpty());
	}

	@Test
	public void testParametersEmpties() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>(); 
	
		// parametres de la requette 
	    request.setParameter("nom", "");
	    request.setParameter("prenom", "  ");
	    request.setParameter("pseudo", "");
	    request.setParameter("password", "          ");
	    request.setParameter("mail", "");
	    request.setParameter("dateNaissance", "     ");
	    
		// proxy pour simuler l'action
		ActionProxy proxy = getActionProxy("/Inscription.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("input", result);
	    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testParametersFalse() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		Date d = new Date(1980, 12, 17);
	    // parametres de la requette 
	    request.setParameter("nom", "nom");
	    request.setParameter("prenom", "prenom");
	    request.setParameter("pseudo", "monpseudo");
	    request.setParameter("password", "monpasseword");
	    request.setParameter("mail", "prenom.nom@gmail");
	    request.setAttribute("dateNaissance", d);
	    
	    // proxy pour simuler l'action
	    ActionProxy proxy = getActionProxy("/Inscription.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("input", result);
	    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testParametersTrue() throws Exception {
			
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		Date d = new Date(1980, 12, 17);
	    // parametres de la requette 
	    request.setParameter("nom", "nomutilisateur");
	    request.setParameter("prenom", "prenomutilisateur");
	    request.setParameter("pseudo", "monpseudoutilisateur");
	    request.setParameter("password", "monpassewordutilisateur");
	    request.setParameter("mail", "prenom.nomutilisateur@gmail.com");
	    request.setAttribute("dateNaissance", d);
	    
	    // proxy pour simuler l'action
	    ActionProxy proxy = getActionProxy("/Inscription.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("success", result);
	    assertFalse(session.isEmpty());
	    
	}
	
}
