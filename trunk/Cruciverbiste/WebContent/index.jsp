<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue Cruciverbistes!</title>
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>	
 <%@ include file="entete.jspf" %>
 <%@ include file="menu.jspf" %>
 <%@ include file="secondaire.jspf" %>
			<div id="principal"><!-- debut principal -->
				<h3>Grille du jour</h3> 
				<div id="grille"><a href="jouer?idGrid=2"><img alt="Grille" src="images/grille.jpg" ></a></div>
			</div><!-- fin principale-->
			
<%@ include file="pied.jspf" %>	
	