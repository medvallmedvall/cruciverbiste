<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<h2>${grille.nomGrille}</h2>

<!-- Creation du menu -->

<div id="menuMotsCroises">
	<ul id="nav">
		<li>
			<a href="#" onclick="return false;">Fichier</a>
			<ul>
				<li><a href="#" onclick="return false;">Sauvegarder</a></li>
			</ul>
		<li>
			<a href="#">Aide</a>
			<ul>
				<li>
					<a href="#" onclick="return false;">Verifier</a>
					<ul>
						<li><a href="#" onclick="checkLetter(); return false;">La lettre</a></li>
						<li><a href="#" onclick="checkWord(); return false;">Le mot</a></li>
					</ul>
				</li>
				<li>
					<a href="#" onclick="return false;">Obtenir</a>
					<ul>
						<li><a href="#" onclick="getLetter(); return false;">La lettre</a></li>
						<li><a href="#" onclick="getWord(); return false;">Le mot</a></li>
					</ul>
				</li>
				
				<li><a href="#" onclick="getSynonym(); return false;">Synonyme</a></li>
			</ul>
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
			<a href="#" onclick="getSolution(); return false;">Solution de la grille</a>
		</li>
	</ul>
	<p style="clear: both;"></p>
</div>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/creationGrilles/mc.js"></script>
<script type="text/javascript" src="javascripts/grilles/messageBox.js"></script>
<script type="text/javascript">
<!--
	var width = ${grille.largeur};
	var height = ${grille.hauteur};
	mGrid = new GrilleMotsCroises(width, height);
//-->
</script>


<!-- Création de la colonne des definitions -->

<div id="definitions">
	<div id="defHorizontales">
		<p class="titreDefinitions">Horizontalement</p>
		<c:set var="i" value="1" />
		<c:forEach var="mDef" items="${grille.motsGrille}">
			<c:if test="${mDef.orientation == 5}">
				<p class="ligne_definition" id="${mDef.idDefinition}">
					${mDef.coordY + 1} : ${mDef.definition} 
					<script type="text/javascript">
						var idDef = ${mDef.idDefinition};
						var word = "${mDef.mot}";
						var synonym = "${mDef.synonyme}"; 
						var x = ${mDef.coordX}
						var y = ${mDef.coordY};
						var coord = new Coord(x, y);
						mGrid.addDefinition(idDef, word, synonym, coord, 0);
					</script>
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
				<p class="ligne_definition" id="${mDef.idDefinition}">
					&#${mDef.coordX + 65}; : ${mDef.definition} 
					<script type="text/javascript">
						var idDef = ${mDef.idDefinition};
						var word = "${mDef.mot}";
						var synonym = "";
						var x = ${mDef.coordX}
						var y = ${mDef.coordY};
						var coord = new Coord(x, y);
						mGrid.addDefinition(idDef, word, synonym, coord, 1);
					</script>
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