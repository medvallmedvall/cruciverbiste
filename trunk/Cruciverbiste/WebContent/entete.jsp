<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Begin Stylesheets -->
<link rel="stylesheet" href="styles/coda-slider-2.0.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />
<!-- End Stylesheets -->
<title>${param.titre}</title>
</head>
<body>

	<div id="langue">
		<!-- debut langue -->
		<a href="#" title="Anglais"><img alt="English"
			src="images/Angleterre.png" /></a> <a href="#" title="Allemand"><img
			alt="Allemand" src="images/Allemagne.png" /></a> <a href="#"
			title="Espagnol"><img alt="Espagnol" src="images/Espagne.png" /></a>
	</div>
	<!-- fin langue -->

	<div id="sous-entete">
		<!-- debut sous entete-->

		<div id="logo">
			<!-- debut logo -->
			<h1 id="titre">
				<a href="index.jsp"> <img alt="Cruciverbiste"
					src="images/cruciverbiste.jpg" /> Cruciverbiste
				</a>
			</h1>

		</div>
		<!-- fin logo -->


		<div id="login">
			<!-- debut login -->
			<form action="Connexion" method="post">
				<table>
					<tr>
						<td>Identifiant</td>
						<td><input type="text" name="identifiant"></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" name="motdepasse"></td>
					</tr>
					<tr>
						<td style="text-align: right;"><a href="inscription.jsp">
								<input type="button" value="Inscription" />
						</a></td>
						<td style="text-align: right;"><input type="submit"
							value="Connexion"></td>
					</tr>
				</table>
			</form>
		</div>
		<!--fin login-->

		<div id="pub">
			<!-- debut pub -->
			<a href="#" title="Pub"><img alt="Publicité" src="images/pub.png" /></a>
		</div>
		<!-- fin pub-->


	</div><!-- fin sous entete-->
	<!-- fin entete-->

	<div id="centre">

		<c:if test="${param.afficherMenu}">
			<div id="navigation">
				<ul>
					<li><a href="choixGrilleMotsFleches">Mots croisés</a></li>
					<li><a href="choixGrilleMotsCroises">Mots fléchés</a></li>
					<li><a href="index.html">Créer une grille</a></li>
					<li><a href="jeuxConcours.jsp">Jeux concours</a></li>
					<li><a href="forum.jsp">Forum</a></li>
				</ul>
			</div>
			<!-- fin navigation-->
		</c:if>

		<c:if test="${param.afficherSecondaire}">
			<div id="secondaire">
				<table>
					<caption>
						<strong>Sujets récents</strong>
					</caption>
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
			</div>
			<!-- fin secondaire-->
		</c:if>
		<div id="principal">
		
	