$(document).ready(function() {
  
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	var thePerson = $.urlParam('person');
	thePerson = decodeURIComponent(thePerson);
	console.log(thePerson);
	
	var theUrl = "https://thepubliclibrary.herokuapp.com/apis/persons/"+thePerson;
	
	$.ajax({
		url: theUrl
	}).then(function(data){
		
		$("#persondetails").append("Name: "+data.personName+"<br>");
		$("#persondetails").append("Mobile: "+data.mobile+"<br><br>");
		$("#persondetails").append("<b>BOOKS BORROWED</b><br>");
		
		for(let value of data.listOfBooks){
			
			$("#persondetails").append("<a href='/books?book="+value.bookName+"'>"+value.bookName+"</a><br>");
			
		}
		
	});
	
});