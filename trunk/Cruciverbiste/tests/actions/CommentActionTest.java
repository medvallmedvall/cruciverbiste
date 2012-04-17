package actions;

import java.util.HashMap;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class CommentActionTest extends StrutsTestCase{

	@Test
	public void testMapping() {
        ActionMapping mapping = getActionMapping("/posterCommentaire.action");
        assertNotNull(mapping);
        assertEquals("/", mapping.getNamespace());
        assertEquals("posterCommentaire", mapping.getName());
    }

	@Test
	public void testUserNotConnected() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>();
		// parametres de la requette 
	    request.setParameter("commentaire", "un commentaire");
	    request.setParameter("idGrille", "1");
	    
		// proxy pour simuler l'action 
	    ActionProxy proxy = getActionProxy("/posterCommentaire.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	     
	    //connexion
	    String result = proxy.execute();
	    
	    //verification connexion
	    assertEquals("error", result);
	    assertTrue(session.isEmpty());
	}
	public void testUserConnectedParametersNulls() throws Exception {
			
			HashMap<String, Object> session = new HashMap<String, Object>();
			
			// parametres de la requette 
		    request.setParameter("pseudo", "medvallmedvall");
		    request.setParameter("password", "123456789"); 
		    request.setAttribute("commentaire", null);
		    request.setAttribute("idGrille", null);
		    
			// proxy pour simuler l'action connexion
		    ActionProxy proxy = getActionProxy("/Connexion.action");
		    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		    actionContext.setSession(session);
		    
		    //connexion
		    String result = proxy.execute();
		    
		    //verification connexion
		    assertEquals("success", result);
		    assertFalse(session.isEmpty());
		    
			// proxy pour simuler l'action commenter
		    ActionProxy proxyComment = getActionProxy("/posterCommentaire.action");

		    // passer la session courante
		    ActionContext.getContext().setSession(session);
		     
		    //commenter
		    result = proxyComment.execute();
		    
		    //verification commenter
		    assertEquals("error", result);
		    assertFalse(session.isEmpty());
		}
	
	public void testUserConnectedParametersEmpties() throws Exception {
		
		HashMap<String, Object> session = new HashMap<String, Object>();
		
		// parametres de la requette 
	    request.setParameter("pseudo", "medvallmedvall");
	    request.setParameter("password", "123456789"); 
	    request.setParameter("commentaire", "   ");
	    request.setAttribute("idGrille", "        ");
	    
		// proxy pour simuler l'action connexion
	    ActionProxy proxy = getActionProxy("/Connexion.action");
	    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
	    actionContext.setSession(session);
	    
	    //connexion
	    String result = proxy.execute();
	    
	    //verification connexion
	    assertEquals("success", result);
	    assertFalse(session.isEmpty());
	    
		// proxy pour simuler l'action commenter
	    ActionProxy proxyComment = getActionProxy("/posterCommentaire.action");

	    // passer la session courante
	    ActionContext.getContext().setSession(session);
	     
	    //commenter
	    result = proxyComment.execute();
	    
	    //verification commenter
	    assertEquals("error", result);
	    assertFalse(session.isEmpty());
	}
	
	public void testUserConnectedParametersFalse() throws Exception {
			
			HashMap<String, Object> session = new HashMap<String, Object>();
			
			// parametres de la requette 
		    request.setParameter("pseudo", "medvallmedvall");
		    request.setParameter("password", "123456789"); 
		    request.setParameter("commentaire", "un commentaire");
		    request.setParameter("idGrille", "-1236");
		    
			// proxy pour simuler l'action connexion
		    ActionProxy proxy = getActionProxy("/Connexion.action");
		    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		    actionContext.setSession(session);
		    
		    //connexion
		    String result = proxy.execute();
		    
		    //verification connexion
		    assertEquals("success", result);
		    assertFalse(session.isEmpty());
		    
			// proxy pour simuler l'action commenter
		    ActionProxy proxyComment = getActionProxy("/posterCommentaire.action");
	
		    // passer la session courante
		    ActionContext.getContext().setSession(session);
		     
		    //commenter
		    result = proxyComment.execute();
		    
		    //verification commenter
		    assertEquals("error", result);
		    assertFalse(session.isEmpty());
		}
	public void testUserConnectedParametersTrue() throws Exception {
			
			HashMap<String, Object> session = new HashMap<String, Object>();
			
			// parametres de la requette 
		    request.setParameter("pseudo", "medvallmedvall");
		    request.setParameter("password", "123456789"); 
		    request.setParameter("commentaire", "un commentaire");
		    request.setParameter("idGrille", "1");
		    
			// proxy pour simuler l'action connexion
		    ActionProxy proxy = getActionProxy("/Connexion.action");
		    ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		    actionContext.setSession(session);
		    
		    //connexion
		    String result = proxy.execute();
		    
		    //verification connexion
		    assertEquals("success", result);
		    assertFalse(session.isEmpty());
		    
			// proxy pour simuler l'action commenter
		    ActionProxy proxyComment = getActionProxy("/posterCommentaire.action");
	
		    // passer la session courante
		    ActionContext.getContext().setSession(session);
		     
		    //commenter
		    result = proxyComment.execute();
		    
		    //verification commenter
		    assertEquals("success", result);
		    assertFalse(session.isEmpty());
		}
}
