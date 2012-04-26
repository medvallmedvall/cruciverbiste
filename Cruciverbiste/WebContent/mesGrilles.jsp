<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes grilles</title>
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="entete.jspf"%>
	<div id="principal">
		<h1>Mes grilles</h1>
		<table>
			
			<tr>
				<th>Nom grille</th>
				<th>Type</th>
				<th>Date de creation</th>
				<th>Est finie</th>
				<th>Date validation</th>
				<th>Visualiser</th>
			</tr>
			<c:forEach var="grille" items="${mesGrilles}">
				<tr>
					<td>${grille.nomGrille}</td>
					<td>
						<c:choose>
							<c:when test="${grille.idTypeGrille == 1}"> Mot fléché</c:when>
							<c:when test="${grille.idTypeGrille == 2}"> Mot croisé</c:when>
						</c:choose>
					</td>
					<td>${grille.dateCreationF}</td>
					<td>
						<c:choose>
							<c:when test="${grille.estFinie}">Oui</c:when>
							<c:when test="${!grille.estFinie}">Non</c:when>
						</c:choose>
					</td>
					<td>${grille.dateValidationF}</td>
					<td>
						<c:choose>
							<c:when test="${grille.estFinie}">
								<a href="jouer?idGrille=${grille.idGrille}">Jouer</a>
							</c:when>
							<c:when test="${!grille.estFinie}">
								<a href="creerGrille?idGrille=${grille.idGrille}">Reprendre la creation</a>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	
	<%@ include file="pied.jspf"%>