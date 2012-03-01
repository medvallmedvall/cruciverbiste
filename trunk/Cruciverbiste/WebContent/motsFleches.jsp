<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jouer mots fleches</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

<h2>${grille.nomGrille}</h2>


<!-- Creation de la grille de mots fléchés -->


<table id="grille1">
	<c:forEach var="i" begin="0" end="${grille.longueur - 1}">
		<tr>
			<c:forEach var="j" begin="0" end="${grille.largeur - 1}">
				<td id="${i}-${j}"></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>	


<!-- Ajout des définitions dans les cases -->

<c:forEach var="mDef" items="${grille.motsGrille}">
	<script>
		var idCase = ${mDef.coordX} + "-" + ${mDef.coordY};
		var direction = ${mDef.orientation};
		//var textDef = "${mDef.definition}";
		addDefinition(idCase, direction, "textDef");
	</script>
</c:forEach>
	
	
	
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/grilleMotsFleches.js"></script>


</body>
</html>

