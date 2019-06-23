package com.fx.gui;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.fx.dao.UserDao;

/*
 * manager.java
 *
 * Created on __DATE__, __TIME__
 */



/**
 *银行管理员界面
 * @author  __USER__
 */
public class Manager extends javax.swing.JFrame {

	/** Creates new form manager 
	 * @throws SQLException */
	private String idcard;
	public Manager(String idcard){// 初始化界面
		this.idcard=idcard;
		UserDao udao=new UserDao();
		initComponents();
		jLabel1.setText("银行卡办理人数："+udao.countUser1()+"人");
		jLabel2.setText("业务员在职人数："+udao.countUser2()+"人");
	
		
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jMenuBar2 = new javax.swing.JMenuBar();
		jMenu4 = new javax.swing.JMenu();
		jMenu5 = new javax.swing.JMenu();
		jButton1 = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jbutton_close = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1_1_1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenu2_3 = new javax.swing.JMenu();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenu3_3 = new javax.swing.JMenu();
		jMenuItem9 = new javax.swing.JMenuItem();
		jMenuItem10 = new javax.swing.JMenuItem();
		jMenuItem11 = new javax.swing.JMenuItem();
		jMenu4_2 = new javax.swing.JMenu();
		jMenuItem8 = new javax.swing.JMenuItem();
		jMenuItem7 = new javax.swing.JMenuItem();

		jMenu4.setText("File");
		jMenuBar2.add(jMenu4);

		jMenu5.setText("Edit");
		jMenuBar2.add(jMenu5);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		jButton1.setFont(new java.awt.Font("宋体", 0, 24));
		jButton1.setText("查看");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new UServiceInfo(Manager.this).setVisible(true);
			}
		});
		getContentPane().add(
				jButton1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 120,
						-1));

		jLabel2.setFont(new java.awt.Font("宋体", 1, 24));
		jLabel2.setText("");
		getContentPane().add(
				jLabel2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, -1,
						-1));
		
		jbutton_close.setFont(new java.awt.Font("宋体", 0, 24));
		jbutton_close.setText("退出系统");
		jbutton_close.addActionListener(new java.awt.event.ActionListener() {//退出系统
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeFrame();
			}
		});
		getContentPane().add(
				jbutton_close,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250,
						230, -1));

		jLabel1.setFont(new java.awt.Font("宋体", 1, 24));
		jLabel1.setText("银行卡办理总人数：");
		getContentPane().add(
				jLabel1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1,
						-1));

		jButton2.setFont(new java.awt.Font("宋体", 0, 24));
		jButton2.setText("查看");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new EServiceInfo(Manager.this).setVisible(true);
			}
		});
		getContentPane().add(
				jButton2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160,
						120, -1));

		jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/images/admin.jpg"))); // NOI18N
		getContentPane()
				.add(jLabel3,
						new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0,
								-1, -1));

		jMenu1_1_1.setText("服务");

		jMenuItem1.setText("启动服务");
		jMenu1_1_1.add(jMenuItem1);
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showConfirmDialog(null, "服务已启动！","服务",JOptionPane.DEFAULT_OPTION);
			}
		});

		jMenuItem2.setText("关闭服务");
		jMenu1_1_1.add(jMenuItem2);
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showConfirmDialog(null, "服务已停止！","服务",JOptionPane.DEFAULT_OPTION);
			}
		});
		

		jMenuItem3.setText("更换用户");
		jMenu1_1_1.add(jMenuItem3);
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {//退出系统，进入登陆界面
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeFramet();
				
			}
		});

		jMenuBar1.add(jMenu1_1_1);

		jMenu2_3.setText("业务员管理");
		
		jMenuItem4.setText("业务员添加");
		jMenu2_3.add(jMenuItem4);
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {//进入银行业务员添加界面
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new AddUser2(idcard,Manager.this).setVisible(true);
			}
		});


		jMenuItem5.setText("业务员修改");
		jMenu2_3.add(jMenuItem5);
		jMenuItem5.addActionListener(new java.awt.event.ActionListener() {//进入银行业务员修改界面
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new UpUser2(Manager.this).setVisible(true);
			}
		});
		
		jMenuItem6.setText("业务员列表");
		jMenu2_3.add(jMenuItem6);
		jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {//进入银行业务员列表界面
				new ListUser2(idcard,Manager.this).setVisible(true);
			}
		});

		jMenuBar1.add(jMenu2_3);

		jMenu3_3.setText("客户管理");


		jMenuItem9.setText("银行客户添加");
		jMenu3_3.add(jMenuItem9);
		jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {//进入银行客户添加界面
				new AddUser1(idcard,Manager.this).setVisible(true);
			}
		});
		
		jMenuItem10.setText("银行客户修改");
		jMenu3_3.add(jMenuItem10);
		jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {//进入银行客户修改界面
				new UpUser1(Manager.this).setVisible(true);
			}
		});
		

		jMenuItem11.setText("银行客户列表");
		jMenu3_3.add(jMenuItem11);
		jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {//进入银行客户列表界面
				new ListUser1(idcard,Manager.this).setVisible(true);
			}
		});
		jMenuBar1.add(jMenu3_3);

		jMenu4_2.setText("帮助");

		jMenuItem8.setText("修改密码");
		jMenu4_2.add(jMenuItem8);
		jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {//进入修改密码界面
				new ChangePwd(idcard,Manager.this).setVisible(true);
			}
		});

		jMenuItem7.setText("软件信息");
		jMenu4_2.add(jMenuItem7);
		jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showConfirmDialog(null, "该软件由飞翔团队共同研发！版本：1.0.0 V","软件信息",JOptionPane.DEFAULT_OPTION);
			}
		});

		
		jMenuBar1.add(jMenu4_2);

		setJMenuBar(jMenuBar1);

		this.setTitle("管理员界面");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-END:initComponent
	private void closeFrame() {
		int a = JOptionPane.showConfirmDialog(null, "确定退出系统吗？", "确定关闭",
				JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	private void closeFramet() {
		int a = JOptionPane.showConfirmDialog(null, "确定退出系统吗？", "确定关闭",
				JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			dispose();
			new Login().setVisible(true);
		}
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Manager("10000").setVisible(true);
			}
		});
	}
	

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JMenu jMenu1_1_1;
	private javax.swing.JMenu jMenu2_3;
	private javax.swing.JMenu jMenu3_3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu4_2;
	private javax.swing.JMenu jMenu5;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuBar jMenuBar2;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem10;
	private javax.swing.JMenuItem jMenuItem11;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JMenuItem jMenuItem8;
	private javax.swing.JMenuItem jMenuItem9;
	private javax.swing.JButton jbutton_close;
	// End of variables declaration//GEN-END:variables

}
