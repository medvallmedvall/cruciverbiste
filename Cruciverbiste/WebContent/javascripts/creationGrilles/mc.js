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
	//$("#menuContext").css("display", "none");

	//selection de la case
	mCase.addClass("caseEdition");
	var input = "<input type='text' id='caseTexte' name='caseTexte' maxlength='1'/>";
	mCase.append(input);
	$("#caseTexte").focus();
}


$(document).ready(function(){
	mGrid.initialize();

	$(".caseLettre").dblclick(function(event) {
		$(this).removeClass("caseLettre");
		$(this).addClass("caseNoire");

		$(this).dblclick(function(event) {
			$(this).removeClass("caseNoire");
			$(this).addClass("caseLettre");
		});
	});


	$(".caseLettre").click(function(event) {
		var mCase = $(this);
		selectSquare(mCase);
	});

	/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

	$("#grille1").keyup(
			function(event) {
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
						//mGrid.switchOrientation(mOthCase, sens);
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
				var c = codeTouche(event);
				var regex = /[a-zA-Z]/;
				var char = String.fromCharCode(c);
			
				$(".caseEdition").text(c);
				return false;
			}
	);

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

	var mCase = $(".caseLettre:first");
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



