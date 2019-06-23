package com.tg.admin_UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.dao.DbDao;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;


/**
 * 增加客户界面
 * @author asus
 *
 */
public class AddGoods extends JDialog {
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField stock;
	private User my_user=null;
	//id,name,price,stock
	public AddGoods(User my_user,JFrame jf) {//界面
		super(jf,true);
		this.my_user=my_user;
		setTitle("添加货物");
		setSize(500, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		
		final JLabel label1 = new JLabel();
		label1.setText("商品编号：");
		label1.setBounds(50, 40, 80, 25);
		getContentPane().add(label1);

		id = new JTextField();
		id.setBounds(125, 40, 150, 25);
		getContentPane().add(id);

		final JLabel label2 = new JLabel();
		label2.setText("商  品  名：");
		label2.setBounds(50, 90, 80, 25);
		getContentPane().add(label2);
		
		name = new JTextField();
		name.setBounds(125, 90, 150, 25);
		getContentPane().add(name);
		
		final JLabel label3 = new JLabel();
		label3.setText("价         格：");
		label3.setBounds(50, 140, 80, 25);
		getContentPane().add(label3);
		
		price = new JTextField();
		price.setBounds(125, 140, 150, 25);
		getContentPane().add(price);
		
		final JLabel label4 = new JLabel();
		label4.setText("数         量：");
		label4.setBounds(50, 190, 80, 25);
		getContentPane().add(label4);
		
		stock= new JTextField();
		stock.setBounds(125, 190, 180, 25);
		getContentPane().add(stock);
		
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (check()) {
					insert();
					dispose();
				}
			}
		});
		button.setText("录  入");
		button.setBounds(80, 250, 120, 30);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});
		button_1.setText("取  消");
		button_1.setBounds(300, 250, 120, 30);
		getContentPane().add(button_1);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	private boolean check() {
		String tprice=price.getText().trim();
		String tname=name.getText().trim();
		String tid=id.getText().trim();
		String tstock=stock.getText().trim();
		if (tid.equals("")) {
			JOptionPane.showMessageDialog(null, "商品编号不能省略");
			return false;
		}
		if (tname.equals("")) {
			JOptionPane.showMessageDialog(null, "商品名不能省略");
			return false;
		}
		if (tprice.equals("")) {
			JOptionPane.showMessageDialog(null, "商品价格不能省略");
			return false;
		}
		if (tstock.equals("")) {
			JOptionPane.showMessageDialog(null, "商品数量不能省略");
			return false;
		}
		char[] c = tstock.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				JOptionPane.showMessageDialog(null, "数量有误");
				return false;
			}
		}
		char[] d = tprice.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])&&c[i]!='.') {
				JOptionPane.showMessageDialog(null, "价格有误");
				return false;
			}
		}
		return true;
	}
	private void insert() {//将数据录入数据库
		Goods gs=new Goods();
		gs.setId(id.getText().trim());
		gs.setName(name.getText().trim());
		gs.setPrice(Double.parseDouble(price.getText().trim()));
		gs.setStock(Integer.parseInt(stock.getText().trim()));
		GoodsDao Gdao=new GoodsDao();
		Goods two=Gdao.queryGoods_go(gs);
		if(two==null) {
			int a=Gdao.addGoods(gs);
			if(a==1)
				JOptionPane.showMessageDialog(null, "新商品"+gs.getName()+"入库成功！");
			return ;
		}else {
			JOptionPane.showMessageDialog(null, "商品重复");
			return ;
		}
	}
	private void clear() {//将文本框等状态置零
		id.setText("");
		name.setText("");
		price.setText("");
		stock.setText("");
		id.grabFocus();
	}
	public static void main(String[] args) {
		new AddGoods(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}
