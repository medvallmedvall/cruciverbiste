<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String auth1= null;
if (session.getAttribute("authentification") != null) {
	auth1 = (String) session.getAttribute("authentification");
}
%>
<ul id="navmenu">
	<li><a href="#">Jouer</a>
		<ul>
			<li><a href="choixGrilles?idTypeGrid=2">Mots croises</a> <!-- ul>
			<li><a href="#">Facile</a></li>
			<li><a href="#">Moyen</a></li>
			<li><a href="#">Expert</a></li>
		</ul--></li>
			<li><a href="choixGrilles?idTypeGrid=1">Mots fleches</a> <!--ul>
			<li><a href="#">Facile</a></li>
			<li><a href="#">Moyen</a></li>
			<li><a href="#">Expert</a></li>
		</ul--></li>
		</ul></li>
	<li><a href="#">Creer une grille</a>
		<ul>
			<li><a href="creerGrilleMC.jsp">Mots croises</a></li>
			<li><a href="creerGrilleMF.jsp">Mots fleches</a></li>
		</ul></li>


	<li><a href="#">Espace Jeune</a></li>
	<li><a href="#">Jeu concours</a></li>
	<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true"))) {
%>
	<li><a href="#">Mes Grilles</a></li>
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
</ul>

