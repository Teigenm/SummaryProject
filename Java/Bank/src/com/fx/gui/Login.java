package com.fx.gui;

/**
 * 登陆界面
 */
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.fx.bean.User;
import com.fx.dao.DbDao;
import com.fx.dao.UserDao;
import com.fx.utils.SwingResourceManager;

public class Login extends JFrame {

	private ButtonGroup bg = new ButtonGroup();
	private JPasswordField pass;
	private JTextField eid;
	private JRadioButton role1, role2;
	
	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		setTitle("登录系统");
		setSize(500, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		final JLabel label = new JLabel();
		label.setIcon(SwingResourceManager.getIcon(Login.class,
				"/images/loo.png"));
		label.setBounds(20, 20, 173, 153);
		getContentPane().add(label);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		
		final JLabel label_1 = new JLabel();
		label_1.setText("卡号：");
		label_1.setBounds(215, 50, 80, 30);
		getContentPane().add(label_1);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("密码：");
		label_1_1.setBounds(215, 100, 80, 30);
		getContentPane().add(label_1_1);

		eid = new JTextField();
		eid.setBounds(270, 50, 180, 25);
		getContentPane().add(eid);

		pass = new JPasswordField();
		pass.setBounds(270, 100, 180, 25);
		pass.setEchoChar('●');
		getContentPane().add(pass);
		pass.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass.getPassword().length;
				if(length>=6)
				{	e.consume();
					
				}
				
			}
		});
		
		role1 = new JRadioButton();
		bg.add(role1);
		role1.setSelected(true);
		role1.setText("个人账户");
		role1.setBounds(215, 158, 80, 25);
		getContentPane().add(role1);

		role2 = new JRadioButton();
		bg.add(role2);
		role2.setText("管 理 员");
		role2.setBounds(385, 158, 80, 25);
		getContentPane().add(role2);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// 在swing的数据库操作。分2步。
				// 1.检查窗体输入的合法性。
				if (check()) {
					// 2.和数据库操作。
					try {
						login();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			}
		});
		button.setText("登  录");
		button.setBounds(105, 205, 100, 30);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//销毁窗体
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(285, 205, 100, 30);
		getContentPane().add(button_1);
		

		setVisible(true);

	}

	private void login(){//登陆并与数据库验证，进入相应界面
		boolean f=role1.isSelected() ? true : false;
		String idcard=eid.getText();
		String password=pass.getText();
		User user=new User(idcard,password);
		
		
		if(!f)
		{	user.setUser_type("0");
			
			if(new UserDao().queryUserone(user)==null)
			{	
				JOptionPane.showMessageDialog(null, "管理员账号或密码有误！");
				eid.grabFocus();
				return;
			}
			else
			{	System.out.println("成功登陆"+user.getIdcard());
				new Manager(idcard).setVisible(true);
				dispose();
				return ;
			}
		}
		else
		{	user.setUser_type("1");
		
			if(new UserDao().queryUserone(user)==null)
			{	JOptionPane.showMessageDialog(null, "用户卡号或密码有误！");
				eid.grabFocus();
				
				return;
			}
			else
			{	System.out.println("成功登陆");
				new Client(idcard).setVisible(true);
				dispose();
				return ;
			}
		}
		
	}
	private boolean check(){
		String id = eid.getText();
		String pas = new String(pass.getPassword());
		if ("".equals(id)) {
			JOptionPane.showMessageDialog(null, "卡号不能为空");
			eid.grabFocus();
			return false;
		}
		if ("".equals(pas)) {
			JOptionPane.showMessageDialog(null, "密码不能省略");
			pass.grabFocus();
			return false;
		}
		// 列举：各种不合法情况
		return true;
	}
}
