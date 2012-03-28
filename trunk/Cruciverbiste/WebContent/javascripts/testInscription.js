function verif_formulaire(){ 
	
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	
	if(document.Inscription.pseudo.value == "") {   
		alert("Veuillez entrer votre pseudo!");   
		document.Inscription.pseudo.focus(); 
		document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;  
	}
	if(document.Inscription.pseudo.value.length < 3) {   
		alert("Votre pseudo doit comporter au moins 3 caracteres!");   
		document.Inscription.pseudo.focus(); 
		document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;  
	}
	if(document.Inscription.password.value == "") {	   
		alert("Veuillez entrer votre Mot de passe!");	   
		document.Inscription.password.focus();
		document.Inscription.password.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	if(document.Inscription.password.value.length < 5) {	   
		alert("Votre mot de passe doit comporter au moins 5 caracteres!");	   
		document.Inscription.password.focus();
		document.Inscription.password.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	if(document.Inscription.motdepasse2.value == "") {
		alert("Rentrez de nouveau votre votre Mot de passe!");	   
		document.Inscription.motdepasse2.focus();	   
		return false; 
	}
	if (document.Inscription.password.value != document.Inscription.motdepasse2.value) {
		alert("Rentrez le meme mot de passe du premier champ dans le deuxieme champ!");
		document.Inscription.password.style.backgroundColor = 'rgb(255, 48, 53)';
		document.Inscription.motdepasse2.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	if(document.Inscription.mail.value == "") {   
		alert("Veuillez entrer votre adresse electronique!");   
		document.Inscription.mail.focus();   
		return false;  
	} 
	if(reg.test(document.Inscription.mail.value) == false) {   
		alert("Ceci n'est pas une adresse electronique conforme!");   
		document.Inscription.mail.focus();  
		document.Inscription.mail.style.backgroundColor = 'rgb(255, 48, 53)';
		return false; 
	}
	return true;
}

function changeColor(champ) {
	champ.style.backgroundColor = 'rgb(255, 255, 255)';
}