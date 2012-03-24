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

function GrilleMotsFleches(width, height) {
	this.width = width;
	this.height = height;
	this.squareDataList = new Array();
	this.definitionList = new Array();
	this.horizontal = true;
	this.endGame = false;

	GrilleMotsFleches.prototype.addDefinition = function(idDef, textDef, word, synonym, orientation, coord) {
		var mDef = new Definition(idDef, textDef, word, synonym, coord, orientation);
		this.definitionList[idDef] = mDef;
	};

	GrilleMotsFleches.prototype.initialize = function() {
		/*Creation des cases de la grille selon les definitions*/
		var mDefList = mGrid.definitionList;
		for (var idDef in mDefList) {
			var mDef = mDefList[idDef];
			//On rajoute la definition dans la case
			var x = mDef.coordDef.x;
			var y = mDef.coordDef.y;
			var mSelector = "#" + x + "-" + y;
			var mCase = $(mSelector);
			mCase.addClass("caseDefinitions");
			var mElement = "<p class='definitionMF' id='" + mDef.idDefinition + "'>" + mDef.textDef + "</p>";
			if (mCase.children(".conteneurDef").length == 0) {
				var mConteneur = "<div class='conteneurDef'></div>";
				mCase.append(mConteneur);
				//alert(mCase.children(".conteneurDef").length);
			}
			var mDefCont = mCase.children(".conteneurDef").first();
			mDefCont.append(mElement);
			//var defNb = mDefCont.children("p").length;
			//var heigthCase = 50 / defNb;
			//mDefCont.children("p").css("height", heigthCase + "px");
			
			
			//mCase.append(mElement);
			
			/*var defNb = mCase.children("p").length;
			var heigthCase = 100 / defNb;
			mCase.children("p").css("height", heigthCase + "%");*/
			
			//rajout des fleches liees a la definition dans la case d'a cote
			
			switch(mDef.orientation) {
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
			for (var j = 0; j < mDef.word.length; j++) {
				var letter = mDef.word[j];
				var mId = x + "-" + y;
				var mSquare = new SquareData(mId, letter);
				var oldSquare = this.squareDataList[mId];
				if (oldSquare != undefined) {
					mSquare.idDefinition = oldSquare.idDefinition;
				}
				mSquare.idDefinition[mDef.orientation] = mDef.idDefinition;
				this.squareDataList[mId] = mSquare;
				var mSelector = "#" + mId;
				var mCase = $(mSelector);
				mCase.addClass("caseLettre");
				if (j == 0) {
					//ajouter l'image
					mCase.css("background-image", urlImage).css("background-repeat", "no-repeat");
				}
				//mCase.text(this.squareDataList[mId].letter);
				//si le mot est de sens horizontal
				if ((mDef.orientation == 1) || (mDef.orientation == 4)) {
					x++;
				}
				//si le mot est de sens vertical
				else if ((mDef.orientation == 2) || (mDef.orientation == 3)) {
					y++;
				}
			}
		}
		/*reajustement des hauteurs de definitions dans les cases*/
		$(".conteneurDef").each(
				function(index, element) {
					$(this).children("p").last().css("height", "100%");
				});
	};

	GrilleMotsFleches.prototype.switchOrientation = function(pCase, pHorizontal) {
		var mIdCase = pCase.attr("id");
		var mSquareData = mGrid.squareDataList[mIdCase];
		if (pHorizontal) {
			this.horizontal = ((mSquareData.idDefinition[1] != -1) || (mSquareData.idDefinition[4] != -1));
		}
		else {
			this.horizontal = !((mSquareData.idDefinition[2] != -1) || (mSquareData.idDefinition[3] != -1));
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

	$(".definitionMF").click(
			function(e) {
				var mIdDef = $(this).attr("id");
				var mDef = mGrid.definitionList[mIdDef];
				var x = mDef.coordDef.x;
				var y = mDef.coordDef.y;
				switch(mDef.orientation) {
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
	
	/*Zoom lorsque la souris est sur une definition */
	
	$(".definitionMF").mouseenter(
			function(e) {
				var mCase = $(this).parent();
				var topG = $("#grilleMotFleche").position().top;
				var leftG = $("#grilleMotFleche").position().left;
				var top = mCase.position().top + topG - 5;
				var left = mCase.position().left + leftG - 5;
				$("#zoomDiv").css("top", top);
				$("#zoomDiv").css("left", left);
				var mText = $(this).text();
				$("#zoomDiv").text(mText);
				$("#zoomDiv").show("fast");
			});
	$("#zoomDiv").mouseleave(
			function(e) {
				$("#zoomDiv").hide("fast");
			});
	$("#grilleMotFleche").mouseenter(
			function(e) {
				$("#zoomDiv").hide("fast");
			});
	
	/*configuration de l'alerte lors d'une victoire*/
	
	var docWidth = $("html").width();
	var docHeight = $("html").height();
	var winHeight = $(window).height();
	//alert(docHeight);
	//alert(docHeight + " - " + winHeight);
	var left = (docWidth - $("#alertPers").width()) / 2;
	var top = (winHeight - $("#alertPers").height()) / 2;
	$("#alertConteneur").height(docHeight);
	$("#alertPers").css("left", left);
	$("#alertPers").css("top", top);
	
	/*Cacher l'alert lors du click*/
	$("#alertPers").click(
			function(e) {
				$(this).parent().hide("slow");
			});
	
	var currentScroll = 0;
	
	
	/*Pour centrer l'alert verticalement lors d'un scroll*/
	$(document).scroll(
			function(e) {
				var scrollTmp = $(document).scrollTop();
				var cTop = $("#alertPers").position().top;
				var newTop = cTop + (scrollTmp - currentScroll);
				$("#alertPers").css("top", newTop);
				currentScroll = scrollTmp;
			});
	

	/*Lors de l'appuie sur les touches speciales (direction, backspace, ctr...)*/

	$("#grilleMotFleche").keyup(
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

	$("#grilleMotFleche").keypress(
			function(event) {
				event.preventDefault();
				if (mGrid.endGame) {
					return false;
				}
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
		var mCase = $(this);
		mGrid.switchOrientation(mCase, mGrid.horizontal);
		selectSquare(mCase);
		var topG = $("#grilleMotFleche").position().top;
		var leftG = $("#grilleMotFleche").position().left;
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
	alert("Fin de la partie !");
	/*mGrid.endGame = true;
	$("#caseTexte").attr("disabled", true);*/
}



/*Fonction qui selectionne une case ainsi que sa definition*/

function selectSquare(mCase) {
	//deselection

	var letter = $("#caseTexte").val();
	$(".caseEdition").text(letter);
	$(".caseLettre").removeClass("caseEdition");
	$("#caseTexte").remove();
	$(".caseLettre").removeClass("casesMotSelectionne");
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$(".definitionMF").removeClass("definitionSelectionneeMF");

	//selection de la definition associe au mot
	var mIdCase = mCase.attr("id");
	var mSquareData = mGrid.squareDataList[mIdCase];
	var mIdDef = undefined;
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
				for (var mOrientKey in mSquareData.idDefinition) {
					var mIdDefTmp = mSquareData.idDefinition[mOrientKey];
					if (mIdDefTmp == mIdDef) {
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
	/*if (mGrid.endGame) {
		$("#caseTexte").attr("disabled", true);
	}*/
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
	var res = confirm("Voulez vous vraiment obtenir la solution?");
	if (!res) {
		$("#caseTexte").focus();
		return;
	}
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

function getSynonym() {
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	var mDefId = $(".definitionSelectionneeMF").attr("id");
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
	$(".caseLettre").removeClass("correctCase");
	$(".caseLettre").removeClass("errorCase");
	$("#caseTexte").removeClass("correctCase");
	$("#caseTexte").removeClass("errorCase");
	$("#caseTexte").val("");
	$("#caseTexte").focus();
}

function deleteWord() {
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



