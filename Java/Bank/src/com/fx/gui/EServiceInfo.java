package com.fx.gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.fx.bean.User;
import com.fx.dao.UserDao;


public class EServiceInfo extends JDialog {

	private JTable table;

	private String[] colName = { "编号", "姓名","身份证号","性别", "电话", "地址", "权限" };
	//private String[] colName = { "卡号", "姓名", "身份证号","年龄","性别", "地址", "电话", "权限" };
	private JScrollPane sp;

	public EServiceInfo(JFrame father) {
		super(father, true);
		setTitle("在职业务员信息");
		setSize(750, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		sp = new JScrollPane();
		sp.setBounds(5, 5,735, 350);
		getContentPane().add(sp);
		getListData();
		
		this.addWindowListener(new WindowAdapter() {//销毁窗体
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}


	private void getListData() {//获得业务员，管理员信息并复制于table控件
		List<User> list = new UserDao().getUsersFlag(0);
		String[][] data = new String[list.size()][];
		// "卡号", "姓名", "性别", "电话", "状态", "权限"
		//"卡号", "姓名", "身份证号","年龄","性别", "地址", "电话", "权限"
		for (int i = 0; i < data.length; i++) {
			User eb = list.get(i);
			String r = eb.getUser_type().equals("0")?"管理员":"业务员";
			data[i] = new String[] { eb.getIdcard(), eb.getUser_name(),eb.getId(),eb.getSex(), eb.getTel(),eb.getAddress(),r  };
			
		}
		table = new JTable(data, colName);
		sp.setViewportView(table);
	}
		

}
