<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<% Map<String, Object> ses= ActionContext.getContext().getSession();%>

<h2>${grille.nomGrille}</h2>

<!-- Creation du menu -->

<div id="menuMotsCroises">
	<ul id="nav">
		<li>
			<a href="#" onclick="return false;"><s:property value="getText('message.fichier')"/></a>
			<ul>
				<li><a href="#" name="save" id="save" onclick="saveGrid(); return false;"><s:property value="getText('message.sauver')"/></a></li>
			</ul>
		<li>
			<a href="#"><s:property value="getText('message.aide')"/></a>
			<ul>
				<li>
					<a href="#" onclick="return false;"><s:property value="getText('message.verif')"/></a>
					<ul>
						<li><a href="#" onclick="checkLetter(); return false;"><s:property value="getText('message.lettre')"/></a></li>
						<li><a href="#" onclick="checkWord(); return false;"><s:property value="getText('message.mot')"/></a></li>
					</ul>
				</li>
				<li>
					<a href="#" onclick="return false;"><s:property value="getText('message.obt')"/></a>
					<ul>
						<li><a href="#" onclick="getLetter(); return false;"><s:property value="getText('message.lettre')"/></a></li>
						<li><a href="#" onclick="getWord(); return false;"><s:property value="getText('message.mot')"/></a></li>
					</ul>
				</li>
				
				<li><a href="#" onclick="getSynonym(); return false;"><s:property value="getText('message.syn')"/></a></li>
			</ul>
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
			<a href="#" onclick="getSolution(); return false;"><s:property value="getText('message.solution')"/></a>
		</li>
		<li>
			<a href="telechargerGrille?idGrille=${grille.idGrille}"><s:property value="getText('message.tel')"/></a>
		</li>
	</ul>
	<p style="clear: both;"></p>
</div>

<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<script type="text/javascript" src="javascripts/grilles/mc.js"></script>
<script type="text/javascript" src="javascripts/grilles/messageBox.js"></script>
<script type="text/javascript">
<!--
	var width = ${grille.largeur};
	var height = ${grille.hauteur};
	mGrid = new GrilleMotsCroises(width, height);
	var nameGrid="${grille.nomGrille}";
//-->
</script>

<script type="text/javascript">
<!--
	/* loadGrille */
	function loadGrille() {
		var loadList="<%= ses.get("listLettre").toString() %>";
		var session=<%=ses.containsKey("idUser")%>;
		if(loadList!=""){
			var tabLoadListe=new Array();
			tabLoadListe=stringToTab(loadList);
			var indice=0;
			for ( var i = 0; i <tabLoadListe.length; i++) {
				laLettre=tabLoadListe[i];
				indice=i+1;
				x=tabLoadListe[indice];
				indice=i+2;
				y=tabLoadListe[indice];
				var mIdCase = x + "-" + y; 
				$("#" + mIdCase).text(laLettre);
			}
		}if(!session){
			if(readCookie(nameGrid) != null){
				var tabLoadListe=new Array();
				var loadList=readCookie(nameGrid);
				tabLoadListe=stringToTabCookie(loadList);
				var indice=0;
				for ( var i = 0; i <tabLoadListe.length; i++) {
					laLettre=tabLoadListe[i];
					indice=i+1;
					x=tabLoadListe[indice];
					indice=i+2;
					y=tabLoadListe[indice];
					var mIdCase = x + "-" + y; 
					$("#" + mIdCase).text(laLettre);
				}
			}
		}
	}
//-->
</script>

<script type="text/javascript">
<!--
function saveGrid() {
	var session=<%=ses.containsKey("idUser")%>
	var listeLettre = new Array();
	listeLettre = sauvegarder();
	if(session==true){
		if ($(listeLettre).val() == "") {
			alert("rien � sauvegarder");
			return false;
		}
		var idGrille = ${grille.idGrille};
		var params = "idGrille=" + idGrille + "&listeLettre=" + listeLettre; /*+ "&commentaire=" + content*/
		$.ajax({
			url: "Sauvegarder",
			type: 'POST',
			cache: false,
			data: params,
			success : function(contenu) {
				alert(contenu);
		},
			error : function() {alert("erreur...")}
		});
	}else{
		createCookie(nameGrid,listeLettre,1);
		alert("La Grille a �t� sauvegard�e avec succ�s");
	}
}
//-->
</script>

<!-- Cr�ation de la colonne des definitions -->

<div id="definitions">
	<div id="defHorizontales">
		<p class="titreDefinitions"><s:property value ="getText('message.horizon')"/></p>
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
		<p class="titreDefinitions"><s:property value ="getText('message.vertical')"/></p>
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

<!-- Creation de la grille de mots crois�s -->


<table id="grille1">

	<!-- Creation de l'entete de la grille, index�e par des lettres -->
	
	<tr>
		<th></th>
		<c:forEach var="i" step="1" begin="0" end="${grille.largeur - 1}">
			<th>&#${i + 65};</th>
		</c:forEach>
	</tr>
	
	<!-- Cr�ation de la grille -->
	
	<c:forEach var="i" begin="0" end="${grille.hauteur - 1}">
		<tr>
			<th>${i + 1}</th>
			<c:forEach var="j" begin="0" end="${grille.largeur - 1}">
				<td id="${j}-${i}"></td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>