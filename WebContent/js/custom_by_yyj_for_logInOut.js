	
$(document).ready(function() {

	//--------------------------------------로그 아웃 시작------------------------------
	
	$(document).on('click', '#a_logoutButton', function() {
		
		logoutAndSendUserNameToDB();
		
	});

	// 로그아웃 및 현재 접속중인 유저의 이름 전송 메소드 -> 변경 : memNo 로
	var logoutAndSendUserNameToDB = function() {
		
//		var html = $('#str_loggedName').html();
//		var userName = html.substring(0, html.indexOf('님'));
		
		var memNo = $('#str_loggedMemNo').val();
		
//		alert(userName);
		
		$.ajax({
			url:'logoutOk.board',
			type: 'POST',
			data: {
				memNo: memNo
			},
			dataType: 'xml',
			success: function(xml) {
				
//				console.log(xml);
			
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
					
					top.location.href ='index.board';
					
				} else if(flag == 1){
					alert('로그아웃에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	//--------------------------------------로그 아웃 끝------------------------------
	
	
	//--------------------------------------로그 인 시작------------------------------
	
	//bootstrap UI에 jQuery를 이용한 로그인 방식
	$(document).on('click', '#a_loginModal_login', function() {
		
		checkLoginIdPasswordForloginModal();
		
//		checkLoginIdPasswordForloginModal_json();	
//		json으로 할 경우, MainController에서 url을 loginOk_json 으로 변경 필요
		
	});

	// 로그인을 위한 id,password 전송 메소드(xml 방식)
	var checkLoginIdPasswordForloginModal = function() {
		
		var id = $('#loginForm').find('input[id="id"]').val();
		var password = $('#loginForm').find('input[id="password"]').val();
		
		$.ajax({
			url:'loginOk.board',
			type: 'POST',
			data: {
				id: id,
				password: password
			},
			dataType: 'xml',
			success: function(xml) {
				
//				console.log(xml);
				var flag = $(xml).find('flag').text();
				var id = $(xml).find('id').text();
				var name = $(xml).find('name').text();
				var memNo = $(xml).find('memNo').text();
				
				if(flag == 0) {
					
					$('#headerNavUl').find('li[id="loginIcon"]').remove();
					
					var loginedIcon = '';
					loginedIcon = '<li class="dropdown" id = "loggedIcon">';
					loginedIcon += '<a href="#" class="dropdown-toggle br-pink" data-toggle="dropdown">';
					loginedIcon += '<i class="fa fa-user fa-3x"></i>';
					loginedIcon += '<span class="link-title" id = "str_loggedName">' + name + '님&nbsp;<b class="fa fa-angle-down"></b></span>';
					loginedIcon += '</a>';
					loginedIcon += '<ul class="dropdown-menu dropdown-default">';
					
					if(id == 'admin') {
						loginedIcon += '<li><a href="adminPage.board"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 회원 관리 페이지</a></li>';
						loginedIcon += '<li><a href="adminEventManagePage.board"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 이벤트 관리 페이지</a></li>';
					} else {
						loginedIcon += '<li><a href="#" id="a_modifyButton"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 사용자 정보 수정/탈퇴</a></li>';	
					}
					
					loginedIcon += '<li><a href="#" id = "a_logoutButton"><i class="fa fa-arrow-right dd-link-icon lblue"></i> 로그아웃</a></li>';
					
					loginedIcon += '</ul>';
					loginedIcon += '</li>';
					loginedIcon += '<input type="hidden" value="'+ id +'" id ="str_loggedId">';
					loginedIcon += '<input type="hidden" value="'+ memNo +'" id ="str_loggedMemNo">';
					$('#headerNavUl').append(loginedIcon);
					
					$('#loginModal').modal('hide');
//					alert(name + ' 님이 로그인 했습니다.');
					
				} else if(flag == 1){
					alert('로그인에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	//--------------------------------------로그 인 끝------------------------------
	
	
	
	//------------------------회원 등록 창 띄우기------------------------------------
	
	$(document).on('click', '#a_LoginModal_ShowUp', function() {

		//창 띄우기 전에 공란 만들기
		$('#signUpForm').find('input[id="id"]').val('');
		$('#signUpForm').find('input[id="password"]').val('');
		$('#signUpForm').find('input[id="confirmpassword"]').val('');
		$('#signUpForm').find('input[id="name"]').val('');
		$('#signUpForm').find('input[id="email"]').val('');

		
		//체크 해제는 false로.
		$('#signUpFormA').find('input[id="agreeornot"]').attr('checked', false);
		
		//id, name 둘다 동일해서 each() 사용함.
		$('#signUpForm').find('input[name="receivemailflag"]').each(function(){
			if($(this).val() == 0) {
				$(this).click();
			}
		});

		//색상 선택 검은색으로 초기화
		$('#register_colorselector_1').next().find('.dropdown-menu').find('.colorList > a[data-value="0"]').click();
		$('#register_colorselector_2').next().find('.dropdown-menu').find('.colorList > a[data-value="0"]').click();
		$('#register_colorselector_3').next().find('.dropdown-menu').find('.colorList > a[data-value="0"]').click();
		$('#register_colorselector_4').next().find('.dropdown-menu').find('.colorList > a[data-value="0"]').click();
		
		$('#register_spring').hide();
		$('#register_summer').hide();
		$('#register_fall').hide();
		$('#register_winter').hide();
		
		//라디오 버튼 같은 name에 대하여 전체 미선택할 경우
		$('#signUpForm').find('input[name="register_selectSeasonflag"]').each(function(){
			$(this).attr('checked', false);
		});
		
		//라디오 버튼 같은 name 중 하나를 선택하게 할 경우
		$('#signUpForm').find('input[id="register_likeColorflag_none"]').click();
		
		$('#loginModal').modal('show');
	});	
	
	//------------------------회원 등록 창 띄우기 끝-----------------------------------
	
	
	//--------------------------------------회원 등록 시작------------------------------
	var checkAll = false;
	
	var checkIdOk = false;
	var checkNameOk = false;
	var checkEmailOk = false;
	
	var checkedId = '';
	var checkedName = '';
	var checkedEmail = '';
	
	//회원가입
	$(document).on('click', '#a_signUpModal_signUp', function() {
		
		var agreeornot = $('#signUpFormA').find('input[id="agreeornot"]:checked').val();
		
		var id = $('#signUpForm').find('input[id="id"]').val();
		var password = $('#signUpForm').find('input[id="password"]').val();
		var confirmpassword = $('#signUpForm').find('input[id="confirmpassword"]').val();
		var name = $('#signUpForm').find('input[id="name"]').val();
		var email = $('#signUpForm').find('input[id="email"]').val();
		var receivemailflag = $('#signUpForm').find('input[id="receivemailflag"]:checked').val();
		
//		alert(agreeornot);
		
		
		if (agreeornot != 1) {
			alert('약관에 동의하셔야만 회원 가입이 진행됩니다.')			

		} else {	
			
			if (id == "") {
				alert('아이디를 입력해주세요')						
			} else {								
				if (password.length < 4) {
					alert('비밀번호가 너무 짧습니다.')					
				} else if(password.length > 30){
					alert('비밀번호가 너무 깁니다.')
				} else { 
				
					if (password != confirmpassword) {				
					alert('비밀번호 확인을 다시 해 주세요')
					} else {
						if (name == "") {
							alert('닉네임을 입력해주세요')
						} else {
							if (email == "") {
								alert('이메일을 입력해주세요')
							} else {
								if (checkIdOk == false) {
									alert('중복되는 아이디가 있는지 확인해주세요')
								} else {
									if(checkNameOk == false) {
										alert('중복되는 닉네임이 있는지 확인해주세요')
									} else {
										if(checkEmailOk == false) {
											alert('중복되는 메일 주소가 있는지 확인해주세요')
										} else {
											if(checkedId == id && checkedName == name && checkedEmail == email) {
												
												// 중복 검사 완료
												
												checkDatasForSignUpModal();
												
												checkIdOk = false;
												checkNameOk = false;
												checkEmailOk = false;
												
												$('#loginModal').modal('hide');
												$('#register').removeClass('in active');
												$('#login').addClass('in active');
												$('#mytab > li:first').addClass('active');
												$('#mytab > li:last').removeClass('active');
											} else {
												alert('검사 후에 변경된 내용이 있습니다. 다시 한 번 중복검사해주세요')
											}
										}
									}
								}
																											
							}
						}
						
					}
				}
				
			}
			
		}
			
		});

		
	// 회원 가입 버튼 메소드
	var checkDatasForSignUpModal = function() {		
		var id = $('#signUpForm').find('input[id="id"]').val();
		var password = $('#signUpForm').find('input[id="password"]').val();
		var name = $('#signUpForm').find('input[id="name"]').val();
		var email = $('#signUpForm').find('input[id="email"]').val();
		var receivemailflag = $('#signUpForm').find('input[id="receivemailflag"]:checked').val();
		var likecolor = 0;
		var seasonoflikecolor = '';
		
		if($('#signUpForm').find('input[class="register_likeColorflag"]:checked').val() == 1) {
			//라디오 박스를 클릭했어도 색을 선택하지 않으면. 0이 입력됨.
			likecolor = Number($('#register_colorValue').val());
			seasonoflikecolor = $('#register_colorSeason').val();
		}
		
//		alert(id +' '+ password +' '+ name +' '+ email +' '+ receivemailflag +' '+ likecolor +' '+ seasonoflikecolor);
		
		
		$.ajax({
			url:'signUpOk.board',
			type: 'POST',
			data: {
				id: id,
				password: password,
				name: name,
				email: email,
				receivemailflag: receivemailflag,
				likecolor: likecolor,
				seasonoflikecolor : seasonoflikecolor
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('flag').text();
				
				if(flag == 0) {
						
					alert('회원가입완료');
					
				} else if(flag == 1){
					alert('회원가입실패');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
		
	};
	
	
	/*id체크*/
	$(document).on('click', '#idcheck', function() {

		checkId();
		
	});

	var checkId = function() {		
		var id = $('#signUpForm').find('input[id="id"]').val();
		
		$.ajax({
			url:'checkOk.board',
			type: 'POST',
			data: {
				id: id
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('idflag').text();
				
				standardId();
				idLengthCheck();
				
				if (checkIdOk == true) {
					if(flag == 0) {										
						//checkIdOk = true;
						checkedId = id;
						alert('사용하셔도 좋은 아이디입니다.');
					} else {
						checkIdOk = false;
						alert('중복되는 아이디가 있습니다.');
					}
				}
				
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};


		/*name체크*/
	$(document).on('click', '#namecheck', function() {

		checkName();
		
	});
	
	var checkName = function() {		
		var name = $('#signUpForm').find('input[id="name"]').val();
		
		$.ajax({
			url:'checkOk.board',
			type: 'POST',
			data: {
				name: name
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('nameflag').text();
				
				nameLengthCheck();
				
				if (checkNameOk == true) {
					if(flag == 0) {
							
						//checkNameOk = true;
						checkedName = name;
						alert('사용하셔도 좋은 닉네임입니다.');					
						
					} else {
						checkNameOk = false;
						alert('중복되는 닉네임이 있습니다.');
					}
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
		/*email 체크*/
	$(document).on('click', '#emailcheck', function() {

		checkEmail();
		
	});
	var checkEmail = function() {		
		var email = $('#signUpForm').find('input[id="email"]').val();
		
		$.ajax({
			url:'checkOk.board',
			type: 'POST',
			data: {
				email: email
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('emailflag').text();
				
				if(flag == 0) {
					standardEmail();	
					if(checkEmailOk == true) {
						checkedEmail = email;
						alert('사용하셔도 좋은 메일주소입니다.');	
					} else {
						
					}					
					
				} else {
					checkEmailOk = false;
					alert('중복되는 메일주소가 있습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	
	//--------------------------------------회원 등록 끝 ------------------------------
	
	
	
	//--------------------------------------회원 수정 시작------------------------------
	
	var nameInfo = '';
	var emailInfo = '';
	var receivemailflagInfo = '';
	
	$(document).on('click', '#a_modifyButton', function() {	
		var idInfo = $('#str_loggedId').val();
		$('#modifyModal').find('input[id="id"]').attr('value',idInfo);
		
		modifyCheck();	
		
		$('#modifyModal').modal('show');	
	});

	// ajax 회원정보 가져오기
	var modifyCheck = function() {	
		var ids = $('#str_loggedId').val();
		
		$.ajax({
			url:'modifyOk.board',
			type: 'POST',
			data: {
				id: ids				
			},
			dataType: 'xml',
			success: function(xml) {
		
				var id = $(xml).find('id').text();
				var name = $(xml).find('name').text();
				var email = $(xml).find('email').text();
				var likecolor = Number($(xml).find('likecolor').text());
				var seasonoflikecolor = $(xml).find('seasonoflikecolor').text();
				var receivemailflag = $(xml).find('receivemailflag').text();
				
				nameInfo = name;
			
				emailInfo = email;
				receivemailflagInfo = receivemailflag;
				
				$('#modifyModal').find('input[id="name"]').attr('value',nameInfo);
				$('#modifyModal').find('input[id="email"]').attr('value',emailInfo);
				
				if (receivemailflagInfo == 1) {
					$('#modifyModal').find('input[id="receivemailflagAgree"]').click();
				} else {
					$('#modifyModal').find('input[id="receivemailflagDisagree"]').click();
				}
				
				//초기화
				$("#modify_colorValue").val(0);
		        $("#modify_colorColor").val('');
		        $("#modify_colorTitle").val('');
		        $("#modify_colorSeason").val('');
				
				if(seasonoflikecolor != '' || seasonoflikecolor != 0) {
//					alert('선호 선택');
					
					// 클릭 이벤트 선호 색상 라디오 박스 클릭
					$('#modifyModal').find('input[id="modify_likeColorflag_select"]').click();
//					$('#modifyModal').find('input[id="modify_likeColorflag_none"]').attr('checked', '');
					
//					alert('seasonoflikecolor: ' + seasonoflikecolor);
//					alert('likecolor : ' + likecolor);
					
					switch(seasonoflikecolor) {
						case 'spring':
							$('#modifyModal').find('input[id="modify_selectSeasonflag_spring"]').click();
							$('#modify_colorselector_1').next().find('.dropdown-toggle').click();
							$('#modify_colorselector_1').next().find('.dropdown-menu').find('.colorList > a[data-value="'+likecolor+'"]').click();
							break;
						case 'summer':
							$('#modifyModal').find('input[id="modify_selectSeasonflag_summer"]').click();
							$('#modify_colorselector_2').next().find('.dropdown-toggle').click();
							$('#modify_colorselector_2').next().find('.dropdown-menu').find('.colorList > a[data-value="'+likecolor+'"]').click();
							break;
						case 'fall':
							$('#modifyModal').find('input[id="modify_selectSeasonflag_fall"]').click();
							$('#modify_colorselector_3').next().find('.dropdown-toggle').click();
							$('#modify_colorselector_3').next().find('.dropdown-menu').find('.colorList > a[data-value="'+likecolor+'"]').click();
							break;
						case 'winter':
							$('#modifyModal').find('input[id="modify_selectSeasonflag_winter"]').click();
							$('#modify_colorselector_4').next().find('.dropdown-toggle').click();
							$('#modify_colorselector_4').next().find('.dropdown-menu').find('.colorList > a[data-value="'+likecolor+'"]').click();
							break;
						default:
							break;
					}
					
					
					
				} else {
//					alert('선택 안함');
					
					$('#modifyModal').find('input[id="modify_likeColorflag_none"]').click();	//클릭 이벤트
//					$('#modifyModal').find('input[id="modify_likeColorflag_select"]').attr('checked', '');
				}
				
				
				
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	
	
	var checkedNameM = '';
	//회원정보수정
	$(document).on('click', '#a_modifyUpModal_modify', function() {

		var password = $('#modifyForm').find('input[id="password"]').val();
		var confirmpassword = $('#modifyForm').find('input[id="confirmpassword"]').val();
		var name = $('#modifyForm').find('input[id="name"]').val();
		var receivemailflag = $('modifyForm').find('input[name="receivemailflag"]:checked').val();
		
		if (password != confirmpassword) {				
			alert('비밀번호를 수정하시려면 새로운 비밀번호와 확인 비밀번호를 올바로 입력해주세요')
							
		} else {
			if (password.length < 4 && password.length > 0) {				
				alert('비밀번호가 너무 짧습니다.')					
			} else if(password.length > 30){
				alert('비밀번호가 너무 깁니다.')
			} else {
				if (name == "") {
					alert('닉네임을 입력해주세요')
				} else {
					if (nameInfo != name) {
						if(checkNameOk == false) {
							alert('중복되는 닉네임이 있는지 확인해주세요')
							
						} else {
							if(checkedNameM == name) {
								checkModifyModal();							
								checkNameOk = false;							
								$('#modifyModal').modal('hide');
	
							} else {
								alert('중복검사한 내용에 수정사항이 있습니다.')
							}
						}
					} else {
						
						//중복 검사 완료
						
							checkModifyModal();							
							checkNameOk = false;							
							$('#modifyModal').modal('hide');
		
					
					}

				}
				
			}
		}
			
	});


	var checkModifyModal = function() {		
		var id = $('#modifyForm').find('input[id="id"]').val();
		var password = $('#modifyForm').find('input[id="password"]').val();
		var name = $('#modifyForm').find('input[id="name"]').val();
		var email = $('#modifyForm').find('input[id="email"]').val();
		var receivemailflag = $('#modifyForm').find('input[name="receivemailflag"]:checked').val();
		var likecolor = 0;
		var seasonoflikecolor = '';
		
		if($('#modifyForm').find('input[class="modify_likeColorflag"]:checked').val() == 1) {
			
			likecolor = Number($('#modify_colorValue').val());
			seasonoflikecolor = $('#modify_colorSeason').val();
		}
		
//		alert(id +' '+ password +' '+ name +' '+ email +' '+ receivemailflag +' '+ likecolor +' '+ seasonoflikecolor);
		
		$.ajax({
			url:'modifySuccess.board',
			type: 'POST',
			data: {
				id: id,
				password: password,
				name: name,
				email: email,
				receivemailflag: receivemailflag,
				likecolor: likecolor,
				seasonoflikecolor : seasonoflikecolor
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('flag').text();
				var name = $(xml).find('name').text();

				if(flag == 0) {						
					alert('회원정보 수정완료');
					
					$('#str_loggedName').html(name + '님&nbsp;<b class="fa fa-angle-down"></b>');

				} else if(flag == 1){
					alert('회원정보 수정실패');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};


	$(document).on('click', '#namecheckModify', function() {	
		checkNameOk = false;
		var name = $('#modifyForm').find('input[id="name"]').val();
		if(nameInfo == name) {			
			alert('닉네임 변경을 원하시면 수정해주세요')
		} else {
			checkNameModify();
		}
	});
	
	var checkNameModify = function() {		
		var name = $('#modifyForm').find('input[id="name"]').val();
		
		$.ajax({
			url:'checkOk.board',
			type: 'POST',
			data: {
				name: name
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('nameflag').text();
				
				nameLengthCheckModify();
				
				if (checkNameOk == true) {
					if(flag == 0) {
						
						//checkNameOk = true;
						checkedNameM = name;
						alert('사용하셔도 좋은 닉네임입니다.');					
						
					} else {
						checkNameOk = false;
						alert('중복되는 닉네임이 있습니다.');
					}
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	
	//--------------------------------------회원 수정 끝------------------------------
	
	
	//--------------------------------------회원 탈퇴 시작------------------------------
	
	// 회원탈퇴페이지
	$(document).on('click', '#byebyeButton', function() {	
		var idInfo = $('#str_loggedId').val();
				
		$('#modifyModal').modal('hide');

		$('#byebyeModal').modal('show');	
	});
	
	$(document).on('click', '#a_byebyeModal_byebye', function() {	
		byebyeOk();
	});
	
	// 탈퇴처리버튼 클릭시
	var byebyeOk = function() {		
		var id = $('#str_loggedId').val();
		var password = $('#byebyeForm').find('input[id="password"]').val();
		var why = $('#byebyeForm').find('input[id="why"]').val();
	
		$.ajax({
			url:'byebye.board',
			type: 'POST',
			data: {
				id: id,
				password: password,
				why: why
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('flag').text();

				if(flag == 0) {						
					alert('탈퇴가 완료되었습니다.');
					$('#byebyeModal').modal('hide');
					logoutAndSendUserNameToDB();
					
				} else if(flag == 1){
					alert('회원 탈퇴 실패');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	//--------------------------------------회원 탈퇴 끝------------------------------

	//--------------------------------------임시 비밀번호 발급 시작------------------------------	
	$(document).on('click', '#button_loginModal_sendTempPassword', function() {
		sendTempPassword();
	});
	var sendTempPassword = function() {				
		var email = $('#findPassword').find('input[id="email"]').val();		
	
		$.ajax({
			url:'sendMail.board',
			type: 'POST',
			data: {
				email: email				
			},
			dataType: 'xml',
			success: function(xml) {
				
				var flag = $(xml).find('flag').text();

				if(flag == 0) {						
					alert('임시 비밀번호로 접속하시어 비밀번호 변경을 해주시기 바랍니다.');
					$('#findPassword').modal('hide');					
					
				} else if(flag == 1){
					alert('메일 전송에 실패하였습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('에러 : ' + status + '\n\n' + error);
			}
		});
	};
	
	//--------------------------------------임시 비밀번호 발급 끝-----------------------------
	var standardEmail = function() {		
		
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		  if (exptext.test($('#signUpForm').find('input[id="email"]').val())!=true){
			  alert('이메일 형식이 올바르지 않습니다.');
			  document.userInsertForm.oemail.focus();
			  checkEmailOk = false;
		  } else {
			  checkEmailOk = true;  
		  }
	}


	var standardId = function() {		
		
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		  if (exptext.test($('#signUpForm').find('input[id="id"]').val())!=true){			  
			  checkIdOk = true;
		  } else {
			  alert('아이디는 이메일 형식으로 만들 수 없습니다.');
			  document.userInsertForm.oemail.focus();
			  checkIdOk = false;  
		  }
	}

	var idLengthCheck = function() {
		if ($('#signUpForm').find('input[id="id"]').val().length < 2 || $('#signUpForm').find('input[id="id"]').val()=="" ) {
			alert('아이디는 최소 두 글자 이상입니다.')
			checkIdOk = false;
		} else {			
			if ($('#signUpForm').find('input[id="id"]').val().length > 20) {
				alert('아이디는 스무 자를 넘길 수 없습니다.')
				checkIdOk = false;
			} else {
				checkIdOk = true;
				
			}
		}
			
	}
	
	var nameLengthCheck = function() {
		if ($('#signUpForm').find('input[id="name"]').val().length < 2 || $('#signUpForm').find('input[id="name"]').val()=="" ) {
			alert('닉네임은 최소 두 글자 이상 입력해주세요.')
			checkNameOk = false;
		} else {			
			if ($('#signUpForm').find('input[id="name"]').val().length > 20) {
				alert('닉네임이 너무 깁니다')
				checkNameOk = false;
			} else {
				checkNameOk = true;
				
			}
		}
			
	}
	
	var nameLengthCheckModify = function() {		
		alert($('#modifyForm').find('input[id="name"]').val())
		if ($('#modifyForm').find('input[id="name"]').val().length < 2 || $('#modifyFrom').find('input[id="name"]').val()=="" ) {
			alert('닉네임은 최소 두 글자 이상 입력해주세요.')
			checkNameOk = false;
		} else {			
			if ($('#modifyFrom').find('input[id="name"]').val().length > 20) {
				alert('닉네임이 너무 깁니다')
				checkNameOk = false;
			} else {
				checkNameOk = true;
				
			}
		}
			
	}
	
});
