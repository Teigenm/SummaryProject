package Game;

public class Game_Strat {
	public static void main(String[] args) {
		try {
			Game_Project game=new Game_Project();
			System.out.println("��Ϸ��ʼ!");
			game.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
