<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
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
<c:if test="${not empty game.image}">
<img width="500"  src="data:image/jpeg;base64,${game.image}" />
<p><form method="post" action="game" >
  <input type="hidden"  name="answer" value="${game.trueAnswer}"/>
  <input type="submit" value="${game.trueAnswer}"/>
</form></p>
<p><form method="post" action="game" >
  <input type="hidden"  name="answer" value="${game.falseAnswer}"/>
  <input type="submit" value="${game.falseAnswer}"/>
</form></p>
</c:if>
<c:if test="${empty game.image}">
  <meta http-equiv="refresh" content="0; url=${context}" />
</c:if>
</body>
</html>
