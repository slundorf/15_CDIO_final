/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

function uuub() { return $('#uuuForm').serializeJSON() }
function uub() { return $('#uuForm').serializeJSON() }

$(document).ready(function() {

$('#uuButton').click(function() {
	updateUser();
	return false;
})	
	
$('#uuuButton').click(function() {
	getUser();
	return false;
});
});

function getUser(){
	$.ajax({
		method: "POST",
		contentType: 'application/json',
		url: rootURL + '/getUsr/' + $('#cU').val(),
		data: uuub(),
		dataType: "json",
		success: function(data) {
					$("#eusrID").val(data.userID);
						$("#eusrName").val(data.userName);
						$("#eini").val(data.ini);
						$("#ecpr").val(data.cpr);
						$("#epsword").val(data.password);
						$("#roleID").val(data.role[roleID]);
						$("#hiddenRole").val(data.role[roleName]);
					},
		error: function() {
			console.log("Error loading users");
			alert(jqXHR.responseText)
		}
	});
}

function updateUser() {
	console.log('updateUser');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/uu',
		dataType: "json",
		data: uub(),
		success: function(data, textStatus, jqXHR) {
			alert("User updated successfully");
			document.getElementById("uuForm").reset();
			document.getElementById("uuuForm").reset();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText)
		}
	});
}