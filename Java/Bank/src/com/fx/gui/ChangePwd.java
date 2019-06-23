package com.fx.gui;


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

import com.fx.bean.User;
import com.fx.dao.UserDao;

public class ChangePwd extends JDialog {

	private JPasswordField pass3;
	private JPasswordField pass2;
	private JPasswordField pass1;
	private String idcard;
	
	public ChangePwd(String idcard,JFrame jf) {//界面
		super(jf,true);
		this.idcard = idcard;
		setTitle("修改密码");
		setSize(330, 240);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		final JLabel label = new JLabel();
		label.setText("原  密  码：");
		label.setBounds(30, 20, 90, 25);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setText("新  密  码：");
		label_1.setBounds(30, 60, 90, 25);
		getContentPane().add(label_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("确认密码：");
		label_2.setBounds(30, 100, 90, 25);
		getContentPane().add(label_2);

		pass1 = new JPasswordField();
		pass1.setEchoChar('●');
		pass1.setBounds(105, 20, 180, 25);
		getContentPane().add(pass1);
		pass1.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass1.getPassword().length;
				if(length>=6)
				{	e.consume();
					
				}
				
			}
		});

		pass2 = new JPasswordField();
		pass2.setEchoChar('●');
		pass2.setBounds(105, 60, 180, 25);
		getContentPane().add(pass2);
		pass2.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass2.getPassword().length;
				if(length>=6)
				{	e.consume();
					
				}
				
			}
		});

		pass3 = new JPasswordField();
		pass3.setEchoChar('●');
		pass3.setBounds(105, 100, 180, 25);
		getContentPane().add(pass3);
		pass3.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass3.getPassword().length;
				if(length>=6)
				{	e.consume();
					
				}
				
			}
		});

		JButton button1=new JButton();
		JButton button2=new JButton();
		
		button1.setText("修  改");
		button1.setBounds(35, 150, 100, 30);
		getContentPane().add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
						uptPwd();
				
				}
			}
		});
		
		
		button2.setText("取  消");
		button2.setBounds(180, 150, 100, 30);
		getContentPane().add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	private void uptPwd() {//在数据库中更新密码
		UserDao udao=new UserDao();
		String p3 = new String(pass3.getPassword());
		if (udao.changeUser_pwd(idcard,p3)==1) {
			JOptionPane.showMessageDialog(null, "密码修改成功，下次登录请使用新密码。");
			dispose();
		}
	}

	private boolean check() {//验证密码输入是否符合规范
		String p1 = new String(pass1.getPassword());
		String p2 = new String(pass2.getPassword());
		String p3 = new String(pass3.getPassword());
		UserDao udao=new UserDao();
		if ("".equals(p1)) {
			JOptionPane.showMessageDialog(null, "请输入原始密码");
			return false;
		}
		if ("".equals(p2)) {
			JOptionPane.showMessageDialog(null, "请输入新密码");
			return false;
		}
		if (p2.length() !=6) {
			JOptionPane.showMessageDialog(null, "银行卡密码为6位");
			return false;
		}
		if (!p2.equals(p3)) {
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			return false;
		}
		User eb = new User();
		try {
			eb=udao.queryUserone(idcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!eb.getPassword().equals(p1)) {
			JOptionPane.showMessageDialog(null, "原密码错误");
			return false;
		}
		return true;
	}

}
