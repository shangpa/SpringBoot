<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>회원가입</title>
</head>
<body>
    <h3>가입 회원 정보</h3>
<strong>메일 : ${registerRequest.email}</strong><br>
<strong>이름 : ${registerRequest.name}</strong><br>
<strong>암호 : ${registerRequest.password}</strong><br>
<strong>확인 : ${registerRequest.confirmPassword}</strong><br>
<br><h3>회원가입을 완려했습니다.</h3><br>
<p><a href=" <c:url value='/main'/>">첫화면이동</a> </p>
</body>
</html>