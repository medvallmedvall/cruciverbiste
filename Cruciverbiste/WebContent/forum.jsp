<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forum </title>
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>

<%@ include file="entete.jspf"%>
<!--%@ include file="menu.jspf" %-->

<div id="principal">

<c:if test="${empty urlSujetsRecents}"> <c:set var = "urlSujetsRecents" value = "http://localhost/phpBB3/index.php"/></c:if>
<IFRAME SRC= ${urlSujetsRecents} id="forumFrame" width="95%" height="780" scrolling="no" align="middle">
</IFRAME>
</div>
<%@ include file="pied.jspf" %>	