package actions;

import java.util.HashMap;
import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class ConnexionTest extends StrutsTestCase {
	
	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/Connexion.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("Connexion", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	// parametres de la requette 
    request.setAttribute("pseudo", null);
    request.setAttribute("password", null);
    
	// proxy pour simuler l'action 
    ActionProxy proxy = getActionProxy("/Connexion.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testParametersEmpties() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	// parametres de la requette 
    request.setParameter("pseudo", "               ");
    request.setParameter("password", "");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/Connexion.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);
    
  
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testParametersTrue() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	
	// parametres de la requette 
    request.setParameter("pseudo", "bolo");
    request.setParameter("password", "azerty"); 
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/Connexion.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);
    
    
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("success", result);
    assertFalse(session.isEmpty());
    
	}
	
	@Test
	public void testParametersFalse() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/Connexion.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);
    
    // parametres de la requette 
    request.setParameter("pseudo", "baduser");
    request.setParameter("password", "badpassword");
    
    //connexion
    String result = proxy.execute();
    
    //verification connexion
    assertEquals("error", result);
    assertTrue(session.isEmpty());
    
	}
	
}
