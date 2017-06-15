/**
 * 
 */

var rootURL = "http://localhost:8080/15_CDIO_final/rest/weight";
var webURL = "http://localhost:8080/15_CDIO_final";

// Functions for serializing data to a JSON object.

function login() { return $('#loginForm').serializeJSON() }
function cu() {	return $('#cuForm').serializeJSON() }
function ci() { return $('#ciForm').serializeJSON() }
function ib() { return $('#ibForm').serializeJSON() }
function pb() { return $('#pbForm').serializeJSON() }

// Collection of functions to pick up the event of clicking specific buttons throughout the web application
// and then calling the specific function.

$(document).ready(function() { // Prevents anything from running until the actual event happens.

$('#loginButton').click(function() {
	if($('#usrName').val()=="admin" && $('#pass').val()=="root") {
		window.location.href = "http://localhost:8080/15_CDIO_final/rootadminindex.html";
	} else {
	validateLogin();
	}
	return false;
});

$('#cuButton').click(function() {
	createUser();
	return false;
});

$('#ciButton').click(function() {
	createIngredient();
	return false;
});

$('#ibButton').click(function() {
	createIngredientBatch();
	return false;
});

$('#pbButton').click(function() {
	createProductBatch();
	return false;
});
$('#pbcButton').click(function() {
	createProductBatchComponent();
	return false;
});
$('#runASE').click(function(){
	$.ajax({
		type: 'GET',
		url: rootURL + '/ASE/'+$('#ipadress').val(),
		dataType: 'text',
		success: function(data, textStatus, jqXHR) {
			console.log(textStatus);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
		}
	});
});
getUsers();
getIngredients();
getIngredientbs();
getProductB();
getRecipes();

});

function validateLogin() {
	console.log('validateLogin');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/login/' + $('#usrName').val() + "/" + $('#pass').val(),
		dataType: "json",
		data: login(),
		success: function(data, textStatus, jqXHR) {
			changePage(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert('Idiot');
		}
	});
}

function changePage(data) {
	switch(data) {
	
	case 1:
	window.location.href = "http://localhost:8080/15_CDIO_final/adminindex.html";
	break;
	
	case 2:
	window.location.href = "http://localhost:8080/15_CDIO_final/pharmaindex.html";
	break;
	
	case 3:
	window.location.href = "http://localhost:8080/15_CDIO_final/formanindex.html";
	break;
	
	case 4:
	window.location.href = "http://localhost:8080/15_CDIO_final/operatorindex.html";
	break;
	
	}
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
			alert(textStatus);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
	});
}

function createProductBatch() {
	console.log('createProductBatch');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/pb',
		dataType: "json",
		data: pb(),
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
		data: ci(),
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
		data: ib(),
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

function generateUserHTML(user){
	var deleteId = user.userID;
	return 	'<tr><td>' + user.userID + '</td>' +
				'<td>' + user.userName + '</td>'+
				'<td>' + user.ini+'</td>'+
				'<td>' + user.cpr+'</td>'+
				'<td>' + user.password+'</td>'+
				'<td>' + user.role.roleName+'</td>'+
				'<td>' + generateToggle(user)+'</td>' +
				'</tr>';
}
function generateToggle(user){
	var toggle = '<select  data-userid="'+user.userID+'" onchange=toggleStatus(this); id="status">';
		if(user.status==true){
			toggle += '<option value=true>Active</option>';
			toggle += '<option value=false>Inactive</option>';
		}else{
			toggle += '<option value=false>Inactive</option>';
			toggle += '<option value=true>Active</option>';
		}
		toggle += '</select>';
		return toggle;
}

function toggleStatus(element){
	$.ajax({
		method: "POST",
		url: rootURL +"/toggleStatus/"+$(element).data("userid"),
		data: " ",
		contentType: "application/json",
		dataType: "json",
		success: function(response) { 
			console.log("Success changing status");
			getUsers();
		},
		error: function() {
			console.log("Error changing status");
		}
	});
}
function getRecipes(){
	$("#recipetablebody").html(""); //tømmer element
	$("#recipectablebody").html(""); //tømmer element
	$("#hrc").css("display","none");//fjerner components overskrift
	$("#recipectable").css("display","none");//fjerner component tabel
	$.ajax({
		method: "GET",
		url: rootURL + '/getRecipeList',
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#recipetablebody").append(generateRecipeHTML(list));
			});
		},
		error: function() {
			console.log("Error loading pb");
		}
	});
}
function generateRecipeHTML(recipe){
	return 	'<tr><td>' + recipe.recipeID + '</td>' +
				'<td>' + recipe.recipeName + '</td>'+
				'<td><button data-recipeid="' + recipe.recipeID + '" onclick="appendRecipeCompData(this);">Components</button></td>'+
				'</tr>';
}

