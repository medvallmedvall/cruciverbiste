<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value = "getText('message.messoubli')"/></title>

<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
<script type="text/javascript" src="javascripts/validEmail.js"></script>
</head>
<body>
<%@ include file="entete.jspf" %>
<!--%@ include file="menu.jspf" %-->
 <%@ include file="secondaire.jspf" %>
<div id="principal">
	<s:property value = "getText('message.messerroubli')"/>
	<s:form action = "Emailer" method = "post" onsubmit="return verif_email();"> 
		<s:textfield key ="message.mail" name = "mail" onmousedown="changeColor();"> 
		</s:textfield> 
		<s:submit key="message.env"></s:submit> 
	
	</s:form>
			
		
		
			
		</div><!-- fin principale-->
	<%@ include file="pied.jspf" %>	