<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value ="getText('message.validparticipation)"/></title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
</head>
<body>
	<%@ include file="entete.jspf"%>
	<div id="principal" style="width: auto; margin-left: 230px;">
		<h3><s:property value ="getText('message.participationenr)"/></h3>
		<a href ="index.jsp" ><s:property value ="getText('message.retouraccueil)"/></a>
	</div>
<%@ include file="pied.jspf"%>