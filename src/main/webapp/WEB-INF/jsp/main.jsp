<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ラクラク商品管理システム</title>
</head>
<body style="background-color: #fffeee;">
	<h1>ラクラク商品管理システム</h1>
	<h2>トップページ</h2>
	<p>
		<c:out value="${loginUser.id}" />
		がログイン中です。
	</p>
	<ul>
		<li><a href="/portfolio1/StockCheckServlet">在庫確認</a></li>
		<li><a href="/portfolio1/SalesRecordServlet">販売記録</a></li>
		<li><a href="/portfolio1/LogoutServlet">ログアウト</a></li>
	</ul>
</body>
</html>