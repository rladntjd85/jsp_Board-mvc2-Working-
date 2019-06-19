<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인부터 해주세요.')");
			script.println("location.href= 'login.jsp'");
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
				<form action="write.do" method="post">
				<input type="hidden" value="<%=userID %>"  name="userID">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #006dcc; color:white; text-align: center;">게시판 글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="이름 " name="bName" maxlength="50" required></td>
						</tr>
						<tr>
								<td><input type="text" class="form-control" placeholder="글 제목 " name="bTitle" maxlength="50" required></td>
							</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용 " name="bContent" maxlength="2048" style="height: 350px;"></textarea></td>
						</tr>
					</tbody>
					</table>
						<a href="list.do" class="btn btn-primary pull-left">목록보기</a>
						<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
				</form>
			</div>
		</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>