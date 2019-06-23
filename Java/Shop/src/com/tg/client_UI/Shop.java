package com.tg.client_UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.bean.Ushop;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;
import com.tg.dao.UshopDao;

public class Shop extends JDialog{
	private JTable table;
	private String[] colName = { "购物排队号","商品编号", "商品名","价格","数量"};
	private JScrollPane sp;
	private User my_user=null;
	private int id=-1;
	public Shop(User my_user,JFrame jf) {
		super(jf, true);
		this.my_user=my_user;
		setTitle("购物车");
		setSize(750, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		
		JButton button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id==-1) {
					return;
				}
				int a=JOptionPane.showConfirmDialog(null, "确认删除该商品吗？","信息警告",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					if(new UshopDao().delUshop(id)==1) {
						JOptionPane.showMessageDialog(null, "删除成功");
						getListData();
						return;
					}
				}
			}
		});
		button_2.setText("删除商品");
		button_2.setBounds(450, 330, 110, 30);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "确认结账？","结账信息",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					UshopDao Usdao=new UshopDao();
					List<Ushop> list=Usdao.getListUshop(my_user);
					double sum=0;
					boolean flag=false;
					GoodsDao Gdao=new GoodsDao();
					for(int i=0;i<list.size();i++) {
						Ushop one=list.get(i);
						Goods gs=Gdao.queryGoods_id(one.getGoodsid());
						if(gs.getStock()>=one.getNumber()) {
							sum+=one.getPrice();
							Gdao.updateGoods(gs.getId(), gs.getStock()-one.getNumber());
							Usdao.delUshop(one.getId());
						}else {
							flag=true;
						}
					}
					new ProgressBar(flag,sum,jf).setVisible(true);
					getListData();
				}
			}
		});
		button_3.setText("结        账");
		button_3.setBounds(600, 330, 110, 30);
		getContentPane().add(button_3);
		
		
		JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "确认清空购物车吗？","信息警告",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					if(new UshopDao().delUshop(my_user.getUsername())==1) {
						JOptionPane.showMessageDialog(null, "清空成功");
						getListData();
						return;
					}
				}
			}
		});
		button_4.setText("清空购物车");
		button_4.setBounds(300, 330, 110, 30);
		getContentPane().add(button_4);

		sp = new JScrollPane();
		sp.setBounds(5, 5,735, 310);
		getContentPane().add(sp);
		getListData();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	private void getListData() {
		List<Ushop> list=new UshopDao().getListUshop(my_user);
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			Ushop gs=list.get(i);
			data[i]=new String[] {String.valueOf(gs.getId()),gs.getGoodsid(),gs.getName(),String.valueOf(gs.getPrice()),String.valueOf(gs.getNumber())};
		}
		table=new JTable(data,colName);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				if(row<0)
					return ;
				id=Integer.parseInt((String) table.getValueAt(row, 0));
			}
		});
		sp.setViewportView(table);
	}
	public static void main(String[] args) {
		new Shop(new User("a","a"),new JFrame()).setVisible(true);
	}
}	
