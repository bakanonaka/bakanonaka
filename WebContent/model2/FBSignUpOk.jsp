<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<button id="buttonFB">페이스북 로그인</button>
 <input type="hidden" id="response"> 
<div id = "message"></div>

<!-- <a href="../signUpFB.board">돌아가기</a> -->
<a href="../index.board">메인페이지로</a>
</body>

<script>
function getUserData() {
	FB.api('/me?fields=name,email', function(response) {
		document.getElementById('response').innerHTML = JSON.stringify(response);
    	
    	var obj = JSON.stringify(response);    	
    	var data = JSON.parse(obj);
    	checkEmail(data);
    	
    	//signUpFbAjax(data);
    	
    	
	});
}
 
window.fbAsyncInit = function() {
	//SDK loaded, initialize it
	FB.init({
		appId      : '1300002326680429',		//1280179665331690 , 승조형 AppID : 1300002326680429
		xfbml      : true,
		version    : 'v2.5'
	});
 
	//check user session and refresh it
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
			//user is authorized
			document.getElementById('buttonFB').style.display = 'none';
			getUserData();
		} else {
			//user is not authorized
		}
	});
};
 
//load the JavaScript SDK
(function(d, s, id){
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {return;}
	js = d.createElement(s); js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>
<script>

//add event listener to login button
document.getElementById('buttonFB').addEventListener('click', function() {
	//do the login	
	FB.login(function(response) {
		if (response.authResponse) {
			//user just authorized your app
			document.getElementById('buttonFB').style.display = 'none';
			getUserData();
			
		}
	}, {scope: 'email,public_profile', return_scopes: true});
}, false);

var checkedEmail = '';

var checkEmail = function(data) {		
	var email = data.email;
	
	$.ajax({
		url:'../checkOk.board',
		type: 'POST',
		data: {
			email: email
		},
		dataType: 'xml',
		success: function(xml) {
			
			var flag = $(xml).find('emailflag').text();
			var message = '';
			if(flag == 0) {
				$('#message').empty();
				signUpFbAjax(data);									
				
				message = "축하합니다! 가입이 완료되었습니다. <br>아이디는 회원님의 페이스북 이메일 주소와 동일하며, 비밀번호는 facebook입니다. <br>로그인 하신 후 비밀번호를 꼭 변경해주세요^^";
				$('#message').append(message);				
				
			} else {
				$('#message').empty();
				alert('이미 가입한 회원입니다.');
				message = "회원 가입에 실패했습니다. 다시 한 번 확인해주세요";
				$('#message').append(message);
			}
		},
		error: function(xhr, status, error) {
			alert('에러 : ' + status + '\n\n' + error);
		}
	});
};


var signUpFbAjax = function(data) {
	
	var FBid = data.email;
	var FBemail = FBid;
	var FBname = "FB" + data.name;
	var FBpassword = "facebook";
		
	$.ajax({
		url:'../signUpFB.board',
		type: 'POST',
		data: {
			id: FBid,
			password: FBpassword,
			name: FBname,
			email: FBemail
		},
		dataType: 'xml',
		success: function(xml) {
			
			var flag = $(xml).find('flag').text();
			
			if(flag == 0) {
					
				alert('페이스북 회원가입완료');
				
			} else if(flag == 1){
				alert('페이스북 회원가입실패');
			}
		},
		error: function(xhr, status, error) {
			alert('에러 : ' + status + '\n\n' + error);
		}
	
	});
}
</script>
</html>
