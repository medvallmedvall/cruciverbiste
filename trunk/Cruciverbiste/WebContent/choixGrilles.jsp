<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value = "getText('message.choix')"/></title>

                
                <link type="text/css" href="styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
                <link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
                <script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
                <script type="text/javascript" src="javascripts/jquery-ui-1.7.2.custom.min.js"></script>
                <script type="text/javascript" src="javascripts/jquery.scrollabletab.js"></script>
                <script type="text/javascript" src="javascripts/themeswitchertool.js"></script>
                <script type="text/javascript" src="javascripts/jquery.init.js"></script>
</head>
<body>
        
        <%@ include file="entete.jspf" %>
       <!--%@ include file="menu.jspf" %-->
        <%@ include file="secondaire.jspf" %>
         
 <div id="principal">
	<c:choose>
		<c:when test="${idTypeGrid == 1}">
		        <h1><s:property value = "getText('message.motF')"/></h1>
		</c:when>
		<c:when test="${idTypeGrid == 2}">
		        <h1><s:property value = "getText('message.motC')"/></h1>
		</c:when>
	</c:choose>
                                
	<div id="theme" class="tabs">
		<ul>
		  	<c:forEach var="mTheme" items="${themesList}">
				<li><a href="#${mTheme.idTheme}">${mTheme.nomTheme}</a></li>
			</c:forEach>
		</ul>
		<c:forEach var="mTheme" items="${themesList}">
			<div id="${mTheme.idTheme}">
				<table class="tableGrilles" id="tableTheme${mTheme.idTheme}">
					<tr>
					        <th>Nom grille</th>
					        <th>Createur</th>
					        <th>Taille</th>
					        <th>Niveau</th>
					        <th>Terminée</th>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
</div><!-- fin principale-->

<!-- On ajoute les grilles aux panneaux -->

<c:forEach var="grille" items="${grillesMap}">
	<script type="text/javascript">
		var mId = "#tableTheme" + ${grille.key.idTheme};
		var idValue = ${grille.value};
		if (idValue==true) {
			idValue="<img src='images/oui.gif' alt='la grille finie'/>";			
		}else
			idValue="<img src='images/non.png' alt='la grille n est finie'/>";
		var mUrl = "jouer?idGrille=" + ${grille.key.idGrille};
		var mElement = /*"<tr>"+
		   				   "<td align=center>Nom de la grille</td>"+
						   "<td align=center>Concepteur</td>"+
						   "<td align=center>largeur et hauteur</td>"+
						   "<td align=center>niveau</td>"+
						   "<td align=center>Terminée</td>"+
					   "</tr>"+*/
					   "<tr onclick=\"location.href='"+ mUrl + "'\">" +
	                       "<td align=center>${grille.key.nomGrille}</td>" +
	                       "<td align=center>${grille.key.pseudo}</td>" +
	                       "<td align=center>" + ${grille.key.largeur} + "x" + ${grille.key.hauteur} + "</td>" +
	                       "<td align=center>" + ${grille.key.niveau} + "</td>" +
	                       "<td align=center>" + idValue + "</td>" +
	               	   "</tr>";
		var mTable = $(mId);
		mTable.append(mElement);
	</script>
</c:forEach>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".tableGrilles tr:odd").each(
						function(index, element) {
							$(this).css("cursor", "pointer");
							$(this).addClass(".ligneImpaire");
						});
				$(".tableGrilles tr:even").each(
						function(index, element) {
							$(this).css("cursor", "pointer");
							$(this).addClass("lignePaire");
						});
			});
</script>

<%@ include file="pied.jspf" %>