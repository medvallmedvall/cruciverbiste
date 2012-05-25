<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value ="getText('message.mesgrilles')"/></title>
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
	<div id="principal">
		<h1><s:property value ="getText('message.mesgrilles')"/></h1>
		
		
		<h3><s:text name="message.motF"/></h3>
		<div id="tabs-2">
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
			<thead>
			<tr>
				<th><s:text name="message.nomG"/> </th>
				<th><s:text name="message.dateC"/></th>
				<th><s:text name="message.estF"/></th>
				<th><s:text name="message.dateV"/></th>
				<th><s:text name="message.vis"/></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="grille" items="${mesGrilles}">
				<c:if test="${grille.idTypeGrille == 1}">
			
				<tr class="odd gradeC">
					<td>${grille.nomGrille}</td>
					<td>${grille.dateCreationF}</td>
					<td>
						<c:choose>
							<c:when test="${grille.estFinie}"><s:text name="message.oui"/></c:when>
							<c:when test="${!grille.estFinie}"><s:text name="message.non"/></c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${empty grille.dateValidationF}">
								<a href="creerGrille?idGrille=${grille.idGrille}"><s:text name="message.repC"/></a>
							</c:when>
							<c:otherwise>
								${grille.dateValidationF}
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="tester?action=test&idGrille=${grille.idGrille}"><s:text name="message.voir"/></a>
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<br/><br/>
		
		<h3><s:text name="message.motC"/></h3>
		<div id="tabs-2">
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example1">
			
			<thead>
			<tr>
				<th><s:text name="message.nomG"/> </th>
				<th><s:text name="message.dateC"/></th>
				<th><s:text name="message.estF"/></th>
				<th><s:text name="message.dateV"/></th>
				<th><s:text name="message.vis"/></th>
			</tr>
			</thead>
			
			<tbody>
			<c:forEach var="grille" items="${mesGrilles}">
				<c:if test="${grille.idTypeGrille == 2}">
				<tr class="odd gradeC">
					<td>${grille.nomGrille}</td>
					<td>${grille.dateCreationF}</td>
					<td>
						<c:choose>
							<c:when test="${grille.estFinie}"><s:text name="message.oui"/></c:when>
							<c:when test="${!grille.estFinie}"><s:text name="message.non"/></c:when>
						</c:choose>
					</td>
					<td>
					<c:choose>
							<c:when test="${empty grille.dateValidationF}">
								<a href="creerGrille?idGrille=${grille.idGrille}"><s:text name="message.repC"/></a>
							</c:when>
							<c:otherwise>
								${grille.dateValidationF}
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="tester?action=test&idGrille=${grille.idGrille}"><s:text name="message.voir"/></a>
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
		</div>
		
	</div>
	
	
	
	<%@ include file="pied.jspf"%>
	
	
	