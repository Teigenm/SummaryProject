package com.tg.admin_UI;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.tg.bean.User;
import com.tg.client_UI.Client_UI;
import com.tg.dao.UserDao;

public class Login extends JFrame{
	private JButton jbLogin;
	private JButton jbRegister;
	private JLabel jlusername;
	private JLabel jlpassword;
	private JPasswordField jtpassword;
	private JTextField jtusername;
	
	
	public Login() {
		this.setSize(500, 300);
		this.setTitle("辉煌购物系统");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		Container ct=this.getContentPane();
		
		final JLabel label = new JLabel();
		label.setIcon(new ImageIcon(getClass().getResource(
				"/images/logo.png.png"))); 
		label.setBounds(30, 20, 173, 153);
		ct.add(label);
		
		jlusername=new JLabel("账号：");
		jlusername.setBounds(215, 50, 80, 30);
		ct.add(jlusername);
		jlpassword=new JLabel("密码：");
		jlpassword.setBounds(215, 100, 80, 30);
		ct.add(jlpassword);
		
		jtusername=new JTextField();
		jtusername.setBounds(270, 50, 180, 25);
		ct.add(jtusername);
		jtpassword=new JPasswordField();
		//jtpassword.setEchoChar('●');
		jtpassword.setBounds(270, 100, 180, 25);
		ct.add(jtpassword);
		
		jbLogin=new JButton("登陆");
		jbLogin.setBounds(215, 158, 80, 25);
		ct.add(jbLogin);
		
		jbRegister=new JButton("注册");
		jbRegister.setBounds(385, 158, 80, 25);
		ct.add(jbRegister);
		
		jbLoginAction jla=new jbLoginAction();
		jbLogin.addActionListener(jla);
		
		jbLoginRegister jlr=new jbLoginRegister();
		jbRegister.addActionListener(jlr);
		
		
	}
	private boolean check() {//验证密码输入是否符合规范
		String tusername=jtusername.getText().trim();
		String tpass = new String(jtpassword.getPassword());
		if ("".equals(tusername)) {
			JOptionPane.showMessageDialog(null, "请输入账号");
			return false;
		}
		if ("".equals(tpass)) {
			JOptionPane.showMessageDialog(null, "请输入密码");
			return false;
		}
		return true;
	}
	public class jbLoginAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String pwd=new String(jtpassword.getPassword());
			String username=jtusername.getText().trim();
			if(check()) {
				User user=new User(username,pwd);
				User user2=new UserDao().queryUserone(user);
				
				if(user2==null) {
					JOptionPane.showMessageDialog(null, "账号或密码错误！请重新输入！");
				}
				else if(user2.getType().equals("1")){
					new Manager(user2).setVisible(true);
					dispose();
				}
				else {
					new Client_UI(user2).setVisible(true);
					dispose();
				}
			}
		} 
	}
	public class jbLoginRegister implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Register().setVisible(true);
		}
	}
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
