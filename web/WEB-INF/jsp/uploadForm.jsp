<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <c:set var="context" value="${pageContext.request.contextPath}" />
</head>
<body>
<img width="100" src="${context}/resources/logo1.jpg" />



<center>
  <h2>Прототип онлайн выставки</h2>
  <h3>Пожалуйста выберите картину для загрузки на выставку</h3>

  <br />
  <%--<form:form method="post" enctype="multipart/form-data" modelAttribute="uploadedFile" action="fileUpload.htm">--%>
  <form action="./fileUpload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
    <table>

      <tr>
        <td>Upload File: </td>
        <td><input type="file" name="file" /></td>
        <td>Name:<input type="name" name="name" /></td>
      </tr>
      <tr>
        <td> </td>
        <td><input type="submit" value="Upload" /></td>
        <td> </td>
      </tr>
    </table>
  </form>

</center>
</body>
</html>