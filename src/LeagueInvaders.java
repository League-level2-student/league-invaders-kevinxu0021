import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	final static int width = 500;
	final static int height = 900;
	GamePanel panel;

	LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	
	}

	public static void main(String[] args) {
		LeagueInvaders invaders = new LeagueInvaders();
		invaders.setup();
	}

	void setup() {
		frame.addKeyListener(panel);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		panel.startGame();
	}

}
