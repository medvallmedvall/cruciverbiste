<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue pour l'inscription</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
	<script type="text/javascript" src="javascripts/inscription.js"></script>
</head>
<body>
<%@ include file="entete.jspf"%>
<%@ include file="menu.jspf"%>
<%@ include file="secondaire.jspf"%>
<div id="principal">

<h3>Inscription du client :</h3>
<s:form action="Inscription" method="post">
<table>
	<tr>
		<td><s:textfield name="nom" label="Nom"/></td>
	</tr>
	<tr>
		<td><s:textfield name="prenom" label="Prenom"/></td>
	</tr>
	<tr>
		<td><s:textfield name="dateNaissance" label="Date De Naissance" value = "jj/mm/aaaa"/></td>
		
	</tr>
	<tr>
		<td><s:textfield name="pseudo" label="Pseudo*"/></td>
	</tr>
	<tr>
		<td><s:password name="password" label="Mot De Passe*"/></td>
	</tr>
	<tr>
		<td><s:password name="motdepasse2" label="Retapez le mot de passe*"/></td>
	</tr>
	<tr>
		<td><s:textfield name="mail" label="Adresse email*" /></td>
	</tr>
	<tr>
		<td><s:submit  onclick="verif_formulaire()" value="Inscription" style="width: 100px;" label="Inscription"></s:submit></td>
	</tr>
</table>
</s:form>


</div>
<!-- fin principale-->
<%@ include file="pied.jspf"%>