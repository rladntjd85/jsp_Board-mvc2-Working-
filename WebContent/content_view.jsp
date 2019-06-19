<%@page import="com.mvc.dao.BDao"%>
<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bcustom.css">
<title>Jsp MVC 게시판</title>
</head>
<body>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		
		int bId = 0;
		if(request.getParameter("bId") != null){
			bId = Integer.parseInt(request.getParameter("bId"));
		}
		if(bId == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
	%>
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
				<form action="modify.do" method="post">
				<input type="hidden" name="bId" value="${content_view.bId }">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3" style="background-color: #006dcc; color:white; text-align: center;">게시판 글 보기</th>
						</tr>
					</thead>
						<tr>
							<td style="width: 20%;">번호</td>
							<td colspan="2">${content_view.bId }</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td colspan="2">${content_view.bHit }</td>
						</tr>
						<tr>
							<td>이름</td>
							<td colspan="2">
								<input type="text" class="form-control" name="bName" value="${content_view.bName }">
							</td>
						</tr>
						<tr>
							<td>제목</td>
							<td colspan="2">
								<input type="text" name="bTitle" class="form-control" value="${content_view.bTitle }" maxlength="50">
							</td>
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="2" style="min-height: 200px; text-align: left;">
								<textarea rows="10" class="form-control" name="bContent" maxlength="2048" style="height: 350px;">${content_view.bContent }</textarea>
							</td>
						</tr>
					</tbody>
					</table>
						<a href="list.do" class="btn btn-primary pull-left">목록보기</a>
						<a href="reply_view.do?bId=${content_view.bId}" class="btn btn-primary pull-left">답변</a>
						
					<c:if test="${userID != null && content_view.userID eq userID}">
						<input type="submit" value="수정"  class="btn btn-primary pull-right">                           
						<a href="delete.do?bId=${content_view.bId}" class="btn btn-primary pull-right" onclick="return confirm('정말로 삭제 하시겠습니까?')">삭제</a> 
					</c:if>
				</form>
			</div>
		</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>