<%@page import="java.io.PrintWriter"%>
<%@page import="com.mvc.dao.BDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bcustom.css">
<title>Jsp MVC 게시판</title>
<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration: none;
	}
</style>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
		
		String pageNumber = (String)request.getAttribute("pageNumber");
		System.out.println("jsp의 받는값"+pageNumber);
	%>
	<script type="text/javascript">
	</script>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button	type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP MVC 게시판 웹사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="list.do">게시판</a></li>
			</ul>
			<%
				if(userID == null){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="register.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				}else{
			%>
		
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="6" style="background-color: #006dcc; color:white; text-align: center;"><h4>자유 게시판</h4></th>
				</tr>
				<tr>
					<th style="background-color: #eeeeee; text-align: center;">번호</th>
					<th style="background-color: #eeeeee; text-align: center;">글쓴이</th>
					<th style="background-color: #eeeeee; text-align: center;">제목</th>
					<th style="background-color: #eeeeee; text-align: center;">날짜</th>
					<th style="background-color: #eeeeee; text-align: center;">조회수</th>
					<th style="background-color: #eeeeee; text-align: center;">그룹</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.bId}</td>
					<td>${dto.userID}</td>
					<td>
						<c:forEach begin="1" end="${dto.bIndent}">
							<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
						</c:forEach>
						<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
					</td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
					<td>${dto.bGroup}</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
						 	<a href="write_view.do" class="btn btn-primary pull-right">글작성</a>
						 	<ul class="pagination" style="margin: 0 auto;">  
							<%
								int startPage = (Integer.parseInt(pageNumber) / 10) * 10 + 1;
								if(Integer.parseInt(pageNumber) % 10 == 0) startPage -= 10;
								int targetPage = new BDao().targetPage(pageNumber);
								if(startPage != 1){
							%>
								<li><a href="list.do?pageNumber=<%=startPage -1 %>"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
							<%
								}else{
							%>
								<li><span class="glyphicon glyphicon-chevron-left" style="color: gray;"></span></li>
							<%
								}
								for(int i = startPage; i < Integer.parseInt(pageNumber); i++){
							%>
								<li><a href="list.do?pageNumber=<%=i %>"><%=i %></a></li>
							<%
								}
							%>
								<li class="active"><a href="list.do?pageNumber=<%=pageNumber %>"><%=pageNumber %></a></li>
							<%
								for(int i = Integer.parseInt(pageNumber) + 1; i <= targetPage + Integer.parseInt(pageNumber); i++){
									if(i < startPage + 10){
							%>
										<li><a href="list.do?pageNumber=<%=i %>"><%=i %></a></li>
							<%
									}
								}
								if(targetPage + Integer.parseInt(pageNumber) > startPage + 9){
							%>
								<li><a href="list.do?pageNumber=<%=startPage + 10 %>"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
							<%
								}else{
							%>
								<li><span class="glyphicon glyphicon-chevron-right" style="color: gray;"></span></li>
							<%
								}
							%>
						</ul>	
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>