<%@page import="kr.hs.sdh.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<title>글 상세</title>
</head>

<body>
	<div align="center" style="margin: auto auto">
		<h3>글 상세</h3>
		<h3><%= session.getAttribute("name") %>님 환영합니다
			<a href="LogoutCtrl">Logout</a>
		</h3>
		<hr>
		<form action="updateBoardCtrl" method="post">
			<input name="seq" type="hidden" value=""/>
			<table class="table" style="width: 800px">
				<tr>
					<td>제목</td>
					<td align="left">
						<input name="title" type="text" value="<%= board.getTitle() %>" class="form-control"/>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td align="left"><%= board.getNickname() %></td>
				</tr>
				<tr>
					<td>내용</td>
					<td align="left">
						<textarea name="content" cols="40" rows="10" class="form-control"><%= board.getContent() %></textarea>
					</td>
				</tr>
				<tr>
					<td>등록일</td>
					<td align="left"><%= board.getRegDate() %></td>
				</tr>
				<tr>
					<td>조회수</td>
					<td align="left"><%= board.getCnt() %></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 수정" class="btn btn-primary"/>
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="addBoardCtrl">글등록</a>&nbsp;&nbsp;&nbsp; 
		<a href="">글삭제</a>&nbsp;&nbsp;&nbsp; 
		<a href="getBoardListCtrl">글목록</a>
	</div>
</body>
</html>










