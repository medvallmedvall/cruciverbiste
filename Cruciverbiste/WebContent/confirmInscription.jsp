<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription reussie</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->
	<div id="principal">
		<h2> <s:property value = "getText('message.confirmInscription')"/> </h2>
		<s:actionmessage/>
		<h3>Le menu � gauche vous permettra de parcourir ais�ment notre site. Vous avez acc�s � plusieurs fonctionnalit�s<br>
		et la possibilit� de partager votre exp�rience de cruciverbiste sur le forum.<br>
		Vous participerez aussi � la vie du site par la cr�ation de grilles, l'ajout de nouveaux mots � nos dictionnaires etc..<br>
		Amusez vous!! </h3>
	</div>
	<%@ include file="pied.jspf"%>