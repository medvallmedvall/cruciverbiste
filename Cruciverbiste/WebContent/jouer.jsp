<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="getText('message.jouer')"/></title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body onload = "loadGrille();clearArea(); " >
	
	<%@ include file="entete.jspf"%>
	<c:if test = "${!grille.isForConcours()}">
		<div id="rechercheMotDiv">
		<s:actionerror/>
			<%@ include file="grilles/rechercheMotif.jsp"%>
		</div>
		
	</c:if>
	<div id="principal" style="width: auto; margin-left: 230px;">
		<div id="grilleJeuMenu">
			<c:choose>
				<c:when test="${grille.idTypeGrille == 1}">
					<jsp:include page="/motsFleches.jsp">
						<jsp:param name="grille" value="${grille}" />
					</jsp:include>
				</c:when>
				<c:when test="${grille.idTypeGrille == 2}">
					<jsp:include page="/motsCroises.jsp">
						<jsp:param name="grille" value="${grille}" />
					</jsp:include>
				</c:when>
			</c:choose>
		</div>
		
		<!-- Zone de commentaires -->
		
		<script type="text/javascript">
			var size = ${listMots.size()};
		</script>

		<script type="text/javascript">
		
			//var size = ${listMots.size()};
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
								alert("erreur: Aucun mot ne correspond à votre motif dans notre dictionnaire")
							}
						}).done(function() {
							//alert("fin");
						});
					},
					error : function() {
						alert("Une erreur : Aucun mot ne correspond à votre motif dans notre dictionnaire");
					}
				});
			}
			//pour les commentaires
			function sendComment() {
				if ($("#commentaireArea").val() == "") {
					alert("Veuillez remplir le champ pour poster");
					$("#commentaireArea").focus();
					return false;
				}
				return true;
			}
			function clearArea() {
			    document.getElementById("motif").value ="";
			    document.getElementById("resultArea").options.length = 0;
				
			}
				
		</script>
		<c:choose>
			<c:when test = "${!grille.isForConcours()}">
				<div id="commentaires">
					<%@ include file="grilles/commentaires.jsp" %>
				</div>
					<!-- Creation du menu contextuel -->

		<ul id="menuContext">
			<li>
				<a href="#"><s:property value="getText('message.verif')"/></a>
				<ul>
					<li><a href="#" onclick="checkLetter(); return false;"><s:property value="getText('message.lettre')"/></a></li>
					<li><a href="#" onclick="checkWord(); return false;"><s:property value="getText('message.mot')"/></a></li>
				</ul>
			</li>
			<li>
				<a href="#"><s:property value="getText('message.obt')"/></a>
				<ul>
					<li><a href="#" onclick="getLetter(); return false;"><s:property value="getText('message.lettre')"/></a></li>
					<li><a href="#" onclick="getWord(); return false;"><s:property value="getText('message.mot')"/></a></li>
				</ul>
			</li>
			<li>
				<a href="#" onclick="getSynonym(); return false;"><s:property value="getText('message.syn')"/></a>
			</li>
			<li>
				<a href="#" onclick="return false;"><s:property value="getText('message.eff')"/></a>
				<ul>
					<li><a href="#" onclick="deleteLetter(); return false;"><s:property value="getText('message.lettre')"/></a></li>
					<li><a href="#" onclick="deleteWord(); return false;"><s:property value="getText('message.mot')"/></a></li>
					<li><a href="#" onclick="deleteAll(); return false;"><s:property value="getText('message.grille')"/></a></li>
				</ul>
			</li>
			<li>
				<a href="#"  onclick="getSolution(); return false;"><s:property value="getText('message.solution')"/></a>
			</li>
		</ul> 
	
		<!-- Alert et confirm dialogue personnalise -->
		
		<div id="alertConteneur">
			<div id="alertPers">
				<p><s:property value="getText('message.fin')"/></p>
			</div>
		</div>
		
		<div id="confirmConteneur">
			<div id="confirmPers">
				<p><s:text name="message.questobtain"/></p>
				<div id="buttonsConfirm">
					<button class="bYes"><s:text name = "message.oui"/></button>
					<button class="bNo"><s:text name = "message.non"/></button>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="javascripts/grilles/messageBox.js"></script>
				
			</c:when>
			<c:otherwise>
				<s:form action="validerJeuconcours" method="post">
					<s:submit value="Valider" label="Valider" style="width: 100px;"> 
					</s:submit>
				</s:form>
			</c:otherwise>
			
		</c:choose>
		
		
	
	</div>
	


<%@ include file="pied.jspf"%>
