<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
hr {
border-color : blue;
}
</style>
<script type="text/javascript" src="/res/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

	$(function() {
		$('button#submitBasicValue').click(function() {
			var serData = $('form.basicValue').serialize();
			var serArrayData =  $('form.basicValue').serializeArray();
			
			$.getJSON('/basicValue.json', serData, function(data) {
				cosnole.log(data);
			});
			
			$.getJSON('/basicValue.json', serArrayData, function(data) {
				cosnole.log(data);
			});
		});
		
		$('button#submitObjectValue').click(function() {
			var serData = $('form.objectValue').serialize();
			var serArrayData =  $('form.objectValue').serializeArray();
			
			$.getJSON('/objectValue.json', serData, function(data) {
				cosnole.log(data);
			});
			$.getJSON('/objectValue.json', serArrayData, function(data) {
				cosnole.log(data);
			});
			
		});
		
		$('button#submitListString').click(function() {
			var serData = $('form.listString').serialize();
			var serArrayData =  $('form.listString').serializeArray();
			
			$.getJSON('/listString.json', serData, function(data) {
				cosnole.log(data);
			});
			$.getJSON('/listString.json', serArrayData, function(data) {
				cosnole.log(data);
			});
			
		});
		
		$('button#submitListObject').click(function() {
			var serData = $('form.listObject').serialize();
			var serArrayData =  $('form.listObject').serializeArray();
			
			var stringify = JSON.stringify(serArrayData);
			var param = $.param(serArrayData);
			
			console.log(serData);
			console.log(serArrayData);
			console.log(stringify);
			console.log(param);
			
			$.getJSON('/listObject.json', serData, function(data) {
				cosnole.log(data);
			});
			$.getJSON('/listObject.json', serArrayData, function(data) {
				cosnole.log(data);
			});
			
		$('#button1').click(function() {
				var d2 = [ {
					username : "ly",
					password : "bc"
				}, {
					username : "xc",
					password : "ew"
				} ];
				d2 = JSON.stringify(d2);
				d2 = {
					"users" : d2
				}
				console.log(d2);

				$.ajax({
					url : 'userInfo.json',
					type : "POST",
					dataType : 'json',
					data : d2,
					success : function(data) {
						console.log(data);
					}
				});
			});
		});
		
	});
</script>
</head>
<body>
	<h1>This is home page.</h1>
	<form class="basicValue" action="/basicValue" method="post">
		<input type="input" name="username" />
		<input type="password" name="password" />
		<input type="input" name="cnName" />
		<input type="submit" value="Submit basic value" />
	</form>
	<button id="submitBasicValue">Submit basic value By Json</button>
	<hr/>
	<br/>
	
	<form class="objectValue" action="/objectValue" method="post">
		<input type="input" 	name="user.username" />
		<input type="password" 	name="user.password" />
		<input type="input" 	name="user.cnName" />
		<input type="submit" value="Submit Object value"></input>
	</form>
	<button id="submitObjectValue">Submit Object value By Json</button>
	<hr/>
	<br/>
	
	<form class="listString" action="/listString" method="post">
		<input type="input" name="names[0]" />
		<input type="input" name="names[1]" />
		<input type="input" name="names[2]" />
		<input type="submit" value="Submit list String" />
	</form>
	<button id="submitListString">Submit list String By Json</button>
	<hr/>
	<br/>
	
	<form class="listObject" action="/listObject" method="post">
		<input type="input" name="users[0].username" />
		<input type="password" name="users[0].password" />
		<input type="input" name="users[0].cnName" />
		<br/>
		
		<input type="input" name="users[1].username" />
		<input type="password" name="users[1].password" />
		<input type="input" name="users[1].cnName" />
		
		<input type="submit" value="Submit List Object"></input>
	</form>
	<button id="submitListObject">Submit list Object By Json</button>
	<hr/>
	<br/>

	<br />
	<button id="button1">Json</button>

</body>
</html>