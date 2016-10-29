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
  <a href="<%=request.getContextPath()%>/uploadForm">Upload Form</a> | <a href="<%=request.getContextPath()%>/check">check</a> | <a href="<%=request.getContextPath()%>/login">Login</a> | <a href="<%=request.getContextPath()%>/gameLaunch">game Launch</a>
</p>
</body>
</html>