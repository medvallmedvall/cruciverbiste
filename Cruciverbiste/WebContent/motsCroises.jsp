<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test mots croises</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

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
		<p class="titreDefinitions">Horizontalement</p>
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
	
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/grilleMotsCroises.js"></script>

<!-- ajout des cases noires -->

<c:forEach var="caseNoire" items="${grille.casesNoires}">
	<script>
		var id = ${caseNoire.coordX} + "-" + ${caseNoire.coordY};
		addCaseNoire(id);
	</script>
</c:forEach>


</body>
</html>

