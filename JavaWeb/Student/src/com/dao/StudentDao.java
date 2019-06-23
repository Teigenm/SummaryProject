package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Student;

public class StudentDao extends DbDao{
	private static StudentDao studentDao=new StudentDao();
	private StudentDao() {
	}
	public static StudentDao getInstance() {
		if(null==studentDao) {
			synchronized (studentDao) {
				if(null==studentDao) {
					studentDao=new StudentDao();
				}
			}
		}
		return studentDao;
	}
	public List<Student> queryall(){
		List<Student> list=new ArrayList<Student>();
		String sql="select * from stu_info";
		ResultSet rs=query(sql);
		Student stu=null;
		try {
			while(rs.next()) {
				stu=new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setMajor(rs.getString("major"));
				stu.setGrade(rs.getString("grade"));
				stu.setHome(rs.getString("home"));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int countAll(){
		String sql="select count(*) as stucount from stu_info";
		ResultSet rs=query(sql);
		int cnt=-1;
		try {
			if(rs.next()) {
				cnt=rs.getInt("stucount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}
	public List<Student> queryBypage(int pageNo,int count){
		List<Student> list=new ArrayList<Student>();
		String sql="select * from stu_info limit ?,?";
		Student stu=null;
		try {
			getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1,(pageNo-1)*count);
			ps.setInt(2, count);
			rs=ps.executeQuery();
			while(rs.next()) {
				stu=new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setMajor(rs.getString("major"));
				stu.setGrade(rs.getString("grade"));
				stu.setHome(rs.getString("home"));
				list.add(stu);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	public boolean delStudent(int id) {
		String sql="delete from stu_info where id=?";
		int cnt=-1;
		try {
			getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cnt==-1)
			return false;
		return true;
	}
	public boolean addStudent(Student stu) {
		String sql="insert into stu_info(name,sex,major,grade,home) values('"+stu.getName()+"','"+stu.getSex()+"','"+stu.getMajor()+"','"+stu.getGrade()+"','"+stu.getHome()+"')";
		//System.out.println(sql);
		int cnt=-1;
		cnt=update(sql);
		if(cnt==-1)
			return false;
		return true;
	}
	public boolean upStudent(Student stu) {
		String sql="update stu_info set name='"+stu.getName()+"',sex='"+stu.getSex()+"',"+"major='"+stu.getMajor()+
				"',grade='"+stu.getGrade()+"',home='"+stu.getHome()+"' where id="+stu.getId();
		System.out.println(sql);
		int cnt=-1;
		cnt=update(sql);
		if(cnt==-1)
			return false;
		return true;
	}
	public static void main(String[] args) {
		Student stu=new Student("小明","男","酒店管理","2016级","北京市");
		System.out.println(StudentDao.getInstance().countAll());
	}
}
