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
<h2>회원 정보 입력</h2>
<form action="/regist/step3" method="post">
    <!-- name="email" : 서버에 폼 데이터를 전송할 때 사용할 파라미터 이름 -->
    <!-- id="email" : HTML 요소의 고유 식별자이며, JavaScript나 Css에서 참조할 때 사용 -->
    <p>
        <input type="hidden" name="view" value="registerStep3">
        <label>이메일:<br>
            <input type="text" name="email" id="email" value="${registerRequest.email}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>이름:<br>
            <input type="text" name="name" id="name" value="${registerRequest.name}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>비밀번호:<br>
            <input type="password" name="password" id="password" value="${registerRequest.password}" onfocus="this.value = '';">
        </label>
    </p>
    <p>
        <label>비밀번호:<br>
            <input type="password" name="confirmPassword" id="confirmPassword" value="${registerRequest.confirmPassword}" onfocus="this.value = '';">
        </label>
    </p>

    <input type="submit" value="가입 완료"/>
</form>

</body>
</html>