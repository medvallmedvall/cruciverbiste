package actions;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class GetGridListActionTest extends StrutsTestCase{
	
	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/choixGrilles.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("choixGrilles", mapping.getName());
    }
	

	@Test
	public void testExecuteNulls() throws Exception {

		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		// parametres de la requette 
	    request.setAttribute("idTypeGrid", null);
	   
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/choixGrilles.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testExecuteEmpties() throws Exception {

		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		// parametres de la requette 
	    request.setAttribute("idTypeGrid", "              ");
	   
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/choixGrilles.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    assertTrue(session.isEmpty());
	}
	
	@Test
	public void testExecuteFalse() throws Exception {
		HashMap<String, Object> session = new HashMap<String, Object>(); 
			
		// parametres de la requette 
	    request.setParameter("idTypeGrid", "-1234");
	   
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/choixGrilles.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    assertTrue(session.isEmpty());
    
	}
	
	@Test
	public void testExecuteTrue() throws Exception {
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		// parametres de la requette 
	    request.setParameter("idTypeGrid", "1");
	   
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/choixGrilles.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("success", result);
	    assertTrue(session.isEmpty());
	    
	}

}
