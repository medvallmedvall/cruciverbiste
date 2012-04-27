<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% Date current = new Date(); %>
<% SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); %>
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
		<h2 style="color: #B0DFFF;">Jeux concours</h2>
		
	<div id = "resultatJeu">
		<h4>Jeu Concours du <%=format.format(current) %> </h4>
		 <div style="text-align: right;">Gagnant : Mr Dupont (Test) </div>
	</div>
	<div id ="grilleJeu">
		<a href = "jouer?idGrille=4">Grille en Jeu</a>
	</div>
	
	
	<div id = "previous">
		
	</div>
	</div><!-- fin principale-->

	<%@ include file="pied.jspf"%>