package com.fx.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.fx.bean.User;
import com.fx.dao.DbDao;
import com.fx.dao.UserDao;

/**
 *	增加业务员界面
 * @author asus
 *
 */
public class AddUser2 extends JDialog {
	private String idcard;
	private JComboBox role;
	private JTextField addr;
	private JTextField tel;
	private JTextField age;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pass;
	private JTextField tname;
	private JTextField tid;
	private JTextField tidcard;
	private JRadioButton sex1, sex2;
	private String[] roleData = {"管 理 员", "业 务 员"};

	
	private void insert() {//将数据录入数据库
		User eb = new User();
		UserDao ed = new UserDao();
		eb.setIdcard(tidcard.getText().toString().trim());
		String pass = new String(this.pass.getPassword());
		eb.setPassword(pass);
		eb.setUser_name(tname.getText().toString().trim());
		eb.setId(tid.getText().toString().trim());
		eb.setAge(age.getText().toString().trim());
		eb.setSex(sex1.isSelected() ? sex1.getText().toString().trim(): sex2.getText().toString().trim());
	
		eb.setAddress(addr.getText().toString().trim());
		eb.setTel(tel.getText().toString().trim());
		eb.setUser_type(role.getSelectedIndex()+"");
		eb.setState("1");

		if (ed.addUser(eb) == 1) {
			JOptionPane.showMessageDialog(null, "编号为" + eb.getIdcard()
					+ "的员工,录入成功");
			clear();
		}

	}

	private boolean check() {//判断录入员工信息是否符合标准
		String name = tname.getText();
		String pass = new String(this.pass.getPassword());
		String age = this.age.getText();
		String tel = this.tel.getText();
		String id=this.tid.getText();
		String idcard=tidcard.getText();
		User user2=new UserDao().queryUserone(idcard);
		if ("".equals(idcard)) {
			JOptionPane.showMessageDialog(null, "编号不能为空！");
			return false;
		}
		if (user2!=null) {
			JOptionPane.showMessageDialog(null, "编号重复！");
			return false;
		}
		if ("".equals(pass) || pass.length() < 6) {
			JOptionPane.showMessageDialog(null, "请输入6位密码！");
			return false;
		}
		if ("".equals(name)) {
			JOptionPane.showMessageDialog(null, "姓名不能为空");
			return false;
		}

		if (!"".equals(age)) {
			int a = 0;
			try {
				a = Integer.parseInt(age);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "年龄有误");
				return false;
			}
			if (a < 18 || a > 60) {
				JOptionPane.showMessageDialog(null, "年龄不在合法区间");
				return false;
			}
		}
		if (tel.equals("")) {
			JOptionPane.showMessageDialog(null, "电话不能省略");
			return false;
		}
		if (tel.length() != 11) {
			JOptionPane.showMessageDialog(null, "电话有误");
			return false;
		}
		char[] c = tel.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				JOptionPane.showMessageDialog(null, "电话有误");
				return false;
			}
		}
		if (id.toString().length() != 18) {
			JOptionPane.showMessageDialog(null, "身份证号必须为18位！");
			return false;
		}
		char[] d = id.toCharArray();
		for (int i = 0; i <d.length; i++) {
			if (!Character.isDigit(d[i])) {
				JOptionPane.showMessageDialog(null, "身份证号不符合规范！");
				return false;
			}
		}
		return true;
	}
	

	private void clear() {//将文本框等状态置零
		tname.setText("");
		
		pass.setText("");
		sex1.setSelected(true);
		age.setText("");
		tel.setText("");
		addr.setText("");
		role.setSelectedIndex(0);
		tid.setText("");
		tidcard.setText("");
		tidcard.grabFocus();

	}

	public AddUser2(String idcard,JFrame jf) {//界面
		super(jf,true);
		setTitle("添加员工");
		setSize(500, 580);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		final JLabel label = new JLabel();
		label.setText("编         号：");
		label.setBounds(50, 40, 80, 25);
		getContentPane().add(label);

		final JLabel label_1 = new JLabel();
		label_1.setText("密         码：");
		label_1.setBounds(50, 80, 80, 25);
		getContentPane().add(label_1);
		final JLabel label_2 = new JLabel();
		label_2.setText("姓         名：");
		label_2.setBounds(50, 120, 80, 25);
		getContentPane().add(label_2);
		
		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("身份证号：");
		label_1_1.setBounds(50, 285, 80, 25);
		getContentPane().add(label_1_1);

		final JLabel label_1_2 = new JLabel();
		label_1_2.setText("性         别：");
		label_1_2.setBounds(50, 160, 80, 25);
		getContentPane().add(label_1_2);
		
		final JLabel label_1_7 = new JLabel();
		label_1_7.setText("年         龄：");
		label_1_7.setBounds(50, 200, 80, 25);
		getContentPane().add(label_1_7);

		final JLabel label_1_3 = new JLabel();
		label_1_3.setText("电         话：");
		label_1_3.setBounds(50, 245, 80, 25);
		getContentPane().add(label_1_3);

		final JLabel label_1_4 = new JLabel();
		label_1_4.setText("地         址：");
		label_1_4.setBounds(50, 325, 80, 25);
		getContentPane().add(label_1_4);

		final JLabel label_1_5 = new JLabel();
		label_1_5.setText("权         限：");
		label_1_5.setBounds(50, 370, 80, 25);
		getContentPane().add(label_1_5);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
					insert();
				}
			}
		});
		button.setText("录  入");
		button.setBounds(80, 470, 120, 30);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(300, 470, 120, 30);
		getContentPane().add(button_1);

		tidcard = new JTextField();
		tidcard.setBounds(125, 40, 150, 25);
		getContentPane().add(tidcard);
		
		tname = new JTextField();
		tname.setBounds(125, 120, 150, 25);
		getContentPane().add(tname);

		pass = new JPasswordField();
		pass.setBounds(125, 80, 150, 25);
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
		
		sex1 = new JRadioButton();
		buttonGroup.add(sex1);
		sex1.setSelected(true);
		sex1.setText("女");
		sex1.setBounds(125, 160, 50, 25);
		getContentPane().add(sex1);

		sex2 = new JRadioButton();
		buttonGroup.add(sex2);
		sex2.setText("男");
		sex2.setBounds(225, 160, 50, 25);
		getContentPane().add(sex2);

		age = new JTextField();
		age.setBounds(125, 200, 100, 25);
		getContentPane().add(age);
		
		tel= new JTextField();
		tel.setBounds(125, 245, 180, 25);
		getContentPane().add(tel);

		tid = new JTextField();
		tid.setBounds(125, 285, 180, 25);
		getContentPane().add(tid);

		addr = new JTextField();
		addr.setBounds(125, 325, 320, 25);
		getContentPane().add(addr);

		role = new JComboBox(roleData);
		role.setBounds(125, 370, 120, 25);
		getContentPane().add(role);

	/*  final JLabel label_1_6 = new JLabel();
		label_1_6.setForeground(Color.RED);
		label_1_6.setText("密码为6位！");
		label_1_6.setBounds(300, 80, 133, 25);
		getContentPane().add(label_1_6);*/

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}


}
