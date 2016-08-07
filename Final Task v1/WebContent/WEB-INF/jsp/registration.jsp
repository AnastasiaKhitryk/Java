<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="Controller?command=registration" method="post">
            <div class="form-group">
                <input name="email" value="email" class="form-control" id="email">
            </div>
            <div class="form-group">
                <input name="password" value="password" type="password" class="form-control" id="password">
            </div>
            <div class="form-group">
                <input name="userName" value="name" type="text" class="form-control" id="name">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-default">Enter</button>
            </div>
     </form>
</body>
</html>