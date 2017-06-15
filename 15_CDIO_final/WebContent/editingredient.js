/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

function uiib() { return $('#uiiForm').serializeJSON() }
function uib() { return $('#uiForm').serializeJSON() }

$(document).ready(function() {

$('#uiButton').click(function() {
	updateIng();
	return false;
})	
	
$('#uiiButton').click(function() {
	getIng();
	return false;
});
});

function getIng(){
	console.log('getIng')
	$.ajax({
		method: "POST",
		contentType: 'application/json',
		url: rootURL + '/getIng/' + $('#cI').val(),
		data: uiib(),
		dataType: "json",
		success: function(data) {
					$("#eingID").val(data.ingredientID);
					$("#eingName").val(data.ingredientName);
					$("#esupp").val(data.supplier);
					},
		error: function() {
			console.log("Error loading users");
		}
	});
}

function updateIng() {
	console.log('updateIng');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/ui',
		dataType: "json",
		data: uib(),
		success: function(data, textStatus, jqXHR) {
			alert(textStatus);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}