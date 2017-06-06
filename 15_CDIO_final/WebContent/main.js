/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

var login = $('#loginForm').serializeJSON();
var cu = $('#cuForm').serializeJSON();
var recept = $('#receptForm').serializeJSON();
var product = $('#productForm').serializeJSON();

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

$('#receptButton').click(function() {
	createRecept();
	return false;
});

$('#productButton').click(function() {
	createProduct();
	return false;
});

function validateLogin() {
	console.log('validateLogin');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/login',
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
		url: rootURL + '/cu',
		dataType: "json",
		data: cu,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function createRecept() {
	console.log('createRecept');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/recept',
		dataType: "json",
		data: recept,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function createProduct() {
	console.log('createProduct');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/product',
		dataType: "json",
		data: product,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

