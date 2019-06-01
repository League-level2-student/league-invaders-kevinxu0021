import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font subtitleFont;
	Rocketship rocketship = new Rocketship(250, 700, 50, 50);
	ObjectManager objectManager = new ObjectManager(rocketship);

	public static BufferedImage alienImg;

    public static BufferedImage rocketImg;

    public static BufferedImage bulletImg;

    public static BufferedImage spaceImg;
	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtitleFont = new Font("Arial", Font.PLAIN, 26);

		try {

            alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));

            rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));

            bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));

            spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));

    } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

    }
	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		objectManager.update();
		objectManager.manageEnemies();
		objectManager.checkCollision();
		objectManager.purgeObjects();
		if(rocketship.isAlive == false) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("League Invaders", 70, 200);
		g.setFont(subtitleFont);
		g.drawString("Press ENTER to Start", 125, 350);
		g.drawString("Press SPACE for instructions", 80, 500);

	}

	void drawGameState(Graphics g) {
		g.drawImage(spaceImg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		objectManager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("Game Over", 125, 200);
		g.setFont(subtitleFont);
		g.drawString("You killed " + objectManager.score + " enemies", 125, 350);
		g.drawString("Press ENTER to restart", 125, 500);
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("a");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			if(currentState == END_STATE) {
				rocketship = new Rocketship(250, 700, 50, 50);
				objectManager = new ObjectManager(rocketship);
			}
			currentState++;
			
			 if (currentState > END_STATE) {
				currentState = MENU_STATE;

			}
			
		}
		if (e.getKeyCode() == e.VK_UP) {
			rocketship.up = true;
		} else if (e.getKeyCode() == e.VK_DOWN) {
			rocketship.down = true;
		} else if (e.getKeyCode() == e.VK_LEFT) {
			rocketship.left = true;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			rocketship.right = true;
		}
		if (e.getKeyCode() == e.VK_SPACE) {
			objectManager.addProjectile(new Projectile(rocketship.x + 20, rocketship.y, 10, 10));
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == e.VK_UP) {
			rocketship.up = false;
		} else if (e.getKeyCode() == e.VK_DOWN) {
			rocketship.down = false;
		} else if (e.getKeyCode() == e.VK_LEFT) {
			rocketship.left = false;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			rocketship.right = false;
		}
	}
}
