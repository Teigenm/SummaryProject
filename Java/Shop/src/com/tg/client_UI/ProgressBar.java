package com.tg.client_UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.bean.Ushop;
import com.tg.dao.GoodsDao;
import com.tg.dao.UshopDao;

public class ProgressBar extends JDialog implements ActionListener, ChangeListener{
	private boolean flag=false;
	private double sum=0;
	private JProgressBar jp;
	private int value=0;
	private Timer timer;
	public ProgressBar(boolean flag,double sum,JFrame jf) {
		super(jf,true);
		this.flag=flag;
		this.sum=sum;
		this.setTitle("结账中.....");
		this.setSize(270,120 );
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		this.setLayout(null);
		
		jp=new JProgressBar();
		jp.setBounds(30,30,200,25);
		jp.setMaximum(100);
		jp.setMinimum(0);
		jp.setValue(0);
		jp.setStringPainted(true);
		getContentPane().add(jp);
		value=0;

		jp.addChangeListener(this);
		timer = new Timer(10, this);
		timer.start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	public void stateChanged(ChangeEvent e) {
		int t=jp.getValue();
		if(e.getSource()==jp) {
			if(t==100) {
				this.setVisible(false);
				if(flag==false) {
					JOptionPane.showMessageDialog(null, "结账成功,你需要付"+sum+"元");
				}else {
					JOptionPane.showMessageDialog(null, "部分账单因货存量不足无法结账,已结账"+sum+"元");
				}
				dispose();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == timer) {
			value++;
			jp.setValue(value);
		}
	}
	
}
