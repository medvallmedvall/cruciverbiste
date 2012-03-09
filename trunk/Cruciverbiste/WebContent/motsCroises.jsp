<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	<c:forEach var="i" begin="0" end="${grille.hauteur - 1}">
		<tr>
			<th>${i + 1}</th>
			<c:forEach var="j" begin="0" end="${grille.largeur - 1}">
				<td id="${j}-${i}"></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>	

<!-- Creation du menu contextuel -->

<ul id="mContextMenu" class="contextMenu">
	<li><a href="#getLetter">Lettre</a></li>
	<li><a href="#getWord">Mot</a></li>
	<li><a href="#getSynonym">Synonyme</a></li>
	<li><a href="#getSolution">Solution</a></li>
</ul>
	
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/grilleMotsCroises.js"></script>
<script type="text/javascript" src="javascripts/jquery.contextMenu.js"></script>

<!-- ajout des cases noires -->

<c:forEach var="caseNoire" items="${grille.casesNoires}">
	<script>
		//var id = ${caseNoire.coordX} + "-" + ${caseNoire.coordY};
		var id = ${caseNoire.coordY} + "-" + ${caseNoire.coordX};
		addCaseNoire(id);
	</script>
</c:forEach>

<!-- ajout des lettres (cachees) -->

<c:forEach var="mDef" items="${grille.motsGrille}">
	<script type="text/javascript">
		var x = ${mDef.coordX};
		var y = ${mDef.coordY};
		var word = "${mDef.mot}";
		var orientation = ${mDef.orientation};
		addWord(x, y, word, orientation);
	</script>
</c:forEach>
