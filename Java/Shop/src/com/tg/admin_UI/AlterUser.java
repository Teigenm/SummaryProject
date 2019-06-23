package com.tg.admin_UI;

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

import com.tg.bean.User;
import com.tg.dao.UserDao;

/**
 * 银行维护客户信息界面
 * @author asus
 *
 */
public class AlterUser extends JDialog {

	private JTable table;
	private JComboBox role;
	private JLabel username;
	private JTextField name;
	private JRadioButton sex1, sex2;
	private JTextField addr;
	private JTextField tel;
	private ButtonGroup bg = new ButtonGroup();
	private JComboBox roleState;
	private String[] roleData = { "", "客户" };
	private String[] roleStateData={"","在线","注销"};
	private String[] colName = { "账号", "姓名","性别", "电话", "状态", "权限" };
	private JScrollPane sp;
	private User my_user=null;
	//id, username,password, name,sex,tel,address,state, type
	public AlterUser(User my_user,JFrame jf) {
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
		
		username = new JLabel();
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
		//id, username,password, name,sex,tel,address,state, type
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

		final JLabel label_1_5 = new JLabel();
		label_1_5.setText("权限：");
		label_1_5.setBounds(40, 40, 80, 25);
		getContentPane().add(label_1_5);
		
		role = new JComboBox(roleData);
		role.setBounds(100, 40, 120, 25);
		getContentPane().add(role);
		
		final JLabel label_1_7 = new JLabel();
		label_1_7.setText("状态：");
		label_1_7.setBounds(480, 40, 80, 25);
		getContentPane().add(label_1_7);
		
		roleState=new  JComboBox(roleStateData);
		roleState.setBounds(520, 40, 120, 25);
		getContentPane().add(roleState);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//复位密码
				String  tusername= AlterUser.this.username.getText().trim();
				UserDao udao=new UserDao();
				if (tusername == null || "".equals(tusername)) {
					return;
				}
				if (udao.changeUser_pwd(tusername,"00000000")==1) {
					JOptionPane.showMessageDialog(null, "账号为"+tusername+ "的密码复位成功,复位密码为00000000！");
				}
			}
		});
		button.setText("密码复位");
		button.setBounds(150, 130, 150, 25);
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

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {//修改信息
			public void actionPerformed(final ActionEvent e) {
				String tusername = AlterUser.this.username.getText().trim();
				if (tusername == null || "".equals(tusername)) {
					return;
				}
				if (check()) {
					update();
					JOptionPane.showMessageDialog(null, "账号为"+tusername + "的信息已修改！");
				}
			}
		});
		button_3.setText("修  改");
		button_3.setBounds(320, 130, 110, 25);
		getContentPane().add(button_3);

		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {//删除
				String tusername = 	AlterUser.this.username.getText().trim();
				if (tusername == null || "".equals(tusername)) {
					return;
				}
				int a=JOptionPane.showConfirmDialog(null, "确定删除吗?","信息警告",JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					int b = new UserDao().delUser(tusername);
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


		this.addWindowListener(new WindowAdapter() {//销毁窗体
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}


	private void update() {//更新数据库中user信息
		User eb = new User();
		eb.setUsername(this.username.getText().trim().trim());
		eb.setName(name.getText().trim());
		eb.setSex(sex1.isSelected() ? sex1.getText().trim() : sex2.getText().trim());
		eb.setTel(tel.getText().trim());
		eb.setAddress(addr.getText().trim());
		if(role.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择权限");
			return ;
		}
		if(role.getSelectedIndex()==1)
			eb.setType("0");
		if(roleState.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择状态");
			return ;
		}
		if(roleState.getSelectedIndex()==1)
			eb.setState("1");
		else
			eb.setState("0");
		//System.out.println(eb);
		if (new UserDao().updateUserExceptPwd(eb) == 1) {
			getListData();
		
		}
	}

	private boolean check() {//判断信息是否不符规范
		String tusername=username.getText().trim();
		String tname = this.name.getText().trim();
		String ttel = this.tel.getText().trim();
		
		if ("".equals(tname)) {
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return false;
		}
		if (ttel.equals("")) {
			JOptionPane.showMessageDialog(null, "电话不能省略！");
			return false;
		}
		if (ttel.length() != 11) {
			JOptionPane.showMessageDialog(null, "电话有误！");
			return false;
		}
		char[] c = ttel.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				JOptionPane.showMessageDialog(null, "电话有误！");
				return false;
			}
		}
		return true;
	}

	private void getListData() {//获得信息并复制于table控件
		List<User> list = new UserDao().getUsers0();
		String[][] data = new String[list.size()][];
		// "编号", "姓名", "性别", "电话", "状态", "权限"
		//id, username, name,sex,tel,address,state, type
		for (int i = 0; i < data.length; i++) {
			User eb = list.get(i);
			String r = eb.getType().equals("0") ? "客户":"";
			String p=eb.getState().equals("1")?"在线": "注销";
			data[i] = new String[] { eb.getUsername(), eb.getName(),eb.getSex(), eb.getTel(),p,r  };
		}
		table = new JTable(data, colName);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					return;
				}
				String o = (String) table.getValueAt(row, 0);//
				if (o == null || "".equals(o)) {
					return;
				}
				getUser(o);
			}
		});
		sp.setViewportView(table);
	}
	public void getUser(String tusername) {
		User eb = new UserDao().queryUserone(tusername);
		if (eb == null) {
			return;
		}
		this.username.setText(eb.getUsername());
		this.name.setText(eb.getName());
		if (eb.getSex().equals("女")) {
			this.sex1.setSelected(true);
		} else {
			this.sex2.setSelected(true);
		}
		this.tel.setText(eb.getTel());
		this.addr.setText(eb.getAddress());
		int index =eb.getType().equals("0")?1:0;
		this.role.setSelectedIndex(index);
		int indexs =eb.getState().equals("1")?1:2;
		this.roleState.setSelectedIndex(indexs);
		
	}
	public static void main(String[] args) {
		new AlterUser(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}