function appendRecipeCompData(element){
	$("#recipectablebody").html("");
	$("#hrc").css("display","inline");
	$("#recipectable").css("display","inline");
	$.ajax({
		method: "GET",
		url: rootURL + '/getRecipeCompList/'+$(element).data('recipeid'),
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#recipectablebody").append(
						'<tr><td>' + list.recipeID + '</td>' +
						'<td>' + list.ingredientID + '</td>'+
						'<td>' + list.amount + '</td>'+
						'<td>' + list.tolerance + '</td>'+
						'</tr>'
						);
			});
		},
		error: function() {
			console.log("Error loading recipecomponents");
		}
	});
	
}





function getProductB(){
	$("#pbtablebody").html(""); //tømmer element
	$("#pbctablebody").html(""); //tømmer element
	$("#hpbc").css("display","none");//fjerner components overskrift
	$("#pbctable").css("display","none");//fjerner component tabel
	$.ajax({
		method: "GET",
		url: rootURL + '/getPB',
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#pbtablebody").append(generatePBHTML(list));
			});
		},
		error: function() {
			console.log("Error loading pb");
		}
	});
}
function generatePBHTML(pb){
	return 	'<tr><td>' + pb.productBatchID + '</td>' +
				'<td>' + pb.recipeID + '</td>'+
				'<td>' + pb.createdDate + '</td>'+
				'<td>' + pb.status + '</td>'+
				'<td><button data-pbid="' + pb.productBatchID + '" onclick="appendPCompData(this);">Components</button></td>'+
				'</tr>';
}

function appendPCompData(element){
	$("#pbctablebody").html("");
	$("#hpbc").css("display","inline");
	$("#pbctable").css("display","inline");
	$.ajax({
		method: "GET",
		url: rootURL + '/getPBC/'+$(element).data('pbid'),
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#pbctablebody").append(
						'<tr><td>' + list.pbId + '</td>' +
						'<td>' + list.ingredientID + '</td>'+
						'<td>' + list.ingredientBatchID + '</td>'+
						'<td>' + list.netto + '</td>'+
						'<td>' + list.userId + '</td>'+
						'</tr>'
						);
			});
		},
		error: function() {
			console.log("Error loading pb components");
		}
	});
	
}

function getIngredientbs(){
	$("#ingredientbtablebody").html(""); //tømmer element
	$.ajax({
		method: "GET",
		url: rootURL + '/getIngredientBatches',
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#ingredientbtablebody").append(generateIngredientBHTML(list));
			});
		},
		error: function() {
			console.log("Error loading ingredient batches");
		}
	});
}

function generateIngredientBHTML(ingb){
	return 	'<tr><td>' + ingb.ingredientBatchID + '</td>' +
				'<td>' + ingb.ingredientID + '</td>'+
				'<td>' + ingb.amount+'</td>'+
				'</tr>';
}


function getIngredients(){
	$("#ingredienttablebody").html(""); //tømmer element
	$.ajax({
		method: "GET",
		url: rootURL + '/getIngredients',
		dataType: "json",
		success: function(response) { 
			$.each(response, function(i, list) {
				$("#ingredienttablebody").append(generateIngredientHTML(list));
				
			});
		},
		error: function() {
			console.log("Error loading ingredients");
		}
	});
}
function generateIngredientHTML(ing){
	return 	'<tr><td>' + ing.ingredientID + '</td>' +
				'<td>' + ing.ingredientName + '</td>'+
				'<td>' + ing.supplier+'</td>'+
				'</tr>';
}