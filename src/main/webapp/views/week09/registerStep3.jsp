<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>ȸ������</title>
</head>
<body>
    <h3>���� ȸ�� ����</h3>
<strong>���� : ${registerRequest.email}</strong><br>
<strong>�̸� : ${registerRequest.name}</strong><br>
<strong>��ȣ : ${registerRequest.password}</strong><br>
<strong>Ȯ�� : ${registerRequest.confirmPassword}</strong><br>
<br><h3>ȸ�������� �Ϸ��߽��ϴ�.</h3><br>
<p><a href=" <c:url value='/main'/>">ùȭ���̵�</a> </p>
</body>
</html>