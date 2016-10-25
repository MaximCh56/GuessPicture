<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Title Login Page</title>
</head>
<body>
<c:if test="${not empty param.login_error}">
  <span style="color: red; ">
    Your login attempt was not successful, try again.<br/><br/>
    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
  </span>
</c:if>
<p>Авторизация</p>
<form name="frm" action="<c:url value='login'/>" method="post">
  <table>
    <tr> <td>User:</td> <td><input type="text" name="username"></td></tr>

    <tr><td>Password:</td> <td><input type="password" name="password"></td></tr>

    <tr><td colspan="2"><input name="submit" type="submit"></td></tr>
    <tr><td colspan="2"><input name="reset" type="reset"></td></tr>
  </table>

  <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
</form>

</body>

</html>