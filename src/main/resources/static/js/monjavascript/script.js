
function verification(){
	var message=document.getElementById('message');
	var nom=document.getElementById('nom').value;
	var prenom=document.getElementById('prenom').value;
	var nomtuteur=document.getElementById('nomtuteur').value;
	var teltuteur=document.getElementById('teltuteur').value;
	var sexe=document.getElementById('sexe').value;
	var datenais=document.getElementById('datenais').value;
	if(nom==""||prenom==""||nomtuteur==""||teltuteur==""||sexe==""||datenais=="")
		
		return false;
	else
		
		return true;
		
}