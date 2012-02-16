<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jeux Concours</title>
<link href="css/stylebegin.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/jquery.ui.all.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.17.custom.js"></script>
	<script type="text/javascript" src="js/jquery.bgiframe-2.1.2.js"></script>
	<link href="css/demos.css" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="js/ddaccordion.js"></script>
    <script type="text/javascript" src="js/fg.menu.js"></script>
    <link type="text/css" href="css/fg.menu.css" media="screen" rel="stylesheet" />
	<link type="text/css" href="css/cssbeginadd.css" media="screen" rel="stylesheet" />	
    <script type="text/javascript" src="js/jsbeginadd.js"></script>
</head>
<body>
<div id="barre">
		<a tabindex="0" href="#search-engines" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="flat"><span class="ui-icon ui-icon-triangle-1-s"></span>Langage</a>
		<div id="search-engines" class="hidden">
			<ul>
				<li><a href="#">English</a></li>
				<li><a href="#">Allemand</a></li>
				<li><a href="#">Espagnol</a></li>
			</ul>
		</div>
		<div id="connexioncss">
			<button id="connexion">Connexion</button>
			<button id="create-user">Inscription</button>
		</div>
	</div>
	<div id="lignetop"></div>
	<div id="top">
		<div id="logo">
			<a href="begin.html"><img src="images/cruciverbiste1.png" alt="" /></a>
		</div> 
	</div>
	<div id="lignetop"></div>
		<div id="sidebar1">
		<div class="glossymenu">
		<a class="menuitem" href="#">Jouer</a>
		<a class="menuitem" href="#" >Grilles en cours</a>
		<a class="menuitem" href="#">Créer une grille</a>
		<a class="menuitem" href="#">Mes Grilles</a>
		<a class="menuitem" href="#">Jeux concours</a>
		<a class="menuitem" href="#">Forum</a>	
		</div>
	</div>
	<div id="content"></div>
<div id="dialog-form" title="Create new user">
	<p class="validateTips">All form fields are required.</p>
	<form>
		<fieldset>
			<label for="name">Name</label>
			<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
			<label for="email">Email</label>
			<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
			<label for="password">Password</label>
			<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>
<div id="dialog-connexion" title="Connexion">
	<p class="validateTips">All form fields are required.</p>
	<form>
	<fieldset>
		<label for="pseudo">pseudo</label>
		<input type="text" name="pseudo" id="pseudo" class="text ui-widget-content ui-corner-all" />
		<label for="password">Password</label>
		<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
	<div id="footer">
		<p>Copyright 2012</p>
	</div>

</body>
</html>
		
	
