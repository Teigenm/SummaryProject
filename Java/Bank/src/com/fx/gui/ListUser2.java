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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.fx.bean.User;
import com.fx.dao.UserDao;
/**
 * 查看业务员，管理员界面
 * @author asus
 *
 */

public class ListUser2 extends JDialog {
	private String idcard;
	private JComboBox role;
	private JTextField addr;
	private JTextField tel;
	private JTable table;
	private JTextField age;
	private ButtonGroup bg = new ButtonGroup();
	private JPasswordField pass;
	private JTextField tname;
	private JTextField tid;
	private JTextField tidcard;
	private JRadioButton sex1, sex2;
	private String[] roleData = { "","业务员" };
	//private String[] colName = { "卡号", "姓名", "身份证号","年龄","性别", "地址", "电话", "权限" };
	private String[] colName={"编号", "姓名","性别","电话", "身份证号","年龄","权限","状态"};
	private JScrollPane sp;

	public ListUser2(String idcard,JFrame jf) {
		super(jf,true);
		this.idcard=idcard;
		setTitle("查询业务员信息");
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		final JLabel label_1_1_1 = new JLabel();
		label_1_1_1.setText("编号：");
		label_1_1_1.setBounds(40, 10, 80, 25);
		getContentPane().add(label_1_1_1);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		final JLabel label = new JLabel();
		label.setText("姓名：");
		label.setBounds(280, 10, 80, 25);
		getContentPane().add(label);
		
		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("性别：");
		label_1_1.setBounds(40, 40, 80, 25);
		getContentPane().add(label_1_1);

		final JLabel label_1_3 = new JLabel();
		label_1_3.setText("电话：");
		label_1_3.setBounds(40, 70, 80, 25);
		getContentPane().add(label_1_3);

		final JLabel label_1_5 = new JLabel();
		label_1_5.setText("权限：");
		label_1_5.setBounds(280, 40, 80, 25);
		getContentPane().add(label_1_5);

		final JButton button_1 = new JButton();
		button_1.setText("取  消");
		button_1.setBounds(460, 70, 110, 25);
		getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});

		tname = new JTextField();
		tname.setBounds(330, 10, 120, 25);
		getContentPane().add(tname);

		sex1 = new JRadioButton();
		bg.add(sex1);
		sex1.setText("女");
		sex1.setBounds(90, 40, 50, 25);
		getContentPane().add(sex1);

		sex2 = new JRadioButton();
		bg.add(sex2);
		sex2.setText("男");
		sex2.setBounds(145, 40, 50, 25);
		getContentPane().add(sex2);

		tel = new JTextField();
		tel.setBounds(90, 70, 120, 25);
		getContentPane().add(tel);

		role = new JComboBox(roleData);
		role.setBounds(331, 40, 120, 25);
		getContentPane().add(role);

		final JButton button_3 = new JButton();
		button_3.setText("查  询");
		button_3.setBounds(220, 70, 110, 25);
		getContentPane().add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//获得业务员，管理员信息
				User eb = new User();
				if (tidcard.getText().length() > 0) {
					eb.setIdcard(tidcard.getText());
				}
				if (!tname.getText().equals("")) {
					eb.setUser_name(tname.getText());
				}
				if (sex1.isSelected()) {
					eb.setSex(sex1.getText());
				}
				if (sex2.isSelected()) {
					eb.setSex(sex2.getText());
				}
				if (role.getSelectedIndex() > 0) {
					if(role.getSelectedIndex()==1)
						eb.setUser_type("2");
				}
				if (tel.getText().length() > 0) {
					eb.setTel(tel.getText());
				}
				getListData(eb);
			}
		});

		final JButton button_4 = new JButton();
		button_4.setText("清  空");
		button_4.setBounds(340, 70, 110, 25);
		getContentPane().add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//将控件状态置零
				tidcard.setText("");
				tname.setText("");
				bg.remove(sex1);
				bg.remove(sex2);
				sex1.setSelected(false);
				sex2.setSelected(false);
				bg.add(sex1);
				bg.add(sex2);
				role.setSelectedIndex(0);
				tel.setText("");
				
			}
		});

	
		sp = new JScrollPane();
		sp.setBounds(5, 100, 585, 260);
		getContentPane().add(sp);

		getListData(new User());
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		tidcard = new JTextField();
		tidcard.setBounds(90, 10, 120, 25);
		getContentPane().add(tidcard);
		
	}

	private void getListData(User emp) {//获得业务员，管理员信息并复制于table控件
		List<User> list = new UserDao().getUsers2(emp);
		String[][] data = new String[list.size()][];
		// "卡号", "姓名", "性别", "电话", "状态", "权限"
		for (int i = 0; i < data.length; i++) {
			User eb = list.get(i);
			String r = "业务员";
			String p=eb.getState().equals("1")?"在职" : "离职";
			data[i] = new String[] { eb.getIdcard(), eb.getUser_name(),eb.getSex(), eb.getTel(),eb.getId(),eb.getAge(),r,p};
		}
		table = new JTable(data, colName);
		sp.setViewportView(table);
	}

}
