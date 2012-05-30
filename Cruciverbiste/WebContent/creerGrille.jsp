<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value= "getText('message.creer')"/></title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
</head>
<body onload="clearArea();">

	<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
	<%@ include file="entete.jspf"%>
	
	<div id="rechercheMotDiv">
		<%@ include file="grilles/rechercheMotif.jsp"%>
	</div>

		<div id="principal" style="width: auto; margin-left: 230px;">
		<h1><s:property value= "getText('message.creer')"/></h1>
		
		<c:choose>
			<c:when test="${grille.idGrille == -1}">
				<div>
					<form action="creerGrille" method="post" onsubmit = "return checkCreationForm();">
					<table>
						<tr>
							<td><label for="nomGrille"><s:property value= "getText('message.nomG')"/></label> </td>
							<td><input type="text" name="grille.nomGrille" id="nomGrille" value="${grille.nomGrille}" maxlength="50" ${disabled}> </td>
						</tr>
						<tr>
							<td><label for="hauteurGrille"><s:property value= "getText('message.nbLignes')"/></label> </td>
							<td><input type="text" name="grille.hauteur" id="hauteurGrille" maxlength="2" size="1" value="${grille.hauteur}" ${disabled}> </td>
						</tr>
						<tr>
							<td><label for="largeurGrille"><s:property value= "getText('message.nbCol')"/></label>  </td>
							<td><input type="text" name="grille.largeur" id="largeurGrille" maxlength="2" size="1" value="${grille.largeur}" ${disabled}> </td>
						</tr>
						<tr>
							<td><s:property value= "getText('message.langue')"/> </td>
							<td><select name="grille.idLangue" ${disabled}>
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
						</select> </td>
						</tr>
						<tr>
							<td><s:property value= "getText('message.theme')"/> </td>
							<td><select name="grille.idTheme" ${disabled}>
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
						</select> </td>
						</tr>
						<tr>
						<input type="hidden" name="creerGrille" value="true" />
						<input type="hidden" name="idTypeGrille" value="${idTypeGrille}" />
						</tr>
						<tr>
							<c:if test="${empty disabled}">
							<td><s:submit key="message.cre" name="submit" > </s:submit> </td>
							</c:if>
						</tr>
					</table>
						
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
		<li><a href="#"><s:property value ="getText('message.ajout')"/></a>
	<ul>
		<li><a href="#" onclick="addOtherDefinition(1); return false;"><s:property value ="getText('message.droite')"/></a></li>
		<li><a href="#" onclick="addOtherDefinition(3); return false;"><s:property value ="getText('message.droiteb')"/></a></li>
		<li><a href="#" onclick="addOtherDefinition(2); return false;"><s:property value ="getText('message.bas')"/></a></li>
		<li><a href="#" onclick="addOtherDefinition(4); return false;"><s:property value ="getText('message.basd')"/></a></li>
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
ïalert("Le nombre de ligne doit être un nombre");
ï$("#hauteurGrille").focus();
 return false;
}
if (isNaN(colNb)) {
alert("Le nombre de colonne doit être un nombre");
$("#largeurGrille").focus();
 return false;
}
if ((rowNb < 2 ) || (rowNb > 20)) {
 alert("Le nombre de ligne doit être entre 2 et 20");
ï $("#hauteurGrille").focus();
ï return false;
}
if ((colNb < 2 ) || (colNb > 15)) {
alert("Le nombre de colonne doit être entre 2 et 15");
$("#largeurGrille").focus();
 return false;
}
 return true;
}
</script>

<script type="text/javascript">
	var navHeight = $("#navmenu").height() + 200;
	$("#rechercheMotDiv").css("top", navHeight + "px");
</script>

<script type= "text/javascript">
	//pour l'appli de recherche de mots
	function copyArea() {
		if ($("#motif").val() == "") {
			alert("Vous devez rentrer un motif de plus de 2 caractères");
			$("#motif").focus();
			return false();
		}
		var motif = $("#motif").val();

		if (motif.indexOf("?") == -1) {
			alert("Votre motif doit comporter au moins un caractère ?");
			$("#motif").focus();
			return false();
		}
		var idGrille = ${grille.idGrille};
		var params = "idGrille=" + idGrille + "&motif=" + motif;
		$.ajax({
			url : "rechercherMot",
			type : 'POST',
			cache : false,
			data : params,
			success : function(content) {
				$.ajax({
					url : "grilles/rechercheMotif.jsp",
					cache : false,
					success : function(contenu1) {
						$("#rechercheMotDiv").html(contenu1);
					},
					error : function() {
						alert("erreur...")
					}
				}).done(function() {
					//alert("fin");
				});
			},
			error : function() {
				alert("Une erreur : ");
			}
		});
	}
	function clearArea() {
	    document.getElementById("motif").value ="";
	    document.getElementById("resultArea").options.length = 0;
		
	}
</script>

<%@ include file="pied.jspf"%>