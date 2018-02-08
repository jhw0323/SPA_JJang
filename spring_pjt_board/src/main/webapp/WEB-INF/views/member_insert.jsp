<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입 화면</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	$(window).load(function(){
		
		set_birth(); //생년월일 셋팅
		
		//아이디 중복체크 확인				
		$('#btn_idChk').click(function(){
			$.ajax({
				type:'get',
				data: {"id":$('#id').val()},
				async:false,
				url:'member_chk',
				success:function(responseData){
					var data = JSON.parse(responseData);			
					var chkYn = data.chkYn;
					if(chkYn=="Y"){
						alert("중복되는 ID가 존재합니다. 다른 ID를 사용하여 주세요.");
						$('#btn_idChkYn').val('N');
					}else{
						if(confirm("중복되는 ID가 존재하지 않습니다. 사용하시겠습니까?")){
							$('#btn_idChkYn').val('Y');
							$('#btn_id').attr("readOnly",true);
							return false;
						}
					}
				},
				error:function(request,status,error){
					alert(request.status+"\n"+request.responseText+"//\n"+error);
					
					alert("회원 아이디 중복체크 시 에러가 발생하였습니다. \n관리자에게 문의하여 주시기 바랍니다.");
				}
			});
		});
		
		//회원가입 체크
		$('#btn_memberInsert').click(function(){
			var returnNow = false;
			
			if($('#id').val()==""){
				alert('아이디를 입력하세요.');
				$('#id').focus();
				return false;
			}
			
			if($('#name').val()==""){
				alert('이름을 입력하세요.');
				$('#name').focus();
				return false;
			}
			
			if($('#btn_idChkYn').val()!="Y"){
				alert('회원 아이디 중복체크 해주세요.');
				return false;
			}
			
			if($('#pw1').val()=="" || $('#pw2').val()==""){
				alert("비밀번호는 필수 입력 입니다.");
				return false;
			}
						
			if($('#pw1').val()!=$('#pw2').val()){
				alert('비밀번호와 비밀번호 확인을 동일하게 입력하세요');
				return false;
			}
			
			if($('#email').val()==""){
				alert('이메일은 필수 입력 입니다.');
				return false;
			}
			
			$('input[name^=phone]').each(function(index,item){
				if($(item).val()==""){
					alert("핸드폰 번호는 필수 입력 입니다.");
					$(item).focus();
					returnNow = true;					
					return false;
				}
			});
			
			if(returnNow)
				return false;
			
			if($('#address').val()==""){
				alert('주소는 필수입력 값입니다.');
				return false;
			}
			
			$('#frm').submit();
		});
	});
	
	function set_birth(){
		var date = new Date();
		var yyyy = date.getFullYear();
		for(var i=0; i<99 ; i++){
			$('#yyyy').append($('<option>',{text:yyyy-i,value:yyyy-i}));
		}
		$('#yyyy').children('option').eq(20).attr('selected','selected');		
		
		for(var i=1; i<=12; i++){
			$('#mm').append($('<option>',{text:i,value:i}));
		}
		$('#mm').children('option').eq(0).attr('selected','selected');
		
		set_dd();
		
		$('#mm').change(function(){
			set_dd();
		});
	}
	
	function set_dd(){
		var last = [31,28,31,30,31,30,31,31,30,31,30,31];
		var year = $('#yyyy').val();
		if((year%4==0&&year%100!=0)||year%400==0){
			last[1]=29;
		}
		var mm = $('#mm').val();
		for(var i=1,len=last[mm-1];i<=len;i++){
			$('#dd').append($('<option>',{text:i,value:i}));
		}
	}
		
</script>


</head>
<body>
	<form id="frm" method="POST" action="member_insert">
		<input type="hidden" id="btn_idChkYn" value="N" />
		<label for="id">아이디<font color='red'>(*)</font>:</label><input type="text" id="id" name="id" />
		<input type="button" id="btn_idChk" value="아이디 중복 확인" />
		<br/>
		<label for="name">이름<font color='red'>(*)</font>:</label><input type="text" id="name" name="name"/><br/>
		<label for="pw1">비밀번호<font color='red'>(*)</font>:</label><input type="password" id="pw1" name="pw1"/><br/>
		<label for="pw2">비밀번호 확인<font color='red'>(*)</font>:</label><input type="password" id="pw2" name="pw2"/><br/>
		<label for="birth">생년월일<font color='red'>(*)</font>:</label>
		<select id="yyyy"></select>-<select id="mm"></select>-<select id="dd"></select><br/>
		<label for="email">이메일<font color='red'>(*)</font>:</label><input type="text" id="email" name="email"/><br/>
		<label for="phone">핸드폰번호<font color='red'>(*)</font>:</label>
		<input type="text" id="phone1" name="phone1" maxlength="3"/>-
		<input type="text" id="phone2" name="phone2" maxlength="4"/>-
		<input type="text" id="phone3" name="phone3" maxlength="4"/><br/>
		<label for="phone">자택번호:</label>
		<input type="text" id="homePhone1" name="homePhone1" maxlength="3"/>-
		<input type="text" id="homePhone2" name="homePhone2" maxlength="4"/>-
		<input type="text" id="homePhone3" name="homePhone3" maxlength="4"/><br/>
		<label for="address">주소<font color='red'>(*)</font>:</label><input type="text" id="address" name="address"/><br/>
		
		<input type="button" id="btn_memberInsert" value="회원가입" />
	</form>
</body>
</html>