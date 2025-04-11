<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week03/mapDIVIew.jsp</title>
</head>
<body>
<c:forEach var="entry" items="${animalMap}" varStatus="status">
    <li>${entry.key} : ${entry.value} :${entry.value.sound()}</li>
    </c:forEach>
</body>
</html>