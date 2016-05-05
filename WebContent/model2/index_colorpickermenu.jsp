<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Custom JS by YYJ -->
			<script src="js/custom_by_yyj_for_colorpicker_productsorting.js"></script>
<!-- 
<div class="header" style = "margin-top: 30px;">
				<div class="header-navigation">
					<div class="container">
					<div class="row">	
							<div class="col-md-8">
								<nav class="navbar navbar-default" role="navigation">
									
									<div class="navbar-header">
										<button type="button" class="navbar-toggle br-orange" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
											<span class="sr-only">Toggle navigation</span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
										</button>
									</div>
									
									<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
										<ul class="nav navbar-nav2 navbar-left" id = "headerNavUl">
										
											<li class="dropdown">
												<a href="#" class="dropdown-toggle br-green" data-toggle="dropdown">

													<i class="fa fa-pencil-square-o fa-3x"></i>

													<span class="link-title">봄 <b class="fa fa-angle-down"></b></span> 
												</a>
												<ul class="dropdown-menu dropdown-md2">
												
													<li>
														<div class="row">
															<div class="col-md-12">
																<div class="col-inner">
																	<ul class="list-unstyled">
																		<li><a href="qnaBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 이벤트 Q&A</a></li>
																		<li><a href="reviewBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 후기</a></li>
																		<li><a href="requestBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 요청</a></li>
																	</ul>
																</div>
															</div>
															
														</div>
													</li>
													<li>
														<div class="row">
															<div class="col-md-12">
																<div class="col-inner">
																	<ul class="list-unstyled">
																		<li><a href="qnaBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 이벤트 Q&A</a></li>
																		<li><a href="reviewBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 후기</a></li>
																		<li><a href="requestBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 요청</a></li>
																	</ul>
																</div>
															</div>
															
														</div>
													</li>
													<li>
														<div class="row">
															<div class="col-md-12">
																<div class="col-inner">
																	<ul class="list-unstyled">
																		<li><a href="qnaBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 이벤트 Q&A</a></li>
																		<li><a href="reviewBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 후기</a></li>
																		<li><a href="requestBoard.board"><i class="fa fa-arrow-right dd-link-icon"></i> 요청</a></li>
																	</ul>
																</div>
															</div>
															
														</div>
													</li>
													
												</ul>
											</li>
											
											
											
										</ul>
									</div>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
-->

