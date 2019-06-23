package com.tg.client_UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.bean.Ushop;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;
import com.tg.dao.UshopDao;

public class AddShop extends JDialog{
	private User my_user=null; 
	private String goodsid="";
	private JTextField number;
	public AddShop(String goodsid,User my_user,JFrame jf) {
		this.my_user=my_user;
		this.goodsid=goodsid;
		this.setTitle("购买中.....");
		this.setSize(300,250 );
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		this.setLayout(null);
		
		Goods gs=new GoodsDao().queryGoods_id(goodsid);
		final JLabel label1=new JLabel("请输入购买商品数量：");
		label1.setBounds(15,75,140,25);
		getContentPane().add(label1);
		
		final JLabel label2=new JLabel("商品名： "+gs.getName());
		label2.setBounds(85,35,140,25);
		getContentPane().add(label2);
		
		number=new JTextField();
		number.setBounds(145,75,120,25);
		getContentPane().add(number);
		
		final JButton button1 = new JButton();
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ushop us=new Ushop();
				us.setUsername(my_user.getUsername());
				us.setGoodsid(goodsid);
				us.setName(gs.getName());
				us.setPrice(gs.getPrice());
				us.setNumber(Integer.parseInt(number.getText().trim()));
				int a=new UshopDao().addUshop(us);
				if(a==1) {
					JOptionPane.showMessageDialog(null, "商品:"+us.getName()+"成功添加至购物车");
					dispose();
				}
				return ;
			}
		});
		button1.setText("确   认");
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
		new AddShop("1000",new User("a","a"),new JFrame()).setVisible(true);
	}
}