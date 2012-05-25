<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:choose>
<c:when test="${sauvegardeOK}">
	<p><s:property value = "getText('message.sauve')"/></p>
</c:when>
<c:otherwise>
	<p><s:property value = "getText('message.notsauve')"/></p>
	<s:actionerror />
</c:otherwise>
</c:choose>