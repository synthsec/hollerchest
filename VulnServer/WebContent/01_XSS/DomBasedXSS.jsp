<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fruit Selector</title>
<script type="text/javascript" src="/VulnServer/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function displaySelectedFruit(fruit){
	var selectedFruit = document.getElementById("selectedFruit");
	$(selectedFruit)
		.append($("<h2>").text("Congratulations, you've selected a " + fruit + "!")
				.append($("<br>"))
				.append($("<img>").attr("src", "images/" + fruit + ".jpg"))
				.append($("<h2>").text("To share your fruit with your best friends, send them this link: " + document.location.href.split('?')[0] + "?fruit=" + fruit)));
}

(function(fn){
	if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading"){
		fn();
	} else {
		document.addEventListener('DOMContentLoaded', fn);
	}
})(function(){
	var fruitSelector = document.getElementById("fruitSelector");
	fruitSelector.addEventListener("change", function(){
		var fruit = fruitSelector.options[fruitSelector.selectedIndex].value;
		displaySelectedFruit(fruit);
	});	
	
	var searchParams = new URLSearchParams(document.location.search);
	var selectedFruit = searchParams.get("fruit");
	if(selectedFruit){
		displaySelectedFruit(selectedFruit);
		var opts = fruitSelector.options;
		for(var opt in opts){
			if(opts[opt].value === selectedFruit){
				fruitSelector.selectedIndex = opt;
				break;
			}
		}
	}
});


</script>
</head>
<body>
<H2>Select your fruit!</H2>
<div id="selectedFruit"></div>
<form>
		<select id="fruitSelector">
			<option value="banana">Banana</option>
			<option value="orange">Orange</option>
			<option value="apple">Apple</option>
			<option value="blueberry">Blueberry</option>
			<option value="beef">Beef</option>
			<option value="drywall">Drywall</option>
		</select>
</form>
</body>
</html>