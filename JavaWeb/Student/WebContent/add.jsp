<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加学生</title>
</head>
<body>
	<form action="StudentServlet" method="post">
		<input type="hidden" name="op" value="addStudent"/>
		姓名：<input type="text" name="name"/><br/>
		性别：<input type="text" name="sex"/><br/>
		专业：<input type="text" name="major"/><br/>
		年级：<input type="text" name="grade"/><br/>
		家乡：<input type="text" name="home"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>