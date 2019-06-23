package com.fx.dao;

import java.sql.SQLException;

import com.fx.bean.UCash;
import com.fx.bean.User;
/**
 * 对blance数据库的操作
 * @author K50
 *
 */
public class CashDao extends DbDao{
	
	/**
	 * 根据uc更新数据库
	 * @param uc
	 * @return
	 */
	public int updateCash(UCash uc){
		String sql="update balance set endCash="+uc.getEndCash()+"where idcard='"+uc.getIdcard()+"'";
		int cnt=-1;
		cnt=update(sql);
		return cnt;
	}
	/**
	 * 根据idcard查询数据库返回UCash对象
	 * @param idcard
	 * @return	uc
	 */
	public UCash queryUCashone(String idcard){
		UCash uc=null;
		String sql="select idcard,type,endcash from balance where idcard='"+idcard+"'";
		try {
			rs=query(sql);
			while(rs.next())
			{	uc=new UCash();
				uc.setIdcard(rs.getString("idcard"));
				uc.setType(rs.getString("type"));
				uc.setEndCash(rs.getDouble("endcash"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return uc;
	}
}
