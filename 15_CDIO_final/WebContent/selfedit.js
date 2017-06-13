/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";

$( 'window ').load(function() {
	getUser();
});

function getCurrentID() {
	$.ajax({
		method: "GET",
		url: rootURL + '/getUsrs',
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

function getUser(){
	$.ajax({
		method: "POST",
		contentType: 'application/json',
		url: rootURL + '/getUsr/' + $('#cU').val(),
		data: uuub(),
		dataType: "json",
		success: function(data) {
			alert(data);
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
		}
	});
}