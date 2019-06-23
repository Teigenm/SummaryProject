package com.tg.client_UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

import com.tg.admin_UI.Login;
import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;

public class Client_UI extends JFrame{
	private JLabel jla;
	private JFrame jf;
	private User my_user=null;
	private JScrollPane sp;
	private JTextField name;
	private JTextField price1;
	private JTextField price2;
	private JButton search;
	private JButton addshop;
	private JButton shop;
	private JButton exit;
	private JTable table;
	private String selected="";
	private String[] colName = { "商品编号", "商品名","价格", "库存"};
	private double tprice1=-1,tprice2=-1;
	
	public Client_UI(User my_user){
		this.my_user=my_user;
		this.jf=this;
		this.setTitle("欢迎来到辉煌超市");
		this.setSize(1000,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		
		Container ct=this.getContentPane();
		
		JMenuBar menu=new JMenuBar();
		this.setJMenuBar(menu);
		JMenu manager=new JMenu("个人信息管理");
		menu.add(manager);
		
		JMenuItem improve=new JMenuItem("个人信息完善");
		improve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Improve(my_user,jf).setVisible(true);
			}
		});
		manager.add(improve);
		
		JMenuItem changepass=new JMenuItem("修改密码");
		changepass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ChangePwd(my_user,jf).setVisible(true);
			}
		});
		manager.add(changepass);
		
		final JLabel label1=new JLabel("商品名：");
		label1.setForeground(Color.WHITE);
		label1.setBounds(40,20,80,25);
		ct.add(label1);
		
		
		name=new JTextField();
		name.setBounds(100,20,100,25);
		ct.add(name);
		
		final JLabel label2=new JLabel("价格：");
		label2.setForeground(Color.WHITE);
		label2.setBounds(40,60,80,25);
		ct.add(label2);
		
		price1=new JTextField();
		price1.setBounds(100,60,70,25);
		ct.add(price1);
		
		final JLabel label3=new JLabel("——");
		label3.setFont(new Font("宋体",Font.BOLD,14));
		label3.setBounds(170,60,30,25);
		ct.add(label3);
		
		price2=new JTextField();
		price2.setBounds(200,60,70,25);
		ct.add(price2);
		
		final JLabel label4=new JLabel("辉煌超市欢迎您！");
		label4.setFont(new Font("楷体",Font.BOLD,28));
		label4.setForeground(Color.WHITE);
		label4.setBounds(700,0,300,100);
		ct.add(label4);
		
		search=new JButton("搜索");
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Goods gs=new Goods();
				gs.setName(name.getText().trim());
				if(check()) {
					getListGoods(gs,tprice1,tprice2);
				}
			}
		});
		search.setFont(new Font("宋体",Font.BOLD,14));
		search.setBounds(300,60,100,25);
		ct.add(search);
		
		addshop=new JButton("添加至购物车");
		addshop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selected.equals("")) {
					JOptionPane.showMessageDialog(null, "请选中商品");
					return ;
				}
				new AddShop(selected,my_user,jf).setVisible(true);
			}
		});
		addshop.setFont(new Font("宋体",Font.BOLD,14));
		addshop.setBounds(450,60,150,25);
		ct.add(addshop);
		
		shop=new JButton("购物车");
		shop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Shop(my_user,jf).setVisible(true);
				
			}
		});
		shop.setFont(new Font("宋体",Font.BOLD,25));
		shop.setBounds(640,486,120,50);
		ct.add(shop);
		
		exit=new JButton("退出系统");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "你真的要退出吗？","信息警告",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					dispose();
					new Login().setVisible(true);
				}
			}
		});
		exit.setFont(new Font("宋体",Font.BOLD,25));
		exit.setBounds(800,486,150,50);
		ct.add(exit);
		
		sp=new JScrollPane();
		sp.setBounds(0,100,995,380);
		ct.add(sp);
		
		init();
		
		final JLabel label10=new JLabel();
		label10.setIcon(new ImageIcon(getClass().getResource("/images/1.jpg")));;
		label10.setBounds(0,-230,1300,1000);
		ct.add(label10);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "你真的要退出吗？","信息警告",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					dispose();
					new Login().setVisible(true);
				}
			}
		});
	}
	public void init() {
		getListGoods(new Goods(),-1,-1);
	}
	private boolean check() {
		String tp1=price1.getText().trim();
		String tp2=price2.getText().trim();
		if(tp1.equals("")&&tp2.equals("")) {
			tprice1=-1;
			tprice2=-1;
			return true;
		}
		if(!tp1.equals("")&&tp2.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入另一个价格区间");
			return false;
		}
		if(tp1.equals("")&&!tp2.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入另一个价格区间");
			return false;
		}
		if(Double.parseDouble(tp1)>Double.parseDouble(tp2)) {
			JOptionPane.showMessageDialog(null, "无效的价格区间");
			return false;
		}
		char[] d = tp1.toCharArray();
		for (int i = 0; i < d.length; i++) {
			if (!Character.isDigit(d[i])&&d[i]!='.') {
				JOptionPane.showMessageDialog(null, "价格有误");
				return false;
			}
		}
		d = tp2.toCharArray();
		for (int i = 0; i < d.length; i++) {
			if (!Character.isDigit(d[i])&&d[i]!='.') {
				JOptionPane.showMessageDialog(null, "价格有误");
				return false;
			}
		}
		tprice1=Double.parseDouble(price1.getText().trim());
		tprice2=Double.parseDouble(price2.getText().trim());
		return true;
	}
	private void getListGoods(Goods goods,double price1,double price2) {
		List<Goods> list=new GoodsDao().getListGoods_price(goods,price1,price2);
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			Goods gs=list.get(i);
			data[i]=new String[] {gs.getId(),gs.getName(),String.valueOf(gs.getPrice()),String.valueOf(gs.getStock())};
		}
		table=new JTable(data,colName);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				if(row<0)
					return ;
				selected=(String)table.getValueAt(row, 0);
			}
		});
		sp.setViewportView(table);
	}
	public static void main(String[] args) {
		new Client_UI(new User("a","a")).setVisible(true);
	}
}
