<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://page.way2a.com/tag" prefix="pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.bean.Student" %>
<%@ page import="com.bean.Pc" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生管理</title>
<base href="<%=basePath%>">	
</head>
<%	
Pc pc=(Pc)request.getAttribute("pc");
if(pc==null){
	response.sendRedirect("StudentServlet?op=pageStudent");
	return ;
}
request.setAttribute("list", pc.getList());
%>
<body>
	<div style="width:100%;height:40px;">
		<input style="float:right;width:70px;" type="button" value="添加" onclick="addStu()"/>
	</div>
	<table width="100%" border="1">
		<tr>
			<th width="15%">学号</th>
			<th width="15%">姓名</th>
			<th width="15%">性别</th>
			<th width="15%">专业</th>
			<th width="15%">年级</th>
			<th width="15%">家乡</th>
			<th width="15%">操作</th>
		</tr>
		<c:forEach items="${list}" var="temp" varStatus="ptid">
			<tr>
				<td><input id="id${temp.getId()}" style="width:100%" type="text" value="${temp.getId()}" name="id"/></td>
				<td><input id="name${temp.getId()}" style="width:100%" type="text" value="${temp.getName()}" name="name"/></td>
				<td><input id="sex${temp.getId()}" style="width:100%" type="text" value="${temp.getSex()}" name="sex"/></td>
				<td><input id="major${temp.getId()}" style="width:100%" type="text" value="${temp.getMajor()}" name="major"/></td>
				<td><input id="grade${temp.getId()}" style="width:100%" type="text" value="${temp.getGrade()}" name="grade"/></td>
				<td><input id="home${temp.getId()}" style="width:100%" type="text" value="${temp.getHome()}" name="home"/></td>
				<td>
					<input id="up${temp.getId()}" type="button" value="更新" onclick="updateStu(this)"/> 
					<input id="del${temp.getId()}" type="button" value="删除" onclick="deleteStu(this)"/> 
				</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<div style="width:550px;height:50px;margin:0px auto;">
		<pager:page pageSize="${pc.pageSize}" pageNo="${pc.pageNo }" url="${pc.url }" totalRecord="${pc.getTotalRecord()}"/>
	</div>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		function updateStu(obj){
			var upid=obj.id;
			upid=upid.substring(2);
			console.log(upid);
			var name=document.getElementById('name'+upid).value;
			var sex=document.getElementById('sex'+upid).value;
			var major=document.getElementById('major'+upid).value;
			var grade=document.getElementById('grade'+upid).value;
			var home=document.getElementById('home'+upid).value;
			$.ajax({
				url:"StudentServlet",
				type:"post",
				data:{"op":"upStudent","id":upid,"name":name,"sex":sex,"major":major,"grade":grade,"home":home},
				success:function(result){
					if(result){
						window.location.replace("index.jsp");
						alert("修改成功！");
					}else{
						alert("修改失败！");
					}
				}
			});
		}
		function addStu(){
			var h=window.location.href;
			h=h.substring(0,30)+'add.jsp';
			window.location.replace(h);
		}
		function deleteStu(obj){
			var delid=obj.id;
			delid=delid.substring(3);
			console.log(delid);
			$.ajax({
				url:"StudentServlet",
				type:"post",
				data:{"op":"delStudent","delid":delid},
				success:function(result){
					if(result){
						window.location.replace("index.jsp");
						alert("删除成功！");
					}else{
						alert("删除失败！");
					}
				}
			});
			
		}
	</script>
</body>
</html>