var idDefCmpt = -2;
var idCaseSelect = "";

/*Definition d'une case de la grille*/

function SquareData(idSquare, letter) {
	this.idSquare = idSquare;
	this.letter = letter;
	this.idDefinition = new Array();
	this.idDefinition[1] = -1;
	this.idDefinition[2] = -1;
	this.idDefinition[3] = -1;
	this.idDefinition[4] = -1;
	//(1 - droite) (2 - bas) (3 - droite-bas) (4 - bas-droite)
}


function addNewDefinition(orientation) {
	var mCase = $(".caseEdition");
	var id = mCase.attr("id");
	var x = id.split("-")[0];
	var y = id.split("-")[1];
	var coord = new Coord(x, y);
	var mDef = new Definition(idDefCmpt, "?", "", "", coord, orientation);
	addDefinition(mCase, mDef);
	idDefCmpt--;
}

function addOtherDefinition(orientation) {
	//var mCase = $(".definitionSelectionneeMF").parents("td");
	var mCase = $("#" + idCaseSelect);
	if (mCase.length <= 0) {
		return;
	}
	var id = mCase.attr("id");
	var x = id.split("-")[0];
	var y = id.split("-")[1];
	var coord = new Coord(x, y);
	var mDef = new Definition(idDefCmpt, "?", "", "", coord, orientation);
	addDefinition(mCase, mDef);
	idDefCmpt--;
}

