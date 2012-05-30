/*Definition d'une case de la grille*/

function SquareData(idSquare, letter) {
	this.idSquare = idSquare;
	this.letter = letter;
	this.idDefinitionH = -1;
	this.idDefinitionV = -1;
}

/*Objet pour une definition*/

function Definition(idDefinition, word, synonym, coordStartWord, orientation) {
	this.idDefinition = idDefinition;
	this.word = word;
	this.synonym = synonym;
	this.orientation = orientation;
	this.coordWord = coordStartWord;
}

function Coord(x, y) {
	this.x = x;
	this.y = y;

	Coord.prototype.equals = function(pCoord) {
		return ((this.x == pCoord.x) && (this.y == pCoord.y));
	};
}

function GrilleMotsCroises(width, height, idGrille) {
	this.width = width;
	this.height = height;
	this.squareDataList = new Array();
	this.definitionList = new Array();
	this.horizontal = true;
	this.endGame = false;
	this.idGrille = idGrille;

	GrilleMotsCroises.prototype.addDefinition = function(idDef, word, synonym, coord, orientation) {
		var mDef = new Definition(idDef, word, synonym, coord, orientation);
		this.definitionList[idDef] = mDef;
	};

	GrilleMotsCroises.prototype.initialize = function() {
		/*Creation des cases de la grille selon les definitions*/
		var mDefList = mGrid.definitionList;
		for (var idDef in mDefList) {
			var mDef = mDefList[idDef];
			var x = mDef.coordWord.x;
			var y = mDef.coordWord.y;
			for (var j = 0; j < mDef.word.length; j++) {
				var letter = mDef.word[j];
				var mId = x + "-" + y;
				var mSquare = new SquareData(mId, letter);
				//si c'est une definition horizontale
				if (mDef.orientation == 0) {
					mSquare.idDefinitionH = mDef.idDefinition;
					x++;
				}
				//si c'est une definition verticale
				else if (mDef.orientation == 1) {
					mSquare.idDefinitionV = mDef.idDefinition;
					y++;
				}
				var oldSquare = this.squareDataList[mId];
				if (oldSquare != undefined) {
					if (oldSquare.idDefinitionH != -1) {
						mSquare.idDefinitionH = oldSquare.idDefinitionH;
					}
					else if (oldSquare.idDefinitionV != -1) {
						mSquare.idDefinitionV = oldSquare.idDefinitionV;
					}
				}
				this.squareDataList[mId] = mSquare;
				var mSelector = "#" + mId;
				var mCase = $(mSelector);
				mCase.addClass("caseLettre");
				//mCase.text(this.squareDataList[mId].letter);
			}
		}
		/*ajout des cases noires*/
		$("#grille1 td:not(.caseLettre)").each(
				function(index, mCase) {
					$(this).addClass("caseNoire");
				}
		);
	};

	GrilleMotsCroises.prototype.switchOrientation = function(pCase, pHorizontal) {
		var mIdCase = pCase.attr("id");
		var mSquareData = mGrid.squareDataList[mIdCase];
		if (pHorizontal) {
			this.horizontal = (mSquareData.idDefinitionH != -1);
		}
		else {
			this.horizontal = !(mSquareData.idDefinitionV != -1);
		}
	};
}

