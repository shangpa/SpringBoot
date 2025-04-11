<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week03/listDIVIew.jsp</title>
</head>
<body>
    <c:forEach var="animal" items="${animalService.animals}" varStatus="status">
        <li>${status.count} : <c:out value="${animal.sound()}"/>
    </c:forEach>
</body>
</html>