function addDefinition(mCase, mDef) {
	var id = mCase.attr("id");
	var x = id.split("-")[0];
	var y = id.split("-")[1];
	var mDefTmp = undefined;
	for (var mDefKey in mGrid.definitionList) {
		var d = mGrid.definitionList[mDefKey];
		if ((d.coordDef.x == x) && (d.coordDef.y == y)) {
			mDefTmp = d;
			break;
		}
	}
	if (mDefTmp != undefined) {
		if (mDefTmp.orientation == mDef.orientation) {
			alert("il y a déjà une définition dans ce sens");
			return;
		}
		if (((mDef.orientation == 1) && (mDefTmp.orientation == 3)) ||
				(mDef.orientation == 3) && (mDefTmp.orientation == 1) ||
				(mDef.orientation == 2) && (mDefTmp.orientation == 4) ||
				(mDef.orientation == 4) && (mDefTmp.orientation == 2)) {
			alert("Impossible d'ajouter une definition qui vont dans le même sens");
			return;
		}
	}
	var id = mCase.attr("id");
	var x = id.split("-")[0];
	var y = id.split("-")[1];
	//variable pour obtenir la case suivante
	var xC = x; var yC = y;
	var horizontal = true;
	switch(mDef.orientation) {
	case 1:
		//droite
		x++;
		urlImage = "images/grilles/droite.png";
		break;
	case 2:
		//bas
		y++;
		urlImage = "images/grilles/bas.png";
		horizontal = false;
		break;
	case 3:
		//droite-bas
		x++;
		urlImage = "images/grilles/droite-bas.png";
		horizontal = false;
		break;
	case 4:
		//bas-droite
		y++;
		urlImage = "images/grilles/bas-droite.png";
		break;
	default:
		//erreur
		break;
	}
	//on verifie qu'on peut ajouter une definition

	var mIdNextCase = x + "-" + y;
	var mNextCase = $("#" + mIdNextCase);
	//si il n'y a pas de case après la definition
	if (mNextCase.length <= 0) {
		alert("impossible de placer une définition ici");
		return;
	}
	if (mNextCase.hasClass("caseDefinitions")) {
		alert("impossible de placer une définition ici");
		return;
	}
	if (mCase.children(".conteneurDef").children().length == 2) {
		alert("Vous ne pouvez pas rajouter plus de definition dans cette case");
		return;
	}

	if (!checkPrevCase(mCase)) {
		return;
	}

	if(mNextCase.css("background-image") == "none") {
		var mUrl = "url('" +  urlImage + "')";
		mNextCase.css("background-image", mUrl).css("background-repeat", "no-repeat");
	}
	else {
		var tmp = "<div class='" + mDef.idDefinition + "'/>";
		mNextCase.append(tmp);
		var mUrl = "url('" +  urlImage + "')";
		var mSelec = "div." + mDef.idDefinition;
		$(mSelec).css("background-image", mUrl).css("background-repeat", "no-repeat");
	}
	mGrid.switchOrientation(mCase, horizontal);
	//deselection

	var letter = $("#caseTexte").val();
	$(".caseEdition").text(letter);
	$(".caseLettre").removeClass("caseEdition");
	$("#caseTexte").remove();
	$(".definitionMF").removeClass("definitionSelectionneeMF");
	$(".caseLettre").removeClass("casesMotSelectionne");

	//ajout de la definition

	mGrid.definitionList[mDef.idDefinition] = mDef;

	//On rajoute la definition dans la case

	mCase.addClass("caseDefinitions");
	mCase.unbind();
	if (mCase.children(".conteneurDef").length <= 0) {
		mCase.text("");
	}
	mCase.removeClass("caseLettre");
	var mElement = "<div class='definitionMF' id='" + mDef.idDefinition + "'>" + mDef.textDef + "</div>";
	if (mCase.children(".conteneurDef").length == 0) {
		var mConteneur = "<div class='conteneurDef'></div>";
		mCase.append(mConteneur);
		//alert(mCase.children(".conteneurDef").length);
	} 
	else {
		var tmp = mCase.children(".conteneurDef:first").children(".definitionMF");
		var mTmpId =  tmp.attr("id");
		var mDefTmp =  mGrid.definitionList[mTmpId];;
		if (mDefTmp == undefined) {
			tmp.remove();
		}
	}
	var mDefCont = mCase.children(".conteneurDef").first();
	if ((mDef.orientation == 1) || (mDef.orientation == 3)) {
		mDefCont.prepend(mElement);
	}
	//si le mot est de sens vertical
	else if ((mDef.orientation == 2) || (mDef.orientation == 4)) {
		mDefCont.append(mElement);
	}

	var end = false;
	while(!end) {
		var mId = x + "-" + y;
		var mSelector = "#" + mId;
		var mCCase = $(mSelector);
		if ((mCCase.length <= 0) || (mCCase.hasClass("caseDefinitions"))) {
			end = true;
		}
		else {
			var mSquare = new SquareData(mId, "");
			var oldSquare = mGrid.squareDataList[mId];
			if (oldSquare != undefined) {
				mSquare.idDefinition = oldSquare.idDefinition;
			}

			//si le mot est de sens horizontal
			if ((mDef.orientation == 1) || (mDef.orientation == 4)) {
				mSquare.idDefinition[1] = -1;
				mSquare.idDefinition[4] = -1;
				x++;
			}
			//si le mot est de sens vertical
			else if ((mDef.orientation == 2) || (mDef.orientation == 3)) {
				mSquare.idDefinition[2] = -1;
				mSquare.idDefinition[3] = -1;
				y++;
			}
			mSquare.idDefinition[mDef.orientation] = mDef.idDefinition;
			mGrid.squareDataList[mId] = mSquare;
		}
		selectSquare(mNextCase);
	}

	//ajout de case definitions
	if ((mDef.orientation == 3) || (mDef.orientation == 4)) {
		if (mDef.orientation == 3) {
			//droite-bas : on regarde la case au dessus
			xC++; 
			yC--;
			var mId = xC + "-" + yC;
			var mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.addClass("emptyDef");
			}
			xC++; yC++;
			mId = xC + "-" + yC;
			mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.addClass("emptyDef");
			}
		}
		else if (mDef.orientation == 4) {
			//bas-droite
			xC--; 
			yC++;
			var mId = xC + "-" + yC;
			var mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.addClass("emptyDef");
			}
			xC++;
			yC++;
			mId = xC + "-" + yC;
			mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.addClass("emptyDef");
			}
		}

	}

	//reajustement des hauteurs de definitions dans les cases 
	var contDefDiv = mCase.children(".conteneurDef");
	mCase.children(".conteneurDef").children(".definitionMF").css("height", "");
	if (contDefDiv.children(".definitionMF").length == 1) {
		contDefDiv.children(".definitionMF").height(50);
	}
	else {
		contDefDiv.children(".definitionMF:last").css("height", "100%");
	}

	//ajout des evenements
	mCase.children(".conteneurDef").children(".definitionMF").click(
			function(e) {
				var mIdDef = $(this).attr("id");
				var mDef = mGrid.definitionList[mIdDef];
				if (mDef == undefined) {
					return;
				}
				var x = mDef.coordDef.x;
				var y = mDef.coordDef.y;
				if ((mDef.orientation == 1) || (mDef.orientation == 3)) {
					x++;
				}
				else {
					y++;
				}
				var mIdCase = x + "-" + y;
				var mCase = $("#" + mIdCase);
				if ((mDef.orientation == 1) || (mDef.orientation == 4)) {
					mGrid.horizontal = true;
				}
				//si le mot est de sens vertical
				else if ((mDef.orientation == 2) || (mDef.orientation == 3)) {
					mGrid.horizontal = false;
				}
				selectSquare(mCase);
			}
	);

	//ajouter une autre definition sur une definition lors du clique droit
	$(".definitionMF").bind("contextmenu", function(e){  
		var mCaseDef = $(this).parents("td");
		$(this).click();
		idCaseSelect = mCaseDef.attr("id");
		//on selectionne la 1ere case associee a la def
		//mCaseDef.children("div:first").click();
		var top = $(this).position().top + 70;
		var left = $(this).position().left + 25 + 150;

		$("#menuContext").css("display", "inline");
		$("#menuContext").css("top", top);
		$("#menuContext").css("left", left);
		return false;  
	});

	$(document).scroll(function(e) {
		var scroll = $(this).scrollTop();
		var header = $("#principal").offset().top;
		var height = $("#grilleMotFleche").height();
		var val = scroll + header;
		if (val < height) {
			$("#menuMotsCroises").css("top", scroll);
		}
	});

	//editer la definition lors du double clique
	mCase.children(".conteneurDef").children(".definitionMF").dblclick(
			function(e) {
				editDefinition();
			}
	);
}

