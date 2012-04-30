package actions;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class RechercheMotTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/rechercherMot.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("rechercherMot", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception {
	
	// parametres de la requette 
    request.setAttribute("motif", null);
    
	// proxy pour simuler l'action 
    ActionProxy proxy = getActionProxy("/rechercherMot.action");
   
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);
  
	}
	
	@Test
	public void testParametersEmpties() throws Exception {
	
	// parametres de la requette 
	request.setAttribute("motif", "           ");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/rechercherMot.action");

    
  
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);

	}
	
	@Test
	public void testBadPattern() throws Exception {
	
	// parametres de la requette 
	request.setAttribute("motif", "  hfu dfhuhsdf <>*é&àç_-'");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/rechercherMot.action");

    
  
    
    //action
    String result = proxy.execute();
    
    //verification action
    assertEquals("error", result);

	}
	
	@Test
	public void testGoodPattern() throws Exception {
		
	HashMap<String, Object> session = new HashMap<String, Object>(); 
	
	// parametres de la requette 
	request.setParameter("motif", "t???e");
    
	// proxy pour simuler l'action et la session
    ActionProxy proxy = getActionProxy("/rechercherMot.action");
    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
    actionContext.setSession(session);

    // action
    String result = proxy.execute();
    
    // verification action
    assertEquals("success", result);
    assertEquals(session.get("recherche"),"true");
    assertNotNull(session.get("listMots"));

	}
	


}
