<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String auth= null;
if (session.getAttribute("authentification") != null) {
	auth = (String) session.getAttribute("authentification");
}
if ((auth != null) && (auth.equalsIgnoreCase("true"))) {
%>
	Bienvenue ${pseudo}, <a href="#" onclick="disconnect();">Deconnexion</a>
<%
}
else {
%>
	<s:form action="Connexion" method="post" onsubmit="return false;" id="loginForm">
			<s:textfield name="pseudo" label="Identifiant" id="pseudo" />
			<s:password name="password" label="Mot de passe" id="password" />

			<s:submit label="Connexion" value="Connexion" id="submit"
				cssStyle="text-align: center;" onclick="connect();">
			</s:submit>
		</s:form>
		<p id="loginP">
			<a href="oubli.jsp">Mot de passe oublié?</a>
		</p>
<%
}
%>

