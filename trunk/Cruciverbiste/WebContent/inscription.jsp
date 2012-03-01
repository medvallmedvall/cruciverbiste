<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue pour l'inscription</title>

<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>
	<%@ include file="entete.jspf" %>
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

	</div> <!-- fin centre-->
</body>
</html>