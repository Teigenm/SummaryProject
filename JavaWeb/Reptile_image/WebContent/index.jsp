<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面</title>
	<style type="text/css">
        *{margin:0px;padding:0px;}
        body{background:url("image/1.jpg");overflow: auto;background-size: cover;}
        h1{text-align:center;color:#C0C0C0;margin-top:10px;}
        .box{width:100%;height:100%;padding:10px}
        #htmlSource{width:97.5%;height:300px;text-align:left;padding:10px;font-size:16px;font-family:"微软雅黑";margin-top:15px;}
     	 #download{height:40px;width:140px;background:#a9a9a9;border-radius:15px;cursor:pointer;margin-top:15px;}
    </style>
</head>

<body>
	<div class="box">
		<h1>源代码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
		<textarea  id="htmlSource">${htmlSource }</textarea>
		<hr style="margin-top:10px;"/>
		<h3 style="color:#C0C0C0;margin-top:10px;text-align:center;">符合要求的图片有：&nbsp;<span id="number">1</span>&nbsp;张</h3>
		
		<form action="" style="text-align:center;">
			<input type="submit" value="下载" id="download"/>
		</form>
		
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	var source = $("#htmlSource").val();
	var $source = $(source);
	var i=0;
	$source.find("img").each(function(){
		var src =$(this).attr("src");
		if(src!=""&&src.length>0&&src.indexOf(".jpg")!=-1){
			$("body").append("<div class='source_img' style='width:359px;height:220px;padding:10px;float:left;'><img src='"+src+"' width=359 height=210/></div>")
			i++;
		}
	});
	$("#number").text(i);
	document.getElementById("download").onclick = function(){
		$.ajax({
			url:"ReptileServlet",
			data:{"htmlSource":source,"op":"download"},
			type:"post",
			success:function(result){
				alert(result);
			}
		});
	}
</script>
</body>
</html>