/*Pour verifier qu'une definition peut etre placé dans cette case la */
function checkPrevCase(mCase) {
	var id = mCase.attr("id");
	var x = id.split("-")[0];
	var y = id.split("-")[1];
	var auDessus = true;
	y--;
	for (var i = 0; i < 2; i++) {
		var mIdPrecCase = x + "-" + y;
		var mPrecCase = $("#" + mIdPrecCase);
		var ok = true;
		if ((mPrecCase.length > 0) && (mPrecCase.hasClass("caseDefinitions"))) {
			mPrecCase.children(".conteneurDef:first").children(".definitionMF").each(function(elem, index) {
				var mTmpId =  $(this).attr("id");
				var mDefTmp =  mGrid.definitionList[mTmpId];
				if (auDessus) {
					if ((mDefTmp.orientation == 2) || (mDefTmp.orientation == 4)) {
						alert("impossible de placer une définition ici");
						ok = false;
						return false;
					}
				} 
				else {
					if ((mDefTmp.orientation == 1) || (mDefTmp.orientation == 3)) {
						alert("impossible de placer une définition ici");
						ok = false;
						return false;
					}
				}
			});
			if (!ok) {
				return false;
			}
		}
		y++;
		x--;
		auDessus = false;
	}
	return true;
}


function addEmptyDefinition(mCase) {
	mCase.css("background-color", "orange");
}

/*Objet pour une definition*/

function Definition(idDefinition, textDef, word, synonym, coordDef, orientation) {
	this.idDefinition = idDefinition;
	this.textDef = textDef;
	this.word = word;
	this.synonym = synonym;
	this.orientation = orientation;
	this.coordDef = coordDef;
	//(1 - droite) (2 - bas) (3 - droite-bas) (4 - bas-droite)
}

function Coord(x, y) {
	this.x = x;
	this.y = y;
}

