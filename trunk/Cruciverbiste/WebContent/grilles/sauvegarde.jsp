<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:choose>
<c:when test="${sauvegardeOK}">
	<p>La grille a �t� sauvegard� avec succ�s</p>
</c:when>
<c:otherwise>
	<p>la grille n'a pas �t� sauvegard�</p>
	<s:actionerror />
</c:otherwise>
</c:choose>