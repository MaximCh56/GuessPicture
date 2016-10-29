<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 28.10.2016
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<img width="500"  src="data:image/jpeg;base64,${Image}" />
<p><form method="get" action="game" >
  <%--<input type="hidden"  name="id" value="${trueName}"/>--%>
  <input type="submit" value="${trueName}"/>
</form></p>
<p><form method="get" action="game" >
  <%--<input type="hidden"  name="id" value="Кнопка + ${falseName}"/>--%>
  <input type="submit" value="${falseName}"/>
</form></p>
</body>
</html>
