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
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->

	<div id="principal">
		<c:choose>
			<c:when test="${grille.idTypeGrille == 1}">
				<jsp:include page="motsFleches.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
			<c:when test="${grille.idTypeGrille == 2}">
				<jsp:include page="motsCroises.jsp">
					<jsp:param name="grille" value="${grille}"/>
				</jsp:include>
			</c:when>
		</c:choose>
		
		
		<!-- Zone de commentaires -->
		
		
		<script type="text/javascript">
			//pour les commentaires
			function sendComment() {
				if ($("#commentaireArea").val() == "") {
					alert("Veuillez remplir le champ pour poster");
					$("#commentaireArea").focus();
					return false;
				}
				var idGrille = ${grille.idGrille};
				var content = $("#commentaireArea").val();
				var params = "idGrille=" + idGrille + "&commentaire=" + content;
				$.ajax({
					url: "posterCommentaire",
					type: 'POST',
					cache: false,
					data: params,
					success: function(contenu) {
						//chargement du contenu du fichier pour le menu
						$.ajax({
							url : "grilles/commentaires.jsp",
							cache : false,
							success : function(contenu1) {
								$("#commentaires").html(contenu1);
							},
							error : function() {alert("erreur...")}
						}).done(function() {
							//alert("fin");
						});
					},
					error: function() {
						alert("Une erreur : ");
					}
				});
			}
		</script>
		<div id="commentaires">
			<%@ include file="grilles/commentaires.jsp" %>
		</div>
		<!-- Creation du menu contextuel -->

		<ul id="menuContext">
			<li>
				<a href="#">Verifier</a>
				<ul>
					<li><a href="#" onclick="checkLetter(); return false;">La lettre</a></li>
					<li><a href="#" onclick="checkWord(); return false;">Le mot</a></li>
				</ul>
			</li>
			<li>
				<a href="#">Obtenir</a>
				<ul>
					<li><a href="#" onclick="getLetter(); return false;">La lettre</a></li>
					<li><a href="#" onclick="getWord(); return false;">Le mot</a></li>
				</ul>
			</li>
			<li>
				<a href="#" onclick="getSynonym(); return false;">Synonyme</a>
			</li>
			<li>
				<a href="#" onclick="return false;">Effacer</a>
				<ul>
					<li><a href="#" onclick="deleteLetter(); return false;">La lettre</a></li>
					<li><a href="#" onclick="deleteWord(); return false;">Le mot</a></li>
					<li><a href="#" onclick="deleteAll(); return false;">La grille</a></li>
				</ul>
			</li>
			<li>
				<a href="#"  onclick="getSolution(); return false;">Solution</a>
			</li>
		</ul> 
	
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
	


<%@ include file="pied.jspf"%>