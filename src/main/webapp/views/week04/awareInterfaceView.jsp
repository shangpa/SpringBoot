<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week04/awareInterfaceView.jsp</title>
</head>
<body>
<h3>등록된 빈 목록</h3>
    <c:forEach var="name" items="${beanNames}" varStatus="status">
        ${status.count} : <c:out value="${name}"/><br>
    </c:forEach>
</body>
</html>