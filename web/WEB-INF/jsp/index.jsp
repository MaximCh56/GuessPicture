<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Welcome! - ConsistentCoder.com</title>
</head>
<body>
<h1>${ message }</h1>
<p>
  <a href="<%=request.getContextPath()%>/uploadForm">Upload Form</a> | <a href="<%=request.getContextPath()%>/check">check</a> | <a href="<%=request.getContextPath()%>/login">Login</a>
</p>
<p><u>Admin login details</u></p>
<p>
<ul>
  <li>username: <strong>admin</strong></li>
  <li>password: <strong>admin</strong></li>
</ul>
</p>
<p><u>Member login details</u></p>
<p>
<ul>
  <li>username: <strong>member</strong></li>
  <li>password: <strong>member</strong></li>
</ul>
</p>
</body>
</html>