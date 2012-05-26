<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.LinkedList" %>
<%String recherche = (String) session.getAttribute("recherche"); %>
<%LinkedList<String> listMots = (LinkedList<String>) session.getAttribute("listMots"); %>
<%String motif = (String) session.getAttribute("motif"); %>

<h3><s:property value ="getText('message.motif')"/></h3>
		<s:actionerror/>
		<s:label>Ex : u??v?rs <s:property value ="getText('message.resultat')"/> : univers </s:label>
		<s:form action="rechercherMot" method ="post" onsubmit="return false;">
			<%if (recherche == "true") { %>
				<tr>
				<td> <s:textfield key ="message.motifSearch" id="motif" name="motif" value = "%{#session.motif}" style="width: 100px;"/> </td>
				</tr>
				<tr>
				<td> <s:select id="resultArea" key ="message.resultat" name="resultat" size="8" style="width: 100px;" list= "#session.listMots"> </s:select> 
				</td>
				 </tr>
			<%} else { %>
				<tr>
				<td> <s:textfield key ="message.motifSearch" id="motif" name="motif" value = "%{#session.motif}" style="width: 100px;"/> </td>
				</tr>
				<tr>
			<%} %>
			
			<s:hidden name="idGrille" value="%{grille.idGrille}"></s:hidden>
			<tr>
				<td> <s:submit key="message.recherche" label="Recherche"  onclick="copyArea()"></s:submit></td>
			</tr>
		</s:form>