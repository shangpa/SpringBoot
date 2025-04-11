<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week03/scopeBeanView.jsp</title>
</head>
<body>
<c:forEach var="unitArray" items="${scopeBeanArray}" varStatus="status">
    <c:forEach var="unit" items="${unitArray}" varStatus="status">
        ${status.count} : <c:out value="${unit}"/><br>
    </c:forEach>
    <c:out value="====================="/> <br>
</c:forEach>
</body>
</html>