<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String auth1= null;
int droit = 0;
if (session.getAttribute("authentification") != null) {
	auth1 = (String) session.getAttribute("authentification");
	if (session.getAttribute("droit") != null) {
		droit = (Integer)session.getAttribute("droit");
	}
}
%>
<ul id="navmenu">
	<li><a href="#">Jouer</a>
		<ul>
			<li><a href="choixGrilles?idTypeGrid=2">Mots croises</a></li>
			<li><a href="choixGrilles?idTypeGrid=1">Mots fleches</a> </li>
		</ul></li>
	<li><a href="#">Espace Jeune</a></li>
	<li><a href="#">Jeu concours</a></li>
	<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true"))) {
%>
	<li><a href="#">Creer une grille</a>
		<ul>
			<li><a href="creerGrille?idTypeGrille=2">Mots croises</a></li>
			<li><a href="creerGrille?idTypeGrille=1">Mots fleches</a></li>
		</ul></li>
	<li><a href="mesGrilles">Mes Grilles</a></li>
	<li><a href="#">Grilles En Cours</a></li>
	<% 
	}
	else {
		%>
	<li><a href="inscription.jsp">Inscription</a></li>
	<%
	}
%>

	<li><a href="forum.jsp">Forum</a></li>
	
<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true")) && (droit != 0)) {
%>
	<li><a href="validerGrille?action=getGridToValidate">Moderation/Validation</a></li>
<%
	}
%>	
</ul>

