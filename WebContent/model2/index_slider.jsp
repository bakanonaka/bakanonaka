<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <!-- Styles -->
  <!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
		<link href="css/settings.css" rel="stylesheet">
		
  <!-- Javascript files -->
		<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
		<script type="text/javascript" src="js/jquery.themepunch.plugins.min.js"></script>
		<script type="text/javascript" src="js/jquery.themepunch.revolution.min.js"></script>
		
		<!-- This Page JavaScript -->
		<script type="text/javascript" >
		
			// SLIDER REVOLUTION Java Script
				jQuery(document).ready(function() {
					   jQuery('.tp-banner').revolution(
						{
							delay:7000,
							
							startheight:300,	/* 500 -> 300 */
							
							hideThumbs:10,
							
							navigationType:"none",	
							
							
							hideArrowsOnMobile:"on",
							
							touchenabled:"on",
							onHoverStop:"on",
							
							navOffsetHorizontal:0,
							navOffsetVertical:20,
							
							stopAtSlide:-1,
							stopAfterLoops:-1,

							shadow:0,
							
							fullWidth:"off",
							fullScreen:"off"
						});
				});
				
			// Type your codde here
		</script>
		
<!-- Slider Start -->
			
			<div class="container">
				<!--
				#################################
					- THEMEPUNCH BANNER -
				#################################
				-->
				<div class="tp-banner-container">
					<div class="tp-banner" >
						<ul>
							
							<!-- SLIDE  -->
							<li data-transition="zoomout" data-slotamount="7" data-masterspeed="1000" >
								<!-- MAIN IMAGE -->
								<img src="img/slider/slide1_.jpg"  alt="darkblurbg"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
								<!-- LAYERS -->
								<!-- LAYERS NR. 1 // -->
								
								<!-- 
								
									data-x="50" 		좌상단 좌표x	
									data-y="140"		우상단 좌표y
									data-speed="1500"	움직임 스피드
									data-start="1000"	1초 후 시작
									data-easing="Power4.easeOut"	
									data-endspeed="300"				마지막 움직임 스피드
									data-endeasing="Linear.easeNone"
									data-captionhidden="off">Red Perspective
								
								 -->
								 
								<div class="tp-caption lfl largegreenbg br-red"
									data-x="-55"	
									data-y="20"
									data-speed="3000"
									data-start="1000"
									data-easing="Power4.easeOut"
									data-endspeed="0"
									data-endeasing="Linear.easeNone"
									data-captionhidden="off">나만의 립스틱
								</div>	
								<!-- LAYERS NR. 2 -->
								<div class="tp-caption skewfromleft medium_text paragraph"
									data-x="-45"
									data-y="120"
									data-speed="800"
									data-start="2000"
									data-easing="Power4.easeOut"
									data-endspeed="400"
									data-endeasing="Power4.easeOut"
									data-captionhidden="off">What's your personal Color?<br />Find Lipstick, only for you
								</div>
								<!-- LAYERS NR. 3 // Slide Button -->
<!-- 								<div class="tp-caption sfb" -->
<!-- 									data-x="-45" -->
<!-- 									data-y="220" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="3000" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><a href="#" class="btn btn-info btn-lg btn-circle">Know More <i class="fa fa-angle-right"></i></a> -->
<!-- 								</div> -->
							</li>
							
							
							<!-- SLIDE  -->
							<li data-transition="zoomout" data-slotamount="7" data-masterspeed="1500" >
								<!-- MAIN IMAGE -->
								<img src="img/slider/slide2.jpg"  alt="slidebg1"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
								<!-- LAYERS -->
								<!-- LAYERS NR. 1 // Logo Image -->
								<div class="tp-caption lfl"
									data-x="130"
									data-y="center"
									data-speed="1500"
									data-start="1200"
									data-easing="Power4.easeOut"
									data-endspeed="300"
									data-endeasing="Linear.easeNone"
									data-captionhidden="off"><img src="img/logo/r_logo_l.png" class="img-responsive" alt="" />
<!-- 									data-captionhidden="off"><img src="img/slider/logo.png" class="img-responsive" alt="" /> -->
								</div>
								<!-- LAYERS NR. 2 -->
								<div class="tp-caption lfr thinheadline_dark"
									data-x="500"
									data-y="30"
									data-speed="1500"
									data-start="2000"
									data-easing="Power4.easeOut"
									data-endspeed="300"
									data-endeasing="Linear.easeNone"
									data-captionhidden="off">Welcome To
								</div>
								<!-- LAYERS NR. 2.1 -->
								<div class="tp-caption lfr very_large_text"
									data-x="500"
									data-y="60"
									data-speed="1500"
									data-start="2300"
									data-easing="Power4.easeOut"
									data-endspeed="300"
									data-endeasing="Linear.easeNone"
									data-captionhidden="off">Redlipstick
								</div>
								<!-- LAYERS NR. 3 -->
								<div class="tp-caption lfr medium_text paragraph"
									data-x="500"
									data-y="130"
									data-speed="1500"
									data-start="3000"
									data-easing="Power4.easeOut"
									data-endspeed="300"
									data-endeasing="Linear.easeNone"
									data-captionhidden="off">Choose Color for your lip
								</div>
