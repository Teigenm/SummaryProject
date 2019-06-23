<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>获取网络图片</title>
    <style type="text/css">
        *{margin:0px;padding:0px;}
        body{background:url("image/1.jpg");overflow: hidden;background-size: cover;}
        h1{margin-top:20px;text-align:center;color:#C0C0C0;}
        .box{width:960px;height:480px;margin:180px auto 0px;}
        input{margin:30px auto 0;display:block;}
        #btn{height:40px;width:140px;background:#a9a9a9;border-radius:15px;cursor:pointer;}
        #url{width:360px;height:35px;}
        h3{text-align:center;margin-top:20px;color:#C0C0C0;}
    </style>
</head>
<body>
    <div class="box">
        <h1>JAVA·梦想起帆</h1>
        <form action="ReptileServlet" method="post">
        	<input type="hidden" name="op" value="getHtmlSource">
            <input type="text" id="url" name="url" placeholder="请输入网址,如http://www.qq.com"/>
            <input type="submit" value="获取网页源代码" id="btn"/>
        </form>
        <h3>真滴帅</h3>
    </div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>