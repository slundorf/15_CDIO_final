/**
 * 
 */

var componentArray = []
var count = 0;
var compIDValue
var ingIDValue
var amountValue
var toleranceValue
var recIDValue
function recipe() { return $('#recipeForm').serializeJSON() }

 $(document).ready(function () {
	 
	 $('#recipeButton').click(function() {
		 	if(changeComponent() == false) {
		 		return false
		 	}
		 	componentArray.push(addComponent())
		 	createRecipe();
		    return false;
		})

     $('#addComponent').click(function () {
    	 if(changeComponent() == false) {
    		 return false
    	 }
    	 
    	 componentArray.push(addComponent())
    	 count++
           $('<div/>', {
               'class' : 'extraComponent', html: GetHtml()
     }).hide().appendTo('#container2').slideDown('fast');
         
     });
 })
 
function GetHtml()
{	
    var $html = $('.extraRecipeComponent').clone();
    $html.find('[id=ingID0]')[0].id="ingID"+count;
    $html.find('[id=amount0]')[0].id="amount"+count;
    $html.find('[id=tolerance0]')[0].id="tolerance"+count;
    return $html.html();    
}
 
 function createRecipeComponent() {
		console.log('createRecipeComponent');
		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: rootURL + '/recipeComponent',
			dataType: "json",
			data: JSON.stringify(componentArray),
			success: function(data, textStatus, jqXHR) {
				alert('Yay');
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert('Idiot');
			}
		});
	}
 
function createRecipe() {
		console.log('createRecipe');
		$.ajax({
			type: 'POST',
			contentType: 'application/json',
			url: rootURL + '/recipe',
			dataType: "json",
			data: recipe(),
			success: function(data, textStatus, jqXHR) {
				alert('Yay');
			 	createRecipeComponent();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert('Idiot');
			}
		});
	}

function changeComponent() {
	 ingIDValue = $('#ingID'+count).val()
	 amountValue = $('#amount'+count).val()
	 toleranceValue = $('#tolerance'+count).val()
	 recIDValue = $('#recipeID').val()
	 if(recIDValue == "") {
		 alert("Please enter recipe ID")
	 } else if($('#recipeName').val() == "") {
		 alert("Please enter recipe name")
	 }
	 if(ingIDValue == "" || amountValue == "" || toleranceValue == "") {
		alert("Please enter all values for the recipe component")
		return false;
	 }
}
function addComponent() {
	var components = {
		ingredientID: ingIDValue,
		amount: amountValue,
		tolerance: toleranceValue,
		recipeID: recIDValue
	}
	return components

}