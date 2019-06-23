/*
 * QueryMoney.java
 *
 * Created on __DATE__, __TIME__
 */

package com.fx.gui;

import javax.swing.JFrame;

import com.fx.bean.UCash;
import com.fx.dao.CashDao;

/**
 * 银行客户端查看余额界面
 * @author  __USER__
 */
public class QueryMoney extends javax.swing.JDialog {
	private String idcard;
	/** Creates new form QueryMoney */
	public QueryMoney(String idcard,JFrame jf) {//初始化界面
		super(jf,true);
		this.idcard=idcard;
		initComponents();
		UCash uc=new CashDao().queryUCashone(idcard); //查询数据库
		jLabel1.setText("可用余额为："+uc.getEndCash()+"元");
		jLabel2.setText("卡号为："+uc.getIdcard());
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(
				new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18));
		jLabel1.setText("可用余额为：");
		getContentPane().add(
				jLabel1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1,
						-1));
		setIconImage(getToolkit().getImage(getClass().getResource("/images/1.jpg")));
		jButton1.setText("确定");
		getContentPane().add(
				jButton1,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 100,
						30));

		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		});
		
		jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18));
		jLabel2.setText("卡号：");
		getContentPane().add(
				jLabel2,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1,
						-1));

	
		this.setTitle("查看余额");
		this.setSize(300,200);
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
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	// End of variables declaration//GEN-END:variables

}