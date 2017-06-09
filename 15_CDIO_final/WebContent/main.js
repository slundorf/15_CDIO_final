/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

// Functions for serializing data to a JSON object.

function login() { return $('#loginForm').serializeJSON() }
function cu() { return $('#cuForm').serializeJSON() }
function ci() { return $('#ciForm').serializeJSON() }
function ib() { return $('#ibForm').serializeJSON() }
function recept() { return $('receptForm').serializeJSON() }
function product() { return $('productForm').serializeJSON() }


// Collection of functions to pick up the event of clicking specific buttons throughout the web application
// and then calling the specific function.

$(document).ready(function() { // Prevents anything from running until the actual event happens.

$('#loginButton').click(function() {
	var login = login();
	validateLogin();
	return false;
});

$('#cuButton').click(function() {
	createUser();
	getUsers();
	return false;
});

$('#ciButton').click(function() {
	var ci = ci();
	createIngredient();
	return false;
});

$('#ibButton').click(function() {
	var ib = ib();
	createIngredientBatch();
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
		data: cu(),
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


function createIngredient() {
	console.log('createIngredient');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/ci',
		dataType: "json",
		data: ci,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function createIngredientBatch() {
	console.log('createIngredientBatch');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/ib',
		dataType: "json",
		data: ib,
		success: function(data, textStatus, jqXHR) {
			alert('Yay');
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function getUsers(){
	$("#usertablebody").html(""); //tømmer element
	$.ajax({
		method: "GET",
		url: rootURL + '/getUsr',
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, user) {
				$("#usertablebody").append(generateUserHTML(user));
				
			});
		},
		error: function() {
			console.log("Error loading users");
		}
	});
}

function generateUserHTML(user){
	var deleteId = user.usrId;
	console.log("user id "+deleteId);
	console.log("user id " + user.usrId);
	return 	'<tr><td>' + user.usrId + '</td>' +
				'<td>' + user.usrName + '</td>'+
				'<td>' + user.ini+'</td>'+
				'<td>' + user.cpr+'</td>'+
				'<td>' + user.psword+'</td>'+
				'<td>' + user.roles+'</td>'+
				'<td><button data-userid="' + user.usrId + '" onclick="deleteUser2(this);">Slet bruger</button></td>' +
				'</tr>';
}