<!-- 								LAYERS NR. 4 // Slide Button -->
<!-- 								<div class="tp-caption lfr medium_text paragraph" -->
<!-- 									data-x="500" -->
<!-- 									data-y="160" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="3400" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"> Looking for your <br />personal Color -->
<!-- 								</div> -->
								<!-- LAYERS NR. 4.1 // Slide Button -->
<!-- 								<div class="tp-caption sfb" -->
<!-- 									data-x="500" -->
<!-- 									data-y="200" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="3400" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><a href="#" class="btn btn-info btn-lg btn-circle">Show me <i class="fa fa-angle-right"></i></a> -->
<!-- 								</div> -->
							</li>
							
							
							<!-- SLIDE  -->
							<li data-transition="zoomout" data-slotamount="7" data-masterspeed="1500" >
								<!-- MAIN IMAGE -->
								<img src="img/slider/slide3_.jpg"  alt="slidebg1"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
<!-- 								LAYERS NR. 1 -->
<!-- 								<div class="tp-caption lfl" -->
<!-- 									data-x="100" -->
<!-- 									data-y="20" -->
<!-- 									data-speed="800" -->
<!-- 									data-start="1500" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><img src="img/slider/s31.png" class="img-responsive" alt="" /> -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 2 -->
<!-- 								<div class="tp-caption lfl" -->
<!-- 									data-x="0" -->
<!-- 									data-y="142" -->
<!-- 									data-speed="800" -->
<!-- 									data-start="2100" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><img src="img/slider/s32.png" class="img-responsive" alt="" /> -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 3 -->
<!-- 								<div class="tp-caption lfl" -->
<!-- 									data-x="350" -->
<!-- 									data-y="180" -->
<!-- 									data-speed="800" -->
<!-- 									data-start="2700" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><img src="img/slider/s33.png" class="img-responsive" alt="" /> -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 4 -->
<!-- 								<div class="tp-caption lfl" -->
<!-- 									data-x="250" -->
<!-- 									data-y="199" -->
<!-- 									data-speed="800" -->
<!-- 									data-start="3300" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off"><img src="img/slider/s34.png" class="img-responsive" alt="" /> -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 5 // -->
<!-- 								<div class="tp-caption lfr largegreenbg br-orange" -->
<!-- 									data-x="right" -->
<!-- 									data-hoffset="-10" -->
<!-- 									data-y="30" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="4000" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off">Full Responsive -->
<!-- 								</div>	 -->
<!-- 								LAYERS NR. 6 // -->
<!-- 								<div class="tp-caption lfr modern_big_redbg br-green" -->
<!-- 									data-x="right" -->
<!-- 									data-hoffset="-10" -->
<!-- 									data-y="110" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="4300" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off">Desktop -->
<!-- 								</div>	 -->
<!-- 								LAYERS NR. 7 // -->
<!-- 								<div class="tp-caption lfr modern_big_redbg br-yellow" -->
<!-- 									data-x="right" -->
<!-- 									data-hoffset="-10" -->
<!-- 									data-y="160" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="4600" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off">Laptop & Tablet -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 8 // -->
<!-- 								<div class="tp-caption lfr modern_big_redbg br-blue" -->
<!-- 									data-x="right" -->
<!-- 									data-hoffset="-10" -->
<!-- 									data-y="210" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="4900" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off">Smartphone & i-pad -->
<!-- 								</div> -->
<!-- 								LAYERS NR. 9 // -->
<!-- 								<div class="tp-caption lfr modern_big_redbg br-purple" -->
<!-- 									data-x="right" -->
<!-- 									data-hoffset="-10" -->
<!-- 									data-y="260" -->
<!-- 									data-speed="1500" -->
<!-- 									data-start="5200" -->
<!-- 									data-easing="Power4.easeOut" -->
<!-- 									data-endspeed="300" -->
<!-- 									data-endeasing="Linear.easeNone" -->
<!-- 									data-captionhidden="off">Mobile Phone -->
<!-- 								</div> -->
							</li>
							
						</ul>
					</div>
				</div>
			</div>
			
<!-- Slider End -->
		