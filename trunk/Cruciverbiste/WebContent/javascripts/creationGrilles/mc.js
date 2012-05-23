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

function GrilleMotsCroises(width, height) {
	this.width = width;
	this.height = height;
	this.squareDataList = new Array();
	this.definitionList = new Array();
	this.horizontal = true;
	this.endGame = false;

	GrilleMotsCroises.prototype.addDefinition = function(idDef, word, synonym, coord, orientation) {
		var mDef = new Definition(idDef, word, synonym, coord, orientation);
		this.definitionList[idDef] = mDef;
	};

	GrilleMotsCroises.prototype.initialize = function() {
		//initialisation
		for (var i = 0; i < this.height; i++) {
			for (var j = 0; j < this.width; j++) {
				var mId = i + "-" + j;
				var square = new SquareData(mId, "");
				this.squareDataList[mId] = square;
				$("#" + mId).addClass("caseLettre");
			}
		}
		/*Creation des cases de la grille selon les definitions*/
		/*var mDefList = mGrid.definitionList;
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
		}*/
		/*ajout des cases noires*/
		/*$("#grille1 td:not(.caseLettre)").each(
				function(index, mCase) {
					$(this).addClass("caseLettre");
					//$(this).addClass("caseNoire");
				}
		);*/
	};

}

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


