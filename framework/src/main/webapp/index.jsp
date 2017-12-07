<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").eq(2).click(function() {
			$.ajax({
				'url':'edit.do'	
				,'method':'put'	
			});
			return false;
		});
		var urls=['add','edit','del'];
		var methods=['post','put','delete'];
		$("form").click(function() {
			$.ajax({
				'url':urls[$("form").index(this)]+'.do'	
				,'method':methods[$("form").index(this)]//'delete'	
				,'data':$(this).serialize()
			});
			return false;
		});
	});
</script>
</head>
<body>
	<h2>Hello index!</h2>

	<a href="list.do">get</a>
	<a href="list.do">post</a>
	<a href="list.do">put</a>
	<a href="list.do">delete</a>
	<br />
	<form method="post" action="add.do">
		num:<input type="text" name="num" />
		<button type="submit">post</button>
	</form>
	<form method="post" action="edit.do">
		<input type="hidden" name="_method" value="put">
		 num:<input
			type="text" name="num" />
		<button type="submit">put</button>
	</form>
	<form method="post" action="del.do?_method=DELETE">
		 num:<input
			type="text" name="num" />
		<button type="submit">del</button>
	</form>

</body>
</html>
