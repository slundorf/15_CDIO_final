/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

var login = $('#loginForm').serializeJSON();
var cu = $('#cuForm').serializeJSON();

// Collection of functions to pick up the event of clicking specific buttons throughout the web application
// and then calling the specific function.

$('#loginButton').click(function() {
	validateLogin();
	return false;
});

$('#cuButton').click(function() {
	createUser();
	return false;
});

function validateLogin() {
	console.log('validateLogin');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: login,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function createUser() {
	console.log('createUser');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: data,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}
