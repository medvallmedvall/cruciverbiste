<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:choose>
<c:when test="${sauvegardeOK}">
	<p>La grille a �t� Finie avec succ�s</p>
</c:when>
<c:otherwise>
	<p>la grille n'a pas �t� Finie!!</p>
	<s:actionerror />
</c:otherwise>
</c:choose>