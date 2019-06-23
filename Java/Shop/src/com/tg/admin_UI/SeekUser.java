package com.tg.admin_UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.tg.bean.User;
import com.tg.dao.UserDao;

public class SeekUser extends JDialog{
	private JTable table;
	private JComboBox role;
	private JTextField username;
	private JTextField name;
	private JRadioButton sex1, sex2;
	private JTextField addr;
	private JTextField tel;
	private ButtonGroup bg = new ButtonGroup();
	private JComboBox roleState;
	private String[] roleData = { "", "客户" };
	private String[] roleStateData={"","在线","注销"};
	private String[] colName = { "账号", "姓名","性别", "电话", "地址","状态", "权限" };
	private JScrollPane sp;
	private User my_user=null;
	
	public SeekUser(User my_user,JFrame jf) {
		super(jf, true);
		this.my_user=my_user;
		setTitle("维护客户信息");
		setSize(750, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		final JLabel label3 = new JLabel();
		label3.setText("账号：");
		label3.setBounds(40, 10, 80, 25);
		getContentPane().add(label3);
		
		username = new JTextField();
		username.setBounds(100, 10, 120, 25);
		getContentPane().add(username);
		
		final JLabel label4 = new JLabel();
		label4.setText("姓名：");
		label4.setBounds(40, 70, 80, 25);
		getContentPane().add(label4);
		
		name = new JTextField();
		name.setBounds(100, 70, 250, 25);
		getContentPane().add(name);
		
		final JLabel label5 = new JLabel();
		label5.setText("性别：");
		label5.setBounds(280, 40, 80, 25);
		getContentPane().add(label5);
		
		sex1 = new JRadioButton();
		sex1.setSelected(true);
		sex1.setText("女");
		sex1.setBounds(320, 40, 50, 25);
		getContentPane().add(sex1);

		sex2 = new JRadioButton();
		sex2.setText("男");
		sex2.setBounds(375, 40, 50, 25);
		getContentPane().add(sex2);

		
		sex1.setSelected(false);
		sex2.setSelected(false);
		bg.add(sex1);
		bg.add(sex2);
		//username,password, name,sex,tel,address,state, type
		final JLabel label_1_3 = new JLabel();
		label_1_3.setText("电话：");
		label_1_3.setBounds(280, 10, 80, 25);
		getContentPane().add(label_1_3);

		tel = new JTextField();
		tel.setBounds(330, 10, 180, 25);
		getContentPane().add(tel);
		
		final JLabel label_1_4 = new JLabel();
		label_1_4.setText("地址：");
		label_1_4.setBounds(40, 100, 80, 25);
		getContentPane().add(label_1_4);

		addr = new JTextField();	
		addr.setBounds(100, 100, 600, 25); 
		getContentPane().add(addr);

		final JLabel label_1_7 = new JLabel();
		label_1_7.setText("状态：");
		label_1_7.setBounds(40, 40, 80, 25);
		getContentPane().add(label_1_7);
		
		roleState=new  JComboBox(roleStateData);
		roleState.setBounds(100, 40, 120, 25);
		getContentPane().add(roleState);
		//username,password, name,sex,tel,address,state, type
		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User eb=new User();
				if (!username.getText().equals("")) {
					eb.setUsername(username.getText());
				}
				if (!name.getText().equals("")) {
					eb.setName(name.getText());
				}
				if (sex1.isSelected()) {
					eb.setSex(sex1.getText());
				}
				if (sex2.isSelected()) {
					eb.setSex(sex2.getText());
				}
				if (roleState.getSelectedIndex() > 0) {
					if(roleState.getSelectedIndex()==1)
						eb.setState("1");
					else
						eb.setState("2");
				}
				if (!tel.getText().equals("")) {
					eb.setTel(tel.getText());
				}
				//System.out.println(eb);
				getListData(eb);
			}
		});
		button_3.setText("查  询");
		button_3.setBounds(400, 70, 110, 25);
		getContentPane().add(button_3);
		
		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				name.setText("");
				bg.remove(sex1);
				bg.remove(sex2);
				sex1.setSelected(false);
				sex2.setSelected(false);
				bg.add(sex1);
				bg.add(sex2);
				tel.setText("");
				addr.setText("");
				roleState.setSelectedIndex(0);
			}
		});
		button_4.setText("清  空");
		button_4.setBounds(550, 70, 110, 25);
		getContentPane().add(button_4);

		sp = new JScrollPane();
		sp.setBounds(5, 130,730, 230);
		getContentPane().add(sp);
		getListData(new User());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	//private String[] colName = { "账号", "姓名","性别", "电话", "地址","状态", "权限" };
	private void getListData(User user) {
		List<User> list=new UserDao().getUsers0(user);
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			User eb=list.get(i);
			String p=eb.getType().equals("0")?"客户":"";
			String s=eb.getState().equals("1")?"在线":"注销";
			data[i]=new String[] {eb.getUsername(),eb.getName(),eb.getSex(),eb.getTel(),eb.getAddress(),s,p};
		}
		table=new JTable(data,colName);
		sp.setViewportView(table);
	}
	public static void main(String[] args) {
		new SeekUser(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}	
