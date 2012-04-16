<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creer grille</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
</head>
<body>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="entete.jspf"%>
	<div id="principal">
		<h1>Creation de grille mots croisés</h1>
		Taille de la grille :
		<form action="creerGrilleMC.jsp">
			Largeur: <input type="text" name="largeurGrille">
			Hauteur: <input type="text" name="hauteurGrille">
			<input type="submit" value="valider">
		</form>
	</div>
	
	<%@ include file="pied.jspf"%>
