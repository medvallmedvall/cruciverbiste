function verif_formulaire(){ 
	
	if(document.Inscription.nom.value == "")  {  
		alert("Veuillez entrer votre nom!");   
		document.formulaire.nom.focus();   
		document.Inscription.nom.style.backgroundColor = 'rgb(255, 0, 0)';
		return false;  
	} 
	if(document.Inscription.mail.value == "") {   
		alert("Veuillez entrer votre adresse électronique!");   
		document.formulaire.courriel.focus();   
		return false;  
	} 
	if(document.Inscription.mail.value.indexOf('@') == -1) {   
		alert("Ce n'est pas une adresse électronique!");   
		document.formulaire.courriel.focus();  
		document.Inscription.mail.style.backgroundColor = 'rgb(255, 0, 0)';
		return false; 
	}
	if(document.Inscription.pseudo.value == "") {   
		alert("Veuillez entrer votre pseudo!");   
		document.formulaire.age.focus(); 
		document.Inscription.pseudo.style.backgroundColor = 'rgb(255, 0, 0)';
		return false;  
	} 
	if(document.Inscription.motdepasse2.value == "") {
		alert("Rentrez de nouveau votre votre Mot de passe!");	   
		document.formulaire.courriel.focus();	   
		return false; 
	}
	if(document.Inscription.password.value == "") {	   
		alert("Veuillez entrer votre Mot de passe!");	   
		document.formulaire.age.focus();
		document.Inscription.password.style.backgroundColor = 'rgb(255, 0, 0)';
		return false;
	}
	
	if (document.Inscription.password.value != document.Inscription.motdepasse2.value) {
		alert("Rentrez le même mot de passe du premier champ dans le deuxième champ!");
		document.Inscription.password.style.backgroundColor = 'rgb(255, 0, 0)';
		document.Inscription.motdepasse2.style.backgroundColor = 'rgb(255, 0, 0)';
		return false;
	}
}