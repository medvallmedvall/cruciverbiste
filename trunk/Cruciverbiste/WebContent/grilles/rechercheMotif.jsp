<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.LinkedList" %>
<%String recherche = (String) session.getAttribute("recherche"); %>
<%LinkedList<String> listMots = (LinkedList<String>) session.getAttribute("listMots"); %>
<h3>Recherchez un mot à partir d'un motif</h3>
<%-- <c:forEach var="liste" items="${listMots}" varStatus="status"> --%>
<%-- 	<c:set var="resultArea" value="${listMots.index}"/>		 --%>
<%-- </c:forEach> --%>

		<s:label>Ex : u??v?rs resultat : univers </s:label>
		<s:form action="rechercherMot" method ="post" onsubmit="return false;">
			<tr>
				<td> <s:textfield id="motif" name="motif" label="Votre motif ici" style="width: 100px;"/> </td>
			</tr>
			<tr>
			<%if (recherche == "true") { %>
				<td> <s:select id="resultArea" name="resultat" size="4" label="Votre resultat" style="width: 100px;" list= "#session.listMots"> </s:select> 
				</td>
				<%} %>
<%-- 			<% } else { session.}%> --%>
			
		
<%-- 			<% } else { %> --%>
<%-- 				<td> <s:textarea id ="resultArea" name="resultat" label="Votre resultat" disabled="true" style="width: 100px;"></s:textarea> --%>
<%-- 			<% } %> --%>
			 </tr>
			<s:hidden name="idGrille" value="%{grille.idGrille}"></s:hidden>
			<tr>
				<td> <s:submit value ="Recherche" label="Recherche"  onclick="copyArea()"></s:submit></td>
			</tr>
		</s:form>