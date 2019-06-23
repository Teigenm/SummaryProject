package com.tg.admin_UI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.tg.bean.User;
import com.tg.dao.UserDao;

public class CountUser extends JDialog{
	private User my_user=null;
	private JScrollPane sp;
	private JTable table;
	private String[] colName = { "账号", "姓名","性别", "电话","状态", "权限" };
	public CountUser(User my_user,JFrame jf) {
		super(jf,true);
		this.my_user=my_user;
		this.setTitle("在线客户");
		this.setSize(600,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		sp=new JScrollPane();
		sp.setBounds(5, 10,585, 360);
		getContentPane().add(sp);
		
		getListData();
		this.addWindowListener(new WindowAdapter() {
			public  void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	//private String[] colName = { "账号", "姓名","性别", "电话","状态", "权限" };
	private void getListData() {
		List<User> list=new ArrayList<User>();
		User eb=new User();
		eb.setState("1");
		list=new UserDao().getUsers0(eb);
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			eb=list.get(i);
			String p=eb.getType().equals("0")?"客户":"";
			String s=eb.getState().equals("1")?"在线":"注销";
			data[i]=new String[] {eb.getUsername(),eb.getName(),eb.getSex(),eb.getTel(),s,p};
		}
		table=new JTable(data,colName);
		sp.setViewportView(table);
	}
	public static void main(String[] args) {
		new CountUser(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}
