/*Ajouter les cases noires à la grille*/

function addCaseNoire(coordonnees) {
	var mSelector = "#" + coordonnees;
	$(mSelector).addClass("caseNoire");
}

/*Lorsque la grille perd le focus, on deselectionne tout*/

$("#grille1").focusout(
		function() {
			deselectionnnerCase();
			$(".ligne_definition").removeClass("definitionSelectionnee2");
			$("#grille1 td").removeClass("caseMotSelectionne");
		});


/*Lors d'un clique sur une case de la grille*/

$("#grille1 td").click(
		function() {
			if ($(this).hasClass("caseNoire")) {
				$("#caseTexte").focus();
				return;
			}
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
			selectDefinition($(this));
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
	if ((mPrevCase.length != 0) && (!mPrevCase.hasClass("caseNoire")))  {
		return true;
	}
	if ((mNextCase.length != 0) && (!mNextCase.hasClass("caseNoire")))  {
		return true;
	}
	return false;
}

/*Selectionner la definition associée à la case, ainsi que les cases du mot*/

function selectDefinition(mCase) {
	//on doit changer la definition
	$(".ligne_definition").removeClass("definitionSelectionnee2");
	var x = -1;
	var y = -1;
	var c = null;
	var selector = "";
	if (horizontal) {
		//on doit chercher la 1ere case qui compose ce mot afin de selectionner les coordonnees et selectionner
		//la definition associee
		c = selectCaseDebutH(mCase);
		selector = "#defHorizontales .ligne_definition";
	}
	else {
		c = selectCaseDebutV(mCase);
		selector = "#defVerticales .ligne_definition";
	}
	x = c.index() - 1;
	y = c.parent("tr").index() - 1;
	var definitions = $(selector);
	for (var i = 0; i < definitions.length; i++) {
		var mDef = $(selector).eq(i);
		var rowIdx =  parseInt(mDef.children(".rowIndex").eq(0).text());
		var colIdx = parseInt(mDef.children(".colIndex").eq(0).text());
		//alert(rowIdx + " - " + colIdx + "////" + x + " - " + y);
		if ((rowIdx == y) && (colIdx == x)) {
			mDef.addClass("definitionSelectionnee2");
			return;
		}
	}
}

function selectCaseDebutH(mCase) {
	var c = mCase;
	while ((c.prev("td").length != 0) && (!c.prev("td").hasClass("caseNoire"))) {
		c = c.prev("td");
	}
	return c;
}

function selectCaseDebutV(mCase) {
	var c = mCase;
	var idColonne =  mCase.index();
	var trTag =  mCase.parents("tr");
	var tmpElem = trTag.prev("tr").children("td").eq(idColonne - 1);
	while ((tmpElem.length != 0) && (!tmpElem.hasClass("caseNoire"))) {
		c = tmpElem;
		trTag =  c.parents("tr");
		tmpElem = trTag.prev("tr").children("td").eq(idColonne - 1);
	}
	return c;
}

/*Selectionne les cases du mot associé a la definition*/

$(".ligne_definition").click(
		function() {
			$(".ligne_definition").removeClass("definitionSelectionnee2");
			deselectionnnerCase();
			$(this).addClass("definitionSelectionnee2");
			$("#grille1 td").removeClass("caseMotSelectionne");
			//si c'est une definition horizontale
			if ($(this).parent("div").attr("id") == "defHorizontales") {
				horizontal = true;
			}
			else {
				horizontal = false;
			}
			var rowIdx =  parseInt($(this).children(".rowIndex").eq(0).text());
			var colIdx = parseInt($(this).children(".colIndex").eq(0).text());
			var mCase = $("#grille1 tr").eq(rowIdx + 1).children().eq(colIdx + 1);
			selectionnerCase(mCase);
		});

/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

$("#grille1").keyup(
		function(event) {
			var c = codeTouche(event);
			//si on a appuyé sur des fleches directionnelles
			if ((c >= 37) && (c <= 40)) {
				//horizontal = true;
				switch(c) {
				case 37:
					//left
					horizontal = true;
					selectPreviousCaseH();
					break;
				case 38:
					//up
					horizontal = false;
					selectPreviousCaseV();
					break;
				case 39:
					//right
					horizontal = true;
					selectNextCaseH();
					break;
				case 40:
					//bottom
					horizontal = false;
					selectNextCaseV();
					break;
				}
			}
			else if (c == 8) {
				//backspace
				if (horizontal) {
					selectPreviousCaseH();
				}
				else {
					selectPreviousCaseV();
				}
			}
			return false;
		}
);

/*Lors de l'appui sur les touches alpha-numerique, ponctuation...*/

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

/*Deselectionne la case courante, qui est en train d'etre editee*/

function deselectionnnerCase() {
	//on deselectionne la case et enleve le champ input
	$("#grille1 td").removeClass("caseSelectionnee");
	var letter = $("#caseTexte").val();
	$("#caseTexte").parent().text(letter);
	$("#caseTexte").remove();
}


/*selectionner une case pour l'editer, les cases suivantes et precedentes, ainsi que la definition*/

function selectionnerCase(mCase) {
	deselectionnnerCase();
	$("#grille1 td").removeClass("caseMotSelectionne");
	$("#grille1 p").removeClass("definitionSelectionnee");

	if (horizontal) {
		var index = mCase.index();
		var children = mCase.parent().children("td");
		//selection des cases apres 
		for (var i = index; i < children.length; i++) {
			var mCaseTmp = children.eq(i);
			//if (mCaseTmp.children(":first").length != 0) {
			if (mCaseTmp.hasClass("caseNoire")) {
				break;
			}
			mCaseTmp.addClass("caseMotSelectionne");
		}
		//selection des cases avants
		var c = mCase;
		while ((c.length != 0) && (!c.hasClass("caseNoire"))) {
			c.addClass("caseMotSelectionne");
			c = c.prev("td");
		}
		selectDefinition(mCase);
	}
	else {
		var trTag = mCase.parents("tr");
		var indexCol = mCase.index() - 1;
		var indexRow = trTag.index() + 1;
		//selection des cases avant
		var childrenTr = $("#grille1 tr");
		for (var i = indexRow; i < childrenTr.length; i++) {
			var mCaseTmp = childrenTr.eq(i).children("td").eq(indexCol);
			if ((mCaseTmp.children(":first").length != 0) 
					|| mCaseTmp.hasClass("caseNoire")) {
				break;
			}
			mCaseTmp.addClass("caseMotSelectionne");
		}
		//selection des cases apres
		var c = mCase;
		//var tmpElem = trTag.prev("tr").children("td").eq(idColonne);
		while ((c.length != 0) && (!c.hasClass("caseNoire"))) {
			c.addClass("caseMotSelectionne");
			trTag =  c.parents("tr");
			c = trTag.prev("tr").children("td").eq(indexCol);
		}
		selectDefinition(mCase);
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
	var nextElem = $("#caseTexte").parents("td").next("td");
	//si la case suivante existe et qu'elle n'est une definition
	if ((nextElem.length != 0) && (!nextElem.hasClass("caseNoire")))  {
		selectionnerCase(nextElem);
	}
}

function selectPreviousCaseH() {
	var prevElem = $("#caseTexte").parent("td").prev("td");
	if ((prevElem.length != 0) && (!prevElem.hasClass("caseNoire"))) {
		selectionnerCase(prevElem);
	}
}

function selectNextCaseV() {
	var idColonne =  $("#caseTexte").parent().index();
	var trTag =  $("#caseTexte").parents("tr");
	var nextElem = trTag.next("tr").children("td").eq(idColonne - 1);
	//si la case suivante existe et qu'elle n'est pas une definition
	if ((nextElem.length != 0) && (!nextElem.hasClass("caseNoire")))  {
		selectionnerCase(nextElem);
	}
}

function selectPreviousCaseV() {
	var idColonne =  $("#caseTexte").parent().index();
	var trTag =  $("#caseTexte").parents("tr");
	var prevElem = trTag.prev("tr").children("td").eq(idColonne - 1);
	//si la case precedente existe et qu'elle n'est pas une definition
	if ((prevElem.length != 0) && (!prevElem.hasClass("caseNoire"))) {
		selectionnerCase(prevElem);
	}
}

/*fontion qui retourne le code de la touche*/

function codeTouche(event) {
	for (prop in event) {
		if (prop == "which") {
			return event.which;
		}
	}
	return event.keyCode;
}