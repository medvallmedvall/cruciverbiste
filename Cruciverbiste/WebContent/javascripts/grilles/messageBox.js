$(document).ready(
		function() {
			
			
		});


function callMessageBox() {
	var docWidth = $("html").width();
	var docHeight = $("html").height();
	var winHeight = $(window).height();
	var left = (docWidth - $("#alertPers").width()) / 2;
	var top = (winHeight - $("#alertPers").height()) / 2;
	var currentScroll = $(document).scrollTop();
	$("#alertConteneur").height(docHeight);
	$("#alertPers").css("left", left);
	$("#alertPers").css("top", top + currentScroll);
	
	/*Cacher l'alert lors du click*/
	$("#alertPers").click(
			function(e) {
				$(this).parent().hide("slow");
			});
	
	/*Pour centrer l'alert verticalement lors d'un scroll*/
	$(document).scroll(
			function(e) {
				var scrollTmp = $(document).scrollTop();
				var cTop = $("#alertPers").position().top;
				var newTop = cTop + (scrollTmp - currentScroll);
				$("#alertPers").css("top", newTop);
				currentScroll = scrollTmp;
			});
	
	$("#alertConteneur").show("slow");
	
}


function callConfirmDialog(message, fonctionNo, fonctionYes) {
	var docWidth = $("html").width();
	var docHeight = $("html").height();
	var winHeight = $(window).height();
	var left = (docWidth - $("#confirmPers").width()) / 2;
	var top = (winHeight - $("#confirmPers").height()) / 2;
	var currentScroll = $(document).scrollTop();
	$("#confirmConteneur").height(docHeight);
	$("#confirmPers").css("left", left);
	$("#confirmPers").css("top", top + currentScroll);
	
	/*Changement du message*/
	$("#confirmPers p").text(message);
	
	/*Cacher l'alert lors du click*/
	$(".bYes").click(
			function(e) {
				fonctionYes();
				$("#confirmConteneur").hide("slow");
			});
	$(".bNo").click(
			function(e) {
				fonctionNo();
				$("#confirmConteneur").hide("slow");
			});
	
	
	/*Pour centrer l'alert verticalement lors d'un scroll*/
	$(document).scroll(
			function(e) {
				var scrollTmp = $(document).scrollTop();
				var cTop = $("#confirmPers").position().top;
				var newTop = cTop + (scrollTmp - currentScroll);
				$("#confirmPers").css("top", newTop);
				currentScroll = scrollTmp;
			});
	
	$("#confirmConteneur").show("slow");

}