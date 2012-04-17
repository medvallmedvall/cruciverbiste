package actions;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class DeconnexionTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/Deconnexion.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("Deconnexion", mapping.getName());
    }
	
	@Test
	public void testExecute() throws Exception{
		
		HashMap<String, Object> session = new HashMap<String, Object>(); 
		
		//parametres de la requette 
	    request.setParameter("pseudo", "bolo");
	    request.setParameter("password", "aaa");
	    
	    
	    
		//proxy pour simuler l'action et la session
	    ActionProxy proxyConnexion = getActionProxy("/Connexion.action");
	    ActionContext actionContextConnexion = proxyConnexion.getInvocation().getInvocationContext();
	    actionContextConnexion.setSession(session);
	     
	    //connexion
	    String result = proxyConnexion.execute();
	    
	    //verification connexion
	    assertEquals("success", result);
	    assertFalse(session.isEmpty());
	    
	     //proxy pour simuler l'action deconnexion
	    ActionProxy proxyDeconnexion = getActionProxy("/Deconnexion.action");
	    
	    // passer la session courante
	    ActionContext.getContext().setSession(session);
	
	    //deconnexion
	    result = proxyDeconnexion.execute();
	    
	    //verification deconnexion
	     assertEquals("success", result);
	     assertTrue(session.isEmpty());
	    
	}

}
