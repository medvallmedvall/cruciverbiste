<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="sujet" items="${sujetsRecents}" varStatus="status">
	<p><A HREF="RedirectionForum?f=${sujet.forum_id}&t=${sujet.topic_id}"><b>${sujet.topic_title}</b> </A><b>${sujet.topic_first_poster_name}</b></p>
</c:forEach>