function GrilleMotsFleches(width, height, idGrille) {
	this.width = width;
	this.height = height;
	this.squareDataList = new Array();
	this.definitionList = new Array();
	this.horizontal = true;
	this.idGrille = idGrille;
	this.definitionListTmp = new Array();

	GrilleMotsFleches.prototype.addDefinition = function(idDef, textDef, word, synonym, orientation, coord) {
		var mDef = new Definition(idDef, textDef, word, synonym, coord, orientation);
		this.definitionListTmp[idDef] = mDef;
	};

	GrilleMotsFleches.prototype.setLettre = function(lettre, x, y) {
		var mId = x + "-" + y;
		var mCase = $("#" + mId);
		mCase.text(lettre);
	};

	GrilleMotsFleches.prototype.initialize = function() {
		$("#grilleMotFleche td").addClass("caseLettre");
		//ajouter une definition dans une case vide
		$(".caseLettre").bind("contextmenu", function(e){
			idCaseSelect = $(this).attr("id");
			selectSquare($(this));
			var top = $(this).position().top + 70;
			var left = $(this).position().left + 25 + 150;

			$("#menuContext").css("display", "inline");
			$("#menuContext").css("top", top);
			$("#menuContext").css("left", left);
			return false;  
		});

		//Creation des cases de la grille selon les definitions
		var mDefList = mGrid.definitionListTmp;
		for (var idDef in mDefList) {
			var mDef = mDefList[idDef];
			//On rajoute la definition dans la case
			var x = mDef.coordDef.x;
			var y = mDef.coordDef.y;
			var mSelector = "#" + x + "-" + y;
			var mCase = $(mSelector);
			addDefinition(mCase, mDef);
		}
	};

	/*Change l'orientation du mot selectionnee si possible*/

	GrilleMotsFleches.prototype.switchOrientation = function(pCase, pHorizontal) {
		this.horizontal = pHorizontal;
	};
}



/*$(window).unload( function () { alert("Bye now!"); } );*/


