<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h2>${grille.nomGrille}</h2>

<!-- Creation du menu -->

<div id="menuMotsCroises">
	<ul id="nav">
		<li><a href="#" onclick="return false;">Fichier</a>
			<ul>
				<li><a href="#" onclick="return false;">Sauvegarder</a></li>
			</ul>
		<li><a href="#">Ajouter une definition</a>
			<ul>
				<li><a href="#" onclick="addNewDefinition(1); return false;">droite</a></li>
			<li><a href="#" onclick="addNewDefinition(3); return false;">droite-bas</a></li>
			<li><a href="#" onclick="addNewDefinition(2); return false;">bas</a></li>
			<li><a href="#" onclick="addNewDefinition(4); return false;">bas-droite</a></li>
			</ul>
		</li>
		<li><a href="#">Edition de definition</a>
			<ul>
				<li><a href="#" onclick="editDefinition(); return false;">Editer</a></li>
				<li><a href="#" onclick="getSynonyms(); return false;">Obtenir synonyme</a></li>
			</ul>
		</li>
		<li>
			<a href="#" onclick="deleteDefinition(); return false;">supprimer la definition</a>
		</li>
	</ul>
	<p style="clear: both;"></p>
</div>


<!-- Creation de la grille de mots fléchés -->

<div id="synonymes">

</div>

<table id="grilleMotFleche">
	<c:forEach var="i" begin="0" end="${grille.hauteur - 1}">
		<tr>
			<c:forEach var="j" begin="0" end="${grille.largeur - 1}">
				<td id="${j}-${i}"></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>


<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/creationGrilles/mf.js"></script>
<script type="text/javascript">
<!--
	var width = ${grille.largeur};
	var height = ${grille.hauteur};
	mGrid = new GrilleMotsFleches(width, height);
//-->
</script>


<!-- Ajout des définitions dans les cases -->

<c:forEach var="mDef" items="${grille.motsGrille}">
	<script>
		var idDef = ${mDef.idDefinition};
		var textDef = "${mDef.definition}";
		var word = "${mDef.mot}";
		var synonym = "${mDef.synonyme}";
		if (synonym.toLowerCase() == textDef.toLowerCase()) {
			synonym = "";
		}
		var orientation = ${mDef.orientation};
		var x = ${mDef.coordX};
		var y = ${mDef.coordY};
		var coord = new Coord(x, y);
		mGrid.addDefinition(idDef, textDef, word, synonym, orientation, coord);
	</script>
</c:forEach>


<ul id="menuContext">
	<li><a href="#">rajouter definition</a>
		<ul>
			<li><a href="#" onclick="addOtherDefinition(1); return false;">droite</a></li>
			<li><a href="#" onclick="addOtherDefinition(3); return false;">droite-bas</a></li>
			<li><a href="#" onclick="addOtherDefinition(2); return false;">bas</a></li>
			<li><a href="#" onclick="addOtherDefinition(4); return false;">bas-droite</a></li>
		</ul>
	</li>
</ul>

<!-- Div pour le zoom -->

<div id="zoomDiv"></div>




