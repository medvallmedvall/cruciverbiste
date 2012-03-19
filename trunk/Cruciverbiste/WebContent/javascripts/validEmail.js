function verif_email() {
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	
	if(reg.test(document.Emailer.mail.value) == false) {   
		alert("Ceci n'est pas une adresse électronique conforme!");   
		document.Emailer.mail.focus();  
		document.Emailer.mail.style.backgroundColor = 'rgb(255, 48, 53)';
		return false; 
	}
	return true;
}

function changeColor() {
	document.Emailer.mail.style.backgroundColor = 'rgb(255,255,255)';
}