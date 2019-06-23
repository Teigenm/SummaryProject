package com.tg.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.bean.Ushop;
//goodsid,name,price,number
public class UshopDao extends DbDao{
	public List<Ushop> getListUshop(User user){
		List<Ushop> list=new ArrayList<Ushop>();
		String sql="select id,goodsid,name,price,number from ushop_info where username='"+user.getUsername()+"'";
		
		Ushop two=null;
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Ushop();
				two.setId(rs.getInt("id"));
				two.setGoodsid(rs.getString("goodsid"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setNumber(rs.getInt("number"));
				list.add(two);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int addUshop(Ushop two) {
		String	sql="insert into ushop_info(username,goodsid,name,price,number) values('"
				+two.getUsername()+"','"+two.getGoodsid()+"','"+two.getName()+"',"+two.getPrice()+","+two.getNumber()+")";
		return  update(sql);
	}
	public int delUshop(String username) {
		String sql="delete from ushop_info where username='"+username+"'";
		return update(sql);
	}
	public int delUshop(int id) {
		String sql="delete from ushop_info where id="+id;
		return update(sql);
	}
	public static void main(String[] args) {
		
	}
}
