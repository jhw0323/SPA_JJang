<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(window).load(function(){
		if($("#loginChk").val()=="N"){
			alert('입력하신 아이디가 잘못되었습니다. 다시 확인하여 주십시오.');
			$('#id').focus();
		}
	});
	
	$('#btn_join').click(function(){
		
	});
</script>
</head>
<body>
	<form method="post" action="login">
		<input type="hidden" id="loginChk" value="${loginChk}" />
		<br/>
		<label id="id">아이디:</label><input type="text" id="id" name="id" /><br/>
		<label id="pw">비밀번호:</label><input type="password" id="pw" name="pw"/>
		<input type="submit" value="Login" /><br/><br/><br/><br/>
		<a href="member_insertView">회원가입</a>
		<input type="button" value="아이디 찾기" id="btn_idSearch" />
		<input type="button" value="비밀번호 찾기" id="btn_pwSearch" />
	</form>
</body>
</html>