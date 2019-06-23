package com.tg.admin_UI;


/**
 * 改密码界面
 */
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.tg.bean.User;
import com.tg.dao.UserDao;

public class Register extends JFrame {
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField username;
	
	public Register() {//界面
		setTitle("注册界面");
		setSize(330, 240);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		final JLabel label = new JLabel();
		label.setText("账         号：");
		label.setBounds(30, 20, 90, 25);
		getContentPane().add(label);

		username = new JPasswordField();
		username.setBounds(105, 20, 180, 25);
		getContentPane().add(username);
		
		final JLabel label_1 = new JLabel();
		label_1.setText("密         码：");
		label_1.setBounds(30, 60, 90, 25);
		getContentPane().add(label_1);

		pass1 = new JPasswordField();
		//pass2.setEchoChar('●');
		pass1.setBounds(105, 60, 180, 25);
		getContentPane().add(pass1);
		pass1.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass1.getPassword().length;
				if(length>=16)
				{	e.consume();
					
				}
				
			}
		});
		
		final JLabel label_2 = new JLabel();
		label_2.setText("确认密码：");
		label_2.setBounds(30, 100, 90, 25);
		getContentPane().add(label_2);

		pass2 = new JPasswordField();
		//pass3.setEchoChar('●');
		pass2.setBounds(105, 100, 180, 25);
		getContentPane().add(pass2);
		pass2.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass2.getPassword().length;
				if(length>=16)
				{	e.consume();
					
				}
				
			}
		});

		JButton button1=new JButton();
		JButton button2=new JButton();
		
		button1.setText("确  认");
		button1.setBounds(35, 150, 100, 30);
		getContentPane().add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
					insert();
				}else {
					username.setText("");
					pass1.setText("");
					pass2.setText("");
				}
			}
		});
		
		button2.setText("取  消");
		button2.setBounds(180, 150, 100, 30);
		getContentPane().add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
	}

	private void insert() {//
		UserDao udao=new UserDao();
		String tusername=username.getText().trim();
		String tpass = new String(pass1.getPassword());
		User user=new User(tusername,tpass);
		user.setType("0");
		user.setName("");
		user.setTel("");
		user.setAddress("");
		user.setState("1");
		user.setSex("");
		int a=udao.addUser(user);
		if(a==1) {
			JOptionPane.showMessageDialog(null, "注册成功");
			dispose();
			new Login().setVisible(true);
			return ;
		}
	}

	private boolean check() {//验证密码输入是否符合规范
		String tusername=username.getText().trim();
		String p1 = new String(pass1.getPassword());
		String p2 = new String(pass2.getPassword());
		UserDao udao=new UserDao();
		if ("".equals(tusername)) {
			JOptionPane.showMessageDialog(null, "请输入账号");
			return false;
		}
		if ("".equals(p1)) {
			JOptionPane.showMessageDialog(null, "请输入密码");
			return false;
		}
		if (!p2.equals(p2)) {
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			return false;
		}
		if (p2.length() <8) {
			JOptionPane.showMessageDialog(null, "密码太简单");
			return false;
		}
		User eb = new User();
		try {
			eb=udao.queryUserone(tusername);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(eb!=null) {
			JOptionPane.showMessageDialog(null, "账号重复,请重新输入账号");
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		new Register().setVisible(true);
	}
}
