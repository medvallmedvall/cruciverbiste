<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test JQuery</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

	<table id="grille1">
	<c:forEach var="i" step="1" begin="1" end="5">
		<tr>
		<c:forEach var="j" step="1" begin="1" end="5">
			<c:choose>
				<c:when test="${(i == 1) && (j == 1)}">
					<td>
						<p class="caseDefinition1"> def1 <br/> &rarr; 
						<span class="direction">right</span>
						</p>
					</td>
				</c:when>
				<c:when test="${(i == 2) && (j == 3)}">
					<td>
						<p class="caseDefinition2">def2 &darr;
						<span class="direction">right</span>
						</p>
						<p class="caseDefinition2">def3 &darr;
						<span class="direction">down</span>
						</p>
					</td>
				</c:when>
				<c:otherwise>
					<td></td>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</tr>
	</c:forEach>
		
	</table>
	
	<script type="text/javascript" src="javascript/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="javascript/grille.js"></script>
</body>
</html>