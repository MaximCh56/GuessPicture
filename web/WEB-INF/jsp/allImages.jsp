<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Spring MVC and List Example</h2>
  <c:forEach items="${lists}" var="imageDataSet" varStatus="status">
      <li>${imageDataSet.name}</li>
      <p>${imageDataSet.id}</p>
      <img width="100" src="${imageDataSet.URL}" />
      <form method="get" action="delete" >
          <input type="hidden"  name="id" value="${imageDataSet.id}"/>
          <input type="submit" value="send"/>
      </form>
  </c:forEach>
</body>
</html>