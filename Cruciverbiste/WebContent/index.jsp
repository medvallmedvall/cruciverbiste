<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value ="getText('message.title')"/></title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->
	<%@ include file="secondaire.jspf"%>
	<div id="principal"><!-- debut principal -->

		<div id="grilleJour">
		<h2><s:text name = "message.grillejour"/></h2>
			<a href="jouer?idGrille=4">
				<img alt="grille du jour" src="images/grillesJour/grille4.png">
			</a>
		</div>
		<div id = "textDico">
		<h2><s:text name ="message.dico"/></h2>
		<s:property value ="getText('message.textDico')"/> 
		</div>
		<div id ="imageDico">
			<a href="#"> <img alt="France" src="images/dico.jpg"> </a>
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
	