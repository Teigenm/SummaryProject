package com.fx.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.fx.bean.User;
import com.fx.dao.UserDao;

/**
 * 银行维护客户信息界面
 * @author asus
 *
 */
public class UpUser1 extends JDialog {

	private JTable table;
	private JComboBox role;
	private JTextField addr;
	private JTextField tel;
	private JTextField age;
	private JTextField id;
	private ButtonGroup bg = new ButtonGroup();
	private JTextField tname;
	private JRadioButton sex1, sex2;
	private JComboBox roleState;
	private String[] roleData = { "", "客户" };
	private String[] roleDataState = { "", "激活","挂失" };
	private JLabel eid;
	private String[] colName = { "卡号", "姓名", "身份证号","性别", "电话", "状态", "权限" };
	//private String[] colName = { "卡号", "姓名", "身份证号","年龄","性别", "地址", "电话", "权限" };
	private JScrollPane sp;

	public UpUser1(JFrame father) {
		super(father, true);
		setTitle("维护客户信息");
		setSize(750, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		final JLabel label_1_1_1 = new JLabel();
		label_1_1_1.setText("卡号：");
		label_1_1_1.setBounds(40, 10, 80, 25);
		getContentPane().add(label_1_1_1);

		final JLabel label = new JLabel();
		label.setText("姓名：");
		label.setBounds(280, 10, 80, 25);
		getContentPane().add(label);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("性别：");
		label_1_1.setBounds(40, 40, 80, 25);
		getContentPane().add(label_1_1);
		
		final JLabel label_1_6 = new JLabel();
		label_1_6.setText("身份证号：");
		label_1_6.setBounds(40, 70, 80, 25);//480, 10, 80, 25
		getContentPane().add(label_1_6);

		final JLabel label_1_2 = new JLabel();
		label_1_2.setText("年龄：");
		label_1_2.setBounds(280, 40, 80, 25);
		getContentPane().add(label_1_2);

		final JLabel label_1_3 = new JLabel();
		label_1_3.setText("电话：");
		label_1_3.setBounds(480, 10, 80, 25);
		getContentPane().add(label_1_3);

		final JLabel label_1_4 = new JLabel();
		label_1_4.setText("地址：");
		label_1_4.setBounds(40, 100, 80, 25);
		getContentPane().add(label_1_4);

		final JLabel label_1_5 = new JLabel();
		label_1_5.setText("权限：");
		label_1_5.setBounds(480, 70, 80, 25);
		getContentPane().add(label_1_5);
		
		final JLabel label_1_7 = new JLabel();
		label_1_7.setText("状态：");
		label_1_7.setBounds(480, 40, 80, 25);
		getContentPane().add(label_1_7);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//复位密码
				String idcard = UpUser1.this.eid.getText();
				UserDao udao=new UserDao();
				if (idcard == null || "".equals(idcard)) {
					return;
				}
				if (udao.changeUser_pwd(idcard,"000000")==1) {
					JOptionPane.showMessageDialog(null, "卡号"+idcard + "的密码复位成功,复位密码为000000！");
				}
			}
		});
		button.setText("密码复位");
		button.setBounds(60, 130, 110, 25);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {//销毁窗体
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(580, 130, 110, 25);
		getContentPane().add(button_1);

		tname = new JTextField();
		tname.setBounds(330, 10, 120, 25);
		getContentPane().add(tname);

		
		sex1 = new JRadioButton();
		bg.add(sex1);
		sex1.setSelected(true);
		sex1.setText("女");
		sex1.setBounds(90, 40, 50, 25);
		getContentPane().add(sex1);

		sex2 = new JRadioButton();
		bg.add(sex2);
		sex2.setText("男");
		sex2.setBounds(145, 40, 50, 25);
		getContentPane().add(sex2);

		age = new JTextField();
		age.setBounds(330, 40, 80, 25);
		getContentPane().add(age);
		
		id= new JTextField();
		id.setBounds(100, 70, 350, 25);
		getContentPane().add(id);//550, 10, 120, 25

		tel = new JTextField();
		tel.setBounds(520, 10, 120, 25);
		getContentPane().add(tel);

		addr = new JTextField();
		addr.setBounds(100, 100, 600, 25);
		getContentPane().add(addr);

		role = new JComboBox(roleData);
		role.setBounds(520, 70, 120, 25);
		getContentPane().add(role);

		roleState = new JComboBox(roleDataState);
		roleState.setBounds(520, 40, 120, 25);
		getContentPane().add(roleState);

		final JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//复位银行卡状态
				String idcard = UpUser1.this.eid.getText();
				if (idcard == null || "".equals(idcard)) {
					return;
				}
				if (new UserDao().reSetState(idcard,"1")==1) {
					JOptionPane.showMessageDialog(null,  "卡号"+idcard + "的银行卡状态已激活");
					getListData();
				}
			}
		});
		button_2.setText("状态复位");
		button_2.setBounds(190, 130, 110, 25);
		getContentPane().add(button_2);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {//修改客户信息
			public void actionPerformed(final ActionEvent e) {
				String idcard = UpUser1.this.eid.getText();
				if (idcard == null || "".equals(idcard)) {
					
					return;
				}
				if (check()) {
					update();
					JOptionPane.showMessageDialog(null, "卡号"+idcard + "的信息已修改！");
				}
			}
		});
		button_3.setText("修  改");
		button_3.setBounds(320, 130, 110, 25);
		getContentPane().add(button_3);

		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//删除客户
				String eid = UpUser1.this.eid.getText();
				if (eid == null || "".equals(eid)) {
					return;
				}
				int a = JOptionPane.showConfirmDialog(null, "确定删除吗?");
				if (a == JOptionPane.YES_OPTION) {
					int b = new UserDao().delUser(eid);
					if (b == 1) {
						getListData();
					}
					
				}
			}
		});
		button_4.setText("删  除");
		button_4.setBounds(450, 130, 110, 25);
		getContentPane().add(button_4);

		sp = new JScrollPane();
		sp.setBounds(5, 160,730, 200);
		getContentPane().add(sp);


		getListData();

		eid = new JLabel();
		eid.setBounds(90, 10, 80, 25);
		getContentPane().add(eid);

		this.addWindowListener(new WindowAdapter() {//销毁窗体
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}


	private void update() {//更新数据库中user信息
		User eb = new User();
		eb.setIdcard(this.eid.getText());
		eb.setUser_name(tname.getText());
		eb.setSex(sex1.isSelected() ? sex1.getText() : sex2.getText());
		eb.setAge(age.getText());
		eb.setTel(tel.getText());
		eb.setId(id.getText());
		eb.setAddress(addr.getText());
		if(role.getSelectedIndex()==1)
			eb.setUser_type("1");
		if(roleState.getSelectedIndex()==1)
			eb.setState("2");
		if(roleState.getSelectedIndex()==2)
			eb.setState("3");
		if (new UserDao().updateUser(eb) == 1) {
			getListData();
		}
		
	}

	private boolean check() {//判断客户信息是否不符规范
		String name = tname.getText();
		String age = this.age.getText();
		String tel = this.tel.getText();
		String id = this.id.getText();
		if ("".equals(name)) {
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return false;
		}
		if (!"".equals(age)) {
			int a = 0;
			try {
				a = Integer.parseInt(age);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "年龄有误！");
				return false;
			}
			if (a < 18 || a > 60) {
				JOptionPane.showMessageDialog(null, "年龄不在合法区间！");
				return false;
			}
		}
		if (tel.equals("")) {
			JOptionPane.showMessageDialog(null, "电话不能省略！");
			return false;
		}
		if (tel.length() != 11) {
			JOptionPane.showMessageDialog(null, "电话有误！");
			return false;
		}
		

		char[] c = tel.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				JOptionPane.showMessageDialog(null, "电话有误！");
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

	private void getListData() {//获得客户信息并复制于table控件
		List<User> list = new UserDao().getUsers1();
		String[][] data = new String[list.size()][];
		// "卡号", "姓名", "性别", "电话", "状态", "权限"
		//"卡号", "姓名", "身份证号","年龄","性别", "地址", "电话", "权限"
		for (int i = 0; i < data.length; i++) {
			User eb = list.get(i);
			String r = eb.getUser_type().equals("1") ? "客户" : "";
			String p=eb.getState().equals("2")?"激活" : "挂失";
			data[i] = new String[] { eb.getIdcard(), eb.getUser_name(),eb.getId(),eb.getSex(), eb.getTel(),p,r  };
		}
		table = new JTable(data, colName);
		table.addMouseListener(new MouseAdapter() {//添加table监控并获得idcard
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					return;
				}
				String o = (String) table.getValueAt(row, 0);// id
				if (o == null || "".equals(o)) {
					return;
				}
				getUser(o);
			}
		});
		sp.setViewportView(table);
	}
	private void getUser(String idcard) {//根据idcard获得user具体信息
		User eb = new UserDao().queryUserone(idcard);
		if (eb == null) {
			return;
		}
		this.eid.setText(eb.getIdcard());
		tname.setText(eb.getUser_name());
		if (eb.getSex().equals("女")) {
			sex1.setSelected(true);
		} else {
			sex2.setSelected(true);
		}
		age.setText(eb.getAge());
		tel.setText(eb.getTel());
		addr.setText(eb.getAddress());
		int index =eb.getUser_type().equals("1")?1:0;
		id.setText(eb.getId());
		role.setSelectedIndex(index);
		int indexs =eb.getState().equals("2")?1:2;
		roleState.setSelectedIndex(indexs);
		
	}

}
