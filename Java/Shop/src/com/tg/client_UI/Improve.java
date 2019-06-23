package com.tg.client_UI;

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
 *
 * @author asus
 *
 */
public class Improve extends JDialog {
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel username;
	private JTextField name;
	private JRadioButton sex1, sex2;
	private JTextField tel;
	private JTextField addr;
	private JComboBox role;
	private String[] roleData = {"客户"};
	private User my_user=null;
	//id, username,password, name,sex,tel,address,state, type
	private void update() {//将数据录入数据库
		User eb = new User();
		UserDao ed = new UserDao();
		eb.setUsername(username.getText().toString().trim());
		eb.setName(name.getText().toString().trim());
		eb.setSex(sex1.isSelected() ? sex1.getText().toString().trim(): sex2.getText().toString().trim());	
		eb.setAddress(addr.getText().toString().trim());
		eb.setTel(tel.getText().toString().trim());
		eb.setType(role.getSelectedIndex()+"");
		eb.setState("1");
		
		if (ed.updateUserExceptPwd(eb) == 1) {
			//System.out.println(eb);
			JOptionPane.showMessageDialog(null, "帐号为" + eb.getUsername()
					+ "的客户信息,保存成功");
			dispose();
		}
	}

	private boolean check() {//判断录入客户信息是否符合标准
		String ttel = this.tel.getText().trim();
		String tname=this.name.getText().trim();
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
	private void getData() {//将文本框等状态置零
		User eb=new UserDao().queryUserone(my_user.getUsername());
		this.username.setText(eb.getUsername());
		if (eb.getSex().equals("女")) {
			this.sex1.setSelected(true);
		} else {
			this.sex2.setSelected(true);
		}
		this.name.setText(eb.getName());
		this.tel.setText(eb.getTel());
		this.addr.setText(eb.getAddress());
	}

	public Improve(User my_user,JFrame jf) {//界面
		super(jf,true);
		this.my_user=my_user;
		setTitle("完善个人信息");
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		final JLabel label1 = new JLabel();
		label1.setText("帐         号：");
		label1.setBounds(50, 40, 80, 25);
		getContentPane().add(label1);

		username = new JLabel();
		username.setBounds(125, 40, 150, 25);
		getContentPane().add(username);
		
		final JLabel label3 = new JLabel();
		label3.setText("姓         名：");
		label3.setBounds(50, 90, 80, 25);
		getContentPane().add(label3);
		
		name = new JTextField();
		name.setBounds(125, 90, 150, 25);
		getContentPane().add(name);
		
		final JLabel label4 = new JLabel();
		label4.setText("性         别：");
		label4.setBounds(50, 240, 80, 25);
		getContentPane().add(label4);

		sex1=new JRadioButton();
		sex1.setText("女");
		sex1.setBounds(125, 240, 50, 25);
		getContentPane().add(sex1);

		sex2=new JRadioButton();
		sex2.setText("男");
		sex2.setBounds(225, 240, 50, 25);
		getContentPane().add(sex2);
		
		sex1.setSelected(false);
		sex2.setSelected(false);
		
		buttonGroup.add(sex1);
		buttonGroup.add(sex2);
		
		final JLabel label5 = new JLabel();
		label5.setText("电         话：");
		label5.setBounds(50, 140, 80, 25);
		getContentPane().add(label5);
		
		tel= new JTextField();
		tel.setBounds(125, 140, 180, 25);
		getContentPane().add(tel);
		
		final JLabel label6 = new JLabel();
		label6.setText("地         址：");
		label6.setBounds(50, 190, 80, 25);
		getContentPane().add(label6);

		addr = new JTextField();
		addr.setBounds(125, 190, 320, 25);
		getContentPane().add(addr);
		
		final JLabel label7 = new JLabel();
		label7.setText("权         限：");
		label7.setBounds(50, 290, 80, 25);
		getContentPane().add(label7);

		role = new JComboBox(roleData);
		role.setBounds(125, 290, 120, 25);
		getContentPane().add(role);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
					update();
					dispose();
				}
			}
		});
		button.setText("保  存");
		button.setBounds(80, 370, 120, 30);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(300, 370, 120, 30);
		getContentPane().add(button_1);

		getData();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	public static void main(String[] args) {
		new Improve(new User("a","12345678"),new JFrame()).setVisible(true);
	}
}
