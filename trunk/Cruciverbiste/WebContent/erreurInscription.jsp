<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Echec Inscription</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
</head>
<body>
	<%@ include file="entete.jspf"%>
	<%@ include file="menu.jspf"%>
	<div id="principal">
		<h1>Une erreur est survenue lors de l'inscription.<br> 
		Veuillez changer votre pseudo ou votre e-mail</h1>
		<s:actionerror/>
	</div>
	<%@ include file="pied.jspf"%>