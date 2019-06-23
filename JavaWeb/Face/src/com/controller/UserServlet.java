package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.aip.face.AipFace;
import com.bean.User;
import com.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String op = req.getParameter("op");
		if(op.equals("login")) {
			login(req,resp);
		}else if(op.equals("register")) {
			register(req,resp);
		}
	}
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String facePass = req.getParameter("facePass");
		PrintWriter writer = resp.getWriter();
		
		String result = UserService.getInstance().faceLogin(userId, facePass);
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		if(jsonObject.getString("error_msg").equals("SUCCESS")) {
			//System.out.println("识别中");
			JSONObject rsObj = jsonObject.getJSONObject("result");
			//System.out.println(rsObj);
			JSONObject userObj = (JSONObject) rsObj.getJSONArray("user_list").get(0);
			double score = userObj.getDouble("score");
			if(score>=95) {
				writer.print("登录成功！");
			}else {
				writer.print("登录失败,请对准镜头,再试一次吧！！");
			}
			
		}else {
			writer.print("登录失败！");
		}
	}
	public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String facePass = req.getParameter("facePass");
		PrintWriter writer = resp.getWriter();
		
		String result = UserService.getInstance().faceRegister(userId, facePass);
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		if(jsonObject.getString("error_msg").equals("SUCCESS")) {
			writer.print("注册成功！");
		}else {
			writer.print("注册失败！");
		}
	}
}
