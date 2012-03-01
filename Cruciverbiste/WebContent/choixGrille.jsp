<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix grille</title>

<link rel="stylesheet" type="text/css" href="styles/style.css" media="screen" />

<!-- Begin Stylesheets -->
		<link rel="stylesheet" href="styles/coda-slider-2.0.css" type="text/css" media="screen" />
	<!-- End Stylesheets -->
	
	<!-- Begin JavaScript -->
		<script type="text/javascript" src="javascripts/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="javascripts/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="javascripts/jquery.coda-slider-2.0.js"></script>
		 <script type="text/javascript">
			$().ready(function() {
				$('#coda-slider-1').codaSlider();
			});
		 </script>
	<!-- End JavaScript -->

</head>
<body>
	

		<%@ include file="entete.jspf" %>
		
	<div id="centre">
		<div id="navigation">
			<ul>
				<li><a href="jouerMotsCroises.jsp">Mots croisés</a></li>
				<li><a href="jouerMotsFleches.jsp">Mots fléchés</a></li>
				<li><a href="index.html">Créer une grille</a></li>
				<li><a href="jeuxConcours.jsp">Jeux concours</a></li>
				<li><a href="forum.jsp">Forum</a></li>
			</ul>
		</div><!-- fin navigation-->
		<div id="secondaire">
			<table>
			   <caption><strong>Sujets récents</strong></caption>
					   <tr>
							<td><a href="Forum">C'est quoi les mots croisés?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">C'est quoi les mots fléchés?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">Une grille défi pour tous!</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">Le sens du mot cruciverbiste?</a></td>
					   </tr>
					   <tr>
							<td><a href="Forum">C'est quoi un mot palindrome?</a></td>
			</table>
		</div> <!-- fin secondaire-->
		<div id="principal">
			
				<div class="coda-slider-wrapper">
	<div class="coda-slider preload" id="coda-slider-1">
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 1</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus nulla, commodo a sodales sed, dignissim pretium nunc. Nam et lacus neque. Sed volutpat ante id mauris laoreet vestibulum. Nam blandit felis non neque cursus aliquet. Morbi vel enim dignissim massa dignissim commodo vitae quis tellus. Nunc non mollis nulla. Sed consectetur elit id mi consectetur bibendum. Ut enim massa, sodales tempor convallis et, iaculis ac massa. Etiam suscipit nisl eget lorem pellentesque quis iaculis mi mattis. Aliquam sit amet purus lectus. Maecenas tempor ornare sollicitudin.</p>
			</div>
		</div>
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 2</h2>
				<p>Proin nec turpis eget dolor dictum lacinia. Nullam nunc magna, tincidunt eu porta in, faucibus sed magna. Suspendisse laoreet ornare ullamcorper. Nulla in tortor nibh. Pellentesque sed est vitae odio vestibulum aliquet in nec leo.</p>
			</div>
		</div>
		<div class="panel">
			<div class="panel-wrapper">
				<h2 class="title">Panel 3</h2>
				<p>Cras luctus fringilla odio vel hendrerit. Cras pulvinar auctor sollicitudin. Sed lacus quam, sodales sit amet feugiat sit amet, viverra nec augue. Sed enim ipsum, malesuada quis blandit vel, posuere eget erat. Sed a arcu justo. Integer ultricies, nunc at lobortis facilisis, ligula lacus vestibulum quam, id tincidunt sapien arcu in velit. Vestibulum consequat augue et turpis condimentum mollis sed vitae metus. Morbi leo libero, tincidunt lobortis fermentum eget, rhoncus vel sem. Morbi varius viverra velit vel tempus. Morbi enim turpis, facilisis vel volutpat at, condimentum quis erat. Morbi auctor rutrum libero sed placerat. Etiam ipsum velit, eleifend in vehicula eu, tristique a ipsum. Donec vitae quam vel diam iaculis bibendum eget ut diam. Fusce quis interdum diam. Ut urna justo, dapibus a tempus sit amet, bibendum at lectus. Sed venenatis molestie commodo.</p>
			</div>
		</div>
		
	
		
		
	</div><!-- .coda-slider -->
</div><!-- .coda-slider-wrapper -->
			
			
		</div><!-- fin principale-->
	





	</div><!-- fin centre-->

</body>
</html>