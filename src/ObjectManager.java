import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocketship;
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();
	ArrayList<Aliens> aliens = new ArrayList<Aliens>();
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	

	ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;
	}

	void update() {
		rocketship.update();
		for (int i = 0; i < projectile.size(); i++) {
			projectile.get(i).update();
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}

	}

	void draw(Graphics g) {
		rocketship.draw(g);
		for (int i = 0; i < projectile.size(); i++) {
			projectile.get(i).draw(g);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
	}

	void addProjectile(Projectile p) {
		projectile.add(p);
	}

	void addAlien(Aliens a) {
		aliens.add(a);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Aliens(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));

			enemyTimer = System.currentTimeMillis();
		}
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isAlive == false) {
				aliens.remove(i);
			}
		}
		for (int i = 0; i < projectile.size(); i++) {
			if (projectile.get(i).isAlive == false) {
				projectile.remove(i);
			}
		}
	}

	void checkCollision() {
		for (Aliens a : aliens) {
			if (rocketship.collisionBox.intersects(a.collisionBox)) {
				rocketship.isAlive = false;
			}
		}
		for (Aliens a : aliens) {
			for (Projectile p : projectile) {
				if (p.collisionBox.intersects(a.collisionBox)) {
					a.isAlive = false;
					score++;
				}
			}
		}
	}
	
	int getScore() {
		return score;
		
	}

}
