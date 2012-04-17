package actions;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class EmailerTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/Emailer.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("Emailer", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		// parametres de la requette 
	    request.setAttribute("mail", null);
	    

		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/Emailer.action");
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
	    request.setAttribute("mail", "");
	    

		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/Emailer.action");
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
	    request.setParameter("mail", "medvallmedvall@gmail.com");
	    

		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/Emailer.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("success", result);
	    assertTrue(session.isEmpty());
    
	}
	
	@Test
	public void testParametersFalse() throws Exception {
			
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		// parametres de la requette 
	    request.setParameter("mail", "badmail@gmail.com");
	    

		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/Emailer.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    assertTrue(session.isEmpty());
	}
}

