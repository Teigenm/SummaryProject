package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Pc;
import com.bean.Student;
import com.dao.StudentDao;
import com.service.StudentService;

public class StudentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op=req.getParameter("op");
		if(op.equals("addStudent")) {
			addStudent(req,resp);
		}else if(op.equals("delStudent")){
			delStudent(req,resp);
		}else if(op.equals("upStudent")) {
			updateStudent(req,resp);
		}else if(op.equals("pageStudent")) {
			pageStudent(req,resp);
		}
		
	}
	public void pageStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Pc pc=new Pc();
		String pageNo=req.getParameter("pageNo");
		int cnt=-1;
		if(pageNo==null) {
			cnt=1;
		}else {
			cnt=Integer.parseInt(pageNo);
		}
		pc.setPageNo(cnt);
		pc.setPageSize(5);
		pc.setTotalRecord(StudentDao.getInstance().countAll());
		pc.setUrl("StudentServlet?op=pageStudent");
		pc.setList(StudentDao.getInstance().queryBypage(pc.getPageNo(), pc.getPageSize()));
		req.setAttribute("pc", pc);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Student stu=new Student();
		stu.setName(req.getParameter("name"));
		stu.setSex(req.getParameter("sex"));
		stu.setMajor(req.getParameter("major"));
		stu.setGrade(req.getParameter("grade"));
		stu.setHome(req.getParameter("home"));
		boolean flag=StudentService.getInstance().addStudent(stu);
		if(flag) {
			resp.getWriter().write("添加成功！");
			resp.setHeader("refresh", "2;url=/Student/index.jsp");			
		}
		else {
			resp.getWriter().write("添加失败！");
		}
		return ;
	}
	public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Student stu=new Student();
		stu.setId(Integer.parseInt(req.getParameter("id")));
		stu.setName(req.getParameter("name"));
		stu.setSex(req.getParameter("sex"));
		stu.setMajor(req.getParameter("major"));
		stu.setGrade(req.getParameter("grade"));
		stu.setHome(req.getParameter("home"));
		boolean flag=StudentService.getInstance().upStudent(stu);
		if(flag) {
			resp.sendRedirect("index.jsp");
		}else {
			resp.getWriter().write("更新失败！");
		}
		return ;
	}
	public void delStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		String id=req.getParameter("delid");
		boolean flag=StudentService.getInstance().delStudent(Integer.parseInt(id));
		if(flag) {
			resp.sendRedirect("index.jsp");
		}else {
			resp.getWriter().write("删除失败！");
		}
		return ;
	}
}
