<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul>
<c:forEach var="mot" items="${synonyms}">
	<li>${mot.mot}</li>
</c:forEach>
</ul>