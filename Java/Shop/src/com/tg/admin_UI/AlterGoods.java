package com.tg.admin_UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

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
import com.tg.dao.GoodsDao;
import com.tg.dao.UserDao;

public class AlterGoods extends JDialog{
	private JTable table;
	private JComboBox role;
	private JLabel id;
	private JTextField name;
	private JTextField price;
	private JTextField stock;
	private String[] colName = { "商品编号", "商品名","价格", "库存"};
	private JScrollPane sp;
	private User my_user=null;
	
	public AlterGoods(User my_user,JFrame jf) {
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
		
		id = new JLabel();
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
				if(check()) {
					update();
					JOptionPane.showMessageDialog(null, "修改成功");
				}
				return ;
			}
		});
		button_3.setText("修  改");
		button_3.setBounds(300, 70, 110, 25);
		getContentPane().add(button_3);
		
		final JButton button_4 = new JButton();
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tid=id.getText().trim();
				if(tid==null||tid.equals("")) {
					return;
				}
				int a=JOptionPane.showConfirmDialog(null, "确认删除该商品吗？","信息警告",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION) {
					if(new GoodsDao().delGoods(tid)==1) {
						JOptionPane.showMessageDialog(null, "删除成功");
						getListData();
						return;
					}
				}
			}
		});
		button_4.setText("删  除");
		button_4.setBounds(450, 70, 110, 25);
		getContentPane().add(button_4);

		final JButton button_5 = new JButton();
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_5.setText("取  消");
		button_5.setBounds(600, 70, 110, 25);
		getContentPane().add(button_5);
		
		sp = new JScrollPane();
		sp.setBounds(5, 110,730, 250);
		getContentPane().add(sp);
		getListData();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
	}
	private void update() {
		Goods gs=new Goods();
		gs.setId(id.getText().trim());
		gs.setName(name.getText().trim());
		gs.setPrice(Double.parseDouble(price.getText().trim()));
		gs.setStock(Integer.parseInt(stock.getText().trim()));
		//System.out.println(gs);
		if(new GoodsDao().updateGoods(gs)==1) {
			getListData();
		}
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
		for (int i = 0; i < d.length; i++) {
			if (!Character.isDigit(d[i])&&d[i]!='.') {
				JOptionPane.showMessageDialog(null, "价格有误");
				return false;
			}
		}
		return true;
	}
	private void getListData() {
		List<Goods> list=new GoodsDao().getListGoods();
		String[][] data=new String[list.size()][];
		for(int i=0;i<data.length;i++) {
			Goods gs=list.get(i);
			data[i]=new String[] {gs.getId(),gs.getName(),String.valueOf(gs.getPrice()),String.valueOf(gs.getStock())};
		}
		table=new JTable(data,colName);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				if(row<0) {
					return ;
				}
				String o=(String)table.getValueAt(row, 0);
				if(o==null||o.equals("")) {
					return ;
				}
				getGoods(o);
			}
		});
		sp.setViewportView(table);
	}
	public void getGoods(String tid) {
		Goods gs=new GoodsDao().queryGoods_id(tid);
		if(gs==null) {
			return ;
		}
		id.setText(gs.getId());
		name.setText(gs.getName());
		price.setText(String.valueOf(gs.getPrice()));
		stock.setText(String.valueOf(gs.getStock()));
	}
	public static void main(String[] args) {
		new AlterGoods(new User("admin","admin"),new JFrame()).setVisible(true);
	}
}	