$(document).ready(function(){

	$(window).unload(function(){

		alert( "alert not working");

	});


	mGrid.initialize();

	$(document).scroll(function(e) {
		var scroll = $(this).scrollTop();
		var header = $("#principal").offset().top;
		var height = $("#grilleMotFleche").height();
		var val = scroll + header;
		if (val < height) {
			$("#menuMotsCroises").css("top", scroll);
		}
	});

	$(".caseLettre").click(
			function(e) {
				var mCase = $(this);
				if (mCase.hasClass("caseDefinitions")) {
					return;
				}
				if (mCase.hasClass("caseEdition")) {
					var pHorizontal = !mGrid.horizontal;
					mGrid.switchOrientation(mCase, pHorizontal);
					selectSquare(mCase);
				}
				else {
					mGrid.switchOrientation(mCase, true);
					selectSquare(mCase);
				}
			}
	);

	/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

	$("#grilleMotFleche").keyup(
			function(event) {
				var c = codeTouche(event);
				if ($("#editDef").length > 0) {
					if ((c == 13) || (c == 27)) {
						validateDefinition();
					}
					return true;
				}
				//si on a appuyé sur des fleches directionnelles ou la touche pour effacer
				if ((c == 8) || ((c >= 37) && (c <= 40))) {
					var mCase = $(".caseEdition:first");
					if (mCase == undefined) {
						return;
					}
					var mIdString = mCase.attr("id");
					var mTab = mIdString.split("-");
					var x = mTab[0];
					var y = mTab[1];
					var sens = true;
					switch(c) {
					case 8:
						//backspace
						sens = mGrid.horizontal;
						$("#caseTexte").val("");
						if (mGrid.horizontal) {
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
					var idOthCase = x + "-" + y;
					var mOthCase = $("#" + idOthCase);
					if (mOthCase.hasClass("caseLettre")) {
						if (mGrid.squareDataList[idOthCase] == undefined) {
							mGrid.horizontal = sens;
						}
						else {
							mGrid.switchOrientation(mOthCase, sens);
						}
						selectSquare(mOthCase);
					}
				}
				return false;
			}
	);

	/*Lors de l'appui sur les touches alpha-numerique, ponctuation...*/

	$("#grilleMotFleche").keypress(
			function(event) {
				event.preventDefault();
				if ($("#editDef").length > 0) {
					var c = codeTouche(event);
					//var regex = /[a-zA-Z -]/;
					var char = String.fromCharCode(c);
					//si c'est une lettre
					//if (regex.test(char)) {
					var content = $("#editDef").val();
					content = content + char;
					$("#editDef").val(content);
					//}
					return true;
				}
				var c = codeTouche(event);
				var regex = /[a-zA-Z]/;
				var char = String.fromCharCode(c);
				//si c'est une lettre
				if (regex.test(char)) {
					//on le char dans la case
					$("#caseTexte").val(char);
					//on passe a la case suivante
					var mCase = $(".caseEdition:first");
					var mIdString = mCase.attr("id");
					var mTab = mIdString.split("-");
					var x = mTab[0];
					var y = mTab[1];
					if (mGrid.horizontal) {
						x++;
					}
					else {
						y++;
					}
					var idOthCase = x + "-" + y;
					var mOthCase = $("#" + idOthCase);
					//(mOthCase.length > 0) && (
					if (mOthCase.hasClass("caseLettre")){
						if (mGrid.squareDataList[idOthCase] == undefined) {
							//mGrid.horizontal = sens;
						}
						else {
							mGrid.switchOrientation(mOthCase, mGrid.horizontal);
						}
						selectSquare(mOthCase);
					}
					return true;
				}
				return false;
			}
	);

	$(document).click(
			function(e) {
				$("#menuContext").css("display", "none");
			}
	);

	/*selection de la 1ere case de la grille*/

	var mCase = $(".caseLettre:first");
	mGrid.switchOrientation(mCase, true);
	selectSquare(mCase);

	/*Dynamise le menu horizontal*/
	mainmenu();

});


/* Menu horizontal dynamique */

function mainmenu(){
	$(" #nav ul ").css({display: "none"}); // Opera Fix
	$(" #nav li").hover(function(){
		$("#menuContext").css("display", "none");
		$(this).find('ul:first').css({visibility: "visible",display: "none"}).show(400);
	},function(){
		$("#menuContext").css("display", "none");
		$(this).find('ul:first').css({visibility: "hidden"});
	});
};


function validateDefinition() {
	//deselection
	if ($("#editDef").length > 0) {
		var content = $("#editDef").val().trim();
		$("#editDef").remove();
		if (content == "") {
			content = "?";
		}
		$(".definitionSelectionneeMF").text(content);
		var mIdDef = $(".definitionSelectionneeMF").attr("id");
		var mDef = mGrid.definitionList[mIdDef];
		mDef.textDef = content;
		var mContDef = $(".definitionSelectionneeMF").parent(".conteneurDef");
		mContDef.children(".definitionMF").height("");
		if (mContDef.children(".definitionMF").length == 1) {
			mContDef.children(".definitionMF").css("height", "100%");
		}
	}
}


/*Fonction qui selectionne une case ainsi que sa definition*/

function selectSquare(mCase) {
	validateDefinition();
	var letter = $("#caseTexte").val();
	$(".caseEdition").text(letter);
	$(".caseLettre").removeClass("caseEdition");
	$("#caseTexte").remove();
	$(".definitionMF").removeClass("definitionSelectionneeMF");
	$(".caseLettre").removeClass("casesMotSelectionne");

	//selection de la definition associe au mot
	var mIdCase = mCase.attr("id");
	var mSquareData = mGrid.squareDataList[mIdCase];
	var mIdDef = undefined;
	if (mSquareData != undefined) {
		if (mGrid.horizontal) {
			if (mSquareData.idDefinition[1] != -1) {
				mIdDef = mSquareData.idDefinition[1];
			}
			else if (mSquareData.idDefinition[4] != -1) {
				mIdDef = mSquareData.idDefinition[4];
			}
		}
		else {
			if (mSquareData.idDefinition[2] != -1) {
				mIdDef = mSquareData.idDefinition[2];
			}
			else if (mSquareData.idDefinition[3] != -1) {
				mIdDef = mSquareData.idDefinition[3];
			}
		}
		var mSelector = "#" + mIdDef;
		$(mSelector).addClass("definitionSelectionneeMF");
		//selection des cases faisant parties de la definition
		$(".caseLettre").each(
				function(index, element) {
					var mIdCase = $(this).attr("id");
					mSquareData = mGrid.squareDataList[mIdCase];
					if (mSquareData != undefined) {
						for (var mOrientKey in mSquareData.idDefinition) {
							var mIdDefTmp = mSquareData.idDefinition[mOrientKey];
							if (mIdDefTmp == mIdDef) {
								$(this).addClass("casesMotSelectionne");
							}
						}
					}
				}
		);
	}
	if (mIdDef == undefined) {
		//selectionner les cases avants et apres
		var tab = mIdCase.split("-");
		$("#grilleMotFleche td").each(
				function(index, element) {
					var mIdCase2 = $(this).attr("id");
					var tab2 = mIdCase2.split("-");
					var x2 = tab2[0];
					var y2 = tab2[1];
					if ((mGrid.horizontal) && (tab[1] == y2)) {
						if ($(this).hasClass("caseDefinitions")) {
							return;// false;
						}
						$(this).addClass("casesMotSelectionne");
					}
					else if ((!mGrid.horizontal) && (tab[0] == x2)) {
						if ($(this).hasClass("caseDefinitions")) {
							return;//false;
						}
						$(this).addClass("casesMotSelectionne");
					}
				}
		);
	}
	//selection de la case
	mCase.addClass("caseEdition");
	//on prend la lettre deja dans la case
	letter = mCase.text();
	var mDiv = undefined;
	if (mCase.children("div").length > 0) {
		mDiv = mCase.children("div").clone();
		alert("pet de cable");
	}
	mCase.text("");
	//var mDivStr = mCase.html();
	var inputStr = "<input type='text' id='caseTexte' name='caseTexte' maxlength='1'/>";
	mCase.append(inputStr);
	if (mDiv != undefined) {
		mCase.append(mDiv);
	}
	
	//mCase.html(input);

	$("#caseTexte").val(letter);
	$("#caseTexte").focus();
}


function editDefinition() {
	var mDefSelect = $(".definitionSelectionneeMF");
	if (mDefSelect.length <= 0) {
		alert("aucune definition n'est selectionnee");
		return;
	}
	var content = mDefSelect.text();
	mDefSelect.text("");
	var textArea = "<textarea id='editDef' name='editDef' cols='5' rows='5' wrap='default'>"
		+ content + "</textarea>";
	mDefSelect.append(textArea);
	//$("#editDef").css("height", "100%");
	$("#editDef").focus();
	$("#editDef").css("height", "");
	var mContDef = mDefSelect.parent(".conteneurDef");
	if (mContDef.children(".definitionMF").length == 2) {
		if (mDefSelect.index() == 0) {
			mContDef.children(".definitionMF:first").css("height", "");
			mContDef.children(".definitionMF:last").css("height", "");
			var lastHeight = mContDef.children(".definitionMF:last").height();
			var newHeight = 50 - lastHeight;
			$("#editDef").height(newHeight);
		}
	}
	$("#editDef").keypress(function(event) {
		event.preventDefault();
		var nbChar = $(this).val().length;
		mContDef.children(".definitionMF").each(function(elem, index) {
			nbChar+= $(this).text().length;
		});
		if (nbChar > 40) {
			alert("Plus de place dans la case");
			return false;
		}
	});
}

function getSynonyms() {
	var mDefSelect = $(".definitionSelectionneeMF");
	if (mDefSelect.length <= 0) {
		alert("aucune definition n'est selectionnee");
		return;
	}
	var mIdDef = $(".definitionSelectionneeMF").attr("id");
	var word = "";
	var mDef = mGrid.definitionList[mIdDef];
	var orientation = mDef.orientation;
	$(".caseLettre").each(
			function(index, element) {
				var mIdCase = $(this).attr("id");
				mSquareData = mGrid.squareDataList[mIdCase];
				if (mSquareData != undefined) {
					var mIdDefTmp = mSquareData.idDefinition[orientation];
					if (mIdDefTmp == mIdDef) {
						var lettre = $(this).text();
						if ($(this).hasClass("caseEdition")) {
							lettre = $("#caseTexte").val();
						}
						if (lettre == "") {
							return false;
						}
						word+= lettre;
					}
				}
			}
	);
	//envoie d'une requete ajax pour obtenir le synonyme
	if (word == "") {
		alert("aucun mot associé à la definition");
		return;
	}
	var params = "action=getSynonyms&mot=" + word;
	$.ajax({
		url: "mots",
		type: 'POST',
		cache: false,
		data: params,
		success: function(contenu) {
			$("#synonymes").children().remove();
			$("#synonymes").append(contenu);
		},
		error: function() {
			alert("Une erreur lors de la requete ajax");
		}
	});
}

function selectSynonym(idMot, synonym) {
	var mDefSelect = $(".definitionSelectionneeMF");
	if (mDefSelect.length <= 0) {
		alert("aucune definition n'est selectionnee");
		return;
	}
	mDefSelect.text(synonym);
	var mIdDef = $(".definitionSelectionneeMF").attr("id");
	var mDef = mGrid.definitionList[mIdDef];
	mDef.textDef = synonym;
}

function deleteElem() {
	var mCase = $("#" + idCaseSelect);
	if (mCase.length <= 0) {
		return;
	}
	if (mCase.hasClass("caseLettre")) {
		$("#caseTexte").val("");
	}
	else if (mCase.hasClass("caseDefinitions")) {
		deleteDefinition();
	}
}


function deleteDefinition() {
	var mDefSelect = $(".definitionSelectionneeMF");
	if (mDefSelect.length <= 0) {
		alert("aucune definition n'est selectionnee");
		return;
	}
	var mIdDef = $(".definitionSelectionneeMF").attr("id");
	var mDef = mGrid.definitionList[mIdDef];
	var orientation = mDef.orientation;
	var mCase = $(".definitionSelectionneeMF").parents("td");
	$(".definitionSelectionneeMF").remove();
	if (mCase.children(".conteneurDef").children().length <= 0) {
		mCase.children(".conteneurDef").remove();
		mCase.removeClass("caseDefinitions");
		mCase.addClass("caseLettre");
		$(mCase).click(
				function(e) {
					var mCase = $(this);
					if (mCase.hasClass("caseDefinitions")) {
						return;
					}
					if (mCase.hasClass("caseEdition")) {
						var pHorizontal = !mGrid.horizontal;
						mGrid.switchOrientation(mCase, pHorizontal);
						selectSquare(mCase);
					}
					else {
						mGrid.switchOrientation(mCase, true);
						selectSquare(mCase);
					}
				}
		);
	}
	else {
		mCase.children(".conteneurDef").children("div").last().css("height", "100%");
	}
	//on enleve la fleche
	var x = mDef.coordDef.x;
	var xC = x;
	var y = mDef.coordDef.y;
	var yC = y;
	if ((orientation == 1) || (orientation == 3)) {
		x++;
	}
	else {
		//2 - 4
		y++;
	}
	var mNextCase = $("#" + x + "-" + y);
	mNextCase.css("background-image", "").css("background-repeat", "");
	$(".caseLettre").each(
			function(index, element) {
				var mIdCase = $(this).attr("id");
				mSquareData = mGrid.squareDataList[mIdCase];
				if (mSquareData != undefined) {
					var mIdDefTmp = mSquareData.idDefinition[orientation];
					if (mIdDefTmp == mIdDef) {
						mSquareData.idDefinition[orientation] = -1;
						if ((mSquareData.idDefinition[1] == -1) &&
								(mSquareData.idDefinition[2] == -1) &&
								(mSquareData.idDefinition[3] == -1) &&
								(mSquareData.idDefinition[4] == -1)) {
							delete mGrid.squareDataList[mIdCase];
						}
					}
				}
			}
	);
	delete mGrid.definitionList[mIdDef];
	selectSquare($(".caseEdition:first"));
	//suppression des autres cases oranges
	if ((orientation == 3) || (orientation == 4)) {
		if (mDef.orientation == 3) {
			//droite-bas : on regarde la case au dessus
			xC++; 
			yC--;
			var mId = xC + "-" + yC;
			var mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.removeClass("emptyDef");
			}
			xC++; yC++;
			mId = xC + "-" + yC;
			mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.removeClass("emptyDef");
			}
		}
		else if (mDef.orientation == 4) {
			//bas-droite
			xC--; 
			yC++;
			var mId = xC + "-" + yC;
			var mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.removeClass("emptyDef");
			}
			xC++;
			yC++;
			mId = xC + "-" + yC;
			mCaseDefEmpty = $("#" + mId);
			if ((mCaseDefEmpty.length > 0) && (mCaseDefEmpty.hasClass("caseLettre"))) {
				mCaseDefEmpty.removeClass("emptyDef");
			}
		}

	}
}


