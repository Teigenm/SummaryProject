package com.fx.gui;

/*
 * DeMoney.java
 *
 * Created on __DATE__, __TIME__
 */



import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.fx.bean.UCash;
import com.fx.dao.CashDao;

/**
 *	取款界面
 * @author  __USER__
 */
public class AddMoney extends javax.swing.JDialog {
	private String idcard;
	/** Creates new form DeMoney */
	public AddMoney(String idcard,JFrame jf) {
		super(jf,true);
		this.idcard=idcard;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		jButton1.setText("100");
		getContentPane().add(
				jButton1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 90, 90,
						-1));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("100");
			}
		});

		jButton2.setText("200");
		getContentPane().add(
				jButton2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 90, 90,
						-1));
		getContentPane().add(
				jTextField1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 160,
						-1));
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("200");
			}
		});

		jLabel1.setText("请输入存款金额：");
		getContentPane().add(
				jLabel1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1,
						-1));
		

		jButton3.setText("300");
		getContentPane().add(
				jButton3,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 170, 90,
						-1));
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("300");
			}
		});

		jButton4.setText("500");
		getContentPane().add(
				jButton4,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 170, 90,
						-1));
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("500");
			}
		});

		jButton5.setText("1000");
		getContentPane().add(
				jButton5,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 250, 90,
						-1));
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("1000");
			}
		});

		jButton6.setText("2000");
		getContentPane().add(
				jButton6,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 250, 90,
						-1));
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1.setText("2000");
			}
		});
		jButton7.setText("确认");
		getContentPane().add(
				jButton7,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300,
						170, -1));
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(jTextField1.getText().equals(""))//没有输入数据时
				{	JOptionPane.showMessageDialog(null, "没有输入金额！");
					return;
				}
				String tmoney=jTextField1.getText().toString().trim();
				
				CashDao cdao=new CashDao();
				Double money =Double.valueOf(tmoney);
				UCash uc=cdao.queryUCashone(idcard);
				if(money<=0)//输入判断
				{	JOptionPane.showMessageDialog(null, "金额输入必须大于0！");
					return;
				}
				else
				{	uc.setEndCash(uc.getEndCash()+money);
					cdao.updateCash(uc);		//更新数据库
					JOptionPane.showMessageDialog(null, "存款成功！");
					dispose();	//销毁界面
				}
			}
		});
		
		this.setTitle("银行客户端");
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */


	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}