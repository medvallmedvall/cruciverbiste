<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value ="getText('message.title')"/></title>
<link rel="stylesheet" type="text/css" href="styles/style.css"
	media="screen" />
<script type="text/javascript" src="javascripts/jquery-1.7.1.js"></script>
<sx:head/>
</head>
<body>
	<%@ include file="entete.jspf"%>
	<!--%@ include file="menu.jspf" %-->
	<%@ include file="secondaire.jspf"%>
	<div id="principal"><!-- debut principal -->
		<div id = "concours">
		<h2><s:property value ="getText('message.creerConcours')"/></h2>
			<s:form action = "creerConcours" method = "post">
			<table>
				<tr>
					<td><sx:datetimepicker key ="message.dateDeb" name="dateDebut" displayFormat="dd-MM-yyyy"/></td>
				</tr>
				<tr>
					<td><sx:datetimepicker key ="message.dateFin" name="dateFin" displayFormat="dd-MM-yyyy"/></td>
				</tr>
				<tr>
					<td><s:textfield key ="message.idGrille" name = "idGrille"/></td>
				</tr>
				<tr>
					<td><s:submit key = "message.cre" style="width: 100px;"/></td>
				</tr>
			
			</table>
			</s:form>
	</div>
		
	</div><!-- fin principale-->
</body>
</html>