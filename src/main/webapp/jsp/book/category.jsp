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
				<div class="crumb_nav">
					<a href="${base }/book/IndexAction.do">首页</a> &gt;&gt; 分类图书
				</div>
				<div class="title">
					<span class="title_icon"><img
						src="${base }/images/bullet1.gif" alt="" title="" /></span>分类图书
				</div>
				<div class="new_products">
					<c:forEach items="${pageing.books }" var="book" varStatus="status">
						<div class="new_prod_box">
							<a href="${base }/book/${book.bookId}/detailBook.do">${book.bookName}</a>
							<div class="new_prod_bg">
								<c:choose>
									<c:when test="${book.category.cateId==1000002 }">
										<span class="new_icon"><img
											src="${base }/images/new_icon.gif" alt="" title="" /></span>
									</c:when>
									<c:when test="${book.category.cateId==1000001 }">
										<span class="new_icon"><img
											src="${base }/images/promo_icon.gif" alt="" title="" /></span>
									</c:when>
								</c:choose>
								<a href="${base }/book/${book.bookId}/detailBook.do"><img
									src="${base }/images/thumb1.gif" alt="" title="" class="thumb"
									border="0" /></a>
							</div>
						</div>
					</c:forEach>

					<div class="pagination">
						<c:if test="${pageing.pageNow!=1 }">
							<a
								href="${base }/book/1/${pageing.pageSize}/cateBook.do">首页</a>&nbsp;
							<a
								href="${base }/book/${pageing.pageNow-1 }/${pageing.pageSize}/cateBook.do">&lt;&lt;</a>&nbsp;
						</c:if>
						<c:choose>
							<c:when test="${pageing.pageNow<3 }">
								<c:set var="beg" value="1"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="beg" value="${pageing.pageNow-1 }"></c:set>
							</c:otherwise>
						</c:choose>

						<c:forEach var="p" begin="${beg }" end="${beg+2 }">
							<c:if test="${p<=pageing.pageCount }">
								<a
									href="${base }/book/${p}/${pageing.pageSize}/cateBook.do">${p
									}</a>
							</c:if>
						</c:forEach>
						<c:if test="${pageing.pageNow!=pageing.pageCount }">
							<a
								href="${base }/book/${pageing.pageNow+1}/${pageing.pageSize}/cateBook.do">&gt;&gt;</a>&nbsp;
							<a
								href="${base }/book/${pageing.pageCount}/${pageing.pageSize}/cateBook.do">末页</a>&nbsp;
						</c:if>
						当前页${pageing.pageNow}/${pageing.pageCount}总共页&nbsp;&nbsp;
					</div>
				</div>


				<div class="clear"></div>
			</div>
			<!--end of left content-->

			<div class="right_content">
				<div class="languages_box">
					<span class="red">Languages:</span> <a href="#"><img
						src="${base }/images/gb.gif" alt="" title="" border="0" /></a> <a
						href="#"><img src="${base }/images/fr.gif" alt="" title=""
						border="0" /></a> <a href="#"><img src="${base }/images/de.gif"
						alt="" title="" border="0" /></a>
				</div>
				<div class="currency">
					<span class="red">Currency: </span> <a href="#">GBP</a> <a href="#">EUR</a>
					<a href="#"><strong>USD</strong></a>
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
                  			<a href="${base }/jsp/cart/cart.jsp"
								class="view_cart">查看购物车</a>
						</c:if>
					</div>
				</div>
				<div class="title">
					<span class="title_icon"><img
						src="${base }/images/bullet3.gif" alt="" title="" /></span>关于我们店
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
							<a href="${base }/book/${recommendbook.bookId}/detailBook.do">${recommendbook.bookName }</a>
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
							src="${base }/images/bullet5.gif" alt="" title="" /></span>图书分类
					</div>

					<ul class="list">
						<c:forEach items="${categorys }" var="category">
							<li><a
								href="${base }/book/${category.cateId}/1/8/viewBookByCate.do">${category.cateName
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
				<a href="${base }/book/IndexAction.do">home</a> <a
					href="${base }/jsp/about.jsp">about us</a> <a href="#">services</a>
				<a href="#">privacy policy</a> <a href="${base }/jsp/contact.jsp">contact
					us</a>
			</div>
		</div>
	</div>
</body>
</html>