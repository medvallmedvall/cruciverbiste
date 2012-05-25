<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<p><s:property value ="getText('message.synMot')"/><b> &nbsp; ${mot}</b> </p>

<c:choose>
	<c:when test="${empty synonyms}">
		<p><s:property value ="getText('message.aucunSyn')"/></p>
	</c:when>
	<c:otherwise>
		<ul>
			<c:forEach var="mot" items="${synonyms}">
				<li>
					<a href="#" onclick="selectSynonym(${mot.idMot}, '${mot.mot}'); return false;">
						${mot.mot}
					</a>
				</li>
			</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>

