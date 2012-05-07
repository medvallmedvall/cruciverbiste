<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jouer</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="/entete.jspf"%>
		
		<div id="secondaire">
		<%@ include file="/grilles/rechercheMotif.jsp"%>
		
		</div>

	<div id="principal">
		<c:choose>
			<c:when test="${grille.idTypeGrille == 1}">
				<jsp:include page="/motsFleches.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
			<c:when test="${grille.idTypeGrille == 2}">
				<jsp:include page="/motsCroises.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
		</c:choose>
		
	
			Anciennes invalidations:
			<c:forEach var="mess" items="${messages}">
			<p>
				date: ${mess.date} <br/>
				cause: ${mess.message}
			</p>
			</c:forEach>
		
		
		<c:if test="${droit != 0}">
			<form method="post" action="validerGrille">
				<input type="submit" name="validate" value="valider la grille">
				<input type="hidden" name="action" value="validate">
				<input type="hidden" name="idGrille" value="${grille.idGrille}"/>
			</form>
			<form method="post" action="validerGrille">
				<input type="submit" name="unvalidate" value="ne pas valider"><br/>
				<input type="hidden" name="action" value="unvalidate">
				<input type="hidden" name="idGrille" value="${grille.idGrille}"/>
				<textarea cols="50" rows="10" name="message">raison de la non validation</textarea>
			</form>	
			
		</c:if>
		
		
	
		<!-- Alert et confirm dialogue personnalise -->
		
		<div id="alertConteneur">
			<div id="alertPers">
				<p>Fin de la partie !</p>
			</div>
		</div>
		
		<div id="confirmConteneur">
			<div id="confirmPers">
				<p>Voulez vous vraiment obtenir la solution?</p>
				<div id="buttonsConfirm">
					<button class="bYes">Oui</button>
					<button class="bNo">Non</button>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="javascripts/grilles/messageBox.js"></script>
	</div>
	


<%@ include file="/pied.jspf"%>
