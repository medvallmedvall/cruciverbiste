<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erreur!</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
</head>
<body>
	<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="entete.jspf"%>
	<div id="principal">
		<h1>Une erreur est survenue</h1>
		<s:actionerror/>
	</div>
	
	<%@ include file="pied.jspf"%>