package actions;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class WordActionTest extends StrutsTestCase {

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/mots.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("mots", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception {
		
	// parametres de la requette 
    request.setAttribute("mot", null);
    request.setAttribute("action", null);
    
	// proxy pour simuler l'action 
    ActionProxy proxy = getActionProxy("/mots.action");
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
    
	}
	
	@Test
	public void testParametersEmpties() throws Exception {
		 
	
	// parametres de la requette 
	request.setParameter("mot", "       ");
	request.setParameter("action", "  ");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/mots.action");
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
	}
	
	@Test
	public void testParametersTrue() throws Exception {
		

	// parametres de la requette 
	request.setParameter("mot", "terre");
	request.setParameter("action", "getSynonyms");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/mots.action");
      
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("success", result);

    
	}
	
	@Test
	public void testParametersFalse() throws Exception {
				

		// parametres de la requette 
		request.setParameter("mot", "te%re");
		request.setParameter("action", "getNothing");
	    
		// proxy pour simuler l'action et la session
	    ActionProxy proxy = getActionProxy("/mots.action");
	      
	    
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);

	    
		}

}
