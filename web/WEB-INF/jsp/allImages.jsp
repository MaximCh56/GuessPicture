<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Spring MVC and List Example</h2>
  <c:forEach items="${lists}" var="imageDataSet" varStatus="status">
      <li>${imageDataSet.name}</li>
      <img width="100" src="${imageDataSet.URL}" />
  </c:forEach>
</body>
</html>
