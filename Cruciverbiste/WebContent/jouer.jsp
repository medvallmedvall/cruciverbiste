<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jouer</title>
<link rel="stylesheet" type="text/css" href="styles/main.css" media="all" />
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>
	<div id="global">

	<div id="langue">
					
						<a href="#" title="Anglais"><img style="cursor:pointer; cursor:hand;" alt="English" src="images/Angleterre.png" /></a>
						<a href="#" title="Allemand"><img style="cursor:pointer; cursor:hand;" alt="Allemand" src="images/Allemagne.png" /></a>
						<a href="#" title="Espagnol"><img style="cursor:pointer; cursor:hand;" alt="Espagnol" src="images/Espagne.png" /></a>
	</div>
	<div id="entete">
	<div id="login">
	
			<form action="Connexion" method="post">
				<table>
					
					<tr>
						<td>Identifiant</td>
						<td><input type="text" name="identifiant"></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" name="motdepasse" ></td>
					</tr>
					<tr>
						<td style="text-align:right;"><a href="inscription.jsp"> <input type="button" value="Inscription" /></a></td>
						<td style="text-align:right;" ><input type="submit" value="Connexion"></td>	
					</tr>
				</table>
			</form>
		</div><!-- login-->
		
		<div id="logo">
			<a href="index.jsp">
			<h1>
				<img alt="Cruciverbiste" src="images/cruciverbiste.jpg" />
				<span>Cruciverbiste</span>
				
			</h1>
			
		</a> </div>
		<div id="pub">
		<a href="#" title="Pub"><img style="cursor:pointer; cursor:hand;" alt="English" src="images/pub.png" /></a>
		</div> <!-- fin pub-->	
		
		
	</div> <!-- fin entete-->
	
	<div id="centre">
		<div id="navigation">
			<ul>
				<li><a href="jouerMotsCroises.jsp">Mots croisés</a></li>
				<li><a href="jouerMotsFleches.jsp">Mots fléchés</a></li>
				<li><a href="index.html">Créer une grille</a></li>
				<li><a href="jeuxConcours.jsp">Jeux concours</a></li>
				<li><a href="forum.jsp">Forum</a></li>
			</ul>
		</div> <!-- fin navigation-->

		<div id="principal">
			
		<!--img alt="Grille" src="images/grille.jpg" -->
		
		
		
		<h2>${grille.nomGrille}</h2>


<!-- Création de la colonne des definitions -->

<div id="definitions">
	<div id="defHorizontales">
		<p class="titreDefinitions">Horizontalement</p>
		<c:set var="i" value="1" />
		<c:forEach var="mDef" items="${grille.motsGrille}">
			<c:if test="${mDef.orientation == 5}">
				<p class="ligne_definition">
					${mDef.coordY + 1} : ${mDef.definition} 
					<span class="rowIndex">${mDef.coordY}</span>
					<span class="colIndex">${mDef.coordX}</span>
				</p>
			</c:if>
			<c:set var="i" value="${i + 1}" />
		</c:forEach>
	</div>
	<div id="defVerticales">
		<p class="titreDefinitions">Verticalement</p>
		<c:set var="i" value="65" />
		<c:forEach var="mDef" items="${grille.motsGrille}">
			<c:if test="${mDef.orientation == 6}">
				<p class="ligne_definition">
					&#${mDef.coordX + 65}; : ${mDef.definition} 
					<span class="rowIndex">${mDef.coordY}</span> 
					<span class="colIndex">${mDef.coordX}</span>
				</p>
			</c:if>
		</c:forEach>
	</div>
</div>

<!-- Creation de la grille de mots croisés -->


<table id="grille1">

	<!-- Creation de l'entete de la grille, indexée par des lettres -->
	
	<tr>
		<th></th>
		<c:forEach var="i" step="1" begin="0" end="${grille.largeur - 1}">
			<th>&#${i + 65};</th>
		</c:forEach>
	</tr>
	
	<!-- Création de la grille -->
	
	<c:forEach var="i" begin="0" end="${grille.longueur - 1}">
		<tr>
			<th>${i + 1}</th>
			<c:forEach var="j" begin="0" end="${grille.largeur - 1}">
				<td id="${i}-${j}"></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>	
	
<script type="text/javascript" src="javascript/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascript/grilleMotsCroises.js"></script>

<!-- ajout des cases noires -->

<c:forEach var="caseNoire" items="${grille.casesNoires}">
	<script>
		var id = ${caseNoire.coordX} + "-" + ${caseNoire.coordY};
		addCaseNoire(id);
	</script>
</c:forEach>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		</div><!-- fin principale-->

		<!--  div id="secondaire">
		<form action="Rechercher" method="get">
			<table>
			  <caption>Rechercher un mot</caption>
					   <tr>
							<td><input type="text" name="motif"/></td>
							<td><input type="submit" value="OK"/></td>
					   </tr>
					   <tr>
							<td colspan="2"><textarea id="patern"></textarea></td>
					   </tr>
			</table>
		</form>	
		</div--> <!-- fin secondaire-->

	</div><!-- fin centre-->

</div><!-- fin global-->
</body>
</html>