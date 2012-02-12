horizontal = true;

function clickGrille() {
	$("#grille1 td").click(
			function() {
				var contenuCase = $(this).children(":first");
				//si ce n'est pas une definition, ni une case qui est en train d'etre editée
				if (contenuCase.length == 0) {
					horizontal = true;
					selectionnerCase($(this));
				}
				else {
					//si c'est une case entrain d'etre éditée
					if (contenuCase.attr("id") == "caseTexte") {
						horizontal = !horizontal;
						selectionnerCase($(this));
					}
				}
				
			}
	);

	
	//corriger le selecteur
	$("p").click(
			function() {
				//on deselectionne tout
				$("p").removeClass("definitionSelectionnee");
				$(this).addClass("definitionSelectionnee");
				$("#grille1 td").removeClass("caseMotSelectionne");
				deselectionnnerCase();
				horizontal = true;
				//on recupere la direction du mot dans la balise span cachée
				var text = $(this).children("span").text();
				if (text.indexOf("right") != -1) {
					//a factoriser
					var trTag = $(this).parents("tr");
					var index = $(this).parent().index();
					var children = trTag.children("td");
					selectionnerCase(children.eq(index + 1));
				}
				if (text.indexOf("down") != -1) {
					var trTag = $(this).parents("tr");
					var indexCol = $(this).parent().index();
					var indexRow = trTag.index();
					var childrenTr = $("#grille1 tr");
					horizontal = false;
					selectionnerCase(childrenTr.eq(indexRow + 1).children("td").eq(indexCol));
				}
				
			}
	);
	$("#grille1").keyup(
			function(event) {
				var c = codeTouche(event);
				//si on a appuyé sur des fleches directionnelles
				if ((c >= 37) && (c <= 40)) {
					//horizontal = true;
					switch(c) {
						case 37:
							//left
							selectPreviousCaseH();
							break;
						case 38:
							//up
							selectPreviousCaseV();
							break;
						case 39:
							//right
							selectNextCaseH();
							break;
						case 40:
							//bottom
							selectNextCaseV();
							break;
					}
				}
				else if (c == 8) {
					//backspace
					selectPreviousCaseH();
				}
				return false;
			}
	);
	$("#grille1").keypress(
			function(event) {
				event.preventDefault();
				var c = codeTouche(event);
				var regex = /[a-zA-Z]/;
				var char = String.fromCharCode(c);
				//si c'est une lettre
				if (regex.test(char)) {
					//on efface
					$("#caseTexte").val(char);
					//on passe a la case suivante
					if (horizontal) {
						selectNextCaseH();
					}
					else {
						selectNextCaseV();
					}
					return true;
				}
				return false;
			}
	);
}


function deselectionnnerCase() {
	//on deselectionne la case et enleve le champ input
	$("#grille1 td").removeClass("caseSelectionnee");
	var letter = $("#caseTexte").val();
	$("#caseTexte").parent().text(letter);
	$("#caseTexte").remove();
}

function selectionnerCase(mCase) {
	deselectionnnerCase();
	//si on a selectionné une case qui ne fait pas parti du mots en cours de selection on deselectionne...
	//if (!mCase.hasClass("caseMotSelectionne")) {
		$("#grille1 td").removeClass("caseMotSelectionne");
		$("p").removeClass("definitionSelectionnee");
	//}
	
	if (horizontal) {
		var index = mCase.index();
		var children = mCase.parent().children("td");
		for (var i = index; i < children.length; i++) {
			var mCaseTmp = children.eq(i);
			if (mCaseTmp.children(":first").length != 0) {
				break;
			}
			mCaseTmp.addClass("caseMotSelectionne");
		}
	}
	else {
		var trTag = mCase.parents("tr");
		var indexCol = mCase.index();
		var indexRow = trTag.index();
		var childrenTr = $("#grille1 tr");
		for (var i = indexRow; i < childrenTr.length; i++) {
			var mCaseTmp = childrenTr.eq(i).children("td").eq(indexCol);
			if (mCaseTmp.children(":first").length != 0) {
				break;
			}
			mCaseTmp.addClass("caseMotSelectionne");
		}
	}
	
	
	mCase.addClass("caseSelectionnee");
	//on prend la lettre deja dans la case
	letter = mCase.text();
	mCase.text("");
	var input = "<input type='text' id='caseTexte' name='caseText' />";
	mCase.append(input);
	$("#caseTexte").val(letter);
	$("#caseTexte").focus();
	
}

function selectNextCaseH() {
	var nextElem = $("#caseTexte").parent().next();
	//si la case suivante existe et qu'elle n'est une definition
	if ((nextElem.length != 0) && (nextElem.children(":first").length == 0))  {
		selectionnerCase(nextElem);
	}
}

function selectPreviousCaseH() {
	var prevElem = $("#caseTexte").parent().prev();
	if ((prevElem.length != 0) && (prevElem.children(":first").length == 0)) {
		selectionnerCase(prevElem);
	}
}

function selectNextCaseV() {
	var idColonne =  $("#caseTexte").parent().index();
	var trTag =  $("#caseTexte").parents("tr");
	var nextElem = trTag.next("tr").children("td").eq(idColonne);
	//si la case suivante existe et qu'elle n'est pas une definition
	if ((nextElem.length != 0) && (nextElem.children(":first").length == 0))  {
		selectionnerCase(nextElem);
	}
}

function selectPreviousCaseV() {
	var idColonne =  $("#caseTexte").parent().index();
	var trTag =  $("#caseTexte").parents("tr");
	var prevElem = trTag.prev("tr").children("td").eq(idColonne);
	//si la case precedente existe et qu'elle n'est pas une definition
	if ((prevElem.length != 0) && (prevElem.children(":first").length == 0)) {
		selectionnerCase(prevElem);
	}
}


function codeTouche(event) {
	for (prop in event) {
		if (prop == "which") {
			return event.which;
		}
	}
	return event.keyCode;
}

clickGrille();