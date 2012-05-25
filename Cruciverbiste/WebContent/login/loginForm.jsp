<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String auth= null;
if (session.getAttribute("authentification") != null) {
	auth = (String) session.getAttribute("authentification");
}
if ((auth != null) && (auth.equalsIgnoreCase("true"))) {
%>
	<s:property value = "getText('message.bienvenue')"/> ${pseudo}, <a href="Deconnexion"> <s:property value = "getText('message.deconnexion')"/></a>
<%
}
else {
%>
	<s:form action="Connexion" method="post" onsubmit="return true;" id="loginForm">
			<s:textfield name="pseudo" key ="message.pseudo"  id="pseudo" />
			<s:password name="password"  key="message.password" id="password" />

			<s:submit key="message.connexion" id="submit"
				cssStyle="text-align: center;">
			</s:submit>
		</s:form>
		<p id="loginP">
			<s:a href ="oubli.jsp" > <s:text name = "message.oubli"/></s:a>
		</p>
<%
}
%>

