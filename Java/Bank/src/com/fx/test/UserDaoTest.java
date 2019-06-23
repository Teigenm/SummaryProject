package com.fx.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fx.bean.User;
import com.fx.dao.UserDao;

public class UserDaoTest {
	private UserDao dao=null;
	@Before
	public void setUp() throws Exception{
		dao=new UserDao();
	}
	@After
	public void tearDown() throws Exception{
		dao=null;
	}

	@Test
	public void queryUserone(){
		User user =new User("10000","123456","0");
		User user2=null;
		try {
			user2=dao.queryUserone(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(user2);
	}
	@Test
	public void updateUser(){
		User user =new User("10000","123456","0","0","0","0","0","0","0","0");
		int cnt=-1;
		try {
			cnt=dao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
	@Test
	public void reSetState(){
		int cnt=-1;
		try {
			cnt=dao.reSetState("10000", "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
	@Test
	public void delUser(){
		int cnt=-1;
		try {
			cnt=dao.delUser("10000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
	@Test
	public void queryUserone_idcard(){
		User user2=null;
		try {
			user2=dao.queryUserone("10000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(user2);
	}	
	@Test
	public void addUser(){
		User user =new User("10000","123456","0","0","0","0","0","0","0","0");
		int cnt=-1;
		try {
			cnt=dao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
	@Test
	public void countUser1(){
		
		int count=0;
		try {
			count=dao.countUser1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(count,9);
	}
	@Test
	public void countUser2(){
		
		int count=0;
		try {
			count=dao.countUser2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(count,3);
	}
	@Test
	public void getUsers1_User(){
		User user=new User();
		List<User> list = null;
		try {
			list=dao.getUsers1(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}	
	@Test
	public void getUsers2_User(){
		User user=new User();
		List<User> list = null;
		try {
			list=dao.getUsers2(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}	
	@Test
	public void getUsers2(){
		List<User> list = null;
		try {
			list=dao.getUsers2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}
	@Test
	public void getUsers1(){
		List<User> list = null;
		try {
			list=dao.getUsers1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}	
	@Test
	public void getUsersFlag(){
		List<User> list = null;
		int flag=1;
		try {
			list=dao.getUsersFlag(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}	
	@Test
	public void changeUser_pwd(){
		int cnt=-1;
		try {
			cnt=dao.changeUser_pwd("10000", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
}
