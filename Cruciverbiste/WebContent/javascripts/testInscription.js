function verif_formulaire(){ 
	
	var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');
	
	if(document.Inscription.nom.value == "")  {  
		alert("Veuillez entrer votre nom!");   
		document.Inscription.nom.focus();  
		document.Inscription.nom.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;  
	} 
	if(document.Inscription.mail.value == "") {   
		alert("Veuillez entrer votre adresse �lectronique!");   
		document.Inscription.mail.focus();   
		return false;  
	} 
	if(reg.test(document.Inscription.mail.value) == false) {   
		alert("Ceci n'est pas une adresse �lectronique conforme!");   
		document.Inscription.mail.focus();  
		document.Inscription.mail.style.backgroundColor = 'rgb(255, 48, 53)';
		return false; 
	}
	if(document.Inscription.pseudo.value == "") {   
		alert("Veuillez entrer votre pseudo!");   
		document.Inscription.pseudo.focus(); 
		document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;  
	} 
	if(document.Inscription.motdepasse2.value == "") {
		alert("Rentrez de nouveau votre votre Mot de passe!");	   
		document.Inscription.motdepasse2.focus();	   
		return false; 
	}
	
	if (document.Inscription.dateNaissance == "") {
		alert("Rentrez votre date de naissance");
		document.Inscription.dateNaissance.focus();
		document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	if(document.Inscription.password.value == "") {	   
		alert("Veuillez entrer votre Mot de passe!");	   
		document.Inscription.password.focus();
		document.Inscription.password.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	
	if (document.Inscription.password.value != document.Inscription.motdepasse2.value) {
		alert("Rentrez le m�me mot de passe du premier champ dans le deuxi�me champ!");
		document.Inscription.password.style.backgroundColor = 'rgb(255, 48, 53)';
		document.Inscription.motdepasse2.style.backgroundColor = 'rgb(255, 48, 53)';
		return false;
	}
	return true;
}

function changeColor() {
	document.Inscription.nom.style.backgroundColor = 'rgb(255, 255, 255)';
	document.Inscription.mail.style.backgroundColor = 'rgb(255, 255, 255)';
	document.Inscription.password.style.backgroundColor = 'rgb(255, 255, 255)';
	document.Inscription.motdepasse2.style.backgroundColor = 'rgb(255, 255, 255)';
	document.Inscription.dateNaissance.style.backgroundColor = 'rgb(255, 255, 255)';
	document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 255, 255)';
}