<div class="container">

	<div style = "height: 20px; width: 100%;">	<!-- space -->
	</div>
	
	<div class="col-sm-12">
			<div id="productsorting_selectorList">
				<div id="productsorting_spring" style="float: left;width:10%;">
					<div style = "text-align: center;">봄</div>
					<select id="productsorting_colorselector_1" style = "display: none">
						<option value="0" data-color="#000000" data-season="">#000000</option>

						<option value="1" data-color="#fcdbc8" data-season="spring">#fcdbc8</option>
						<option value="2" data-color="#fed1b4" data-season="spring">#fed1b4</option>
						<option value="3" data-color="#f8bcb4" data-season="spring">#f8bcb4</option>
						<option value="4" data-color="#fca4a3" data-season="spring">#fca4a3</option>
						<option value="5" data-color="#fb9495" data-season="spring">#fb9495</option>
						<option value="6" data-color="#fb7f7f" data-season="spring">#fb7f7f</option>
						<option value="7" data-color="#f99d88" data-season="spring">#f99d88</option>

						<option value="8" data-color="#f53c1e" data-season="spring">#f53c1e</option>
						<option value="9" data-color="#ff4121" data-season="spring">#ff4121</option>
						<option value="10" data-color="#fc3516" data-season="spring">#fc3516</option>
						<option value="11" data-color="#e82000" data-season="spring">#e82000</option>
						<option value="12" data-color="#d81f01" data-season="spring">#d81f01</option>
						<option value="13" data-color="#f6252a" data-season="spring">#f6252a</option>
						<option value="14" data-color="#d91819" data-season="spring">#d91819</option>

						<option value="15" data-color="#ffb245" data-season="spring">#ffb245</option>
						<option value="16" data-color="#ffca7e" data-season="spring">#ffca7e</option>
						<option value="17" data-color="#febc5c" data-season="spring">#febc5c</option>
						<option value="18" data-color="#ff9b0f" data-season="spring">#ff9b0f</option>
						<option value="19" data-color="#ff8a00" data-season="spring">#ff8a00</option>
						<option value="20" data-color="#ff7e00" data-season="spring">#ff7e00</option>
						<option value="21" data-color="#fc6b00" data-season="spring">#fc6b00</option>

						<option value="22" data-color="#fff997" data-season="spring">#fff997</option>
						<option value="23" data-color="#fffcc7" data-season="spring">#fffcc7</option>
						<option value="24" data-color="#fefbb1" data-season="spring">#fefbb1</option>
						<option value="25" data-color="#fdf681" data-season="spring">#fdf681</option>
						<option value="26" data-color="#ffeb68" data-season="spring">#ffeb68</option>
						<option value="27" data-color="#fee63f" data-season="spring">#fee63f</option>
						<option value="28" data-color="#ffdc4e" data-season="spring">#ffdc4e</option>

						<option value="29" data-color="#acec57" data-season="spring">#acec57</option>
						<option value="30" data-color="#ccf099" data-season="spring">#ccf099</option>
						<option value="31" data-color="#bde87f" data-season="spring">#bde87f</option>
						<option value="32" data-color="#86dc19" data-season="spring">#86dc19</option>
						<option value="33" data-color="#69db14" data-season="spring">#69db14</option>
						<option value="34" data-color="#6ebe4b" data-season="spring">#6ebe4b</option>
						<option value="35" data-color="#04af45" data-season="spring">#04af45</option>

						<option value="36" data-color="#adf1f2" data-season="spring">#adf1f2</option>
						<option value="37" data-color="#cdf8ef" data-season="spring">#cdf8ef</option>
						<option value="38" data-color="#b9f2e9" data-season="spring">#b9f2e9</option>
						<option value="39" data-color="#7deeda" data-season="spring">#7deeda</option>
						<option value="40" data-color="#6ce5fa" data-season="spring">#6ce5fa</option>
						<option value="41" data-color="#31c9fa" data-season="spring">#31c9fa</option>
						<option value="42" data-color="#1d4eb7" data-season="spring">#1d4eb7</option>

						<option value="43" data-color="#a66dd4" data-season="spring">#a66dd4</option>
						<option value="44" data-color="#7a43ab" data-season="spring">#7a43ab</option>
						<option value="45" data-color="#8942c4" data-season="spring">#8942c4</option>
						<option value="46" data-color="#8b30d5" data-season="spring">#8b30d5</option>
						<option value="47" data-color="#9c4cdb" data-season="spring">#9c4cdb</option>
						<option value="48" data-color="#a35adf" data-season="spring">#a35adf</option>
						<option value="49" data-color="#9854cd" data-season="spring">#9854cd</option>

						<option value="50" data-color="#f5e391" data-season="spring">#f5e391</option>
						<option value="51" data-color="#eac968" data-season="spring">#eac968</option>
						<option value="52" data-color="#d7ac50" data-season="spring">#d7ac50</option>
						<option value="53" data-color="#d69b3d" data-season="spring">#d69b3d</option>
						<option value="54" data-color="#b9711d" data-season="spring">#b9711d</option>
						<option value="55" data-color="#9f5a0d" data-season="spring">#9f5a0d</option>
						<option value="56" data-color="#946027" data-season="spring">#946027</option>

						<option value="57" data-color="#bcb6a0" data-season="spring">#bcb6a0</option>
						<option value="58" data-color="#d8c9c4" data-season="spring">#d8c9c4</option>
						<option value="59" data-color="#d3c2bb" data-season="spring">#d3c2bb</option>
						<option value="60" data-color="#c7b4ae" data-season="spring">#c7b4ae</option>
						<option value="61" data-color="#d6bdb8" data-season="spring">#d6bdb8</option>
						<option value="62" data-color="#d6c0b2" data-season="spring">#d6c0b2</option>
						<option value="63" data-color="#c9b4af" data-season="spring">#c9b4af</option>

						<option value="64" data-color="#445281" data-season="spring">#445281</option>
						<option value="65" data-color="#324377" data-season="spring">#324377</option>
						<option value="66" data-color="#2a3869" data-season="spring">#2a3869</option>
						<option value="67" data-color="#212e58" data-season="spring">#212e58</option>
						<option value="68" data-color="#2a4e8a" data-season="spring">#2a4e8a</option>
						<option value="69" data-color="#2a4783" data-season="spring">#2a4783</option>
						<option value="70" data-color="#264389" data-season="spring">#264389</option>

					</select>
				</div>
				<div id="productsorting_summer" style="float: left;width:10%;">
					<div style = "text-align: center;">여름</div>
					<select id="productsorting_colorselector_2" style = "display: none">
						<option value="0" data-color="#000000" data-season="">#000000</option>

						<option value="1" data-color="#d18794" data-season="summer">#d18794</option>
						<option value="2" data-color="#dba2ab" data-season="summer">#dba2ab</option>
						<option value="3" data-color="#fdbcdc" data-season="summer">#fdbcdc</option>
						<option value="4" data-color="#ff89b9" data-season="summer">#ff89b9</option>
						<option value="5" data-color="#fa9fcb" data-season="summer">#fa9fcb</option>
						<option value="6" data-color="#ffd6ea" data-season="summer">#ffd6ea</option>
						<option value="7" data-color="#fad5dc" data-season="summer">#fad5dc</option>

						<option value="8" data-color="#f26170" data-season="summer">#f26170</option>
						<option value="9" data-color="#f45363" data-season="summer">#f45363</option>
						<option value="10" data-color="#f54051" data-season="summer">#f54051</option>
						<option value="11" data-color="#f43344" data-season="summer">#f43344</option>
						<option value="12" data-color="#eb304f" data-season="summer">#eb304f</option>
						<option value="13" data-color="#e42544" data-season="summer">#e42544</option>
						<option value="14" data-color="#ca4057" data-season="summer">#ca4057</option>

						<option value="15" data-color="#eeff7d" data-season="summer">#eeff7d</option>
						<option value="16" data-color="#efff85" data-season="summer">#efff85</option>
						<option value="17" data-color="#f2ffa5" data-season="summer">#f2ffa5</option>
						<option value="18" data-color="#f4ffa3" data-season="summer">#f4ffa3</option>
						<option value="19" data-color="#f4ffb3" data-season="summer">#f4ffb3</option>
						<option value="20" data-color="#f6ffbe" data-season="summer">#f6ffbe</option>
						<option value="21" data-color="#f9ffd9" data-season="summer">#f9ffd9</option>

						<option value="22" data-color="#86e295" data-season="summer">#86e295</option>
						<option value="23" data-color="#98eea7" data-season="summer">#98eea7</option>
						<option value="24" data-color="#c8ffd5" data-season="summer">#c8ffd5</option>
						<option value="25" data-color="#93e3c0" data-season="summer">#93e3c0</option>
						<option value="26" data-color="#68cfb2" data-season="summer">#68cfb2</option>
						<option value="27" data-color="#43c19c" data-season="summer">#43c19c</option>
						<option value="28" data-color="#27b289" data-season="summer">#27b289</option>

						<option value="29" data-color="#d1eaff" data-season="summer">#d1eaff</option>
						<option value="30" data-color="#bde0fc" data-season="summer">#bde0fc</option>
						<option value="31" data-color="#aed8fb" data-season="summer">#aed8fb</option>
						<option value="32" data-color="#a2bae8" data-season="summer">#a2bae8</option>
						<option value="33" data-color="#7996ce" data-season="summer">#7996ce</option>
						<option value="34" data-color="#78a2ee" data-season="summer">#78a2ee</option>
						<option value="35" data-color="#4e6caa" data-season="summer">#4e6caa</option>

						<option value="36" data-color="#896388" data-season="summer">#896388</option>
						<option value="37" data-color="#8f5a8e" data-season="summer">#8f5a8e</option>
						<option value="38" data-color="#dbbdf9" data-season="summer">#dbbdf9</option>
						<option value="39" data-color="#cda8f5" data-season="summer">#cda8f5</option>
						<option value="40" data-color="#c296f5" data-season="summer">#c296f5</option>
						<option value="41" data-color="#9a72d3" data-season="summer">#9a72d3</option>
						<option value="42" data-color="#8b64c1" data-season="summer">#8b64c1</option>

						<option value="43" data-color="#b59b9a" data-season="summer">#b59b9a</option>
						<option value="44" data-color="#b8aaa9" data-season="summer">#b8aaa9</option>
						<option value="45" data-color="#d4c8b0" data-season="summer">#d4c8b0</option>
						<option value="46" data-color="#aa9898" data-season="summer">#aa9898</option>
						<option value="47" data-color="#9b8383" data-season="summer">#9b8383</option>
						<option value="48" data-color="#997b7b" data-season="summer">#997b7b</option>
						<option value="49" data-color="#8c6a69" data-season="summer">#8c6a69</option>

						<option value="50" data-color="#aaacb9" data-season="summer">#aaacb9</option>
						<option value="51" data-color="#bdbfcc" data-season="summer">#bdbfcc</option>
						<option value="52" data-color="#c5c5c5" data-season="summer">#c5c5c5</option>
						<option value="53" data-color="#b2b1b6" data-season="summer">#b2b1b6</option>
						<option value="54" data-color="#9d9d9d" data-season="summer">#9d9d9d</option>
						<option value="55" data-color="#868686" data-season="summer">#868686</option>
						<option value="56" data-color="#9e9898" data-season="summer">#9e9898</option>

						<option value="57" data-color="#647096" data-season="summer">#647096</option>
						<option value="58" data-color="#59658b" data-season="summer">#59658b</option>
						<option value="59" data-color="#586383" data-season="summer">#586383</option>
						<option value="60" data-color="#535c7d" data-season="summer">#535c7d</option>
						<option value="61" data-color="#4c567a" data-season="summer">#4c567a</option>
						<option value="62" data-color="#47506f" data-season="summer">#47506f</option>
						<option value="63" data-color="#434a64" data-season="summer">#434a64</option>

					</select>
				</div>
				<div id="productsorting_fall" style="float: left;width:10%;">
					<div style = "text-align: center;">가을</div>
					<select id="productsorting_colorselector_3" style = "display: none">
						<option value="0" data-color="#000000" data-season="">#000000</option>

						<option value="1" data-color="#ffbba6" data-season="fall">#ffbba6</option>
						<option value="2" data-color="#e69d8a" data-season="fall">#e69d8a</option>
						<option value="3" data-color="#df8e74" data-season="fall">#df8e74</option>
						<option value="4" data-color="#f29e86" data-season="fall">#f29e86</option>
						<option value="5" data-color="#fe9578" data-season="fall">#fe9578</option>
						<option value="6" data-color="#ec8063" data-season="fall">#ec8063</option>
						<option value="7" data-color="#d97154" data-season="fall">#d97154</option>

						<option value="8" data-color="#5b0c08" data-season="fall">#5b0c08</option>
						<option value="9" data-color="#84130d" data-season="fall">#84130d</option>
						<option value="10" data-color="#9e1d08" data-season="fall">#9e1d08</option>
						<option value="11" data-color="#bd2d15" data-season="fall">#bd2d15</option>
						<option value="12" data-color="#c82208" data-season="fall">#c82208</option>
						<option value="13" data-color="#802826" data-season="fall">#802826</option>
						<option value="14" data-color="#682d29" data-season="fall">#682d29</option>

						<option value="15" data-color="#a44809" data-season="fall">#a44809</option>
						<option value="16" data-color="#d25b0b" data-season="fall">#d25b0b</option>
						<option value="17" data-color="#c66e17" data-season="fall">#c66e17</option>
						<option value="18" data-color="#da7919" data-season="fall">#da7919</option>
						<option value="19" data-color="#ed7e13" data-season="fall">#ed7e13</option>
						<option value="20" data-color="#f49335" data-season="fall">#f49335</option>
						<option value="21" data-color="#f2a964" data-season="fall">#f2a964</option>

						<option value="22" data-color="#674a04" data-season="fall">#674a04</option>
						<option value="23" data-color="#7f650e" data-season="fall">#7f650e</option>
						<option value="24" data-color="#aa8405" data-season="fall">#aa8405</option>
						<option value="25" data-color="#d8ad13" data-season="fall">#d8ad13</option>
						<option value="26" data-color="#ffdd67" data-season="fall">#ffdd67</option>
						<option value="27" data-color="#986d40" data-season="fall">#986d40</option>
						<option value="28" data-color="#864e13" data-season="fall">#864e13</option>

						<option value="29" data-color="#40560b" data-season="fall">#40560b</option>
						<option value="30" data-color="#4b6907" data-season="fall">#4b6907</option>
						<option value="31" data-color="#5f8609" data-season="fall">#5f8609</option>
						<option value="32" data-color="#80a62d" data-season="fall">#80a62d</option>
						<option value="33" data-color="#84b14a" data-season="fall">#84b14a</option>
						<option value="34" data-color="#b5c376" data-season="fall">#b5c376</option>
						<option value="35" data-color="#cddb8e" data-season="fall">#cddb8e</option>

						<option value="36" data-color="#94b6c2" data-season="fall">#94b6c2</option>
						<option value="37" data-color="#9cc6d4" data-season="fall">#9cc6d4</option>
						<option value="38" data-color="#53a2af" data-season="fall">#53a2af</option>
						<option value="39" data-color="#248fa3" data-season="fall">#248fa3</option>
						<option value="40" data-color="#3799a6" data-season="fall">#3799a6</option>
						<option value="41" data-color="#085d7a" data-season="fall">#085d7a</option>
						<option value="42" data-color="#044257" data-season="fall">#044257</option>

						<option value="43" data-color="#826395" data-season="fall">#826395</option>
						<option value="44" data-color="#795595" data-season="fall">#795595</option>
						<option value="45" data-color="#8155a4" data-season="fall">#8155a4</option>
						<option value="46" data-color="#652a88" data-season="fall">#652a88</option>
						<option value="47" data-color="#7a3aac" data-season="fall">#7a3aac</option>
						<option value="48" data-color="#4e1a6c" data-season="fall">#4e1a6c</option>
						<option value="49" data-color="#441664" data-season="fall">#441664</option>

						<option value="50" data-color="#422807" data-season="fall">#422807</option>
						<option value="51" data-color="#5b3b14" data-season="fall">#5b3b14</option>
						<option value="52" data-color="#78572c" data-season="fall">#78572c</option>
						<option value="53" data-color="#bc9f67" data-season="fall">#bc9f67</option>
						<option value="54" data-color="#ceb179" data-season="fall">#ceb179</option>
						<option value="55" data-color="#dbc393" data-season="fall">#dbc393</option>
						<option value="56" data-color="#eed8b1" data-season="fall">#eed8b1</option>

						<option value="57" data-color="#605454" data-season="fall">#605454</option>
						<option value="58" data-color="#6c5d5a" data-season="fall">#6c5d5a</option>
						<option value="59" data-color="#776767" data-season="fall">#776767</option>
						<option value="60" data-color="#8f7d7d" data-season="fall">#8f7d7d</option>
						<option value="61" data-color="#a09b85" data-season="fall">#a09b85</option>
						<option value="62" data-color="#8a7f7d" data-season="fall">#8a7f7d</option>
						<option value="63" data-color="#726867" data-season="fall">#726867</option>

						<option value="64" data-color="#4f5e7b" data-season="fall">#4f5e7b</option>
						<option value="65" data-color="#3f5972" data-season="fall">#3f5972</option>
						<option value="66" data-color="#2d4762" data-season="fall">#2d4762</option>
						<option value="67" data-color="#1f3c5a" data-season="fall">#1f3c5a</option>
						<option value="68" data-color="#1c414a" data-season="fall">#1c414a</option>
						<option value="69" data-color="#122d4a" data-season="fall">#122d4a</option>
						<option value="70" data-color="#0c2742" data-season="fall">#0c2742</option>

					</select>
				</div>
				<div id="productsorting_winter" style="float: left;width:10%;">
					<div style = "text-align: center;">겨울</div>
					<select id="productsorting_colorselector_4" style = "display: none">
						<option value="0" data-color="#000000" data-season="">#000000</option>

						<option value="1" data-color="#ffdcf2" data-season="winter">##ffdcf2</option>
						<option value="2" data-color="#fec5e8" data-season="winter">##fec5e8</option>
						<option value="3" data-color="#f344b1" data-season="winter">##f344b1</option>
						<option value="4" data-color="#e22798" data-season="winter">##e22798</option>
						<option value="5" data-color="#c90d64" data-season="winter">##c90d64</option>
						<option value="6" data-color="#6b143e" data-season="winter">##6b143e</option>
						<option value="7" data-color="#ff3a99" data-season="winter">##ff3a99</option>

						<option value="8" data-color="#471025" data-season="winter">##471025</option>
						<option value="9" data-color="#611b35" data-season="winter">##611b35</option>
						<option value="10" data-color="#590828" data-season="winter">##590828</option>
						<option value="11" data-color="#6f0930" data-season="winter">##6f0930</option>
						<option value="12" data-color="#8c0f37" data-season="winter">##8c0f37</option>
						<option value="13" data-color="#9e103e" data-season="winter">##9e103e</option>
						<option value="14" data-color="#a70a3d" data-season="winter">##a70a3d</option>

						<option value="15" data-color="#feffe0" data-season="winter">##feffe0</option>
						<option value="16" data-color="#fdffb6" data-season="winter">##fdffb6</option>
						<option value="17" data-color="#faff6f" data-season="winter">##faff6f</option>
						<option value="18" data-color="#f5ff02" data-season="winter">##f5ff02</option>
						<option value="19" data-color="#e9fe02" data-season="winter">##e9fe02</option>
						<option value="20" data-color="#f0ff44" data-season="winter">##f0ff44</option>
						<option value="21" data-color="#f1ff62" data-season="winter">##f1ff62</option>

						<option value="22" data-color="#09523f" data-season="winter">##09523f</option>
						<option value="23" data-color="#016048" data-season="winter">##016048</option>
						<option value="24" data-color="#17493d" data-season="winter">##17493d</option>
						<option value="25" data-color="#063f1c" data-season="winter">##063f1c</option>
						<option value="26" data-color="#02522b" data-season="winter">##02522b</option>
						<option value="27" data-color="#006233" data-season="winter">##006233</option>
						<option value="28" data-color="#1b9d4b" data-season="winter">##1b9d4b</option>

						<option value="29" data-color="#dbe1ff" data-season="winter">##dbe1ff</option>
						<option value="30" data-color="#6755f5" data-season="winter">##6755f5</option>
						<option value="31" data-color="#4813f5" data-season="winter">##4813f5</option>
						<option value="32" data-color="#2073b5" data-season="winter">##2073b5</option>
						<option value="33" data-color="#483baf" data-season="winter">##483baf</option>
						<option value="34" data-color="#1b53b0" data-season="winter">##1b53b0</option>
						<option value="35" data-color="#0e23a2" data-season="winter">##0e23a2</option>

						<option value="36" data-color="#450f4d" data-season="winter">##450f4d</option>
						<option value="37" data-color="#53075f" data-season="winter">##53075f</option>
						<option value="38" data-color="#6b1078" data-season="winter">##6b1078</option>
						<option value="39" data-color="#9e14b5" data-season="winter">##9e14b5</option>
						<option value="40" data-color="#911ea3" data-season="winter">##911ea3</option>
						<option value="41" data-color="#832091" data-season="winter">##832091</option>
						<option value="42" data-color="#691a87" data-season="winter">##691a87</option>

						<option value="43" data-color="#ffffff" data-season="winter">##ffffff</option>
						<option value="44" data-color="#fafafa" data-season="winter">##fafafa</option>
						<option value="45" data-color="#efeeec" data-season="winter">##efeeec</option>
						<option value="46" data-color="#ece9e4" data-season="winter">##ece9e4</option>
						<option value="47" data-color="#e5e2dd" data-season="winter">##e5e2dd</option>
						<option value="48" data-color="#dedad1" data-season="winter">##dedad1</option>
						<option value="49" data-color="#dfd6c5" data-season="winter">##dfd6c5</option>

						<option value="50" data-color="#424651" data-season="winter">##424651</option>
						<option value="51" data-color="#3f3f3f" data-season="winter">##3f3f3f</option>
						<option value="52" data-color="#565656" data-season="winter">##565656</option>
						<option value="53" data-color="#676767" data-season="winter">##676767</option>
						<option value="54" data-color="#e7e7e7" data-season="winter">##e7e7e7</option>
						<option value="55" data-color="#f3f1f2" data-season="winter">##f3f1f2</option>
						<option value="56" data-color="#7e7e7e" data-season="winter">##7e7e7e</option>

						<option value="57" data-color="#181077" data-season="winter">##181077</option>
						<option value="58" data-color="#110d61" data-season="winter">##110d61</option>
						<option value="59" data-color="#1b195b" data-season="winter">##1b195b</option>
						<option value="60" data-color="#211f4e" data-season="winter">##211f4e</option>
						<option value="61" data-color="#24234b" data-season="winter">##24234b</option>
						<option value="62" data-color="#181b37" data-season="winter">##181b37</option>
						<option value="63" data-color="#000000" data-season="winter">##000000</option>
					</select>
				</div>
				<div id="space" style="float: right; padding-left:10px; width:60%; vertical-align: middle;">
				<h4>선택 색상 & 계절</h4>
					<input type="text" id="productsorting_colorValue" style = "display: none;" value = "0"/> 
					<input type="text" id="productsorting_colorColor" style = "border:0px; background-color: lightgrey; color: black;" value = "" maxlength="10" readonly="readonly"/>
					<input type="text" id="productsorting_colorTitle" style = "display: none;" value = ""/>
					<input type="text" id="productsorting_colorSeason"style = "border:0px; background-color: lightgrey; color: black;" value = "" maxlength="10" readonly="readonly"/>
					&nbsp; <a id = "a_ColorPickerProductSorting" class="btn btn-black" style = "margin-top: -5px;">색상으로 검색</a>
					&nbsp;&nbsp;&nbsp;<a id = "a_initSortingProducts" class="btn btn-black" style = "margin-top: -5px;">일반 전체 검색</a>
				</div>
		</div>
	</div>
</div>
		
