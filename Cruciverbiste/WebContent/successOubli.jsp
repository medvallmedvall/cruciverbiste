<%@page import="java.util.Date"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue NomClient</title>

<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>
<%@ include file="entete.jspf" %>
<!--%@ include file="menu.jspf" %-->
 <%@ include file="secondaire.jspf" %>
<div id="principal">
	<s:property value ="getText('message.bienenvoye')"/><s:property value="mail"/>
				
		</div><!-- fin principale-->
	<%@ include file="pied.jspf" %>	