<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="entete.jsp">
	<jsp:param value="monTitre" name="titre" />
	<jsp:param value="true" name="afficherMenu" />
	<jsp:param value="false" name="afficherSecondaire"/>
</jsp:include>

<!-- style ici -->

<!-- Begin JavaScript -->
<script type="text/javascript" src="javascripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="javascripts/jquery.coda-slider-2.0.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$('#coda-slider-1').codaSlider();
	});
</script>
<!-- End JavaScript -->


<c:choose>
	<c:when test="${idGridType == 1}">
		<h1>Mots Fléchés</h1>
	</c:when>
	<c:when test="${idGridType == 2}">
		<h1>Mots Croisés</h1>
	</c:when>
	<c:otherwise>
		<h1>Erreur...</h1>
	</c:otherwise>
</c:choose>

<c:forEach>


</c:forEach>







<div class="coda-slider-wrapper">
	<div class="coda-slider preload" id="coda-slider-1">
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 1</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Maecenas metus nulla, commodo a sodales sed, dignissim pretium
					nunc. Nam et lacus neque. Sed volutpat ante id mauris laoreet
					vestibulum. Nam blandit felis non neque cursus aliquet. Morbi vel
					enim dignissim massa dignissim commodo vitae quis tellus. Nunc non
					mollis nulla. Sed consectetur elit id mi consectetur bibendum. Ut
					enim massa, sodales tempor convallis et, iaculis ac massa. Etiam
					suscipit nisl eget lorem pellentesque quis iaculis mi mattis.
					Aliquam sit amet purus lectus. Maecenas tempor ornare sollicitudin.</p>
			</div>
		</div>
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 2</h2>
				<p>Proin nec turpis eget dolor dictum lacinia. Nullam nunc
					magna, tincidunt eu porta in, faucibus sed magna. Suspendisse
					laoreet ornare ullamcorper. Nulla in tortor nibh. Pellentesque sed
					est vitae odio vestibulum aliquet in nec leo.</p>
			</div>
		</div>
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 3</h2>
				<p>Cras luctus fringilla odio vel hendrerit. Cras pulvinar
					auctor sollicitudin. Sed lacus quam, sodales sit amet feugiat sit
					amet, viverra nec augue. Sed enim ipsum, malesuada quis blandit
					vel, posuere eget erat. Sed a arcu justo. Integer ultricies, nunc
					at lobortis facilisis, ligula lacus vestibulum quam, id tincidunt
					sapien arcu in velit. Vestibulum consequat augue et turpis
					condimentum mollis sed vitae metus. Morbi leo libero, tincidunt
					lobortis fermentum eget, rhoncus vel sem. Morbi varius viverra
					velit vel tempus. Morbi enim turpis, facilisis vel volutpat at,
					condimentum quis erat. Morbi auctor rutrum libero sed placerat.
					Etiam ipsum velit, eleifend in vehicula eu, tristique a ipsum.
					Donec vitae quam vel diam iaculis bibendum eget ut diam. Fusce quis
					interdum diam. Ut urna justo, dapibus a tempus sit amet, bibendum
					at lectus. Sed venenatis molestie commodo.</p>
			</div>
		</div>




	</div>
	<!-- .coda-slider -->
</div>
<!-- .coda-slider-wrapper -->

<jsp:include page="pied.jsp"/>