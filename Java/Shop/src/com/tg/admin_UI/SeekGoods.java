package com.tg.admin_UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.tg.bean.Goods;
import com.tg.bean.User;
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;

public class SeekGoods extends JDialog{
	private JTable table;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField stock;
	private String[] colName = { "商品编号", "商品名","价格", "库存"};
	private JScrollPane sp;
	private User my_user=null;
	
	public SeekGoods(User my_user,JFrame jf) {
		super(jf, true);
		this.my_user=my_user;
		setTitle("维护客户信息");
		setSize(750, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setIconImage(getToolkit().getImage(getClass().getResource("/images/128.jpg")));
		final JLabel label3 = new JLabel();
		label3.setText("商品编号：");
		label3.setBounds(40, 10, 80, 25);
		getContentPane().add(label3);
		
		id = new JTextField();
		id.setBounds(100, 10, 150, 25);
		getContentPane().add(id);
		
		final JLabel label4 = new JLabel();
		label4.setText("商品名：");
		label4.setBounds(40, 40, 80, 25);
		getContentPane().add(label4);
		
		name = new JTextField();
		name.setBounds(100, 40, 150, 25);
		getContentPane().add(name);
		
		final JLabel label_1_3 = new JLabel();
		label_1_3.setText("价格：");
		label_1_3.setBounds(280, 10, 80, 25);
		getContentPane().add(label_1_3);

		price = new JTextField();
		price.setBounds(330, 10, 180, 25);
		getContentPane().add(price);
		
		final JLabel label_1_4 = new JLabel();
		label_1_4.setText("库存：");
		label_1_4.setBounds(280, 40, 80, 25);
		getContentPane().add(label_1_4);

		stock = new JTextField();	
		stock.setBounds(330, 40, 180, 25); 
		getContentPane().add(stock);

		final JButton button_3 = new JButton();
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods gs=new Goods();
				if (id.getText().length() > 0) {
					gs.setId(id.getText());
				}
				if (!name.getText().equals("")) {
					gs.setName(name.getText());
				}
				if (price.getText().length() > 0) {
					gs.setPrice(Double.parseDouble(price.getText()));
				}
				if (stock.getText().length() > 0) {
					gs.setStock(Integer.parseInt(stock.getText()));
				}
				getListData(gs);
			}
		});
		button_3.setText("查  询");
		button_3.setBounds(400, 70, 110, 25);
		getContentPane().add(button_3);
		
		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				name.setText("");
				price.setText("");
				stock.setText("");
			}
		});
		button_4.setText("清  空");
		button_4.setBounds(550, 70, 110, 25);
		getContentPane().add(button_4);

		sp = new JScrollPane();
		sp.setBounds(5, 110,730, 250);
		getContentPane().add(sp);
		getListData(new Goods());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	private void getListData(Goods goods) {
		List<Goods> list=new GoodsDao().getListGoods(goods);
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			Goods gs=list.get(i);
			data[i]=new String[] {gs.getId(),gs.getName(),String.valueOf(gs.getPrice()),String.valueOf(gs.getStock())};
		}
		table=new JTable(data,colName);
		sp.setViewportView(table);
	}
	public static void main(String[] args) {
		new SeekGoods(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}	
