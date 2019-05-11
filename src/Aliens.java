import java.awt.Color;
import java.awt.Graphics;

public class Aliens extends GameObject {
	Aliens(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		y++;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