$(document).ready(function(){
	mGrid.initialize();
	$(".caseLettre").click(
			function(e) {
				var mCase = $(this);
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

	$(".ligne_definition").click(
			function(e) {
				var mIdDef = $(this).attr("id");
				var mDef = mGrid.definitionList[mIdDef];
				//si c'est horizontal
				if (mDef.orientation == 0) {
					mGrid.horizontal = true;
				}
				//si c'est vertical
				else if (mDef.orientation == 1){
					mGrid.horizontal = false;
				}
				var x = mDef.coordWord.x;
				var y = mDef.coordWord.y;
				var mSelector = "#" + x + "-" + y;
				var mCase = $(mSelector);
				selectSquare(mCase);
			}
	);

	/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

	$("#grille1").keyup(
			function(event) {
				//if (event.keyCode=='86' && event.ctrlKey) {alert('interdit'); return;};
				var c = codeTouche(event);
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
						$("#caseTexte").val(" ");
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
					if (mGrid.squareDataList[idOthCase] != undefined) {
						var mOthCase = $("#" + idOthCase);
						mGrid.switchOrientation(mOthCase, sens);
						selectSquare(mOthCase);
					}
				}
				return false;
			}
	);

	/*Lors de l'appui sur les touches alpha-numerique, ponctuation...*/

	$("#grille1").keypress(
			function(event) {
				event.preventDefault();
				/*if (mGrid.endGame) {
					return false;
				}*/
				$(".caseLettre").removeClass("correctCase");
				$(".caseLettre").removeClass("errorCase");
				$("#caseTexte").removeClass("correctCase");
				$("#caseTexte").removeClass("errorCase");
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
					if (mGrid.squareDataList[idOthCase] != undefined) {
						var mOthCase = $("#" + idOthCase);
						mGrid.switchOrientation(mOthCase, mGrid.horizontal);
						selectSquare(mOthCase);
					}
					checkEndGame();
					return true;
				}
				return false;
			}
	);

	/* Menu contextuel */

	$(".caseLettre").bind("contextmenu", function(e){  
		//on selectionne la case
		var mCaseDef = $(this);
		mGrid.switchOrientation(mCase, mGrid.horizontal);
		selectSquare(mCaseDef);
		
		var top = mCaseDef.offset().top + 25;
		var left = mCaseDef.offset().left + 25;
		
		$("#menuContext").css("display", "inline");
		$("#menuContext").css("top", top);
		$("#menuContext").css("left", left);
		return false;  
	});
	
	$(document).scroll(function(e) {
		var scroll = $(this).scrollTop();
		var header = $("#principal").offset().top;
		var height = $("#grille1").height();
		var val = scroll + header;
		if (val < height) {
			$("#menuMotsCroises").css("top", scroll);
		}
	});

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


/*Fonction pour verifier si la grille est terminee*/

function checkEndGame() {
	var mSquareList = mGrid.squareDataList;
	for (var mSquareId in mSquareList) {
		var mSquare = mSquareList[mSquareId];
		var mCase = $("#" + mSquareId);
		var letterUser = "";
		if (mCase.hasClass("caseEdition")) {
			letterUser =  $("#caseTexte").val();
		}
		else {
			letterUser = mCase.text();
		}
		if (letterUser != mSquare.letter) {
			return;
		}
	}
	var params = "idGrille=" + mGrid.idGrille;
	$.ajax({
		url: "GrilleFinie",
		type: 'POST',
		cache: false,
		data: params,
		success : function(contenu) {
		alert(contenu);
	},
		error : function() {alert("erreur...")}
	});
	/*alert("Fin de la partie !");
	mGrid.endGame = true;
	$("#caseTexte").attr("disabled", true);*/
	
	callMessageBox();
}



/*Fonction qui selectionne une case ainsi que sa definition*/

function selectSquare(mCase) {
	$("#menuContext").css("display", "none");
	//deselection

	var letter = $("#caseTexte").val();
	$(".caseEdition").text(letter);
	$(".caseLettre").removeClass("caseEdition");
	$("#caseTexte").remove();
	$(".caseLettre").removeClass("casesMotSelectionne");
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$(".ligne_definition").removeClass("definitionSelectionnee2");

	//selection de la definition associe au mot
	var mIdCase = mCase.attr("id");
	var mSquareData = mGrid.squareDataList[mIdCase];
	var mIdDef = undefined;
	if (mGrid.horizontal) {
		mIdDef = mSquareData.idDefinitionH;
	}
	else {
		mIdDef = mSquareData.idDefinitionV;
	}
	var mSelector = "#" + mIdDef;
	$(mSelector).addClass("definitionSelectionnee2");
	//selection des cases faisant parties de la definition
	$(".caseLettre").each(
			function(index, element) {
				var mIdCase = $(this).attr("id");
				mSquareData = mGrid.squareDataList[mIdCase];
				if (mGrid.horizontal) {
					if (mSquareData.idDefinitionH == mIdDef) {
						$(this).addClass("casesMotSelectionne");
					}
				}
				else {
					if (mSquareData.idDefinitionV == mIdDef) {
						$(this).addClass("casesMotSelectionne");
					}
				}

			}
	);
	//selection de la case
	mCase.addClass("caseEdition");
	//on prend la lettre deja dans la case
	letter = mCase.text();
	mCase.text("");
	var input = "<input type='text' id='caseTexte' name='caseTexte' maxlength='1'/>";
	mCase.append(input);
	$("#caseTexte").val(letter);
	$("#caseTexte").focus();
	if (mGrid.endGame) {
		$("#caseTexte").attr("disabled", true);
	}
}

/*Gestion de l'aide de jeu*/

function getLetter() {
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	var mCase = $(".caseEdition:first");
	var mId = mCase.attr('id');
	var mSquaredata = mGrid.squareDataList[mId];
	$("#caseTexte").val(mSquaredata.letter);
	$("#caseTexte").focus();
	checkEndGame();
}

function getWord() {
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$(".casesMotSelectionne").each(
			function(index, element) {
				var mCase = $(this);
				var mId = mCase.attr('id');
				var mSquaredata = mGrid.squareDataList[mId];
				if (mCase.hasClass("caseEdition")) {
					$("#caseTexte").val(mSquaredata.letter);
					$("#caseTexte").focus();
				}
				else {
					mCase.text(mSquaredata.letter);
				}
			}
	);
	checkEndGame();
}

function getSolution() {
	var message = "Voulez vous vraiment obtenir la solution???";
	callConfirmDialog(message,
			//lors de l'appuie sur le bouton non
			function() {
				$("#caseTexte").focus();
			},
			//lors de l'appuie sur le bouton oui
			function() {
				$(".caseLettre").removeClass("correctCase");
				$(".caseLettre").removeClass("errorCase");
				$("#caseTexte").removeClass("correctCase");
				$("#caseTexte").removeClass("errorCase");
				$(".caseLettre").each(
						function(index, element) {
							var mCase = $(this);
							var mId = mCase.attr('id');
							var mSquaredata = mGrid.squareDataList[mId];
							if (mCase.hasClass("caseEdition")) {
								$("#caseTexte").val(mSquaredata.letter);
								$("#caseTexte").focus();
							}
							else {
								mCase.text(mSquaredata.letter);
							}
						}
				);
				checkEndGame();
			}
	);
	
}

function getSynonym() {
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	var mDefId = $(".definitionSelectionnee2").attr("id");
	var mSynonym = mGrid.definitionList[mDefId].synonym;
	if (mSynonym == "") {
		alert("Pas de synonyme disponible pour le mot selectionne");
	}
	else {
		alert("Synonyme du mot selectionne: " + mSynonym);
	}
	
	$("#caseTexte").focus();
}

function checkLetter(){
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	var mCase = $(".caseEdition:first");
	var mId = mCase.attr('id');
	var mSquaredata = mGrid.squareDataList[mId];
	if ($("#caseTexte").val() == mSquaredata.letter) {
		$("#caseTexte").addClass("correctCase");
	}
	else {
		$("#caseTexte").addClass("errorCase");
	}
	$("#caseTexte").focus();
}

function checkWord() {
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$(".casesMotSelectionne").each(
			function(index, element) {
				var mCase = $(this);
				var mId = mCase.attr('id');
				var mSquaredata = mGrid.squareDataList[mId];
				if (mCase.hasClass("caseEdition")) {
					if ($("#caseTexte").val() == mSquaredata.letter) {
						$("#caseTexte").addClass("correctCase");
					}
					else {
						$("#caseTexte").addClass("errorCase");
					}
					$("#caseTexte").focus();
				}
				else {
					if (mCase.text() == mSquaredata.letter) {
						mCase.addClass("correctCase");
					}
					else {
						mCase.addClass("errorCase");
					}
				}
			}
	);
}

function deleteLetter(){
	if (mGrid.endGame) {
		return;
	}
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$("#caseTexte").val("");
	$("#caseTexte").focus();
}

function deleteWord() {
	if (mGrid.endGame) {
		return;
	}
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$(".casesMotSelectionne").each(
			function(index, element) {
				var mCase = $(this);
				if (mCase.hasClass("caseEdition")) {
					$("#caseTexte").val("");
					$("#caseTexte").focus();
				}
				else {
					mCase.text("");
				}
			}
	);
}

function deleteAll() {
	if (mGrid.endGame) {
		return;
	}
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$(".caseLettre").each(
			function(index, element) {
				var mCase = $(this);
				if (mCase.hasClass("caseEdition")) {
					$("#caseTexte").val("");
					$("#caseTexte").focus();
				}
				else {
					mCase.text("");
				}
			}
	);
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

/*recuperer tous les donnees taper par le user*/
function sauvegarder(){
	var mSquareList = mGrid.squareDataList;
	var listeLettre="";
	for (var mSquareId in mSquareList) {
		var mSquare = mSquareList[mSquareId];
		var mCase = $("#" + mSquareId);
		var letterUser = "";
		if (mCase.hasClass("caseEdition")) {
			letterUser =  $("#caseTexte").val();
		}
		else {
			letterUser = mCase.text();
		}
		if(letterUser != ""){
			var tabCord = mSquareId.split("-");
			listeLettre+= letterUser + ":" + tabCord[0] + ":" + tabCord[1] + "/" ;
		}
	}
	return listeLettre;
	alert(listeLettre);
}
/*sauvegarde la grille en cookie */
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}
/*cookie*/
function stringToTabCookie(chaine) {
	var listlettre=new Array();
	listlettre=chaine.split("/");
	var allList=new Array();
	var indice=0;
	for ( var int = 0; int < listlettre.length; int++) {
		var tmp=new Array();
		tmp =listlettre[int].split(":");
		if(tmp[0]!=""){
			allList[indice]=tmp[0];
			indice++;
			allList[indice]=tmp[1];
			indice++;
			allList[indice]=tmp[2];
			indice++;
		}
	}
	return allList;
}
/* retourne le tableau depuis un string */
function stringToTab(chaine) {
	var tab1=new Array();
	tab1=chaine.split("&");
	var list=new Array();
	list=tab1[1].split("=");
	var listlettre=new Array();
	listlettre=list[1].split("/");
	var allList=new Array();
	var indice=0;
	for ( var int = 0; int < listlettre.length; int++) {
		var tmp=new Array();
		tmp =listlettre[int].split(":");
		if(tmp[0]!=""){
			allList[indice]=tmp[0];
			indice++;
			allList[indice]=tmp[1];
			indice++;
			allList[indice]=tmp[2];
			indice++;
		}
	}
	return allList;
}