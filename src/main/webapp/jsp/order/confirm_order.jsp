<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="${base }/style/style.css" />
<style>
</style>
<script type="text/javascript" src="${base }/js/jquery-1.12.4.min.js"></script>
<script>
	function addAddress() {
		var addressDiv = document.getElementById("new_Address");

		var maskDiv = document.createElement("div");
		maskDiv.id = "mask";
		maskDiv.style.position = "absolute";

		var l = getScrollTop();

		maskDiv.style.top = l + "px";
		maskDiv.style.left = "0";
		var w = document.body.clientWidth;
		var h = document.documentElement.clientHeight;
		maskDiv.style.width = w + "px";
		maskDiv.style.height = h + "px";
		maskDiv.style.zIndex = "2";
		maskDiv.style.backgroundColor = "#CCC";
		maskDiv.style.opacity = "0.5";

		document.body.appendChild(maskDiv);

		addressDiv.style.position = "absolute";
		addressDiv.style.display = "block";
		addressDiv.style.zIndex = "4";
		addressDiv.style.top = (l + 60) + "px";
		addressDiv.style.left = "300px";
	}

	//获得滚动条滚动的距离
	function getScrollTop() {
		var scrollPos;
		if (window.pageYOffset) {
			scrollPos = window.pageYOffset;
		} else if (document.compatMode && document.compatMode != 'BackCompat') {
			scrollPos = document.documentElement.scrollTop;
		} else if (document.body) {
			scrollPos = document.body.scrollTop;
		}
		return scrollPos;
	}

	//关闭添加地址页面
	function closeDiv(obj) {
		var addressDiv = document.getElementById("new_Address");

		var maskDiv = document.getElementById("mask");
		document.body.removeChild(maskDiv);

		addressDiv.style.display = "none";
	}

	function saveAddress() {
		var addressDiv = document.getElementById("new_Address");
		var maskDiv = document.getElementById("mask");
		document.body.removeChild(maskDiv);

		addressDiv.style.display = "none";
	}

	function selectLi(num) {
		var lis = document.getElementsByName("address");

		for ( var i = 0; i < lis.length; i++) {
			if (i == num) {
				lis[i].parentNode.style.border = "2px dotted red";
			} else {
				lis[i].parentNode.style.border = "";
			}
		}

	}

	onload = function() {
		var checks = document.getElementsByName("address");

		for ( var i = 0; i < checks.length; i++) {
			if (checks[i].checked) {
				checks[i].parentNode.style.border = "2px dotted red";
			}
		}
	}
	
	function sub(){
		var address = document.getElementsByName("address");
		for ( var i = 0; i < address.length; i++) {
			if(address[i].checked){
				addId = address[i].value;
			}
		}
		if(null != addId){
			window.location="${base}/order/"+addId+"/${itemAllPrice}/save.do";
		};
	}
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$.post("${base}/ssxjl/province.do", function(data) {
			var html = "";
			for ( var i = 0; i < data.length; i++) {
				html += "<option value="+data[i].provinceCode+">"+data[i].provinceName+"</option>";
			}
			$("#province").append(html);
		});
	});
	
	$(function(){
		$("#province").change(function(){
			$("#city").get(0).length=1;
			$("#area").get(0).length=1;
			$.post("${base}/ssxjl/city.do",{'provinceCode':$("#province").val()},function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					html += "<option value="+data[i].cityCode+">"+data[i].cityName+"</option>";
				}
				$("#city").append(html);
			});
		});
	});
	
	$(function(){
		$("#city").change(function(){
			$("#area").get(0).length=1;
			$.post("${base}/ssxjl/area.do",{'cityCode':$("#city").val()},function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					html += "<option value="+data[i].areaId+">"+data[i].areaName+"</option>";
				}
				$("#area").append(html);
			});
		});
	});
