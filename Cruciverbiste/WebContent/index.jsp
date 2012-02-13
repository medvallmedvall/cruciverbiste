<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bienvenue</title>
</head>
<body>
<%out.println("Bienvenue les cruciverbistes!"); %>
<br/>
<!-- Exemple de lien pour la selection d'une grille  -->
<a href="jouer_mots_croises?idGrid=3">Teste struts</a> <br/>
<c:out value="${idGrid}"/>
</body>
</html>