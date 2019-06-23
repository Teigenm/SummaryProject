package com.fx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fx.bean.User;

/**
 * 有关User连接数据库的方法
 * @author asus
 *
 */
public class UserDao extends DbDao{

	/**
	 * 用一个User对象查询数据库并返回一个User对象（详细信息）
	 * @param user
	 * @return
	 */
	public User queryUserone(User user){
		User user2=null;
		String sql="select idcard, password, user_name, id, age, sex, address, tel, user_type, state " +
				"from user_info where idcard='"+user.getIdcard()+"' and password='"+user.getPassword()+"'and user_type='"+user.getUser_type()+"'";
		try {
			rs=query(sql);
			while(rs.next())
			{	user2=new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally{
			close();
		}
		
		return user2;
	}
	/**
	 * 在数据库中更新User对象
	 * @param eb
	 * @return
	 */
	public int updateUser(User eb) {
		String sql = "UPDATE user_info "
				+ "SET user_name = ?, id = ?, age = ?, sex = ?,  address = ? ,tel=?,user_type=?,state=?"
				+ "WHERE idcard = ?";
		//idcard, user_name, id, age, sex, address, tel, user_type, state
		String[] param = { eb.getUser_name(), eb.getId(), eb.getAge(),
				eb.getSex(), eb.getAddress(), eb.getTel(), eb.getUser_type(),eb.getState(),eb.getIdcard() };
		int a = update(sql, param);
		return 1;
	}
	/**
	 * 	根据idcard在数据库更新状态
	 * @param idcard
	 * @param state
	 * @return
	 */
	public int reSetState(String idcard,String state) {
		String sql = "UPDATE user_info SET state= '"+state+"' WHERE idcard = '" + idcard
				+ "'";
		int a = update(sql);
		return a;
	}
	/**
	 * 根据idcard在数据库中执行删除操作
	 * @param idcard
	 * @return
	 */
	
	public int delUser(String idcard) {
		String sql = "delete from user_info where idcard='"+idcard+"'";
		int a = update(sql);
		return a;
	}
	/**
	 * 根据idcard在数据库中查询并返回User对象
	 * @param idcard
	 * @return	user
	 */
	public User queryUserone(String idcard){
		User user2=null;
		String sql="select idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+"from user_info where idcard='"+idcard+"'";
		try {
			rs=query(sql);
			while(rs.next())
			{	user2=new User();
			user2.setIdcard(rs.getString("idcard"));
			user2.setUser_name(rs.getString("user_name"));
			user2.setPassword(rs.getString("password"));
			user2.setSex(rs.getString("sex"));
			user2.setAge(rs.getString("age"));
			user2.setId(rs.getString("id"));
			user2.setAddress(rs.getString("address"));
			user2.setTel(rs.getString("tel"));
			user2.setUser_type(rs.getString("user_type"));
			user2.setState(rs.getString("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return user2;
	}
	/**
	 * 在数据库中增加User对象
	 * @param user
	 * @return cnt
	 */
	public int addUser(User user)
	{	int cnt=-1;
		String sql="insert into user_info(idcard, password, user_name, id, age, sex, address, tel, user_type, state" +
				") values('"+user.getIdcard()+"','"+user.getPassword()+"','"+user.getUser_name()+"','"
			+user.getId()+"','"+user.getAge()+"','"+user.getSex()+"','"+user.getAddress()+"','"+user.getTel()+"','"+user.getUser_type()+"','"+user.getUser_type()+"')";
		cnt=update(sql);
		return cnt;
		
	}
	/**
	 * 在数据库中查找客户的数量
	 * @return count
	 */
	public int countUser1(){
		String sql="select count(idcard) from user_info where user_type='1' and state='2'";
		ResultSet rs=null;
		int count=0;
		try {
			rs=query(sql);
			while(rs.next())
			{	String s=rs.getString("count(idcard)");
				count=Integer.parseInt(s);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return count;
	}
	/**
	 *  在数据库中查找员工的数量
	 * @return
	 */
	public int countUser2(){
		String sql="select count(idcard) from user_info where user_type='2' and state='1'";
		ResultSet rs=null;
		int count=0;
		try {
			rs=query(sql);
			while(rs.next())
			{	String s=rs.getString("count(idcard)");
				count=Integer.parseInt(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return count;
	}
	/**
	 * 根据user 进行客户模糊查询返回list集合
	 * @param user
	 * @return
	 */
	public List<User> getUsers1(User user) {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+ "FROM user_info where user_type='1'";
		if (user.getIdcard() != null) {
			sql += "and idcard='" + user.getIdcard() + "' ";
		}
		if (user.getUser_name() != null) {
			sql += "and user_name like '" + user.getUser_name() + "%' ";
		}
		if (user.getSex() != null) {
			sql += "and sex='" + user.getSex() + "' ";
		}
		if (user.getUser_type() != null) {
			sql += "and user_type='" + user.getUser_type() + "' ";
		}
		if (user.getTel() != null) {
			sql += "and tel='" + user.getTel() + "' ";
		}
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				// 将查到的员工信息返回给窗体
				User user2 = new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	/**
	 * 根据user 进行员工模糊查询返回list集合
	 * @param user
	 * @return
	 */
	public List<User> getUsers2(User user) {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+ "FROM user_info where user_type='2'";
		if (user.getIdcard() != null) {
			sql += "and idcard='" + user.getIdcard() + "' ";
		}
		if (user.getUser_name() != null) {
			sql += "and user_name like '" + user.getUser_name() + "%' ";
		}
		if (user.getSex() != null) {
			sql += "and sex='" + user.getSex() + "' ";
		}
		if (user.getUser_type() != null) {
			sql += "and user_type='" + user.getUser_type() + "' ";
		}
		if (user.getTel() != null) {
			sql += "and tel='" + user.getTel() + "' ";
		}
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				// 将查到的员工信息返回给窗体
				User user2 = new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	/**
	 * 查询客户的信息并返回user的list集合
	 * @return
	 */
	public List<User> getUsers1() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+ "FROM user_info where user_type='1'";
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				// 将查到的员工信息返回给窗体
				User user2 = new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	/**
	 * 查询管理员，业务员的信息并返回user的list集合
	 * @return
	 */
	public List<User> getUsers2() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+ "FROM user_info where user_type='2' or user_type='0'";
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				// 将查到的员工信息返回给窗体
				User user2 = new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<User> getUsersFlag(int flag) {
		List<User> list = new ArrayList<User>();
		String sql;
		if(flag==0)
			sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
				+ "FROM user_info where user_type='2' and state='1'";
		else
			sql = "SELECT idcard, password, user_name, id, age, sex, address, tel, user_type, state "
					+ "FROM user_info where user_type='1' and state='2'";
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				// 将查到的员工信息返回给窗体
				User user2 = new User();
				user2.setIdcard(rs.getString("idcard"));
				user2.setUser_name(rs.getString("user_name"));
				user2.setPassword(rs.getString("password"));
				user2.setSex(rs.getString("sex"));
				user2.setAge(rs.getString("age"));
				user2.setId(rs.getString("id"));
				user2.setAddress(rs.getString("address"));
				user2.setTel(rs.getString("tel"));
				user2.setUser_type(rs.getString("user_type"));
				user2.setState(rs.getString("state"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	/**
	 * 根据idcard修改密码
	 * @param idcard
	 * @param pass
	 * @return
	 */
	public int changeUser_pwd(String idcard,String pass){
		String sql="update user_info set password='"+pass+"'where idcard='"+idcard+"'";
		int cnt=-1;
		cnt=update(sql);
		return cnt;
	}
}