$(document).ready(function(){
	mGrid.initialize();
	
	var DELAY = 200, clicks = 0, timer = null;

	$(document).ready(function() {
		$(".caseLettre").click(function(event){
			var mCase = $(this);
	        clicks++;  //count clicks

	        if(clicks === 1) {
	        	if(!$(this).hasClass('caseNoire')) {
	        		timer = setTimeout(function() {
														//perform single-click action  
	        				selectSquare(mCase);
	        				clicks = 0;             //after action performed, reset counter
	        		}, DELAY);
	        	}
	        } else {

	            clearTimeout(timer);    //prevent single-click action
	            						//perform double-click action
	            var letter = $("#caseTexte").val();		//supprime selection
	        	$(".caseEdition").text(letter);
	        	$(".caseLettre").removeClass("caseEdition");
	        	$("#caseTexte").remove();
	        	$(".caseLettre").removeClass("casesMotSelectionne");
	        	$(".caseLettre").removeClass("correctCase");
	        	$(".caseLettre").removeClass("errorCase");
	        	$(".ligne_definition").removeClass("definitionSelectionnee2");
	        	//retire case noire
	        	if($(this).hasClass('caseNoire')) {
	        		$(this).removeClass("caseNoire");
	        		$(this).addClass("caseLettre");
	        		//vide tableau
	        		
	        		if($(this).before) {//tester case noir

	        		}
	        		
	        		var idCase = $(this).attr("id");
					var mTab = idCase.split("-");
					var x = mTab[0];
					var y = mTab[1];
					var addy = y;
					var addx = x;
					addx++;
					addy++;
					var nbCaseNoirey = 0;
					var nbCaseNoirex = 0;
					$("#grilleMotFleche td").each(function(index, element) {
	        			var mId = $(this).attr("id");
	        			var mTab = mId.split("-");
						var xTmp = mTab[0];
						var yTmp = mTab[1];
						if (yTmp == y) {
							if ($(this).hasClass("caseNoire")) {
								nbCaseNoirey++;
							}
						}
						if (xTmp == x) {
							if ($(this).hasClass("caseNoire")) {
								nbCaseNoirex++;
							}
						}
	        		});
					var mElemry = "#defH" + addy + "-" + (nbCaseNoirey+2);
					var mElemrx = "#defV" + addx + "-" + (nbCaseNoirex+2);
	        		$(mElemry).remove();
	        		$(mElemrx).remove();
	        		
	        	} else {
	        		// ajoute case noire
	        		$(this).removeClass("caseLettre");
	        		$(this).addClass("caseNoire");
	        		//remplit tableau
	        		var idCase = $(this).attr("id");
					var mTab = idCase.split("-");
					var x = mTab[0];
					var y = mTab[1];
					var mDefH = $("#defH" + y);
					var mDefV = $("#defV" + x);
					var mNewInput = "<input type='text' />";
					var addy = y;
					var addx = x;
					addy++;
					addx++;
					var nbCaseNoirey = 0;
					var nbCaseNoirex = 0;
					$("#grilleMotFleche td").each(function(index, element) {
	        			var mId = $(this).attr("id");
	        			var mTab = mId.split("-");
						var xTmp = mTab[0];
						var yTmp = mTab[1];
						if (yTmp == y) {
							if ($(this).hasClass("caseNoire")) {
								nbCaseNoirey++;
							}
						}
						if (xTmp == x) {
							if ($(this).hasClass("caseNoire")) {
								nbCaseNoirex++;
							}
						}
	        		});
					var mElemay = "<div id='defH" + addy + "-" + (nbCaseNoirey+1) +"'>" + addy + "-" + (nbCaseNoirey+1) + " " + mNewInput + "</div>";
					var mElemax = "<div id='defV" + addx + "-" + (nbCaseNoirex+1) +"'>" + addx + "-" + (nbCaseNoirex+1) + " " + mNewInput + "</div>";
					mDefH.append(mElemay);
					mDefV.append(mElemax);
	        	}
	            clicks = 0;             //after action performed, reset counter
	        }

	    })
	    $(this).dblclick(function(event) {
	    	event.preventDefault();  //cancel system double-click event
		});
		
	});

	/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

	var sens = true;
	$(".caseLettre").keyup(
			function(event) {
				var c = codeTouche(event);
				//si on a appuyé sur des fleches directionnelles ou la touche pour effacer
				if ((c == 8) || ((c >= 37) && (c <= 40))) {
					var mCase = $(this);
					if (mCase == undefined) {
						return;
					}
					var mIdString = mCase.attr("id");
					var mTab = mIdString.split("-");
					var x = mTab[0];
					var y = mTab[1];
					switch(c) {
					case 8:
						//backspace
						$("#caseTexte").val(" ");
						if (sens) {
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
						if(!$(mOthCase).hasClass('caseNoire')){
							selectSquare(mOthCase);
						}
					}
				} else {
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
						if (sens) {
							x++;
						}
						else {
							y++;
						}
					}
					var idOthCase = x + "-" + y;
					if (mGrid.squareDataList[idOthCase] != undefined) {
						var mOthCase = $("#" + idOthCase);
						if(!$(mOthCase).hasClass('caseNoire')){
							selectSquare(mOthCase);
						}
					}
				}
				return false;
			}
	);

	/*Lors de l'appui sur les touches alpha-numerique, ponctuation...*/

	/*$("#grille1").keypress(
			function(event) {
				event.preventDefault();
				var c = codeTouche(event);
				var regex = /[a-zA-Z]/;
				var char = String.fromCharCode(c);
			
				$(".caseEdition").text(c);
				
				return false;
			}
	);*/

	/* Menu contextuel */

	/*$(".caseLettre").bind("contextmenu", function(e){  
		//on selectionne la case
		var mCase = $(this);
		mGrid.switchOrientation(mCase, mGrid.horizontal);
		selectSquare(mCase);
		var topG = $("#grille1").position().top;
		var leftG = $("#grille1").position().left;
		var top = mCase.position().top + topG + 20;
		var left = mCase.position().left + leftG + 25;
		$("#menuContext").css("display", "inline");
		$("#menuContext").css("top", top);
		$("#menuContext").css("left", left);
		return false;  
	});

	$(document).click(
			function(e) {
				$("#menuContext").css("display", "none");
			}
	);*/



	/*selection de la 1ere case de la grille*/

	//var mCase = $(".caseLettre:first");
	//mGrid.switchOrientation(mCase, true);
	//selectSquare(mCase);

	/*Dynamise le menu horizontal*/
	//mainmenu();

});


/* Menu horizontal dynamique */

/*function mainmenu(){
	$(" #nav ul ").css({display: "none"}); // Opera Fix
	$(" #nav li").hover(function(){
		$("#menuContext").css("display", "none");
		$(this).find('ul:first').css({visibility: "visible",display: "none"}).show(400);
	},function(){
		$("#menuContext").css("display", "none");
		$(this).find('ul:first').css({visibility: "hidden"});
	});
};*/






/*fontion qui retourne le code de la touche*/

function codeTouche(event) {
	for (prop in event) {
		if (prop == "which") {
			return event.which;
		}
	}
	return event.keyCode;
}



