<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h3>Commentaires : </h3>
<s:actionerror/>

<c:if test="${grille.commentaires != null}">
	<c:set var="commentaires" value="${grille.commentaires}"/>
</c:if>

<c:if test="${empty commentaires}">Il n'y a aucun commentaire</c:if>
<c:forEach var="mComm" items="${commentaires}" varStatus="status">
	<c:set var="numTab" value="${status.index / 10}"/>
	<script>
		//alert("${numTab}");
	</script>
	<p class="commentaire">
		<b>${mComm.pseudo}</b> a ecrit le ${mComm.dateFormatee} : <br/>
		<em>${mComm.contenu}</em>
	</p>
</c:forEach>
<c:choose>
	<c:when test="${authentification == true}">
		<div id="logMessage">
			Poster un commentaire : <br/>
			<s:form action="posterCommentaire" method="post" onsubmit="return false;">
				<s:textarea id="commentaireArea" name="commentaire" label="Votre commentaire: " required="true" cols="44" rows="10"></s:textarea>
				<s:hidden name="idGrille" value="%{grille.idGrille}"></s:hidden>
				<s:submit name="submit" value="poster" onclick="sendComment()"></s:submit>
			</s:form>
		</div>
	</c:when>
	<c:otherwise>
		<p id="logMessage">Vous devez vous connecter pour pouvoir poster un commentaire!</p>
	</c:otherwise>
</c:choose>