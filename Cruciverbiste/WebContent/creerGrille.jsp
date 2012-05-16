<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creer grille</title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
</head>
<body>

	<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="entete.jspf"%>
	
	<div id="principal">
		<h1>Creation de grille</h1>
		
		
		<c:set var="grilleExist" value="${grille.idGrille != -1}"/>
		<c:if test="${grille.idGrille != -1}">
			<c:set var="disabled" value="disabled='disabled'"/>
		</c:if>
		<s:actionerror />
		<div>
			<form action="creerGrille" method="post">
				<label for="nomGrille">Nom de la grille: </label> 
				<input type="text" name="grille.nomGrille" id="nomGrille" 
				value="${grille.nomGrille}" maxlength="50" ${disabled}><br />
				<label for="hauteurGrille">Nombre de lignes: </label>
				<input type="text" name="grille.hauteur" id="hauteurGrille" maxlength="2"
				size="1" value="${grille.hauteur}" ${disabled}><br /> 
				<label for="largeurGrille">Nombre de colonnes: </label> 
				<input type="text" name="grille.largeur" id="largeurGrille" maxlength="2" size="1"
				value="${grille.largeur}" ${disabled}><br /> 
				Langue: 
				<select name="grille.idLangue" ${disabled}>
					<c:forEach var="l" items="${langues}">
						<c:choose>
							<c:when test="${grille.idLangue == l.idLangue}">
								<option value="${l.idLangue}" selected="selected">
									${l.nomLangue}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${l.idLangue}">${l.nomLangue}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <br />
				Theme:
				<select name="grille.idTheme" ${disabled}>
					<c:forEach var="t" items="${themes}">
						<c:choose>
							<c:when test="${grille.idTheme == t.idTheme}">
								<option value="${t.idTheme}" selected="selected">
									${t.nomTheme}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${t.idTheme}">${t.nomTheme}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<input type="hidden" name="creerGrille" value="true" />
				<input type="hidden" name="idTypeGrille" value="${idTypeGrille}" /> 
				<c:if test="${empty disabled}">
					<input type="submit" name="submit" value="creer la grille" />
				</c:if>
			</form>
		</div>
		<c:choose>
			<c:when test="${grille.idTypeGrille == 1}">
				<jsp:include page="creationGrille/creerMF.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
			<c:when test="${grille.idTypeGrille == 2}">
				<jsp:include page="creationGrille/creerMC.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
		</c:choose>

	</div>

	<%@ include file="pied.jspf"%>