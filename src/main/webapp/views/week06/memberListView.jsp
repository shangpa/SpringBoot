<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>week04/memberListView.jsp</title>
</head>
<body>
<h3>멤버 리스트</h3>

<c:choose>
    <c:when test="${not empty memberList}"> <!-- 멤버리스트가 not empty 일때 실행 -->
        <table border="1">
            <thead>
                <tr>
                    <th>Map Index</th> <!-- 1번째 열-->
                    <th>Key</th><!-- 2번째 열-->
                    <th>Name</th><!-- 3번째 열-->
                    <th>Password</th><!-- 4번째 열-->
                    <th>RegDate</th><!-- 5번째 열-->
                </tr>
            </thead>
            <tbody>
            <c:forEach var="map" items ="${memberList}" varStatus="mapStauts">
                <c:forEach var="entry" items="${map}">
                    <tr>
                        <td>${mapStauts.index}</td>
                        <td>${entry.key}</td>
                        <td>${entry.value.name}</td>
                        <td>${entry.value.password}</td>
                        <td>${entry.value.registerDateTime}</td>
                    </tr>
                </c:forEach>
            </c:forEach>

            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>No member found.</p>
    </c:otherwise>
</c:choose>
</body>
</html>