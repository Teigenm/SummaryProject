package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.DbUtil;

public class ReptileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String ENCODING = "gbk";
	private static String FilePath = "F:\\Downloads\\images";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String op = req.getParameter("op");
		if(op.equals("getHtmlSource")) {
			getHtmlSource(req,resp);
		}else if(op.equals("download")) {
			download(req,resp);
		}
	}
	protected void	getHtmlSource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getParameter("url");
		String htmlSource = DbUtil.getInstance().htmlSource(url, ENCODING);
		
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("htmlSource", htmlSource);
		resp.sendRedirect("index.jsp");
	}
	protected void	download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String htmlSource = req.getParameter("htmlSource");
		String url = (String) req.getSession().getAttribute("url");
		Document document = Jsoup.parse(htmlSource);
		Elements elements = document.getElementsByTag("img");
		for(Element element : elements) {
			String imgSrc = element.attr("src");
			if(imgSrc!=null&&!imgSrc.equals("")) {
				if((imgSrc.startsWith("http://") || imgSrc.startsWith("https://"))) {
					System.out.println("正在下载的图片地址："+imgSrc);
					DbUtil.getInstance().downloadImage(FilePath,imgSrc);
				}else{
					System.out.println("正在下载的图片地址："+"https://"+imgSrc);
					DbUtil.getInstance().downloadImage(FilePath,"https:"+imgSrc);
				}
			}
		}
	}
}
