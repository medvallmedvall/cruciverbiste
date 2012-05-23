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
	
	<div id="principal" style="width: auto; margin-left: 230px;">
		<h1>Creation de grille</h1>
		
		<c:choose>
			<c:when test="${grille.idGrille == -1}">
				<div>
					<form action="creerGrille" method="post" onsubmit="return checkCreationForm();">
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
			</c:when>
			<c:otherwise>			
				<div id="grilleJeuMenu">
					<c:choose>
						<c:when test="${grille.idTypeGrille == 1}">
							<jsp:include page="creationGrille/creerMF.jsp">
								<jsp:param name="grille" value="${grille}"/>
								<jsp:param name="themes" value="${nomTheme}"/>
								<jsp:param name="langues" value="${nomLangue}"/>
							</jsp:include>
						</c:when>
						<c:when test="${grille.idTypeGrille == 2}">
							<jsp:include page="creationGrille/creerMC.jsp">
								<jsp:param name="grille" value="${grille}"/>
								<jsp:param name="themes" value="${themes}"/>
								<jsp:param name="langues" value="${langues}"/>
							</jsp:include>
						</c:when>
					</c:choose>
				</div>
			</c:otherwise>
		</c:choose>
		<s:actionerror />
		<ul id="menuContext">
					<li><a href="#">Ajouter definition</a>
						<ul>
							<li><a href="#" onclick="addOtherDefinition(1); return false;">droite</a></li>
							<li><a href="#" onclick="addOtherDefinition(3); return false;">droite-bas</a></li>
							<li><a href="#" onclick="addOtherDefinition(2); return false;">bas</a></li>
							<li><a href="#" onclick="addOtherDefinition(4); return false;">bas-droite</a></li>
						</ul>
					</li>
				</ul>
	</div>
	
	<script type="text/javascript">
		function checkCreationForm() {
			if ($("#nomGrille").val() == "") {
				alert("Le nom de la grille est vide");
				$("#nomGrille").focus();
				return false;
			}
			var rowNb = parseInt($("#hauteurGrille").val());
			var colNb = parseInt($("#largeurGrille").val());
			if (isNaN(rowNb)) {
				alert("Le nombre de ligne doit être un nombre");
				$("#hauteurGrille").focus();
				return false;
			}
			if (isNaN(colNb)) {
				alert("Le nombre de colonne doit être un nombre");
				$("#largeurGrille").focus();
				return false;
			}
			if ((rowNb < 2 ) || (rowNb > 20)) {
				alert("Le nombre de ligne doit être entre 2 et 20");
				$("#hauteurGrille").focus();
				return false;
			}
			if ((colNb < 2 ) || (colNb > 15)) {
				alert("Le nombre de colonne doit être entre 2 et 15");
				$("#largeurGrille").focus();
				return false;
			}
			return true;
		}
	</script>
		

	<%@ include file="pied.jspf"%>