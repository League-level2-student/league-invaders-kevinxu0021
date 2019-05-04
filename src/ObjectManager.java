import java.awt.Graphics;

public class ObjectManager {
	Rocketship rocketship;

	ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;
	}

	void update() {
		rocketship.update();
	}

	void draw(Graphics g) {
		rocketship.draw(g);
	}
}
