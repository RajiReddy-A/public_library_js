$(document).ready(function() {
  
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		return results[1] || 0;
	}
	
	var thePerson = $.urlParam('person');
	thePerson = decodeURIComponent(thePerson);
	console.log(thePerson);
	
	var theUrl = "http://localhost:8080/apis/persons/"+thePerson;
	
	$.ajax({
		url: theUrl
	}).then(function(data){
		
		$("#usernameh1").append(data.username);
		$("#mobile").append("Mobile: "+data.mobile);
		
		var i=1;
		for(let value of data.listOfBooks){
			
			$("#bookList").append("<tr><th scope='row'>"+i+"</th><td><a href='/books?book="+value.bookName+"'>"+value.bookName+"</a></td><td>"+value.author+"</td></tr>");
			i=i+1;
			
		}
		
	});
	
});