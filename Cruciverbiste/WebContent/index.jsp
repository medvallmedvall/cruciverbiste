<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue Cruciverbistes!</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->
	<%@ include file="secondaire.jspf"%>
	<div id="principal"><!-- debut principal -->
		<h2>Grille du jour</h2>
		<div id="grilleJour">
			<a href="jouer?idGrille=4">
				<img alt="grille du jour" src="images/grillesJour/grille4.png">
			</a>
			<p><em>Source : http://www.femmeactuelle.fr/</em></p>
		</div>
	</div><!-- fin principale-->
	
	<script>
	$(window).ready(function(){
		$.ajax({
			url : "SujetsRecents",
			cache : false,
			success : function(contenu) {
				$("#secondaire").append(contenu);
			},
			error : function() {
				alert("erreur...")
				}
		});
	});
	
	</script>

	<%@ include file="pied.jspf"%>