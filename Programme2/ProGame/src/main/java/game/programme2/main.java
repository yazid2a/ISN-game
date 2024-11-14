package game.programme2;

import java.awt.*;
import javax.swing.JFrame;

public class main {
	
	public static void main(String[] args) {
		
		Game game=new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.Title);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		
	}
}