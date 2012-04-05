<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix grille</title>

                
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
		        <h1>Mots Fléchés </h1>
		</c:when>
		<c:when test="${idTypeGrid == 2}">
		        <h1>Mots Croisés</h1>
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
					<!--tr>
					        <th>Nom grille</th>
					        <th>Createur</th>
					        <th>Taille</th>
					        <th>Niveau</th>
					</tr-->
				</table>
			</div>
		</c:forEach>
	</div>
</div><!-- fin principale-->

<!-- On ajoute les grilles aux panneaux -->

<c:forEach var="g" items="${grillesList}">
	<script type="text/javascript">
		var mId = "#tableTheme" + ${g.idTheme};
		var mUrl = "jouer?idGrille=" + ${g.idGrille};
		var mElement = "<tr onclick=\"location.href='"+ mUrl + "'\">" +
                       "<td>${g.nomGrille}</td>" +
                       "<td>${g.pseudo}</td>" +
                       "<td>" + ${g.largeur} + "x" + ${g.hauteur} + "</td>" +
                       "<td>" + ${g.niveau} + "</td>" +
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