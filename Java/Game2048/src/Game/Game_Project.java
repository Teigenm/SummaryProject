package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game_Project extends JFrame implements KeyListener,MouseMotionListener{
	private JLabel jifenlabel, Labelcount;
	private JButton btnwudi, btnagin, btnexit, btnup, btndown, btnright, btnleft;
	private JButton[][] btnary;// 二维数组
	private int sjs, sj, num, count = 0;
	private String str, str1, fenshu;
	private JMenuBar menuBar;
	private JMenuItem jmitemwudi;
	private JMenuItem jmrule;
	private JMenu jmabout;
	Random ran = new Random();
	private JPanel jpcenter;
	private boolean fx;
	public Game_Project() {
		getContentPane().setForeground(SystemColor.inactiveCaption);
		
		this.setTitle("2048");
		this.setSize(400, 600);
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		ImageIcon ic=new ImageIcon("img//128.jpg");
		Image img=ic.getImage();
		this.setIconImage(img);
		
		Container con=this.getContentPane();
		this.setLayout(null);
		this.setBackground(SystemColor.activeCaption);
	
		JPanel jpnorth=new JPanel();
		jpnorth.setLayout(null);
		
		jpcenter=new JPanel();
		GridLayout gl_jpcenter=new GridLayout(4,4);
		jpcenter.setLayout(gl_jpcenter);
		btnary=new JButton[4][4];
		for (int i = 0; i < 4; i++) { // 遍历数组
			for (int j = 0; j < 4; j++) {
				btnary[i][j]=new JButton();
				btnary[i][j].setText("");
				jpcenter.add(btnary[i][j]);
				btnary[i][j].setFont(new Font("楷体",Font.BOLD,20));
				btnary[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
			}
		}
		//
		
		jpnorth.setLocation(10, 10);// 设置jpnroth面板的坐标
		jpcenter.setLocation(40, 80);// 设置jpcenter面板的坐标
		jpcenter.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));// 设置jpcenter的边框
		
		jpnorth.setSize(380, 50);// 设置jpnroth面板的大小
		jpcenter.setSize(320, 320);// 设置jpcenter面板的大小
		
		jpnorth.setBackground(SystemColor.activeCaptionBorder);// 设置jpnroth面板的颜色
		jpcenter.setBackground(SystemColor.textText);// 设置jpnroth面板的颜色
		
		jifenlabel=new JLabel("积分");
		jifenlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		jifenlabel.setFont(new Font("楷体",Font.BOLD,17));
		jifenlabel.setBounds(10,0,59,50);
		jpnorth.add(jifenlabel);
		
		Labelcount =new JLabel(String.valueOf(count));
		Labelcount.setForeground(Color.RED);
		Labelcount.setFont(new Font("楷体",Font.PLAIN,27));
		Labelcount.setBounds(79, -1, 114, 50);
		jpnorth.add(Labelcount);
		
		btnwudi=new JButton("测试失败");
		btnwudi.setFont(new Font("楷体",Font.BOLD,10));
		btnwudi.setBounds(291, 13, 80, 30);
		jpnorth.add(btnwudi);
		//btnwudi.setVisible(false);
		
		btnup=new JButton("↑");
		btnup.setFont(new Font("楷体",Font.BOLD,15));
		btnup.setBounds(249, 433, 60, 40);
		con.add(btnup);
		btndown=new JButton("↓");
		btndown.setFont(new Font("楷体",Font.BOLD,15));
		btndown.setBounds(249, 483, 60, 40);
		con.add(btndown);
		btnleft = new JButton("←");
		btnleft.setFont(new Font("楷体", Font.BOLD, 15));
		btnleft.setBounds(178, 483, 60, 40);
		con.add(btnleft);
		btnright = new JButton("→");
		btnright.setFont(new Font("楷体", Font.BOLD, 15));
		btnright.setBounds(325, 483, 60, 40);
		con.add(btnright);
		
		btnagin = new JButton("重新开始");// 实例化一个btnagin重来按钮
		btnagin.setFont(new Font("楷体", Font.BOLD, 12));
		btnexit = new JButton("退出游戏");// 实例化一个btnexit退出按钮
		btnexit.setFont(new Font("楷体", Font.BOLD, 12));
		btnagin.setBounds(54, 433, 93, 40);// 设置btnagin重来按钮的坐标和大小
		btnexit.setBounds(54, 483, 93, 40);// 设置btnexit退出按钮的坐标和大小

		con.add(btnagin);// 把btnagin按钮添加con面板
		con.add(btnexit);// 把btnagin按钮添加con面板
		con.add(jpnorth);// 把jpnorth面板添加con面板
		con.add(jpcenter);// 把jpcenter面板添加con面板
		
		menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu jmhelp=new JMenu("帮助");
		jmhelp.setFont(new Font("楷体",Font.PLAIN,14));
		menuBar.add(jmhelp);
		
		jmitemwudi=new JMenuItem("作弊");
		jmitemwudi.setFont(new Font("楷体",Font.PLAIN,12));
		jmhelp.add(jmitemwudi);
		
		jmabout=new JMenu("关于");
		jmabout.setFont(new Font("楷体",Font.PLAIN,14));
		menuBar.add(jmabout);
		
		jmrule=new JMenuItem("规则");
		jmrule.setFont(new Font("楷体",Font.PLAIN,12));
		jmabout.add(jmrule);
		
		ranRule();
		changeColor();
		wudiAListener wda=new wudiAListener();
		jmitemwudi.addActionListener(wda);
		
		aboutAListener aAr=new aboutAListener();
		jmrule.addActionListener(aAr);
		
		fxbtnAListener fbl = new fxbtnAListener();
		btnup.addActionListener(fbl);
		btndown.addActionListener(fbl);
		btnleft.addActionListener(fbl);
		btnright.addActionListener(fbl);
		btnexit.addActionListener(fbl);
		btnagin.addActionListener(fbl);
		btnwudi.addActionListener(fbl);
		
		myWindowListener mwl=new myWindowListener();
		this.addWindowListener(mwl);
		
		addKeyListener(this);
		addMouseMotionListener(this);
	}
	private void changeColor() {
		for(int a=0;a<4;a++) {
			for(int b=0;b<4;b++) {
				str=btnary[a][b].getText();
				if (str.equals("2")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("4")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("8")) {
					btnary[a][b].setBackground(Color.green);
				}
				if (str.equals("16")) {
					btnary[a][b].setBackground(Color.PINK);
				}
				if (str.equals("32")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("64")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("128")) {
					btnary[a][b].setBackground(Color.green);
				}
				if (str.equals("256")) {
					btnary[a][b].setBackground(Color.PINK);
				}
				if (str.equals("512")) {
					btnary[a][b].setBackground(Color.white);
				}
				if (str.equals("1024")) {
					btnary[a][b].setBackground(Color.blue);
				}
				if (str.equals("2048")) {
					btnary[a][b].setBackground(Color.green);
				} else if (str.equals("")) {
					btnary[a][b].setBackground(Color.white);
				}
			}
		}
	}
	
	private boolean isShu() {
		int cnt=0;
		String dangqianwei=null,qianyiwei=null;
		for(int i=0;i<4;i++) {
			for(int j=1;j<4;j++) {
				dangqianwei=btnary[i][j].getText();
				qianyiwei=btnary[i][j-1].getText();
				if(dangqianwei.equals("")||qianyiwei.equals(""))
					cnt++;
				if(dangqianwei.equals(qianyiwei))
					cnt++;
			}
		}
		for(int i=1;i<4;i++) {
			for(int j=0;j<4;j++) {
				dangqianwei=btnary[i][j].getText();
				qianyiwei=btnary[i-1][j].getText();
				if(dangqianwei.equals("")||qianyiwei.equals(""))
					cnt++;
				if(dangqianwei.equals(qianyiwei))
					cnt++;
			}
		}
		if(cnt==0)
			return true;
		return false;
	}
	
	private boolean isWin() {
		String dangqian=null;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				dangqian=btnary[i][j].getText();
				if(dangqian.equals("2048"))
					return true;
			}
		}
		return false;
	}
	
	private void ranRule() {
		sj=ran.nextInt(4);
		sjs=ran.nextInt(4);
		if(btnary[sj][sjs].getText().equals("")&&sj==0&&sjs==3) {
			btnary[sj][sjs].setText("4");
		}else if(btnary[sj][sjs].getText().equals("")) {
			btnary[sj][sjs].setText("2");
		}else {
			ranRule();
		}
	}
	
		private void leftRule() {
		for(int i=0;i<4;i++) {
			int c=5;
			for(int k=0;k<3;k++) {
				for(int j=1;j<4;j++) {
					String a=btnary[i][j-1].getText();
					String b=btnary[i][j].getText();
					if(a.equals("")) {
						btnary[i][j-1].setText(b);
						btnary[i][j].setText("");
					}else if(a.equals(b)&&c!=j&&c!=j-1) {
						num=Integer.parseInt(a);
						count+=num;
						String fenshu=String.valueOf(count+count);
						Labelcount.setText(fenshu);
						btnary[i][j-1].setText(String.valueOf(num*2));
						btnary[i][j].setText("");
						c=j;
					}
				}
			}
		}
		int cnt=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(btnary[i][j].getText().equals(""))
					cnt++;
			}
		}
		if(cnt>0)
			ranRule();
		changeColor();
	}
	
	private void upRule() {
		for(int i=0;i<4;i++) {
			int c=5;
			for(int k=0;k<3;k++) {
				for(int j=1;j<4;j++) {
					String a=btnary[j-1][i].getText();
					String b=btnary[j][i].getText();
					if(a.equals("")) {
						btnary[j-1][i].setText(b);
						btnary[j][i].setText("");
					}else if(a.equals(b)&&c!=j&&c!=j-1) {
						num=Integer.parseInt(a);
						count+=num;
						String fenshu=String.valueOf(count+count);
						Labelcount.setText(fenshu);
						btnary[j-1][i].setText(String.valueOf(num*2));
						btnary[j][i].setText("");
						c=j;
					}
				}
			}
		}
		int cnt=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(btnary[i][j].getText().equals(""))
					cnt++;
			}
		}
		if(cnt>0)
			ranRule();
		changeColor();
	}
	private void rightRule() {
		for(int i=0;i<4;i++) {
			int c=5;
			for(int k=0;k<3;k++) {
				for(int j=2;j>=0;j--) {
					String a=btnary[i][j+1].getText();
					String b=btnary[i][j].getText();
					if(a.equals("")) {
						btnary[i][j+1].setText(b);
						btnary[i][j].setText("");
					}else if(a.equals(b)&&c!=j&&c!=j+1) {
						num=Integer.parseInt(a);
						count+=num;
						String fenshu=String.valueOf(count+count);
						Labelcount.setText(fenshu);
						btnary[i][j+1].setText(String.valueOf(num*2));
						btnary[i][j].setText("");
						c=j;
					}
				}
			}
		}
		int cnt=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(btnary[i][j].getText().equals(""))
					cnt++;
			}
		}
		if(cnt>0)
			ranRule();
		changeColor();
	}
	private void downRule() {
		for(int i=0;i<4;i++) {
			int c=5;
			for(int k=0;k<3;k++) {
				for(int j=2;j>=0;j--) {
					String a=btnary[j+1][i].getText();
					String b=btnary[j][i].getText();
					if(a.equals("")) {
						btnary[j+1][i].setText(b);
						btnary[j][i].setText("");
					}else if(a.equals(b)&&c!=j&&c!=j+1) {
						num=Integer.parseInt(a);
						count+=num;
						String fenshu=String.valueOf(count+count);
						Labelcount.setText(fenshu);
						btnary[j+1][i].setText(String.valueOf(num*2));
						btnary[j][i].setText("");
						c=j;
					}
				}
			}
		}
		int cnt=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(btnary[i][j].getText().equals(""))
					cnt++;
			}
		}
		if(cnt>0)
			ranRule();
		changeColor();
	}
	//监听类
	private class fxbtnAListener implements ActionListener{
		@Override 
		public void actionPerformed(ActionEvent ae) {
			JButton btn=(JButton)ae.getSource();
			if (btn == btnup) {
				upRule();
				// IsWin();
			}
			if (btn == btndown) {
				downRule();
				// IsWin();
			}
			if (btn == btnleft) {
				leftRule();
				// IsWin();

			}
			if (btn == btnright) {
				rightRule();
				// IsWin();
			}
			
			if(btn==btnagin) {
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						btnary[i][j].setText("");
					}
				}
				Labelcount.setText("0");
				count=0;
				ranRule();
				changeColor();
			}
			
			
			if(btn==btnexit) {
				if(JOptionPane.showConfirmDialog(null, "您真的打算退出吗？", "退出程序",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			if (btn == btnwudi) {// 作弊按钮
				btnary[0][0].setText("2");
				btnary[0][1].setText("4");
				btnary[0][2].setText("2");
				btnary[0][3].setText("4");
				btnary[1][0].setText("8");
				btnary[1][1].setText("2");
				btnary[1][2].setText("8");
				btnary[1][3].setText("2");
				btnary[2][0].setText("16");
				btnary[2][1].setText("4");
				btnary[2][2].setText("16");
				btnary[2][3].setText("4");
				btnary[3][0].setText("32");
				btnary[3][1].setText("2");
				btnary[3][2].setText("32");
				btnary[3][3].setText("2");
				changeColor();

			}
			
			if(isWin()) {
				JOptionPane.showMessageDialog(null, "你赢了!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						btnary[i][j].setText("");
					}
				}
				count=0;
				Labelcount.setText("0");
				ranRule();
				changeColor();
			}
			
			if(isShu()) {
				JOptionPane.showMessageDialog(null, "你输了!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						btnary[i][j].setText("");
					}
				}
				count=0;
				Labelcount.setText("0");
				ranRule();
				changeColor();
			}
		}
		
	}
	
	private class myWindowListener extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			btnexit.doClick();
		}
	}
	private class wudiAListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ael){	
			btnary[3][3].setText("1024");
			btnary[3][2].setText("1024");
			changeColor();
		}
	}
	private class aboutAListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			JOptionPane.showMessageDialog(null, "没有规则!标题就是规则!", "规则", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		char ch=ke.getKeyChar();
		if (ch == 'a') {
			leftRule();
		}
		if (ke.getKeyChar() =='d') {
			rightRule();
		}
		if (ke.getKeyChar() == 'w') {
			upRule();
			
		}
		if (ke.getKeyChar() == 's') {
			downRule();
			
		}
		if (isWin()) {
			JOptionPane.showMessageDialog(null, "恭喜你，胜利了！", "YOU WIN", JOptionPane.INFORMATION_MESSAGE);
			count = 0;
			for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
				for (int j = 0; j < 4; j++) {
					btnary[i][j].setText("");
				}
			}
			ranRule();// 再执行随机数的方法
			Labelcount.setText("0");
			changeColor();
		}
		if (isShu()) {
			JOptionPane.showMessageDialog(null, "很遗憾,游戏结束！", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
			count = 0;
			for (int i = 0; i < 4; i++) {// 利用嵌套循环清空所有的text
				for (int j = 0; j < 4; j++) {
					btnary[i][j].setText("");
				}
			}
			ranRule();// 再执行随机数的方法
			Labelcount.setText("0");
			changeColor();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		boolean status =this.requestFocusInWindow();
	}
}
