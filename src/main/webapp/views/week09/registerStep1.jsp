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
<h3>��� ����Ʈ</h3>
<h2>���</h2>
<p>��� ����</p>
<form action="/register/step2" method="post">
    <input type="hidden" name="view" value="registerStep2">
    <label>
        <input type="checkbox" name="agree" value="true">�������
    </label>
    <input type="submit" value="���� �ܰ�"/>
    <!-- �޽����� �����ϸ� ��� -->
    <c:if test="${not empty message}">
        <p style="color:blue;">${message}</p>
    </c:if>
</form>

</body>
</html>