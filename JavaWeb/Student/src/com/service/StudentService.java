package com.service;

import com.bean.Student;
import com.dao.StudentDao;

public class StudentService {
	private static StudentService stuService=new StudentService();
	private StudentService() {
	}
	public static StudentService getInstance() {
		return stuService;
	}
	public boolean addStudent(Student stu) {
		boolean flag=StudentDao.getInstance().addStudent(stu);
		return flag;
	}
	public boolean upStudent(Student stu) {
		boolean flag=StudentDao.getInstance().upStudent(stu);
		return flag;
	}
	public boolean delStudent(int id) {
		boolean flag=StudentDao.getInstance().delStudent(id);
		return flag;
	}
}
