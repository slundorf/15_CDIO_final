/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

$( window ).on( "load", function() {
	console.log( "window loaded" );
	document.getElementById('eusrID').value ="";
	document.getElementById('eusrName').value = "Simon";
	document.getElementById('eini').value ="";
	document.getElementById('ecpr').value ="";
	document.getElementById('epsword').value="";
	document.getElementById('roleID').value ="";
	document.getElementById('roleName').value ="";
});

function getUser(){
	$.ajax({
		method: "GET",
		url: rootURL + '/getUsr',
		dataType: "json",
		success: function(data) { 
			var userData = data;
			alert(userData);
			},
		error: function() {
			console.log("Error loading users");
		}
	});
}