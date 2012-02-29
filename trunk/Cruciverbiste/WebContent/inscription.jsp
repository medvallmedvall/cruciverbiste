<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue pour l'inscription</title>
<link rel="stylesheet" type="text/css" href="styles/main.css" media="all" />
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>
<div id="global">

	<div id="langue">
					
						<a href="#" title="Anglais"><img style="cursor:pointer; cursor:hand;" alt="English" src="images/Angleterre.png" /></a>
						<a href="#" title="Allemand"><img style="cursor:pointer; cursor:hand;" alt="Allemand" src="images/Allemagne.png" /></a>
						<a href="#" title="Espagnol"><img style="cursor:pointer; cursor:hand;" alt="Espagnol" src="images/Espagne.png" /></a>
	</div>
	<div id="entete">
	<div id="login">
	
			<form action="Connexion" method="post">
				<table>
					
					<tr>
						<td>Identifiant</td>
						<td><input type="text" name="identifiant"></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" name="motdepasse" ></td>
					</tr>
					<tr>
						<td style="text-align:right;"><a href="inscription.jsp"> <input type="button" value="Inscription" /></a></td>
						<td style="text-align:right;" ><input type="submit" value="Connexion"></td>	
					</tr>
				</table>
			</form>
		</div><!-- login-->
		
		<div id="logo">
			<a href="index.jsp">
			<h1>
				<img alt="Cruciverbiste" src="images/cruciverbiste.jpg" />
				<span>Cruciverbiste</span>
				
			</h1>
			
		</a> </div>
		<div id="pub">
		<a href="#" title="Pub"><img style="cursor:pointer; cursor:hand;" alt="English" src="images/pub.png" /></a>
		</div> <!-- fin pub-->	
		
		
	</div> <!-- fin entete-->

	<div id="centre">
		<div id="navigation">
			<ul>
				<li><a href="jouerMotsCroises.jsp">Mots croisés</a></li>
				<li><a href="jouerMotsFleches.jsp">Mots fléchés</a></li>
				<li><a href="index.html">Créer une grille</a></li>
				<li><a href="index.html">Jeux concours</a></li>
				<li><a href="index.html">Forum</a></li>
			</ul>
		</div><!-- fin navigation-->

		<div id="principal">
		
		 <h3>Inscription du client :</h3>
		 <form action="Inscription" method="post">
			<table>
				<tr>
					<td>Prénom</td>
					<td><input type="text" name="prenom" /></td>
				</tr>
				<tr>
					<td>Nom</td>
					<td><input type="text" name="nom" /></td>
				</tr>
				<tr>
					<td>Pseudo</td>
					<td><input type="text" name="pseudo" /></td>
				</tr>
				<tr>
					<td>Mot de passe</td>
					<td><input type="password" name="motdepasse" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;"><input style="width: 100px;" type="submit" value="Inscription" /></td>
				</tr>
			</table> 
			
		</form>
		
			
		</div><!-- fin principale-->

		<div id="secondaire">
			<table>
			   <caption><strong>Sujets récents</strong></caption>
					   <tr>
							<td><a href="Forum">C'est quoi les mots croisés?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">C'est quoi les mots fléchés?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">Une grille défi pour tous!</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">Le sens du mot cruciverbiste?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">C'est quoi un mot palindrome?</a></td>
			</table>
		</div> <!-- fin secondaire-->

	</div> <!-- fin centre-->

</div><!-- fin global-->
</body>
</html>