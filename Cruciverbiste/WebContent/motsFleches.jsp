<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>${grille.nomGrille}</h2>


<!-- Creation de la grille de mots fléchés -->


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
<script type="text/javascript" src="javascripts/grilleMotsFleches.js"></script>


<!-- Ajout des définitions dans les cases -->

<c:forEach var="mDef" items="${grille.motsGrille}">
	<script>
		var idCase = ${mDef.coordX} + "-" + ${mDef.coordY};
		var direction = ${mDef.orientation};
		var textDef = "${mDef.definition}";
		addDefinition(idCase, direction, textDef);
	</script>
</c:forEach>



