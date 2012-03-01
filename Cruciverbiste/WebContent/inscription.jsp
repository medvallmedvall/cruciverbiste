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
 <%@ include file="menu.jspf" %>
 <%@ include file="secondaire.jspf" %>
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
<%@ include file="pied.jspf" %>	