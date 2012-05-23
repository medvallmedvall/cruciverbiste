<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aide création de mots fleches</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="/entete.jspf"%>
	<div id="principal">

		<h1>Creation de mots fléchés : aide</h1>
		<h3>Ajouter une définition</h3>
		<ul>
			<li>Selectionner la case où placer une définition en cliquant
				sur la case ou en utilisant les touches directionnelles <br /> <img
				src="images/creationGrille/aide/aideMF0.jpg" alt="selection case" />
				<br />
			<br />
			</li>
			<li>Faire un clique droit ou utiliser le menu de gauche et
				choisir "ajouter une definition" avec le sens souhaité <br /> <img
				src="images/creationGrille/aide/aideMF01.png" alt="selection case" />
			</li>
			<li>La definition se place</li>
			<li>Pour ajouter une deuxième définition dans une case qui en
				contient une, faire un clique droit > ajouter définition</li>
			<li>Les cases oranges, lors d'un ajout de définition signifie qu'une défnition doit être placé dans ces cases</li>
		</ul>
		<br />
		<br />
		<h3>Modifier une définition</h3>
		<ul>
			<li>Selectionner la définition à modifier</li>
			<li>Manuellement : Double cliquer sur la définition à editer ou
				utiliser le menu de gauche "edition de definition > editer "</li>
			<li>Automatiquement avec un synonyme : 
				<ul>
					<li>écrire le mot associé à la définition</li>
					<li>utiliser le menu de gauche "edition de definition >
				obtenir synonyme "</li>
					<li>Si un synonyme est trouvé, choisir le synonyme souhaité: il se place dans la case définition</li>
				</ul>
				
		</ul>
		<h3>Supprimer une définition</h3>
		<ul>
			<li>Sélectionner la définition à supprimer</li>
			<li>Utiliser le menu de gauche " supprimer la definition"</li>
		</ul>
		<h3>Sauvegarder</h3>
		<p>Une grille en cours de création peut être sauvegardée pour être reprise à un autre moment</p>
		<ul>
			<li>Utiliser le menu de gauche " Fichier > sauvegarder"</li>
		</ul>
		<h3>Soumission de grille</h3>
		<p>Une grille peut être soumise une fois qu'elle est finie; elle sera alors en attente de validation</p>
		<ul>
			<li>Utiliser le menu de gauche " Fichier > soumettre la grille"</li>
		</ul>
	</div>
	<%@ include file="/pied.jspf"%>