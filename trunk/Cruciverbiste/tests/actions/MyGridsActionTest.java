package actions;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class MyGridsActionTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/mesGrilles.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("mesGrilles", mapping.getName());
    }
	
	@Test
	public void testUserNotConnected() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	// parametres de la requette 
    request.setAttribute("authentification", null);
    
	// proxy pour simuler l'action 
    ActionProxy proxy = getActionProxy("/mesGrilles.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
	}
	
	
	public void testUserConnectedHasGrids() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>(); 
	
		// parametres de la requette 
	    session.put("authentification", "true");
	    session.put("idUser", 1);
	    
		// proxy pour simuler l'action et la session
	    ActionProxy proxy = getActionProxy("/mesGrilles.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("success", result);
    
	}

}
