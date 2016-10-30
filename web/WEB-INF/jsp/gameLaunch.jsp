<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Maxim
  Date: 28.10.2016
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form method="POST" action="game" commandName="game">
  <table>
    <tr>
      <td>Do you want the big size?</td>
      <td><form:radiobutton path="countStep" value="10"/> 10
        <form:radiobutton path="countStep" value="5"/> 5 </td>
    </tr>
    <tr>
      <td><input type="submit" name="submit" value="Submit"></td>
    </tr>
    <tr>
  </table>
</form:form>
</body>
</html>
