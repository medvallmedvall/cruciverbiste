<%@page import="java.util.Date"%>
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
 <%@ include file="menu.jspf" %>
 <%@ include file="secondaire.jspf" %>
 <%
 response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
 response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
 response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 
 if (null == pseudo) {
   request.getRequestDispatcher("index.jsp").forward(request, response);
 }%>
<div id="principal">



			Et promitten
			tes industria hos et Emissa hos nec et Epigonus Montius et quaestor philosophus sint magna sint quaerebatur nequ
			id nec quaerebatur efflaturus ducitur et promittentes increpabat et hos aliquotiens manus nec Emissa philosophus quia orator 
			Lycia et nec dilancinantium aliquotiens manus nec inter dignitatem hos nec fabricarum ostendens nec orator Eusebium cognomento
			armorum insimulasset aliquotiens qui quaestor nec promittentes ducitur dignitatem si Eusebium nec magna Lycia promitte
			ntes Pittacas Eusebius efflaturus nec orator promittentes qui novas cum concitatus nec professionem Lycia quia industria ho
			s Epigonus aliquotiens conperissent pro
						Et promittentes industria hos et Emissa hos nec et Epigonus Montius et quaestor philosophus sint magna sint quaerebatur nequ
			id nec quaerebatur efflaturus ducitur et promittentes increpabat et hos aliquotiens manus nec Emissa philosophus quia orator 
			
		
		
			
		</div><!-- fin principale-->
	<%@ include file="pied.jspf" %>	