</script>
</head>
<body>
	<div id="wrap">

		<div class="header">
			<div class="logo">
				<a href="${base }/book/IndexAction.do"><img
					src="${base }/images/logo.gif" alt="" title="" border="0" /></a>
			</div>
			<div id="menu">
				<ul>
					<li class="selected"><a href="${base }/book/IndexAction.do">首页</a></li>

					<li><a href="${base }/book/1/8/cateBook.do">图书</a></li>
					<li><a href="${base }/book/1000000/1/8/viewBookByCate.do">特价图书</a></li>
					<c:if test="${empty user }" var="userCheck">
						<li><a href="${base }/jsp/user/login.jsp">登陆</a></li>
						<li><a href="${base }/user/init.do">注册</a></li>
					</c:if>
					<c:if test="${!userCheck }">
						<li><a href="${base }/user/exit.do">注销</a></li>
						<li><a href="${base }/permission/order/1/8/orderList.do">我的订单</a></li>
					</c:if>
					<!--<li><a href="details.html">prices</a></li>-->
					<li><a href="${base }/jsp/about.jsp">关于我们</a></li>
					<li><a href="${base }/jsp/contact.jsp">联系我们</a></li>
				</ul>
			</div>
		</div>

		<div class="center_content">
			<div class="left_content">
				<div class="address">
					<span class="title_icon"><img src="${base }/images/bullet1.gif"
						alt="" title="" /></span>选择送货地址
				</div>

				<div class="address_list" id="address_list">
					<ul>
						<c:if test="${empty addressPageingDefault }" var="notdefault">
							<c:forEach items="${addressPageing.as }" var="address"
								varStatus="status">
								<li><input type="radio" name="address"
									value="${address.addressId }"
									onclick="selectLi('${status.count-1}')"/>
									&nbsp;${address.area }${address.detailAddres }&nbsp;</li>
							</c:forEach>
						</c:if>
						<c:if test="${!notdefault }">
							<c:forEach items="${addressPageingDefault.as }" var="addressd">
								<li><input type="radio" name="address" value="${addressd.addressId}" onclick="selectLi(0)" checked>&nbsp;${addressd.area }${addressd.detailAddres } &nbsp;<span style="color:#093">默认地址</span></li>
							</c:forEach>
							<c:forEach items="${addressPageing.as }" var="address"
								varStatus="status">
								<li><input type="radio" name="address"
									value="${address.addressId }"
									onclick="selectLi('${status.count}')"/>
									&nbsp;${address.area }${address.detailAddres }&nbsp;</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="address_more">
					<%-- <c:if test="${addressPageing.pageNow!=1 }">
						<a href="${base }/permission/order/balance?pageNow=${addressPageing.pageNow-1 }&pageSize=${addressPageing.pageSize}">上一页</a>
					</c:if> --%>
					<a href="${base }/permission/order/1/${addressPageing.pageSize+3}/balance.do">更多地址</a>
				</div>

				<div class="address_new">
					<input type="button" value="使用新地址" onclick="addAddress()" />
				</div>

				<div id="new_Address" class="new_Address">
					<div class="closeDiv">
						<a href="javascript:closeDiv()">X</a>
					</div>

					<div class="address_title">添加收货地址：</div>

					<div class="address_ps">
						<span style="color:#FC0">新增收货地址</span> <span
							style="color:#999; font-size:10px">电话号码、手机号选填一项,其余均为必填项</span>
					</div>

					<div class="newAddress_detail">
						<form action="${base }/address/add.do" method="post">
							<table>
								<tr>
									<td align="right">所在地区：<span style="color:#F00">*</span></td>
									<td><select name="province" id="province">
											<option>--请选择省份--</option>
									</select> &nbsp; <select id="city" name="city">
											<option>--请选择城市--</option>
									</select> &nbsp; <select id="area" name="area">
											<option>--请选择地区--</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">详细地址： <span style='color:#F00'>*</span>
									</td>
									<td><textarea name="address_detail" rows="2" cols="23"
											id="address_detail"></textarea></td>
								</tr>

								<tr>
									<td align="right">邮政编码:<span style="color:#F00">*</span></td>
									<td><input type="text" name="code" id="code" /></td>
								</tr>

								<tr>
									<td align="right">收货人姓名:<span style="color:#F00">*</span></td>
									<td><input type="text" name="reciverName" id="reciverName" />
									</td>
								</tr>

								<tr>
									<td align="right">手机号:<span style="color:#F00">*</span></td>
									<td><input type="text" name="tel" placeholder-="请符合手机号规则"
										id="tel" /></td>
								</tr>

								<tr>
									<td colspan="2" align="center"><input type="checkbox"
										name="defaultAddress" id="defaultAddress" value="1"/>设为默认地址</td>
								</tr>

								<tr>
									<td colspan="2" align="center"><input
										style="background-color:orange;color:#FFF;width:70px;"
										type="submit" onclick="saveAddress()" name='button1'
										value="保存" /></td>
								</tr>
							</table>
						</form>
					</div>

				</div>

				<div class="title">
					<span class="title_icon"><img
						src="${base }/images/bullet1.gif" alt="" title="" /></span>我的购物车
				</div>

				<div class="feat_prod_box_details">

					<table class="cart_table">
						<tr class="cart_title">
							<td>图片</td>
							<td>书名</td>
							<td>单价</td>
							<td>数量</td>
							<td>合计</td>
						</tr>
						<c:if test="${!empty user }">
							<c:forEach var="item" items="${newItems }">
								<tr>
									<td><a
										href="${base }/book/${item.book.bookId}/detailBook.do"><img
											src="${base }/images/cart_thumb.gif" alt="" title=""
											border="0" class="cart_thumb" /></a></td>
									<td>${item.book.bookName }</td>
									<td>${item.book.price }元</td>
									<td>${item.count }</td>
									<td>${item.allPrice }</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4" class="cart_total"><span class="red">总价:</span></td>
								<td>${itemAllPrice==null?'0':itemAllPrice }$</td>
							</tr>
							<tr>
								<td colspan="4" class="cart_total"><span class="red">优惠后:</span></td>
								<td>${itemAllPrice==null?'0':itemAllPrice*0.75 }$</td>
							</tr>
						</c:if>
						<c:if test="${empty user }">
							<tr>
								<td colspan="4" class="cart_total"><span class="red">总价:</span></td>
								<td>0.0$</td>
							</tr>
						</c:if>
					</table>
					<a href="${base }/jsp/cart/cart.jsp" class="continue">&lt;
						返回购物车修改</a> <a href="javascript:sub()" class="checkout">提交订单</a>

				</div>

				<div class="clear"></div>
			</div>
			<!--end of left content-->

			<div class="right_content">

				<div class="languages_box">
					<span class="red">Languages:</span> <a href="#" class="selected"><img
						src="${base }/images/gb.gif" alt="" title="" border="0" /></a> <a
						href="#"><img src="${base }/images/fr.gif" alt="" title=""
						border="0" /></a> <a href="#"><img src="${base }/images/de.gif"
						alt="" title="" border="0" /></a>
				</div>

				<div class="currency">
					<span class="red">Currency: </span> <a href="#">GBP</a> <a href="#">EUR</a>
					<a href="#" class="selected">USD</a>
				</div>


				<div class="cart">
					<div class="title">
						<span class="title_icon"><img
							src="${base }/images/cart.gif" alt="" title="" /></span>我的购物车
					</div>
					<div class="home_cart_content">
						<c:if test="${empty user }">
							<span id="itemCount">0</span>个商品 |总价: 
							<span class="red" id="allPrice">0.0</span>$
						</c:if>
						<c:if test="${!empty user }">
							<span id="itemCount">${cart.allcount==null?'0':cart.allcount}</span>个商品 |总价: 
							<span class="red" id="allPrice">${cart.allPrice==null?'0.0':cart.allPrice}</span>$
							<a href="${base }/jsp/cart/cart.jsp" class="view_cart">查看购物车</a>
						</c:if>
					</div>
				</div>
				<div class="title">
					<span class="title_icon"><img
						src="${base }/images/bullet3.gif" alt="" title="" /></span>关于我们书店
				</div>
				<div class="about">
					<p>
						<img src="${base }/images/about.gif" alt="" title="" class="right" />
						本书店是天创集团旗下的天智教育经营的书店，开业十年来，一直与国内外五百家出版社，近千家发行单位保持着良好的合作关系。以其集文化传播、艺术鉴赏、休闲为一体的崭新经营方式、一流的购书环境及优质的服务，赢得了众多的荣誉，成为华东地区最大的集图书、音像制品、文化用品、快餐、软件开发、广告策划于一体的图书零售企业。
					</p>

				</div>

				<div class="right_box">
					<div class="title">
						<span class="title_icon"><img
							src="${base }/images/bullet4.gif" alt="" title="" /></span>推荐图书
					</div>
					<c:forEach items="${recommendBook.books }" var="recommendbook"
						varStatus="status">
						<div class="new_prod_box">
							<a href="${base }/book/${recommendbook.bookId}/detailBook.do">${recommendbook.bookName
								}</a>
							<div class="new_prod_bg">
								<span class="new_icon"><img
									src="${base }/images/promo_icon.gif" alt="" title="" /></span> <a
									href="${base }/book/${recommendbook.bookId}/detailBook.do"><img
									src="${base }/images/thumb${status.count }.gif" alt="" title=""
									class="thumb" border="0" /></a>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="right_box">

					<div class="title">
						<span class="title_icon"><img
							src="${base }/images/bullet5.gif" alt="" title="" /></span>类别
					</div>

					<ul class="list">
						<c:forEach items="${categorys }" var="category">
							<li><a
								href="${base }/book/${category.cateId}/1/8/viewBookByCate">${category.cateName
									}</a></li>
						</c:forEach>
					</ul>

					<div class="title">
						<span class="title_icon"><img
							src="${base }/images/bullet6.gif" alt="" title="" /></span>合作伙伴
					</div>

					<ul class="list">
						<li><a href="#">accesories</a></li>
						<li><a href="#">books gifts</a></li>
						<li><a href="#">specials</a></li>
						<li><a href="#">hollidays gifts</a></li>
						<li><a href="#">accesories</a></li>
						<li><a href="#">books gifts</a></li>
						<li><a href="#">specials</a></li>
						<li><a href="#">hollidays gifts</a></li>
						<li><a href="#">accesories</a></li>
					</ul>

				</div>
			</div>
			<!--end of right content-->




			<div class="clear"></div>
		</div>
		<!--end of center content-->


		<div class="footer">
			<div class="left_footer">
				<img src="${base }/images/footer_logo.gif" alt="" title="" /><br />
				<a href="http://csscreme.com"><img
					src="${base }/images/csscreme.gif" alt="by csscreme.com"
					title="by csscreme.com" border="0" /></a>
			</div>
			<div class="right_footer">
				<a href="${base }/book/IndexAction.do">home</a> <a href="${base }/jsp/about.jsp">about us</a> <a href="#">services</a>
				<a href="#">privacy policy</a> <a href="${base }/jsp/contact.jsp">contact us</a>
			</div>
		</div>
	</div>
</body>
</html>