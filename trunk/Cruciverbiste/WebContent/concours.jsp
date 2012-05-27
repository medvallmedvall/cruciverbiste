<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% Date current = new Date(); %>
<% SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value = "getText('message.jeuconcours')"/></title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
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
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->
	<div id="principal"><!-- debut principal -->
	<c:choose>
		<c:when test = "${authentification}">
					<h2 style="color: #B0DFFF;"><s:property value = "getText('message.jeuconcours')"/></h2>
		
					<div id = "resultatJeu">
					<h4><s:property value = "getText('message.concoursdu')"/> <%=format.format(current) %> </h4>
						 <div style="text-align: right;"><s:property value = "getText('message.concoursdu')"/> Mr Dupont (Test) </div>
					</div>
					<div id="tabs-2">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
					<thead>
						<tr> 
							<th><s:property value = "getText('message.dateDeb')"/> </th>
							<th><s:property value = "getText('message.dateFin')"/></th>
							<th><s:property value = "getText('message.grilleconcours')"/></th>
						<c:if test="${authentification && droit != 0}">
							<th><s:property value = "getText('message.supprimerConcours')"/></th>
						</c:if>
						</tr>
				  </thead>
	
				  <tbody>
					<c:forEach var ="c" items= "${listConcours}">
						<c:set var="debut" value ="${c.getDateDebut()}"/>
						<c:set var="fin" value ="${c.getDateFin()}"/>
						<tr class="odd gradeC">
							<td>${debut}</td>
							<td>${fin} </td>
							<td><a href = "jouerConcours?idGrille=${c.idGrille}&idConcours=${c.getIdConcours()}"><s:property value = "getText('message.grillejeu')"/></a></td>
						<c:if test="${authentification && droit != 0}">
							<td> <a href ="SupprJeuconcours?idConcours=${c.getIdConcours()}"><s:property value = "getText('message.supprElem')"/></a></td>
						</c:if>
						</tr>
					</c:forEach>
				 </tbody>
	
	
				</table>
				</div>

		</c:when>
		<c:otherwise>
			<h3><s:property value ="getText('message.validationacces')"/></h3>
		</c:otherwise>
	</c:choose>
	</div><!-- fin principale-->

	<%@ include file="pied.jspf"%>