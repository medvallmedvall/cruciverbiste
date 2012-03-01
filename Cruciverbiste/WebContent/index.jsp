<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue Cruciverbistes!</title>
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
</head>
<body>	
 <%@ include file="entete.jspf" %>
		<div id="centre"><!-- debut centre -->
		
			<div id="navigation"><!-- debut navigation -->
				<ul>
					<li><a href="jouerMotsCroises.jsp">Mots croisés</a></li>
					<li><a href="jouerMotsFleches.jsp">Mots fléchés</a></li>
					<li><a href="index.html">Créer une grille</a></li>
					<li><a href="jeuxConcours.jsp">Jeux concours</a></li>
					<li><a href="jeuxConcours.jsp">Contacts</a></li>
					<li><a href="forum.jsp">Forum</a></li>
				</ul>
			</div><!-- fin navigation-->
 
 			

			<div id="secondaire"><!-- debut secondaire -->
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
				   	</tr>
				</table>
			</div> <!-- fin secondaire-->
			
			<div id="principal"><!-- debut principal -->
				<h3>Grille du jour</h3> 
				<div id="grille"><a href="jouer?idGrid=2"><img alt="Grille" src="images/grille.jpg" ></a></div>
			</div><!-- fin principale-->
			
		</div> <!-- fin centre-->
</body>
</html>