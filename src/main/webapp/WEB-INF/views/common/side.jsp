<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
	
<nav>
<ul>
<c:choose>
<c:when test="${side_menu=='admin_mode' }">//관리자 메뉴 표시
   <li>
		<H3>주요기능</H3>
		<ul>
			<li><a href="${contextPath}/admin/goods/adminGoodsMain.do">상품관리</a></li>
			<li><a href="${contextPath}/admin/order/adminOrderMain.do">주문관리</a></li>
			<li><a href="${contextPath}/admin/member/adminMemberMain.do">회원관리</a></li>
			<li><a href="#">배송관리</a></li>
			<li><a href="#">게시판관리</a></li>
		</ul>
	</li>
</c:when>
<c:when test="${side_menu=='my_page' }">
	<li>
		<h3>주문내역</h3>
		<ul>
			<li><a href="${contextPath}/mypage/listMyOrderHistory.do">주문내역/배송 조회</a></li>
			<li><a href="#">반품/교환 신청 및 조회</a></li>
			<li><a href="#">취소 주문 내역</a></li>
			<li><a href="#">세금 계산서</a></li>
		</ul>
	</li>
	<li>
		<h3>정보내역</h3>
		<ul>
			<li><a href="${contextPath}/mypage/myDetailInfo.do">회원정보관리</a></li>
			<li><a href="#">나의 주소록</a></li>
			<li><a href="#">개인정보 동의내역</a></li>
			<li><a href="#">회원탈퇴</a></li>
		</ul>
	</li>
</c:when>
<c:otherwise>
	<li>
		<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;국내/외 도서</h3>
		<ul>
			<li><a href="${contextPath}/goods/goodsList.do">컴퓨터/IT</a></li>
			<li><a href="#">경제/경영</a></li>
			<li><a href="#">대학교재/수험서</a></li>
			<li><a href="#">자기계발</a></li>
			<li><a href="#">자연과학/공학</a></li>
			<li><a href="#">역사/인문학</a></li>
		</ul>
	</li>
	<li>
		<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;음반</h3>
		<ul>
			<li><a href="#">가요</a></li>
			<li><a href="#">록</a></li>
			<li><a href="#">클래식</a></li>
			<li><a href="#">뉴에이지</a></li>
			<li><a href="#">재즈</a></li>
			<li><a href="#">기타</a></li>
		</ul>
	</li>
 </c:otherwise>
</c:choose>	
</ul>
</nav>
<div class="clear"></div>
<div id="banner">
	<a href="#"><img width="190" height="163" src="${contextPath}/resources/image/n-pay.jpg"> </a>
</div>
<DIV id="notice">
	<H2>공지사항</H2>
	<UL>
	
	<c:forEach  var="i" begin="1" end="1" step="1">
		<li><a href="#">컬처랜드 시스템 점검 안내</a></li>
		<li><a href="#">김봉곤 작가 도서 환불안내</a></li>
		<li><a href="#">교보북클럽 회원 개인정보 내역</a></li>
		<li><a href="#">출간이벤트 당첨자 발표</a></li>
		<li><a href="#">댓글이벤트 당첨자 발표 (7/30)</a></li>
	</c:forEach>
	</ul>
</div>


<div id="banner">
	<a href="#"><img width="190" height="300" src="${contextPath}/resources/image/side_banner1.jpg"></a>
</div>
<div id="banner">
	<a href="#"><img width="190" height="104" src="${contextPath}/resources/image/call_center_logo.jpg"></a>
</div>
<div id="banner">
	<a href="#"><img width="190" height="150" src="${contextPath}/resources/image/QnA_logo.jpg"></a>
</div>
</html>