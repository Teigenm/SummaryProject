package com.fx.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fx.bean.UCash;
import com.fx.bean.User;
import com.fx.dao.CashDao;
import com.fx.dao.UserDao;

public class CashDaoTest {
	private CashDao dao=null;
	@Before
	public void setUp() throws Exception {
		dao=new CashDao();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
	}

	@Test
	public void updateCash() {
		UCash uc =new UCash("10001","0",8000.0);
		int cnt=-1;
		try {
			cnt=dao.updateCash(uc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cnt,1);
	}
	@Test
	public void queryUCashone(){
		String idcard="10001";
		UCash uc=null;
		try {
			uc=dao.queryUCashone(idcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(uc);
	}
	
}
