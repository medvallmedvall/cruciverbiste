<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des grilles</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" href="styles/coda-slider-2.0.css" type="text/css"
	media="screen" />
</head>
<body>
	<%@ include file="entete.jspf"%>
	<%@ include file="menu.jspf"%>
	<%@ include file="secondaire.jspf"%>

	<!-- Begin JavaScript -->
	<script type="text/javascript" src="javascripts/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="javascripts/jquery.easing.1.3.js"></script>
	<script type="text/javascript"
		src="javascripts/jquery.coda-slider-2.0.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			$('#coda-slider-1').codaSlider();
		});
	</script>
	<!-- End JavaScript -->

	<div id="principal">

		<c:choose>
			<c:when test="${idTypeGrid == 1}">
				<h1>Mots Fléchés </h1>
			</c:when>
			<c:when test="${idTypeGrid == 2}">
				<h1>Mots Croisés</h1>
			</c:when>
		</c:choose>

		<!-- On cree les panneaux pour chaque themes -->

		<div class="coda-slider-wrapper">
			<div class="coda-slider preload" id="coda-slider-1">
				<c:forEach var="mTheme" items="${themesList}">
					<div class="panel">
						<div class="panel-wrapper">
							<h2 class="title">${mTheme.nomTheme}</h2>
							<table class="tableGrilles" id="tableTheme${mTheme.idTheme}">
								<!--tr>
									<th>Nom grille</th>
									<th>Createur</th>
									<th>Taille</th>
									<th>Niveau</th>
								</tr-->
							</table>
						</div>
					</div>
				</c:forEach>

			</div><!-- .coda-slider -->
		</div><!--fin .coda-slider-wrapper -->
		
		<!-- On ajoute les grilles aux panneaux -->
		
		<c:forEach var="g" items="${grillesList}">
			<script type="text/javascript">
				var mId = "#tableTheme" + ${g.idTheme};
				var mUrl = "jouer?idGrille=" + ${g.idGrille};
				var mElement = "<tr onclick=\"location.href='"+ mUrl + "'\">" +
									"<td>${g.nomGrille}</td>" +
									"<td>concepteur</td>" +
									"<td>" + ${g.largeur} + "x" + ${g.hauteur} + "</td>" +
									"<td>" + ${g.niveau} + "</td>" +
								"</tr>";
				var mTable = $(mId);
				mTable.append(mElement);
			</script>
		</c:forEach>

	</div><!-- fin de principal -->
	
	<script type="text/javascript">
		$('table.tableGrilles tr:nth-child(even)').addClass('lignePaire').css('cursor','pointer');
		$('table.tableGrilles tr:nth-child(odd)').addClass('ligneImpaire').css('cursor','pointer');
	</script>
	
	

	<jsp:include page="pied.jspf" />