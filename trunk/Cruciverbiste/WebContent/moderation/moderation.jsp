<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value = "getText('message.moderation')"/></title>
<style type="text/css" title="currentStyle">
			@import "styles/demo_page.css";
			@import "styles/demo_table_jui.css";
			@import "styles/jquery-ui-1.8.4.custom.css";
			.ui-tabs .ui-tabs-panel { padding: 10px }
</style>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
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
	<%@ include file="/entete.jspf"%>

	<div id="principal">
		<c:choose>
			<c:when test="${authentification && droit != 0}">
			<h2><s:property value = "getText('message.creerConcours')"/></h2>
				<a href = "creerConcours.jsp"><s:property value = "getText('message.parici')"/></a>
				<h2><s:property value = "getText('message.moderation')"/></h2>
				<s:actionmessage/>
				<h3><s:property value = "getText('message.aval')"/></h3>
				
				<h4><s:property value = "getText('message.motF')"/></h4>
				<div id="tabs-1">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
					<thead>
					<tr>
						<th><s:property value = "getText('message.nom')"/></th>
						<th><s:property value = "getText('message.dateC')"/></th>
						<th><s:property value = "getText('message.vis')"/></th>
					</tr>
					</thead>
					
					<tbody>
					<c:forEach var="g" items="${grilleAValiderMF}">
						<tr class ="odd gradeC">
							<td>${g.nomGrille}</td>
							<td>${g.dateCreation }</td>
							<td><a href="tester?action=test&idGrille=${g.idGrille}"><s:property value = "getText('message.voir')"/></a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<h4><s:property value = "getText('message.motF')"/></h4>
				<div id="tabs-2">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
					<thead>
					<tr>
						<th><s:property value = "getText('message.nom')"/></th>
						<th><s:property value = "getText('message.dateC')"/></th>
						<th><s:property value = "getText('message.vis')"/></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="g" items="${grilleAValiderMC}">
						<tr class = "odd gradeA">
							<td>${g.nomGrille}</td>
							<td>${g.dateCreation }</td>
							<td><a href="tester?action=test&idGrille=${g.idGrille}"><s:property value = "getText('message.voir')"/></a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
			</c:when>
			<c:otherwise>
				<p><s:property value = "getText('message.validationacces')"/></p>
			</c:otherwise>
		</c:choose>
	</div>
	
	
	<%@ include file="/pied.jspf"%>