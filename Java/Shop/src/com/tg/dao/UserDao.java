package com.tg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tg.bean.User;

/**
 * 
 * @author asus
 * username,password, name,sex,tel,address,state, type
 */
public class UserDao extends DbDao{

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User queryUserone(User user){
		User user2=null;
		String sql="select username,password, name,sex,tel,address,state, type " +
				" from user_info where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'";
		try {
			rs=query(sql);
			while(rs.next())
			{	user2=new User();
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
				user2.setName(rs.getString("name"));
				user2.setSex(rs.getString("sex"));
				user2.setTel(rs.getString("tel"));
				user2.setAddress(rs.getString("address"));
				user2.setState(rs.getString("state"));
				user2.setType(rs.getString("type"));
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally{
			close();
		}
		
		return user2;
	}
	/**
	 *
	 * @param eb
	 * @return
	 */
	public int updateUser(User eb) {
		String sql = "UPDATE user_info "
				+ "SET  username = ?, password = ?, name = ?,  sex=? , tel=? , address=? , state=? ,type = ?"
				+ "WHERE username = ?";
		//idcard, user_name, id, age, sex, address, tel, user_type, state
		String[] param = { eb.getUsername(), eb.getPassword(),
				eb.getName(),  eb.getSex(),eb.getTel(),eb.getAddress(),eb.getState(),eb.getType(),eb.getUsername()};
		int a = update(sql, param);
		return 1;
	}
	
	public int updateUserExceptPwd(User eb) {
		String sql = "UPDATE user_info "
				+ "SET  username = ?, name = ?,  sex=? , tel=? , address=? , state=? ,type = ?"
				+ "WHERE username = ?";
		//idcard, user_name, id, age, sex, address, tel, user_type, state
		String[] param = { eb.getUsername(), 
				eb.getName(),  eb.getSex(),eb.getTel(),eb.getAddress(),eb.getState(),eb.getType(),eb.getUsername()};
		int a = update(sql, param);
		return 1;
	}
	/*
	
	 * @param state
	 * @return
	 */
	public int reSetState(String username,String state) {
		String sql = "UPDATE user_info SET state= '"+state+"' WHERE username = '" + username
				+ "'";
		int a = update(sql);
		return a;
	}
	/**
	 * 
	 * @param idcard
	 * @return
	 */
	
	public int delUser(String username) {
		String sql = "delete from user_info where username='"+username+"'";
		return update(sql);
	}
	/**
	 * 
	 * @param idcard
	 * @return	user
	 */
	public User queryUserone(String username){
		User user2=null;
		String sql="select  username,password, name,sex,tel,address,state, type" +
				" from user_info where username='"+username+"'";
		
		try {
			rs=query(sql);
			while(rs.next())
			{	user2=new User();
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
				user2.setName(rs.getString("name"));
				user2.setSex(rs.getString("sex"));
				user2.setTel(rs.getString("tel"));
				user2.setAddress(rs.getString("address"));
				user2.setState(rs.getString("state"));
				user2.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return user2;
	}
	/**
	 * 
	 * @param user
	 * @return cnt
	 */
	//username,password, name,sex,tel,address,state, type
	public int addUser(User user)
	{	int cnt=-1;
		String sql="insert into user_info(username,password, name,sex,tel,address,state, type" +
				") values('"+user.getUsername()+"','"+user.getPassword()+"','"
			+user.getName()+"','"+user.getSex()+"','"+user.getTel()+"','"+user.getAddress()+"','"+user.getState()+"','"+user.getType()+"')";
		cnt=update(sql);
		return cnt;
	}
	/**
	 * 
	 * @return count
	 */
	public int countUser0(){
		String sql="select count(username) from user_info where type='0"
				+ "' and state = '1'";
		ResultSet rs=null;
		int count=0;
		try {
			rs=query(sql);
			while(rs.next())
			{	String s=rs.getString("count(username)");
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
	 *
	 * @param user
	 * @return
	 */
	public List<User> getUsers0(User user) {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT username,password, name,sex,tel,address,state, type "
				+ "FROM user_info where type='0' ";
		if (user.getUsername() != null) {
			sql += " and username like '" + user.getUsername() + "%' ";
		}
		if (user.getName() != null) {
			sql += " and name like '" + user.getName() + "%' ";
		}
		if (user.getSex() != null) {
			sql += " and sex='" + user.getSex() + "' ";
		}
		if (user.getAddress() != null) {
			sql += " and address like '" + user.getAddress() + "%' ";
		}
		if (user.getTel() != null) {
			sql += " and tel like '" + user.getTel() + "%' ";
		}
		if (user.getState() != null) {
			sql += " and state='" + user.getState() + "' ";
		}
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				
				User user2 = new User();
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
				user2.setName(rs.getString("name"));
				user2.setSex(rs.getString("sex"));
				user2.setTel(rs.getString("tel"));
				user2.setAddress(rs.getString("address"));
				user2.setState(rs.getString("state"));
				user2.setType(rs.getString("type"));
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
	 * 
	 * @return
	 */
	public List<User> getUsers0() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT  username,password, name,sex,tel,address,state, type "
				+ "FROM user_info where type='0'";
		try {
			ResultSet rs = query(sql);
			while (rs.next()) {
				
				User user2 = new User();
				user2.setUsername(rs.getString("username"));
				user2.setPassword(rs.getString("password"));
				user2.setName(rs.getString("name"));
				user2.setSex(rs.getString("sex"));
				user2.setTel(rs.getString("tel"));
				user2.setAddress(rs.getString("address"));
				user2.setState(rs.getString("state"));
				user2.setType(rs.getString("type"));
				list.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public int changeUser_pwd(String username,String pass){
		String sql="update user_info set password='"+pass+"'where username='"+username+"'";
		int cnt=-1;
		cnt=update(sql);
		return cnt;
	}
	public static void main(String[] args) {
		UserDao UDao=new UserDao();
		User user =new User("admin","admin");
		user=UDao.queryUserone(user.getUsername());
		System.out.println(user);
	}
}
