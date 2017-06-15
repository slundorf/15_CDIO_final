/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";
var userID

function login() {
	return $('#loginForm').serializeJSON()
}
function es() {
	return $('#esForm').serializeJSON()
}

$(document).ready(function() {

	$('#loginButton').click(function() {
		userID = $('#usrName').val()
		validateLogin()
		return false;
	})

	$('#esButton').click(function() {
		updateUser();
		return false;
	})
})

function updateUser() {
	console.log('updateUser');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/uu',
		dataType : "json",
		data : es(),
		success : function(data, textStatus, jqXHR) {
			alert("User updated successfully");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText)
		}
	});
}

function getUser() {
	$.ajax({
		method : "POST",
		contentType : 'application/json',
		url : rootURL + '/getUsr/' + userID,
		data : userID,
		dataType : "json",
		success : function(data) {
			$("#eusrID").val(data.userID);
			$("#eusrName").val(data.userName);
			$("#eini").val(data.ini);
			$("#ecpr").val(data.cpr);
			$("#epsword").val(data.password);
			$("#roleID").val(data.role[roleID]);
			$("#hiddenRole").val(data.role[roleName]);
		},
		error : function(data, textStatus, jqXHR) {
			console.log("Error loading users");
			alert(jqXHR.responseText);
		}
	});
}

function validateLogin() {
	console.log('validateLogin');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/login/' + $('#usrName').val() + "/" + $('#pass').val(),
		dataType: "json",
		data: login(),
		success: function(data, textStatus, jqXHR) {
			getUser();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}