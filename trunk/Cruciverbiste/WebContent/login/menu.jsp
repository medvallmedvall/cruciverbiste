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
	<li><a href="#"><s:text name="message.jouer"/></a>
		<ul>
			<li><a href="choixGrilles?idTypeGrid=2"><s:text name="message.motC"/> </a></li>
			<li><a href="choixGrilles?idTypeGrid=1"><s:text name="message.motF"/></a> </li>
		</ul></li>
	<li><a href="#"><s:text name="message.jeune"/></a></li>
	<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true"))) {
%>
	<li><a href="#"><s:text name="message.creer"/></a>
		<ul>
			<li><a href="creerGrille?idTypeGrille=2"><s:text name="message.motC"/></a></li>
			<li><a href="creerGrille?idTypeGrille=1"><s:text name="message.motF"/></a></li>
		</ul></li>
	<li><a href="Jeuconcours"><s:text name="message.jeuconcours"/></a></li>
	<li><a href="mesGrilles"><s:text name="message.mes"/></a></li>
	<li><a href="grillesEnCours"><s:text name="message.mesG"/></a></li>
	<% 
	}
	else {
		%>
	<li><a href="inscription.jsp"><s:text name="message.inscription"/></a></li>
	<%
	}
%>

	<li><a href="forum.jsp"><s:text name="message.forum"/></a></li>
	
<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true")) && (droit != 0)) {
%>
<li><a href="validerGrille?action=getGridToValidate"><s:text name="message.moderationval"/> </a></li>
<%
	}
%>

<% 
	if ((auth1 != null) && (auth1.equalsIgnoreCase("true")) && (droit == 3)) {
%>
	<li><a href = "accesAdmin"><s:text name="message.administration"/></a></li>
	
<%
	}
%>	
</ul>

