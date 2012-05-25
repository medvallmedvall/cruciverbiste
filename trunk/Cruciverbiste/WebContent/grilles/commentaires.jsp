<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h3> <s:property value ="getText('message.com')"/>  </h3>
<s:actionerror/>

<c:if test="${grille.commentaires != null}">
	<c:set var="commentaires" value="${grille.commentaires}"/>
</c:if>

<c:if test="${empty commentaires}"><s:property value ="getText('message.aucuncom')"/></c:if>
<c:forEach var="mComm" items="${commentaires}" varStatus="status">
	<c:set var="numTab" value="${status.index / 10}"/>
	<script>
		//alert("${numTab}");
	</script>
	<p class="commentaire">
		<b>${mComm.pseudo}</b> <s:property value ="getText('message.aecri')"/> ${mComm.dateFormatee} : <br/> <em>${mComm.contenu}</em> <br/>
		<c:if test="${authentification && droit != 0}">
			<a href="effacerCommentaire?idCommentaire=${mComm.idCommentaire}
			&idGrille=${grille.idGrille}">
			<s:property value ="getText('message.effacer')"/></a>
		</c:if>
	</p>
</c:forEach>
<c:choose>
	<c:when test="${authentification == true}">
		<div id="logMessage">
			<s:property value ="getText('message.poster')"/> <br/>
			<s:form action="posterCommentaire" method="post" onsubmit="return sendComment();">
				<s:textarea key="message.votrecom" id="commentaireArea" name="commentaire"  required="true" cols="44" rows="10"></s:textarea>
				<s:hidden name="idGrille" value="%{grille.idGrille}"></s:hidden>
				<s:submit key="message.post" name="submit"></s:submit>
			</s:form>
		</div>
	</c:when>
	<c:otherwise>
		<p id="logMessage"><s:property value ="getText('message.info')"/></p>
	</c:otherwise>
</c:choose>