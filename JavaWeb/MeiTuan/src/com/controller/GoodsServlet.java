package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bean.Goods;
import com.service.GoodsService;

import net.sf.json.JSONObject;
public class GoodsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String type = req.getParameter("type");
		int resultCode = 0;
		String msg = "获取数据成功";
		PrintWriter writer = null;
		List<Goods> list = null;
		Map map = new HashMap();
		try {
			if("".equals(type)||type==null) {
				resultCode = 1;
				msg = "获取数据错误";
			}else {
				list = GoodsService.getListType(Integer.valueOf(type));
			}
		}catch(Exception e){
			resultCode = 2;
			msg  = "系统异常,请稍后再试";
		}finally{
			map.put("resultCode", resultCode);
			map.put("msg", msg);
			map.put("list", list);
			JSONObject JSONObj = JSONObject.fromObject(map);
			writer = resp.getWriter();
			writer.print(JSONObj.toString());
		}
	}

}
