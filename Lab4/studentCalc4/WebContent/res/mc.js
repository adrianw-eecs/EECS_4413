function doSimpleAjax(address){
	var xhr = new XMLHttpRequest();

	buildString = "principalone=" + document.getElementById("principalone").value;
	buildString = buildString + "&" + "interestone=" + document.getElementById("interestone").value;
	buildString = buildString + "&" + "periodone=" + document.getElementById("periodone").value;
	buildString = buildString + "&" + "graceP=" + document.getElementById("grace").checked;

	alert(buildString);
	xhr.open("GET", (address + "?" + buildString), true);
	xhr.onreadystatechange = function () {
		handler(xhr);
	};

	xhr.send();
}

function handler(xhr){
	if((xhr.readyState ==4 ) && (xhr.status == 200)){
		var target = document.getElementById("ajaxTarget");
		target.innerHTML = xhr.responseText;
	}
}

function validate(){
	validatePrincipal()
	validateInterest()
	validatePeriod()
}

function validatePrincipal(){
	p = document.getElementById("principalone").value;
	if(isNaN(p) || p <= 0){
		alert("Principal is invalid!");
		document.getElementById("principalError").innerHTML = "Principal is invalid!";
		return false;
	} else {
		document.getElementById("principalError").innerHTML = "";
		return true;
	}
}

function validateInterest(){
	p = document.getElementById("interestone").value;
	if(isNaN(p) || p <= 0 || p >= 100){
		alert("Interest is invalid! Must be between 0 and 100");
		document.getElementById("interestError").innerHTML = "Interest is invalid! Must be between 0 and 100";
		return false;
	} else {
		document.getElementById("interestError").innerHTML = "";
		return true;
	}
}
function validatePeriod(){
	p = document.getElementById("periodone").value;
	if(isNaN(p) || p <= 0){
		alert("Period is invalid! Must be greater 0");
		document.getElementById("periodError").innerHTML = "Period is invalid! Must be greater 0";
		return false;
	} else {
		document.getElementById("periodError").innerHTML = "";
		return true;
	}
	
}