function addDefinition(idCase, direction, textDef) {
	var mSelector = "#" + idCase;
	var mCase = $(mSelector);
	var mElement = "<p class='definition'>" + textDef + "<span>" + direction + "</span></p>";
	mCase.append(mElement);
	var defNb = mCase.children("p").length;
	var heigthCase = 100 / defNb;
	mCase.children("p").css("height", heigthCase + "%");
	var tab = idCase.split('-');
	var x = tab[0];
	var y = tab[1];
	var urlImage = "";
	switch(direction) {
		case 1:
			//droite
			x++;
			urlImage = "url('images/grilles/droite.png')";
			break;
		case 2:
			//bas
			y++;
			urlImage = "url('images/grilles/bas.png')";
			break;
		case 3:
			//droite-bas
			x++;
			urlImage = "url('images/grilles/droite-bas.png')";
			break;
		case 4:
			//bas-droite
			y++;
			urlImage = "url('images/grilles/bas-droite.png')";
			break;
	
		default:
			//erreur
			break;
	}
	var selector = "#" + x + "-" + y;
	var firstCase = $(selector);
	firstCase.css("background-image", urlImage).css("background-repeat", "no-repeat");
	//selectionnerCase(firstCase);
}


$("#grilleMotFleche td").click(
		function() {
			var contenuCase = $(this).children(":first");
			//si ce n'est pas une definition, ni une case qui est en train d'etre editée
			if (contenuCase.length == 0) {
				horizontal = false;
				if (canSwitchOrientation($(this))) {
					horizontal = !horizontal;
				}
				selectionnerCase($(this));
			}
			else {
				//si c'est une case entrain d'etre éditée
				if (contenuCase.attr("id") == "caseTexte") {
					if (canSwitchOrientation($(this))) {
						horizontal = !horizontal;
						selectionnerCase($(this));
					}
				}
			}

		}
);

/*Fon qui verifie si la case suivante peut être selectionné (pour le changement de sens)*/

function canSwitchOrientation(mCase) {
	var mIdString = mCase.attr("id");
	var mTab = mIdString.split("-");
	var x = mTab[0];
	var y = mTab[1];
	var mPrevCase;
	var mNextCase;
	if (horizontal) {
		//on regarde si il y a une case au dessus et en dessous
		y--;
		mPrevCase = $("#" + x + "-" + y);
		y+= 2;
		mNextCase = $("#" + x + "-" + y);
	}
	else {
		//on regarde si il y a une case avant et apres
		x--;
		mPrevCase = $("#" + x + "-" + y);
		x+= 2;
		mNextCase = $("#" + x + "-" + y);
	}
	//si la case suivante existe et qu'elle n'est pas une definition
	if ((mPrevCase.length != 0) && (mPrevCase.children("p").length == 0))  {
		return true;
	}
	if ((mNextCase.length != 0) && (mNextCase.children("p").length == 0))  {
		return true;
	}
	return false;
}


$(document).ready(
		function() {

			$("p").click(
					function() {
						//on deselectionne tout
						$(".definition").removeClass("definitionSelectionnee");
						$(this).addClass("definitionSelectionnee");
						$("#grille1 td").removeClass("caseMotSelectionne");
						deselectionnnerCase();
						horizontal = true;
						//on recupere la direction du mot dans la balise span cachée
						var text = $(this).children("span").text();
						var orientation = parseInt(text);
						var idCase = $(this).parents("td").attr("id");
						var tab = idCase.split('-');
						var x = tab[0];
						var y = tab[1];
						switch (orientation) {
						case 1:
							//droite
							x++;
							horizontal = true;
							break;
						case 2:
							//bas
							y++;
							horizontal = false;
							break;
						case 3:
							//droite-bas
							x++;
							horizontal = false;
							break;
						case 4:
							//bas-droite
							y++;
							horizontal = true;
							break;

						default:
							//erreur
							break;
						}
						var selector = "#" + x + "-" + y;
						var firstCase = $(selector);
						selectionnerCase(firstCase);
					});
		});



$("#grilleMotFleche").keyup(
		function(event) {
			var c = codeTouche(event);
			//si on a appuyé sur des fleches directionnelles ou la touche pour effacer
			if ((c == 8) || ((c >= 37) && (c <= 40))) {
				var mIdString = $("#caseTexte").parent("td").attr("id");
				var mTab = mIdString.split("-");
				var x = mTab[0];
				var y = mTab[1];
				var sens = true;
				switch(c) {
				case 8:
					//backspace
					sens = horizontal;
					if (horizontal) {
						x--;
					}
					else {
						y--;
					}
					break;
				case 37:
					//left
					x--;
					sens = true;
					break;
				case 38:
					//up
					y--;
					sens = false;
					break;
				case 39:
					//right
					x++;
					sens = true;
					break;
				case 40:
					//bottom
					y++;
					sens = false;
					break;
				}
				mCase = $("#" + x + "-" + y);
				if ((mCase.length != 0) && (mCase.children("p").length == 0))  {
					horizontal = sens;
					selectionnerCase(mCase);
				}
			}
			return false;
		}
);

$("#grilleMotFleche").keypress(
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
				var mIdString = $("#caseTexte").parent("td").attr("id");
				var mTab = mIdString.split("-");
				var x = mTab[0];
				var y = mTab[1];
				if (horizontal) {
					x++;
				}
				else {
					y++;
				}
				var mCase = $("#" + x + "-" + y);
				if ((mCase.length != 0) && (mCase.children("p").length == 0))  {
					selectionnerCase(mCase);
				}
				return true;
			}
			return false;
		}
);

function deselectionnnerCase() {
	//on deselectionne la case et enleve le champ input
	$("#grilleMotFleche td").removeClass("caseSelectionnee");
	var letter = $("#caseTexte").val();
	$("#caseTexte").parent().text(letter);
	$("#caseTexte").remove();
}

function selectionnerCase(mCase) {
	deselectionnnerCase();
	//si on a selectionné une case qui ne fait pas parti du mots en cours de selection on deselectionne...
	//if (!mCase.hasClass("caseMotSelectionne")) {
	$("#grilleMotFleche td").removeClass("caseMotSelectionne");
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
		var childrenTr = $("#grilleMotFleche tr");
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

function codeTouche(event) {
	for (prop in event) {
		if (prop == "which") {
			return event.which;
		}
	}
	return event.keyCode;
}