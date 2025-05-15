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
<h3>멤버 뷰</h3>
<c:forEach var="member" items="${members}" varStatus="status">
    ${status.count} : <c:out value="${member.id}"/><br>
    ${status.count} : <c:out value="${member.email}"/><br>
    ${status.count} : <c:out value="${member.name}"/><br>
    ${status.count} : <c:out value="${member.password}"/><br>
    ${status.count} : <c:out value="${member.registerDateTime}"/><br>
    <c:out value="=============="/><br>
</c:forEach>
</body>
</html>