<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>회원가입</title>
</head>
<body>
<h3>멤버 리스트</h3>
<h2>약관</h2>
<p>약관 내용</p>
<form action="/regist/step2" method="post">
    <input type="hidden" name="view" value="registerStep2">
    <label>
        <input type="checkbox" name="agree" value="true">약관동의
    </label>
    <input type="submit" value="다음 단계"/>
    <!-- 메시지가 존재하면 출력 -->
    <c:if test="${not empty message}">
        <p style="color:blue;">${message}</p>
    </c:if>
</form>

</body>
</html>