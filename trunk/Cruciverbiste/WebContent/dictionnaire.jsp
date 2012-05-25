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
		<h2 style="color: #B0DFFF; font-family: Verdana, sans-serif;"><s:text name ="message.dico"/></h2>
		<div id = "textDico">
		<s:property value ="getText('message.textDico')"/> 
		</div>
	<div id ="imageDico">
	<a href="#"> <img alt="France" src="images/dico.jpg"> </a>
	</div>
	</div><!-- fin principale-->

	<%@ include file="pied.jspf"%>