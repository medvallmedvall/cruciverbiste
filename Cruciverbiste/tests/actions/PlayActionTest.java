package actions;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class PlayActionTest  extends StrutsTestCase{
	
	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/jouer.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("jouer", mapping.getName());
    }
	
	@Test
	public void testParametersNulls() throws Exception{
	
		
		// parametres de la requette 
	    request.setAttribute("idGrille", null); 
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/jouer.action");
	  
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    
		}
		
	
	@Test
	public void testParametersEmpties() throws Exception{
	
		
		// parametres de la requette 
	    request.setParameter("idGrille", ""); 
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/jouer.action");
	  
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    
		}
		
	
	@Test
	public void testExecuteFalse() throws Exception{
		
		// parametres de la requette 
	    request.setParameter("idGrille", "-1254"); 
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/jouer.action");
	  
	    //action
	    String result = proxy.execute();
	    
	    //verification action
	    assertEquals("error", result);
	    
	}
	
//	@Test
//	public void testExecuteTrue() throws Exception{
//		
//		// parametres de la requette 
//	    request.setParameter("idGrille", "2"); 
//	    
//		// proxy pour simuler l'action 
//	    ActionProxy proxy = getActionProxy("/jouer.action");
//	  
//	    //action
//	    String result = proxy.execute();
//	    
//	    //verification action
//	    assertEquals("success", result);
//	}

}
