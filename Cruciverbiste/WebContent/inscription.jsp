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

<h3><s:property value = "getText('message.inscription')"/></h3>

<s:actionerror/>

<s:form action="Inscription" method="post" onsubmit="return verif_formulaire();">
	<table>
		<tr>
			<td><s:textfield key ="message.nom" name="nom" /></td>
		</tr>
		<tr>
			<td><s:textfield key = "message.prenom" name="prenom"/></td>
		</tr>
		<tr>
			<td><sx:datetimepicker key="message.naissance" name="dateNaissance"
				 displayFormat="dd-MM-yyyy"/></td>
		</tr>
		<tr>
			<td><s:textfield key="message.ps" name="pseudo" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:password key="message.pass" name="password" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:password key="message.reMdp" name="motdepasse2" onchange="changeColor(this)"/></td>
		</tr>
		<tr>
			<td><s:textfield key = "message.mail" name="mail" onchange="changeColor(this)" /></td>
		</tr>
		<tr>
			<td><s:submit key = "message.ins" style="width: 100px;"></s:submit></td>
		</tr>
	</table>
</s:form>
<h4> * : <s:property value = "getText('message.asterique')"/></h4>
</div>
<!-- fin principale-->
<%@ include file="pied.jspf"%>