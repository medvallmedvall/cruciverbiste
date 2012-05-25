<%@page import="entities.Utilisateur"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%List<Utilisateur>  utilisateurs = (List<Utilisateur>)session.getAttribute("users"); 
  Integer user = (Integer)session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value = "getText('message.espace')"/></title>
<style type="text/css" title="currentStyle">
			@import "styles/demo_page.css";
			@import "styles/demo_table_jui.css";
			@import "styles/jquery-ui-1.8.4.custom.css";
			.ui-tabs .ui-tabs-panel { padding: 10px }
</style>
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" language="javascript" src="javascripts/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="javascripts/jquery-ui-tabs.js"></script>
<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			$("#tabs").tabs( {
				"show": function(event, ui) {
					var oTable = $('div.dataTables_scrollBody>table.display', ui.panel).dataTable();
				if ( oTable.length > 0 ) {
						oTable.fnAdjustColumnSizing();
					}
				}
			} );
				
			$('table.display').dataTable( {
				"sScrollY": "200px",
				"bScrollCollapse": true,
				"bPaginate": false,
				"bJQueryUI": true,
				"aoColumnDefs": [
					{ "sWidth": "10%", "aTargets": [ -1 ] }	]
				} );
			} );
</script>
<%@ include file="entete.jspf" %>
 <%@ include file="secondaire.jspf" %>
<div id="principal">
	<h3><s:property value = "getText('message.pageadmin')"/></h3>
	<c:choose>
		<c:when test = "${users == null}">
			<h3><s:property value = "getText('message.reco')"/></h3>
		</c:when>
		<c:otherwise>
		<div id="tabs-2">
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
			<thead>
			<tr>
				<th><s:property value = "getText('message.nom')"/> </th>
				<th><s:property value = "getText('message.pseudo')"/> </th>
				<th><s:property value = "getText('message.donner')"/></th>
				<th><s:property value = "getText('message.enlever')"/></th>
				<th><s:property value = "getText('message.statut')"/></th>
			</tr>
			</thead>
			 <tbody>
			<c:forEach var = "u" items = "${users}">
			    <c:set var = "nom" value ="${u.getNom()}"/>
			    <c:set var = "pseudo" value ="${u.getPseudo()}"/>
			  
				<tr class="odd gradeC"> 
				<td>${nom}</td>
				<td>${pseudo}</td>
				<td><a href = "nommer?user=${u.getIdUtilisateur()}"><s:property value = "getText('message.nommer')"/></a> </td>
				<td><a href = "enlever?user=${u.getIdUtilisateur()}"><s:property value = "getText('message.retrai')"/></a></td>
				<c:choose>
					<c:when test = "${u.getIdDroit() == 0}">
						<td><s:property value = "getText('message.usersimple')"/> </td>
					</c:when>
					<c:otherwise>
						<td><s:property value = "getText('message.moderateur')"/></td>
					</c:otherwise>
				</c:choose>
           
				</tr>
			</c:forEach>
			</tbody>
		 </table>
		 </div>
		</c:otherwise>
	
	</c:choose>
			
		</div><!-- fin principale-->
	<%@ include file="pied.jspf" %>	