package com.tg.admin_UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.tg.bean.User;
import com.tg.dao.UserDao;

public class Manager extends JFrame{
	private JLabel jla;
	private JLabel label1;
	private JFrame jf;
	private User my_user=null;
	public Manager(User my_user){
		this.my_user=my_user;
		this.jf=this;
		this.setTitle("辉煌超市管理系统");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		Container ct=this.getContentPane();
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		
		label1=new JLabel();
		label1.setBounds(350,150,250,50);
		label1.setText("当前客户在线人数: "+new UserDao().countUser0()+"人");
		label1.setFont(new Font("宋体",Font.BOLD,18));
		label1.setForeground(Color.WHITE);;
		getContentPane().add(label1);
		
		jla=new JLabel();
		jla.setIcon(new ImageIcon(getClass().getResource(
				"/images/admin.jpg"))); 
		jla.setBounds(-200,-230,1300,1000);
		ct.add(jla);
		
		JMenuBar jmbar=new JMenuBar();
		this.setJMenuBar(jmbar);
		
		JMenu user_manage=new JMenu("客户管理");
		jmbar.add(user_manage);
		JMenuItem user_add=new JMenuItem("增加客户");
		user_manage.add(user_add);
		JMenuItem user_del=new JMenuItem("删除客户");
		user_manage.add(user_del);
		JMenuItem user_alter=new JMenuItem("修改客户");
		user_manage.add(user_alter);
		JMenuItem user_seek=new JMenuItem("查看客户");
		user_manage.add(user_seek);
		
		JMenu goods_manage =new JMenu("商品管理");
		jmbar.add(goods_manage);
		JMenuItem goods_add=new JMenuItem("增加商品");
		goods_manage.add(goods_add);
		JMenuItem goods_del=new JMenuItem("删除商品");
		goods_manage.add(goods_del);
		JMenuItem goods_alter=new JMenuItem("修改商品");
		goods_manage.add(goods_alter);
		JMenuItem goods_seek=new JMenuItem("查看商品");
		goods_manage.add(goods_seek);
		
		JMenu help=new JMenu("帮助");
		jmbar.add(help);
		JMenuItem exit=new JMenuItem("退出系统");
		help.add(exit);
		JMenuItem about=new JMenuItem("关于");
		help.add(about);
		
	
		
		
		JButton jbseek=new JButton("查看");
		jbseek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new CountUser(my_user,jf).setVisible(true);
			}
		});
		jbseek.setFont(new Font("宋体",Font.BOLD,18));
		jbseek.setBounds(600,150,200,50);
		getContentPane().add(jbseek);
		
		exitActionListener ealr=new exitActionListener();
		aboutActionListener aalr=new aboutActionListener();
		exit.addActionListener(ealr);
		about.addActionListener(aalr);
		
		goods_addActionListener gaalr=new goods_addActionListener();
		goods_add.addActionListener(gaalr);
		goods_delActionListener gdalr=new goods_delActionListener();
		goods_del.addActionListener(gdalr);
		goods_alterActionListener gtalr=new goods_alterActionListener();
		goods_alter.addActionListener(gtalr);
		goods_seekActionListener gsalr=new goods_seekActionListener();
		goods_seek.addActionListener(gsalr);
		
		user_addActionListener uaalr=new user_addActionListener();
		user_add.addActionListener(uaalr);
		user_delActionListener udalr=new user_delActionListener();
		user_del.addActionListener(udalr);
		user_alterActionListener utalr=new user_alterActionListener();
		user_alter.addActionListener(utalr);
		user_seekActionListener usalr=new user_seekActionListener();
		user_seek.addActionListener(usalr);
		
		
	}
	
	public class user_addActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new AddUser(my_user,jf).setVisible(true);
			label1.setText("当前客户在线人数: "+new UserDao().countUser0()+"人");
		}
	}
	public class user_delActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new DelUser(my_user,jf).setVisible(true);
			label1.setText("当前客户在线人数: "+new UserDao().countUser0()+"人");
		}
	}
	public class user_alterActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new AlterUser(my_user,jf).setVisible(true);
			label1.setText("当前客户在线人数: "+new UserDao().countUser0()+"人");
		}
	}
	public class user_seekActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new SeekUser(my_user,jf).setVisible(true);
		}
	}
	
	
	public class goods_addActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new AddGoods(my_user,jf).setVisible(true);
		}
	}
	public class goods_delActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new DelGoods(my_user,jf).setVisible(true);
			
		}
	}
	public class goods_alterActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new AlterGoods(my_user,jf).setVisible(true);
		}
	}
	public class goods_seekActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new SeekGoods(my_user,jf).setVisible(true);
		}
	}
	public class exitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Login().setVisible(true);
		}
	}
	public class aboutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "版本号：1.10.1v!");
		}
	}
	public static void main(String[] args) {
		new Manager(new User("admin","admin")).setVisible(true);
	}
}