function saveGrille() {
	var definitions = "";
	var mDefList = mGrid.definitionList;
	for (var mKey in mDefList) {
		var mDef = mDefList[mKey];
		definitions+= mDef.textDef + ":" + mDef.orientation + ":" + mDef.coordDef.x + ":" + mDef.coordDef.y;
		definitions+= "/";
	}
	var lettres = "";
	$(".caseLettre").each(
			function(index, elem) {
				var mIdCase = $(this).attr("id");
				var lettre = $(this).text();
				if ($(this).hasClass("caseEdition")) {
					lettre = $("#caseTexte").val();
				}
				if (lettre == "") {
					return;
				}
				var tab = mIdCase.split("-");
				var x = tab[0];
				var y = tab[1];
				lettres+= lettre + ":" + x + ":" + y + "/";
			});
	var params = "idGrille=" + mGrid.idGrille + "&motGrilleString=" + definitions + "&lettresString=" + lettres;
	$.ajax({
		url: "sauvegarderGrille",
		type: 'POST',
		cache: false,
		data: params,
		success: function(contenu) {
			alert(contenu);
		},
		error: function() {
			alert("Une erreur lors de la requete ajax pour la sauvegarde");
		}
	});

}

function endGrille() {
	var allCaseOk = true;
	$(".caseLettre").each(
			function(index, element) {
				if ($(this).hasClass("emptyDef")) {
					alert("Une des cases doit contenir une définition");
					allCaseOk = false;
					return false;
				}
				var mIdCase = $(this).attr("id");
				var lettre = $(this).text();
				if ($(this).hasClass("caseEdition")) {
					lettre = $("#caseTexte").val();
				}
				if (lettre == "") {
					alert("veuillez remplir toutes les cases");
					allCaseOk = false;
					return false;
				}
				if (mGrid.squareDataList[mIdCase] == undefined) {
					$(this).addClass("errorCase");
					alert("Cette case n'est associé à aucune definition");
					allCaseOk = false;
					return false;
				}
			});
	if (!allCaseOk) {
		return;
	}
	saveGrille();
	var motsGrille = "";
	var mDefList = mGrid.definitionList;
	for (var mKey in mDefList) {
		var mDef = mDefList[mKey];
		var x = mDef.coordDef.x;
		var y = mDef.coordDef.y;
		var horizontal = true;
		switch(mDef.orientation) {
		case 1:
			//droite
			x++;
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
			break;
		default:
			//erreur
			break;
		}
		var mot = "";
		var end = false;
		do {
			var mCase = $("#" + x + "-" + y);
			if (mCase == undefined) {
				end = true;
			}
			else if (mCase.hasClass("caseLettre")) {
				var lettre = mCase.text();
				if (mCase.hasClass("caseEdition")) {
					lettre = $("#caseTexte").val();
				}
				mot+= lettre;
				if (horizontal) {
					x++;
				}
				else {
					y++;
				}
			}
			else {
				end = true;
			}

		} while(!end);
		motsGrille+= mDef.textDef + ":" + mot + ":" + mDef.orientation + ":"
		+ mDef.coordDef.x + ":" + mDef.coordDef.y;
		motsGrille+= "/";
	}
	alert(motsGrille);
	var params = "idGrille=" + mGrid.idGrille + "&motGrilleString=" + motsGrille;
	$.ajax({
		url: "soumettreGrille",
		type: 'POST',
		cache: false,
		data: params,
		success: function(contenu) {
			alert(contenu);
		},
		error: function() {
			alert("Une erreur lors de la requete ajax pour la soumission de grille");
		}
	});
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



