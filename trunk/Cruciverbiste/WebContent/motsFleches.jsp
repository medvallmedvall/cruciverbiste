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
<script type="text/javascript" src="javascripts/grilles/mf.js"></script>
<script type="text/javascript">
<!--
	var width = ${grille.largeur};
	var height = ${grille.hauteur};
	mGrid = new GrilleMotsFleches(width, height);
	//pour les commentaires
	function checkCommentArea() {
		if ($("#commentArea").val() == "") {
			alert("Veuillez remplir le champ pour poster");
			$("#commentArea").focus();
			return false;
		}
		return true;
	}
	
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

<!-- Creation des commentaires -->



<div id="commentaires">
	<h3>Commentaires : </h3>
	<s:actionerror/>
	<c:if test="${empty grille.commentaires}">Il n'y a aucun commentaire</c:if>
	<c:forEach var="mComm" items="${grille.commentaires}">
		<p class="commentaire">
			<b>${mComm.pseudo}</b> a ecrit le ${mComm.dateFormatee} : <br/>
			<em>${mComm.contenu}</em>
		</p>
	</c:forEach>
	<c:choose>
		<c:when test="${authentification == true}">
			<div id="logMessage">
				Poster un commentaire : <br/>
				<s:form action="posterCommentaire" method="post" onsubmit="return checkCommentArea();">
					<s:textarea id="commentaireArea" name="commentaire" label="Votre commentaire: " required="true" cols="44" rows="10"></s:textarea>
					<s:hidden name="idGrille" value="%{grille.idGrille}"></s:hidden>
					<s:submit name="submit" value="poster"></s:submit>
				</s:form>
			</div>
		</c:when>
		<c:otherwise>
			<p id="logMessage">Vous devez vous connecter pour pouvoir poster un commentaire!</p>
		</c:otherwise>
	</c:choose>
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

<!-- Div pour le zoom -->

<div id="zoomDiv">

</div>

<!-- Alert personnalise -->

<div id="alertConteneur">
	<div id="alertPers">
		<p>Fin de la partie !</p>
	</div>
</div>



