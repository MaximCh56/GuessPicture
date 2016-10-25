<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="context" value="${pageContext.request.contextPath}" />
<head>
  <title>Being Java Guys | Hello World</title>
</head>
<body>
<center>

  <h2>Being Java Guys | Hello World</h2>
  <h3>
    File name : "<strong> ${message}</strong>" uploaded successfully !
    Image:
    <img width="100" src="${context}/img/${message}" />

    <spring:form method="get"  modelAttribute="uploadedFile" action="check">

      <spring:button>Next Page</spring:button>

    </spring:form>
  </h3>

</center>
</body>
</html>