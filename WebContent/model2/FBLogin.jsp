<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    

<script src="../js/jquery.js"></script>


<script>

function getUserData() {
	FB.api('/me?fields=name,email', function(response) {
		document.getElementById('response').innerHTML = JSON.stringify(response);
    	
    	var obj = JSON.stringify(response);    	
    	var data = JSON.parse(obj);
    	
    	loginFbAjax(data);
    	
    	
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

//add event listener to login button
document.getElementById('buttonFB').addEventListener('click', function() {
	//do the login	
	FB.login(function(response) {
		if (response.authResponse) {
			//user just authorized your app

			getUserData();					
			
			
		} else {					
			
			
		}
	}, {scope: 'email,public_profile', return_scopes: true});
}, false);



var loginFbAjax = function(data) {
	
	var id = data.email;
	
	$.ajax({
		url:'loginFB.board',
		type: 'POST',
		data: {
			id: id
			
		},
		dataType: 'xml',
		success: function(xml) {
			
//			console.log(xml);
			var flag = $(xml).find('flag').text();
			var id = $(xml).find('id').text();
			var name = $(xml).find('name').text();
			var memNo = $(xml).find('memNo').text();
			var password = $(xml).find('password').text();
						
			if(flag == 0) {
				alert(id + ' 님 환영합니다! \n(로그아웃 하실 때는 페이스북을 먼저 로그아웃 해주세요)');
				if(password != ""){
					alert('회원님의 비밀번호가 노출당할 위험이 있으므로 \n비밀번호를 변경해주시기 바랍니다.');
				}
				$('#loginModal').modal('hide');
				parent.location.reload(); 
			} else if(flag == 1){
				alert('로그인에 실패했습니다.');
			}
		},
		error: function(xhr, status, error) {
			//alert('에러 : ' + status + '\n\n' + error);
		}
	});
};


</script>
