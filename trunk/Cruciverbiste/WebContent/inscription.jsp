<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue pour l'inscription</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/testInscription.js"></script>
<sx:head/>
</head>
<body>
<%@ include file="entete.jspf"%>
<!--%@ include file="menu.jspf" %-->
<%@ include file="secondaire.jspf"%>
<div id="principal">

<h3>Inscription du client :</h3>

<s:actionerror/>

<s:form action="Inscription" method="post" onsubmit="return verif_formulaire();">
	<table>
		<tr>
			<td><s:textfield name="nom" label="Nom"/></td>
		</tr>
		<tr>
			<td><s:textfield name="prenom" label="Prenom" /></td>
		</tr>
		<tr>
			<td><sx:datetimepicker name="dateNaissance"
				label="Date De Naissance(dd-MM-yyyy)" displayFormat="dd-MM-yyyy"/></td>
		</tr>
		<tr>
			<td><s:textfield name="pseudo" label="Pseudo*" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:password name="password" label="Mot De Passe*" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:password name="motdepasse2"
				label="Retapez le mot de passe*" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:textfield name="mail" label="Adresse email*" onchange="changeColor(this)" /></td>
		</tr>
		<tr>
			<td><s:submit value="Inscription" style="width: 100px;"
				label="Inscription"></s:submit></td>
		</tr>
	</table>
</s:form></div>
<!-- fin principale-->
<%@ include file="pied.jspf"%>