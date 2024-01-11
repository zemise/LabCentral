var recipe1 = [
"[2.0 µl DNA sample]", 
" 1.0 µl primer 1", 
" 1.0 µl primer 2", 
" 2.0 µl buffer 10x", 
" 0.1 µl enzyme", 
"13.9 µl water", 
"-------", 
"18.0 µl total"
];

var recipe2 = [
"[5.0 µl DNA sample]", 
" 0.2 µl EcoRI", 
" 1.0 µl buffer 10x", 
" 3.8 µl water", 
"-------", 
" 5.0 µl total"
];

var recipe3 = [
"[1.0 µl sample]", 
" 1.0 µl reageant 1", 
" 1.0 µl reageant 2", 
" 1.0 µl reageant 3", 
"15.0 µl water", 
"-------", 
"19.0 µl total"
];

var recipes = [];
recipes["recipe1"] = recipe1;
recipes["recipe2"] = recipe2;
recipes["recipe3"] = recipe3;

function load_recipe() {
	var choiceElement = document.getElementById('recipe_choice');
	var choiceValue = choiceElement.options[choiceElement.selectedIndex].value
	var recipeLines =  recipes[choiceValue]
	
	var recipeElement = document.getElementById('recipe');
	recipeElement.value = recipeLines.join("\n")
	
	update_result()
}

function update_recipe() {
	// save new content into the corresponding variable
	var choiceElement = document.getElementById('recipe_choice');
	var recipeElement = document.getElementById('recipe');
	var recipeString = recipeElement.value
	var recipeLines =  recipeString.split("\n")
	var choiceValue = choiceElement.options[choiceElement.selectedIndex].value
	recipes[choiceValue] = recipeLines
	// update result
	update_result()
}

function update_result() {
	
	var recipeElement = document.getElementById('recipe');
	var recipeString = recipeElement.value
	var recipeLines =  recipeString.split("\n")

	var countElement = document.getElementById('count_choice');
	var countValue = parseInt(countElement.options[countElement.selectedIndex].value)
	var mixLines = []
	recipeLines.forEach (function(line) {
		if (line.match(/^\[/)) { return }
		var matches = line.match(/^([\s\.\d]+)( .*)$/)
		if (matches == null) {
			mixLines.push(line)
		} else {
			var amount = parseFloat(matches[1]) * countValue
			var amountString = formatFloat(amount, 4, 1)
			mixLines.push(amountString + matches[2])
		}
	});
	
	var resultElement = document.getElementById('result');
	resultElement.innerHTML = mixLines.join("\n")
}

function formatFloat(number, intLength, decLength) {
	// integer part
	var intPart = "" + Math.trunc(number)
	while (intPart.length < intLength) { intPart = " " + intPart}
	// decimal par
	var decPart = String(number % 1)
	if (decPart.length < 2) { decPart = "0.0" }
	decPart = decPart.slice(2, 2 + decLength);
	return intPart + '.' + decPart
}
