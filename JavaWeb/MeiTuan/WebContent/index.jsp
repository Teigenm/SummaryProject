<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>美团效果</title>
<style type="text/css">
    .container{width: 1190px;height:704px;margin: 50px auto 0px;border: 1px solid red;}
    .nav{width: 100%;height:54px;font-size:14px;background-color:rgb(190,164,116);
        cursor:pointer;line-height: 54px;border-top-left-radius:7px;border-top-right-radius: 7px;}
    .clear_fix .nav_title{font-size:18px;}
    .clear_fix li{list-style:none;float:left;padding-right: 40px;color: #fff;position:relative;
        text-transform: uppercase;
    }
    .nav .active:after{
        position:absolute;
        left:-37px;
        top:47px;
        right:0px;
        width:0px;
        height:0px;
        border-left:5px solid  transparent;
        border-right:5px solid transparent;
        border-bottom:7px solid #ffffff;
        content:" ";
        display:block;
        margin:auto;
    }
    .area{
        width:100%;
        height:650px;
        padding:11px 10px 10px;
        background-color: #ffffff;
        box-sizing:border-box;
        border-left:1px solid #e5e5e5;
        border-right:1px solid #e5e5e5;
        border-bottom:1px solid #e5e5e5;
    }
    .quality_card{
        float:left;
        text-decoration:none;
    }
    .quality_img{
        width:100%;
        padding:10px;
        text-decoration: none;
    }
    .poi_info{
        text_align:left;
        background-color: #ffffff;
        color: #BE9E4D;
        margin-left:10px;
    }
    .current_price_wrapper{
        height:24px;
        overflow:hidden;
        margin-bottom:10px;
    }
    .price_symbolnumfont{
        font-size:14px;
        color:#BE9E4D;
        font-weight: 500;
    }
    .price{
        font-size: 18px;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <ul class="clear_fix">
                <li class="nav_title">有格调</li>
                <li class="nav_item active" date_type="0">全部</li>
                <li class="nav_item" date_type="1">约会聚餐</li>
                <li class="nav_item" date_type="2">丽人SPA</li>
                <li class="nav_item" date_type="3">电影演出</li>
                <li class="nav_item" date_type="4">品质出游</li>
                <li class="nav_item" date_type="5">丽人聚餐</li>
            </ul>
        </div>
        <div class="area">
 
        </div>

    </div>
    <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
    <script type="text/javascript">
    	$(document).ready(function () {
            $(".nav_item").hover(function () {
                
            	init($(this).attr("date_type"));
                $(".nav_item").removeClass("active");
                $(this).addClass("active");
            })
        })
        function init(type) {
            $.ajax({
            	url:"GoodsServlet",
            	data:{"type":type},
            	type:"post",
            	dataType:"json",
            	success:function(result){
            		//var jsonObj=$.parseJSON(result);
            		$(".area").empty();
            		if(result.list.length>0){
            			for(var i=0;i<result.list.length;i++){
            				var imgSrc = result.list[i].imgSrc.split("WebContent")[1];
            				imgSrc = imgSrc.substring(1,imgSrc.length);
            				console.log(imgSrc);
            				var html = '<a href="www.baidu.com?id="'+result.list[i].id+' class="link quality_card" target="_blank">'
                  				+'  <div class="quality_img">'
                   				+ '    <img src="'+imgSrc+'" height=255 width=369/>'
                   				+'</div>'
                				+'<div class="poi_info">'
                  				+  '<span class="current_price_wrapper">'
                     			+  ' <span class="price_symbolnumfont">&yen;</span>'
                    			+   '<span class="price">'+result.list[i].price+'</span>'
                     			+   '<span class="courrent_price_type">'
                     			+   '    /人均'
                    			+    '</span>'
                  				+  '</span>'
              					+  '</div>'
               					+ '</a>'	
               					$(".area").append(html);
            			}
            		}
            	}
            });
        }
    </script>
</body>

</html>