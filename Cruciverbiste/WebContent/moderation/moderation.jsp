<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moderation</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
</head>
<body>

	<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="/entete.jspf"%>

	<div id="principal">

		<c:choose>
			<c:when test="${authentification && droit != 0}">
				<h1>Moderation</h1>
				<s:actionmessage/>
				<h2>Grilles à valider</h2>
				<table>
					<caption>Mots fléchés</caption>
					<tr>
						<th>Nom</th>
						<th>Date creation</th>
						<th>Visualiser</th>
					</tr>
					<c:forEach var="g" items="${grilleAValiderMF}">
						<tr>
							<td>${g.nomGrille}</td>
							<td>${g.dateCreation }</td>
							<td><a href="tester?action=test&idGrille=${g.idGrille}">tester</a></td>
						</tr>
					</c:forEach>
				</table>
				<table>
					<caption>Mots croisés</caption>
					<tr>
						<th>Nom</th>
						<th>Date creation</th>
						<th>Visualiser</th>
					</tr>
					<c:forEach var="g" items="${grilleAValiderMC}">
						<tr>
							<td>${g.nomGrille}</td>
							<td>${g.dateCreation }</td>
							<td><a href="tester?action=test&idGrille=${g.idGrille}">tester</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>Vous n'avez pas access à cette page</p>
			</c:otherwise>
		</c:choose>

	</div>
	<%@ include file="/pied.jspf"%>