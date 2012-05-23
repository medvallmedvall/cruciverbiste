<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aide cr�ation de mots fleches</title>

<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="styles/styleGrilles.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
</head>
<body>
	<%@ include file="/entete.jspf"%>
	<div id="principal">

		<h1>Creation de mots fl�ch�s : aide</h1>
		<h3>Ajouter une d�finition</h3>
		<ul>
			<li>Selectionner la case o� placer une d�finition en cliquant
				sur la case ou en utilisant les touches directionnelles <br /> <img
				src="images/creationGrille/aide/aideMF0.jpg" alt="selection case" />
				<br />
			<br />
			</li>
			<li>Faire un clique droit ou utiliser le menu de gauche et
				choisir "ajouter une definition" avec le sens souhait� <br /> <img
				src="images/creationGrille/aide/aideMF01.png" alt="selection case" />
			</li>
			<li>La definition se place</li>
			<li>Pour ajouter une deuxi�me d�finition dans une case qui en
				contient une, faire un clique droit > ajouter d�finition</li>
			<li>Les cases oranges, lors d'un ajout de d�finition signifie qu'une d�fnition doit �tre plac� dans ces cases</li>
		</ul>
		<br />
		<br />
		<h3>Modifier une d�finition</h3>
		<ul>
			<li>Selectionner la d�finition � modifier</li>
			<li>Manuellement : Double cliquer sur la d�finition � editer ou
				utiliser le menu de gauche "edition de definition > editer "</li>
			<li>Automatiquement avec un synonyme : 
				<ul>
					<li>�crire le mot associ� � la d�finition</li>
					<li>utiliser le menu de gauche "edition de definition >
				obtenir synonyme "</li>
					<li>Si un synonyme est trouv�, choisir le synonyme souhait�: il se place dans la case d�finition</li>
				</ul>
				
		</ul>
		<h3>Supprimer une d�finition</h3>
		<ul>
			<li>S�lectionner la d�finition � supprimer</li>
			<li>Utiliser le menu de gauche " supprimer la definition"</li>
		</ul>
		<h3>Sauvegarder</h3>
		<p>Une grille en cours de cr�ation peut �tre sauvegard�e pour �tre reprise � un autre moment</p>
		<ul>
			<li>Utiliser le menu de gauche " Fichier > sauvegarder"</li>
		</ul>
		<h3>Soumission de grille</h3>
		<p>Une grille peut �tre soumise une fois qu'elle est finie; elle sera alors en attente de validation</p>
		<ul>
			<li>Utiliser le menu de gauche " Fichier > soumettre la grille"</li>
		</ul>
	</div>
	<%@ include file="/pied.jspf"%>