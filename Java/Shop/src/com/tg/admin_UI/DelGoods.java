package com.tg.admin_UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;

public class DelGoods extends JDialog{
	private User my_user=null; 
	private JTextField id;
	public DelGoods(User my_user,JFrame jf) {
		this.my_user=my_user;
		this.setTitle("删除商品");
		this.setSize(300,250 );
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		this.setLayout(null);
		
		final JLabel label1=new JLabel("请输入商品编号：");
		label1.setBounds(15,65,120,25);
		getContentPane().add(label1);
		
		id=new JTextField();
		id.setBounds(125,65,150,25);
		getContentPane().add(id);
		
		final JButton button1 = new JButton();
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsDao Gdao=new GoodsDao();
				String tid=id.getText().trim();
				Goods gs=Gdao.queryGoods_id(tid);
				if(gs==null) {
					JOptionPane.showMessageDialog(null, "输入商品编号无效！");
					return ;
				}
				else {
					int a = JOptionPane.showConfirmDialog(null, "确认删除商品:"+gs.getName()+"吗？" ,
							"信息确认", JOptionPane.YES_NO_OPTION);
					if (a == JOptionPane.YES_OPTION) {
						int s=Gdao.delGoods(gs.getId());
						if(s==1) {
							JOptionPane.showMessageDialog(null, "删除成功！");
							dispose();
						}
					}
				}
				return ;
			}
		});
		button1.setText("删  除");
		button1.setBounds(20, 150,100, 30);
		getContentPane().add(button1);
		
		final JButton button2 = new JButton();
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button2.setText("取  消");
		button2.setBounds(160, 150, 100, 30);
		getContentPane().add(button2);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	public static void main(String[] args) {
		new DelGoods(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}