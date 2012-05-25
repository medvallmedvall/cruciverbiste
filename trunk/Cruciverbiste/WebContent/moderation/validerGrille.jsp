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
		<div id="grilleJeuMenu">
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
		</div>
		
	
			<s:property value = "getText('message.ancienneval')"/>
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
				<textarea cols="50" rows="10" name="message"><s:property value = "getText('message.raison')"/></textarea>
			</form>	
			
		</c:if>
		
		
	
		<!-- Alert et confirm dialogue personnalise -->
		
		<div id="alertConteneur">
			<div id="alertPers">
				<p><s:property value = "getText('message.fin')"/></p>
			</div>
		</div>
		
		<div id="confirmConteneur">
			<div id="confirmPers">
				<p><s:property value = "getText('message.questObtain')"/></p>
				<div id="buttonsConfirm">
					<button class="bYes"><s:property value = "getText('message.oui')"/></button>
					<button class="bNo"><s:property value = "getText('message.non')"/></button>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="javascripts/grilles/messageBox.js"></script>
	</div>
	


<%@ include file="/pied.jspf"%>
