package com.tg.admin_UI;

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

import com.tg.bean.User;
import com.tg.dao.DbDao;
import com.tg.dao.UserDao;


/**
 * 增加客户界面
 * @author asus
 *
 */
public class AddUser extends JDialog {
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField username;
	private JPasswordField pass;
	private JTextField name;
	private JRadioButton sex1, sex2;
	private JTextField tel;
	private JTextField addr;
	private JComboBox role;
	private String[] roleData = {"客户"};
	private User my_user=null;
	//id, username,password, name,sex,tel,address,state, type
	private void insert() {//将数据录入数据库
		User eb = new User();
		UserDao ed = new UserDao();
		eb.setUsername(username.getText().toString().trim());
		String pass = new String(this.pass.getPassword());
		eb.setPassword(pass);
		eb.setName(name.getText().toString().trim());
		eb.setSex(sex1.isSelected() ? sex1.getText().toString().trim(): sex2.getText().toString().trim());	
		eb.setAddress(addr.getText().toString().trim());
		eb.setTel(tel.getText().toString().trim());
		eb.setType(role.getSelectedIndex()+"");
		eb.setState("1");
		
		if (ed.addUser(eb) == 1) {
			eb=ed.queryUserone(eb);
			JOptionPane.showMessageDialog(null, "帐号为" + eb.getUsername()
					+ "的客户信息,录入成功");
			clear();
		}

	}

	private boolean check() {//判断录入客户信息是否符合标准
		String tusername = username.getText().trim();
		String tpass = new String(this.pass.getPassword());
		String ttel = this.tel.getText().trim();
		String tname=this.name.getText().trim();
		User user2=new UserDao().queryUserone(tusername);
		if ("".equals(tusername)) {
			JOptionPane.showMessageDialog(null, "账号不能为空！");
			return false;
		}
		if (user2!=null) {
			JOptionPane.showMessageDialog(null, "账号重复！请重新输入账号");
			return false;
		}
		if ("".equals(tpass) || tpass.length() <= 8) {
			JOptionPane.showMessageDialog(null, "密码太简单！");
			return false;
		}
		if ("".equals(tname)) {
			JOptionPane.showMessageDialog(null, "姓名不能为空");
			return false;
		}
		if (ttel.equals("")) {
			JOptionPane.showMessageDialog(null, "电话不能省略");
			return false;
		}
		if (ttel.length() != 11) {
			JOptionPane.showMessageDialog(null, "电话有误");
			return false;
		}
		char[] c = ttel.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				JOptionPane.showMessageDialog(null, "电话有误");
				return false;
			}
		}
		return true;
	}
	//id, username,password, name,sex,tel,address,state, type
	private void clear() {//将文本框等状态置零
		username.setText("");
		pass.setText("");
		name.setText("");
		sex1.setSelected(true);
		tel.setText("");
		addr.setText("");
		role.setSelectedIndex(0);
		username.grabFocus();
	}

	public AddUser(User my_user,JFrame jf) {//界面
		super(jf,true);
		this.my_user=my_user;
		setTitle("添加客户");
		setSize(500, 550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		final JLabel label1 = new JLabel();
		label1.setText("帐         号：");
		label1.setBounds(50, 40, 80, 25);
		getContentPane().add(label1);

		username = new JTextField();
		username.setBounds(125, 40, 150, 25);
		getContentPane().add(username);

		final JLabel label2 = new JLabel();
		label2.setText("密         码：");
		label2.setBounds(50, 90, 80, 25);
		getContentPane().add(label2);

		pass = new JPasswordField();
		pass.setBounds(125,90, 150, 25);
		getContentPane().add(pass);
		pass.addKeyListener(new KeyAdapter() {//将密码控制在6位以内
			public void keyTyped(KeyEvent e)
			{	int length=pass.getPassword().length;
				if(length>=16)
				{	e.consume();
					
				}
				
			}
		});
		
		final JLabel label3 = new JLabel();
		label3.setText("姓         名：");
		label3.setBounds(50, 140, 80, 25);
		getContentPane().add(label3);
		
		name = new JTextField();
		name.setBounds(125, 140, 150, 25);
		getContentPane().add(name);
		
		final JLabel label4 = new JLabel();
		label4.setText("性         别：");
		label4.setBounds(50, 290, 80, 25);
		getContentPane().add(label4);

		sex1 = new JRadioButton();
		buttonGroup.add(sex1);
		sex1.setSelected(true);
		sex1.setText("女");
		sex1.setBounds(125, 290, 50, 25);
		getContentPane().add(sex1);

		sex2 = new JRadioButton();
		buttonGroup.add(sex2);
		sex2.setText("男");
		sex2.setBounds(225, 290, 50, 25);
		getContentPane().add(sex2);
		
		final JLabel label5 = new JLabel();
		label5.setText("电         话：");
		label5.setBounds(50, 190, 80, 25);
		getContentPane().add(label5);
		
		tel= new JTextField();
		tel.setBounds(125, 190, 180, 25);
		getContentPane().add(tel);
		
		final JLabel label6 = new JLabel();
		label6.setText("地         址：");
		label6.setBounds(50, 240, 80, 25);
		getContentPane().add(label6);

		addr = new JTextField();
		addr.setBounds(125, 240, 320, 25);
		getContentPane().add(addr);
		
		final JLabel label7 = new JLabel();
		label7.setText("权         限：");
		label7.setBounds(50, 340, 80, 25);
		getContentPane().add(label7);

		role = new JComboBox(roleData);
		role.setBounds(125, 340, 120, 25);
		getContentPane().add(role);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
					insert();
					dispose();
				}
			}
		});
		button.setText("录  入");
		button.setBounds(80, 420, 120, 30);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(300, 420, 120, 30);
		getContentPane().add(button_1);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	public static void main(String[] args) {
		new AddUser(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}
