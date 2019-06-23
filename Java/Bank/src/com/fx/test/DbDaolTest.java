package com.fx.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fx.dao.DbDao;

public class DbDaolTest {
	private DbDao dao=null;
	@Before
	public void setUp() throws Exception {
		dao=new DbDao();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
	}
	

	@Test
	public void query(){
		String sql="select *from user_info ";
		ResultSet rs = null;
		try {
			rs=dao.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			assertNotNull(rs);
	}
	@Test
	public void update(){
		String sql="update balance set endCash= 8000 where idcard='10001'";
		int cnt=-1;
		try {
			cnt=dao.update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
}
