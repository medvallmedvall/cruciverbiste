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

	<%
		int codeAscii = 65;
	%>

	<div id="definitions">
		<div id="defHorizontales">
			<p class="titreDefinitions">Horizontalement</p>
			<c:forEach var="i" step="1" begin="1" end="5">
				<c:choose>
					<c:when test="${i == 1}">
						<p class="ligne_definition">
							1 : Def1 
							<span class="rowIndex">0</span>
							<span class="colIndex">1</span>
						</p>
					</c:when>
					<c:when test="${i == 2}">
						<p class="ligne_definition">
							2 : Def2-1 
							<span class="rowIndex">1</span>
							<span class="colIndex">0</span>
						</p>
						<p class="ligne_definition">
							2 : Def2-2
							<span class="rowIndex">1</span>
							<span class="colIndex">3</span>
						</p>
					</c:when>
					<c:when test="${i == 4}">
						<p class="ligne_definition">
							4 : Def2-2
							<span class="rowIndex">3</span>
							<span class="colIndex">2</span>
						</p>
					</c:when>
					<c:otherwise>
						<p class="ligne_definition">
							${i} : Def${i}
							<span class="rowIndex">${i - 1}</span>
							<span class="colIndex">0</span>
						</p>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div id="defVerticales">
			<p class="titreDefinitions">Verticalement</p>
			<c:forEach var="i" step="1" begin="1" end="5">
				<c:choose>
					<c:when test="${i == 1}">
						<p class="ligne_definition">
							B : DefB-1
							<span class="rowIndex">1</span>
							<span class="colIndex">0</span>
						</p>
					</c:when>
					<c:when test="${i == 2}">
						<p class="ligne_definition">
							B : DefB-1
							<span class="rowIndex">0</span>
							<span class="colIndex">1</span>
						</p>
					</c:when>
					<c:when test="${i == 3}">
						<p class="ligne_definition">
							C : DefC
							<span class="rowIndex">2</span>
							<span class="colIndex">2</span>
						</p>
					</c:when>
					<c:when test="${i == 5}">
						<p class="ligne_definition">
							E : DefE-1
							<span class="rowIndex">0</span>
							<span class="colIndex">4</span>
						</p>
						<p class="ligne_definition">
							E : DefE-2
							<span class="rowIndex">3</span>
							<span class="colIndex">4</span>
						</p>
					</c:when>
					<c:otherwise>
						<p class="ligne_definition">
							<%out.print((char) codeAscii + "");%> : Def${i}
							<span class="rowIndex">0</span>
							<span class="colIndex">${i - 1}</span>
						</p>
					</c:otherwise>
				</c:choose>
				<% codeAscii++;%>
			</c:forEach>
		</div>
	</div>

	<%
		codeAscii = 65;
	%>

	<table id="grille1">
		<tr>
			<th></th>
			<c:forEach var="i" step="1" begin="1" end="5">
				<th>
					<%
						out.print((char) codeAscii + "");
					%>
				</th>
				<%
					codeAscii++;
				%>
			</c:forEach>
		</tr>
		<c:forEach var="i" step="1" begin="1" end="5">
			<tr>
				<th>${i}</th>
				<c:forEach var="j" step="1" begin="1" end="5">
					<c:choose>
						<c:when test="${(i == 1) && (j == 1)}">
							<td class="caseNoire">
								<p></p>
							</td>
						</c:when>
						<c:when test="${(i == 2) && (j == 3)}">
							<td class="caseNoire">
								<p></p>
							</td>
						</c:when>
						<c:when test="${(i == 4) && (j == 2)}">
							<td class="caseNoire">
								<p></p>
							</td>
						</c:when>
						<c:when test="${(i == 3) && (j == 5)}">
							<td class="caseNoire">
								<p></p>
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
	
	<div id="divTest" style="clear: both;">
	</div>

	<script type="text/javascript" src="javascript/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="javascript/grille2.js"></script>
</body>
</html>