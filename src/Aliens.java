import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Aliens extends GameObject {
	Aliens(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		super.update();
		Random random = new Random();
		x+=random.nextInt(21)-10;
		y++;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
