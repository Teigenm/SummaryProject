package com.tg.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tg.bean.Goods;

public class GoodsDao extends DbDao{
	//id,name,price,stock
	public Goods queryGoods_id(String id){
		String sql="select id,name,price,stock from goods_info "+
				"where id='"+id+"'";
		Goods two = null;
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Goods();
				two.setId(rs.getString("id"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setStock(rs.getInt("stock"));
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return two;
	}
	public Goods queryGoods_go(Goods one) {
		String sql="select id,name,price,stock from goods_info "+
				"where id='"+one.getId()+"'";
		Goods two=null;
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Goods();
				two.setId(rs.getString("id"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setStock(rs.getInt("stock"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return two;
	}
	public int addGoods(Goods one) {
		String	sql="insert into goods_info(id,name,price,stock) values('"
				+one.getId()+"','"+one.getName()+"',"+one.getPrice()+","+one.getStock()+")";
		return  update(sql);
	}
	public int updateGoods(Goods one) {
		String sql="update goods_info set "
				+"id='"+one.getId()+"',name='"+one.getName()+"',price="+one.getPrice()+",stock="+one.getStock()+" where id='"+one.getId()+"'";
		return update(sql);
	}
	public int updateGoods(String id,int stock) {
		String sql="update goods_info set "
				+"stock= "+stock+" where id='"+id+"'";
		return update(sql);
	}
	public int delGoods(String id) {
		String sql="delete from goods_info where id='"+id+"'";
		return  update(sql);
	}
	public List<Goods> getListGoods(Goods gs){
		List<Goods> list=new ArrayList<Goods>();
		String sql="select id,name,price,stock from goods_info where 1=1 ";
		Goods two=null;
		if(gs.getId()!=null) {
			sql+=" and id like '"+gs.getId()+"%' ";
		}
		if(gs.getName()!=null) {
			sql+=" and name like '"+gs.getName()+"%' ";
		}
		if(gs.getPrice()!=-1) {
			sql+=" and price="+gs.getPrice()+" ";
		}
		if(gs.getStock()!=-1) {
			sql+=" and stock="+gs.getStock()+" ";
		}
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Goods();
				two.setId(rs.getString("id"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setStock(rs.getInt("stock"));
				list.add(two);
			}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return list;
	}
	public List<Goods> getListGoods(){
		List<Goods> list=new ArrayList<Goods>();
		Goods two=null;
		String sql="select id,name,price,stock from goods_info";
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Goods();
				two.setId(rs.getString("id"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setStock(rs.getInt("stock"));
				list.add(two);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<Goods> getListGoods_price(Goods gs,double price1,double price2){
		List<Goods> list=new ArrayList<Goods>();
		Goods two=null;
		
		String sql="select id,name,price,stock from goods_info where 1=1 ";
		
		if(gs.getName()!=null) {
			sql+=" and name like '"+gs.getName()+"%' ";
		}
		if(price1!=-1&&price2!=-1) {
			sql+=" and price>="+price1+" and price<="+price2;
		}
		try {
			rs=query(sql);
			while(rs.next()) {
				two=new Goods();
				two.setId(rs.getString("id"));
				two.setName(rs.getString("name"));
				two.setPrice(rs.getDouble("price"));
				two.setStock(rs.getInt("stock"));
				list.add(two);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		GoodsDao s=new GoodsDao();
		Goods goods=new Goods("1003");
		System.out.println(goods.getStock());
	}
}
